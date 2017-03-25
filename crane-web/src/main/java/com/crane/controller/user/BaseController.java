package com.crane.controller.user;

import javax.servlet.http.HttpSession;

import com.crane.po.model.SessionUser;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月7日 下午5:41:35
* 
*/
public class BaseController {
	public void setUserBaseInfo(Class<?> classz, Object obj, HttpSession session){
		
	}
	public Integer getUserId(HttpSession session){
		return null;
	}
	public SessionUser getSessionUser(HttpSession session){
		return null;
	}
}
