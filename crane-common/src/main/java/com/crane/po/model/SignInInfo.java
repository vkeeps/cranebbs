/**
 * Project Name:ulewo-common
 * File Name:SignInInfo.java
 * Package Name:com.ulewo.po.model
 * Date:2015年9月23日下午8:47:41
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 */

package com.crane.po.model;

/**
 * ClassName:SignInInfo <br/>
 * Date:     2015年9月23日 下午8:47:41 <br/>
 * @author 多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public class SignInInfo {
    /**
     * 用户签到数量
     */
    private Integer userSignInCount;

    /**
     * 是否已经签到
     */
    private boolean haveSignInToday;

    /**
     * 今日签到数量
     */
    private Integer todaySigInCount;

    /**
     * 这里防止客户端日期错误，所以从服务器端返回日期相关信息
     */
    /**
     * 当前星期
     */
    private String week;

    /**
     * 当前日期
     */
    private String curDay;

    public Integer getUserSignInCount() {
        return userSignInCount;
    }

    public void setUserSignInCount(Integer userSignInCount) {
        this.userSignInCount = userSignInCount;
    }

    public boolean isHaveSignInToday() {
        return haveSignInToday;
    }

    public void setHaveSignInToday(boolean haveSignInToday) {
        this.haveSignInToday = haveSignInToday;
    }

    public Integer getTodaySigInCount() {
        return todaySigInCount;
    }

    public void setTodaySigInCount(Integer todaySigInCount) {
        this.todaySigInCount = todaySigInCount;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCurDay() {
        return curDay;
    }

    public void setCurDay(String curDay) {
        this.curDay = curDay;
    }
}
