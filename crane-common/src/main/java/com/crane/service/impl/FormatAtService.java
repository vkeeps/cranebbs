package com.crane.service.impl;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crane.po.model.User;
import com.crane.service.UserService;
import com.crane.utils.Constants;
import com.crane.utils.ServerUtils;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月3日 下午3:24:38
* 
*/
@Component
public class FormatAtService {

	@Autowired
	private UserService userService;
	
	private static Pattern referer_pattern = Pattern.compile("@([^@\\s\\:\\;\\,\\\\.\\<\\?\\？\\{\\}\\&]{1,})");// @.+?[\\s:]
	
	private static String userUrl =  "/user/";
	
	public String generateRefererLinks(String msg, Set<Integer> userIds, Integer sendUserId){
		StringBuilder html = new StringBuilder();
		Matcher matcher = referer_pattern.matcher(msg);
		int lastIdx = 0;
		while(matcher.find()){
			String origin_str = matcher.group();
			String userName = origin_str.substring(1, origin_str.length()).trim();
			html.append(msg.substring(lastIdx,matcher.start()));
			User user = userService.findUserByUserName(userName);
			if(null!=user){
				html.append("<a href=\""+ServerUtils.getDomain()+userUrl+user.getUserId()+ "\" class=\"referer\" target=\"_blank\">@");
				html.append(userName.trim()+"</a>");
				userIds.add(user.getUserId());
			}else if(Constants.AT_ALL.equalsIgnoreCase(userName) && sendUserId.intValue() == Constants.SEND_USERID){
				html.append("<a href=\"javascript:;\" class=\"referer\" target=\"_blank\">@");
				html.append(userName.trim()+"</a>");
				
				//at所有人时，查询全部用户
				List<User> userList = userService.findAllActiveUser();
				for(User u : userList){
					userIds.add(u.getUserId());
				}
			}else{
				html.append(origin_str);
			}
			lastIdx = matcher.end();
		}
		html.append(msg.substring(lastIdx));
        String resultHtml = html.toString();
        resultHtml = formateContentImgTag(resultHtml);
		return resultHtml;
	}
	//表情格式化
	public String formateContentImgTag(String content) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode allNode = htmlCleaner.clean(content);
        TagNode[] images = allNode.getElementsByName("img", true);
        if (images.length > 0) {
            for (TagNode tag : images) {
                tag.removeAttribute("alt");
                String src = tag.getAttributeByName("src");
                if (null != src && !src.contains("emotion")) {
                    tag.addAttribute("data-original", src);
                    tag.removeAttribute("src");
                    tag.addAttribute("class", "lazy-load");
                }
            }
        }
        content = htmlCleaner.getInnerHtml(allNode).replace("<head />", "").replace("<body>", "")
        		.replace("</body>", "");
        return content;
    }
}
