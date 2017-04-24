package com.crane.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.crane.po.model.SessionUser;
import com.crane.utils.Constants;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月7日 下午5:41:35
* 
*/
public class BaseController {
	/**
	 * 用反射原理设置和获得用户信息
	 * @param classz
	 * @param obj
	 * @param session
	 */
	public void setUserBaseInfo(Class<?> classz, Object obj, HttpSession session){
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		Integer userId = sessionUser.getUserId();
        String userIcon = sessionUser.getUserIcon();
        String userName = sessionUser.getUserName();
        try {
            Method userIdMethod = classz.getDeclaredMethod("setUserId", Integer.class);
            userIdMethod.invoke(obj, userId);
            Method userIconMethod = classz.getDeclaredMethod("setUserIcon", String.class);
            userIconMethod.invoke(obj, userIcon);
            Method userNameMethod = classz.getDeclaredMethod("setUserName", String.class);
            userNameMethod.invoke(obj, userName);
        } catch (Exception e) {
            e.printStackTrace();

        }
	}
	public Integer getUserId(HttpSession session){
		Object sessionObj = session.getAttribute(Constants.SESSION_USER_KEY);
        if (null != sessionObj) {
            SessionUser sessionUser = (SessionUser) sessionObj;
            return sessionUser.getUserId();
        }
		return null;
	}
	public SessionUser getSessionUser(HttpSession session){
		Object sessionObj = session.getAttribute(Constants.SESSION_USER_KEY);
        if (null != sessionObj) {
            SessionUser sessionUser = (SessionUser) sessionObj;
            return sessionUser;
        }
        return null;
	}
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(null==ip||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
			ip=request.getHeader("Proxy-Client-IP");
		}
		if(null==ip||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
			ip=request.getHeader("WL-Proxy-Client-IP");
		}
		if(null==ip||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
			ip=request.getRemoteAddr();
		}
		return ip;
	}
}
