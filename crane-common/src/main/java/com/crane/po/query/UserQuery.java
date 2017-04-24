package com.crane.po.query;

import com.crane.po.enums.OrderByEnum;

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
    private String userNameFuzzy;
    private String startDate;
    private String endDate;
    private String lastLoginStartDate;
    private String lastLoginEndDate;
    private OrderByEnum orderBy;

	public OrderByEnum getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderByEnum orderBy) {
		this.orderBy = orderBy;
	}

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

	public String getUserNameFuzzy() {
		return userNameFuzzy;
	}

	public void setUserNameFuzzy(String userNameFuzzy) {
		this.userNameFuzzy = userNameFuzzy;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLastLoginStartDate() {
		return lastLoginStartDate;
	}

	public void setLastLoginStartDate(String lastLoginStartDate) {
		this.lastLoginStartDate = lastLoginStartDate;
	}

	public String getLastLoginEndDate() {
		return lastLoginEndDate;
	}

	public void setLastLoginEndDate(String lastLoginEndDate) {
		this.lastLoginEndDate = lastLoginEndDate;
	}
}
