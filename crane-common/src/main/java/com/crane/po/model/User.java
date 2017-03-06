package com.crane.po.model;

import java.util.Date;

import com.crane.utils.DateUtil;

public class User {
	private Integer userId;

	private String email;

	private String userName;

	private String password;

	private String userIcon;

	private String userBg;

	private String birthday;

	private String sex;

	private String characters;

	private Integer mark;

	private String address;

	private String work;

	private Date registerTime;

	private String showRegisterTime;

	private Date lastLoginTime;

	private String showlastLoginTime;

	private String activationCode;

	private Integer fansCount;

	private Integer focusCount;

	private boolean haveFocus;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon == null ? null : userIcon.trim();
	}

	public String getUserBg() {
		return userBg;
	}

	public void setUserBg(String userBg) {
		this.userBg = userBg == null ? null : userBg.trim();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters == null ? null : characters.trim();
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work == null ? null : work.trim();
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode == null ? null : activationCode.trim();
	}

	public String getShowRegisterTime() {
		if (null != registerTime) {
			this.showRegisterTime = DateUtil.friendly_time(registerTime);
		}

		return showRegisterTime;
	}

	public void setShowRegisterTime(String showRegisterTime) {
		this.showRegisterTime = showRegisterTime;
	}

	public String getShowlastLoginTime() {
		if (null != lastLoginTime) {
			this.showlastLoginTime = DateUtil.friendly_time(lastLoginTime);
		}
		return showlastLoginTime;
	}

	public void setShowlastLoginTime(String showlastLoginTime) {
		this.showlastLoginTime = showlastLoginTime;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}

	public boolean isHaveFocus() {
		return haveFocus;
	}

	public void setHaveFocus(boolean haveFocus) {
		this.haveFocus = haveFocus;
	}

}