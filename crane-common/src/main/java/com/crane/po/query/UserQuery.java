package com.crane.po.query;
/**
* @author  Crane:
* @version 5.0
* @time 2017年3月11日 下午5:51:02
* 
*/
public class UserQuery extends BaseQuery {

	private String userName;
	private String email;
	private String userId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
