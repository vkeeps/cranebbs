/**
 * Project Name:ulewo-common
 * File Name:StringTools.java
 * Package Name:com.ulewo.utils
 * Date:2015年9月19日下午5:19:30
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 *
*/

package com.crane.utils;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:StringTools <br/>
 * Date:     2015年9月19日 下午5:19:30 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public class StringTools {

	private final static String[] static_ext = { "jpg", "png", "gif", "bmp", "JPG", "PNG", "GIF", "BMP" };


	/**
	 * 判断为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if (null == str || "".equals(str) || "null".equals(str)) {
			return true;
		} else if ("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String str) {

		String checkPassWord = "^[0-9]+$";
		if (null == str) {
			return false;
		}
		if (!str.matches(checkPassWord)) {
			return false;
		}

		return true;
	}

	public static boolean checkEmail(String email) {
		String checkEmail = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
		if (!isEmpty(email)) {
			return email.matches(checkEmail);
		} else {
			return false;
		}
	}

	public static boolean checkUserName(String userName) {
		String checkUserName = "^[\\w\\u4e00-\\u9fa5]+$";
		if (!isEmpty(userName)) {
			return userName.matches(checkUserName);
		} else {
			return false;
		}
	}

	public static boolean checkPassWord(String password) {
		String checkPassWord = "^[0-9a-zA-Z]+$";
		if (!isEmpty(password)) {
			return password.matches(checkPassWord);
		} else {
			return false;
		}
	}

	public static String addLink(String str) {
		String regex = "((http:|https:)//|www.)[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			StringBuffer replace = new StringBuffer();
			if (matcher.group().contains("http")) {
				replace.append("<a href=").append(matcher.group().replace("nbsp;", ""));
			} else {
				replace.append("<a href=http://").append(matcher.group().replace("nbsp;", ""));
			}
			replace.append(" target=\"_blank\">" + matcher.group() + "</a>");
			matcher.appendReplacement(result, replace.toString());
		}
		matcher.appendTail(result);
		return result.toString();
	}

	public static String escapeHtml(String html) {

		if (!isEmpty(html)) {
			html = html.replaceAll("\n", "<br>");
			html = html.replaceAll(" ", "&nbsp;");
			return html;
		}
		return html;
	}

	public static String reFormateHtml(String html) {
		if (!isEmpty(html)) {
			html = html.replaceAll("&nbsp;", " ");
			html = html.replaceAll("&lt;", "<");
			html = html.replaceAll("<br>", "\n");
			return html;
		}
		return html;
	}

	/**
	 * isNew:(是否是两天以内). <br/>
	 *
	 * @author 多多洛
	 * @param dateStr
	 * @return
	 * @since JDK 1.7
	 */
	public static boolean isNew(Date date) {
		if (null == date) {
			return false;
		}
		Calendar c = Calendar.getInstance();
		long max = c.getTimeInMillis() - date.getTime();
		if (max / 86400000 <= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * clearHtml:(清除html标签). <br/>
	 *
	 * @author 多多洛
	 * @param str
	 * @return
	 * @since JDK 1.7
	 */
	public static String clearHtmlTag(String str) {

		if (!isEmpty(str)) {
			return str.replaceAll("<[.[^<]]*>", "").replaceAll("[\\n|\\r]", "").replaceAll("&nbsp;", "");
		} else {
			return str;
		}
	}

	public static void main(String[] args) {
		String str = "asdfasdfasd www.ulewo.com/bbs/3333 dfgadsf";
		System.out.println(addLink(str));
	}
}
