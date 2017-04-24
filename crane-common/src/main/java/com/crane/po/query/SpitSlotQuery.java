package com.crane.po.query;

import java.util.Date;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月29日 下午6:07:36
* 
*/
public class SpitSlotQuery extends BaseQuery {
	private Integer id;
	private Integer userId;
	private String userName;
	private Date startTime;
	private Date endTime;
	private Integer spitSlotId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getSpitSlotId() {
		return spitSlotId;
	}
	public void setSpitSlotId(Integer spitSlotId) {
		this.spitSlotId = spitSlotId;
	}
	
	
}
