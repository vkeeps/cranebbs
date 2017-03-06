package com.crane.po.model;
/**
* @author  Crane:
* @version 5.0
* @time 2016年11月21日 上午12:36:01
* 
*/
public class SessionUser {

	private Integer userId;
	private String userName;
	private String userIcon;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
}
