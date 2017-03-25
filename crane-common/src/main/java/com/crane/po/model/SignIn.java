package com.crane.po.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.crane.po.serializ.CustomDateSerializer;
import com.crane.po.vo.api.SignInVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SignIn {
    private Integer userId;
    private String userName;
    private String userIcon;
    private Date signDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date signTime;
    private boolean isContinueSigIn;
    private String sourceFrom;

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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public boolean isContinueSigIn() {
        return isContinueSigIn;
    }

    public void setContinueSigIn(boolean isContinueSigIn) {
        this.isContinueSigIn = isContinueSigIn;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }


    public static SignInVO convert2VO(SignIn signIn) {
        SignInVO vo = new SignInVO();
        vo.setUserId(signIn.getUserId());
        vo.setUserName(signIn.getUserName());
        vo.setUserIcon(signIn.getUserIcon());
        vo.setSignTime(signIn.getSignTime());
        vo.setSourceFrom(signIn.getSourceFrom());
        return vo;
    }

    public static List<SignInVO> convert2VOList(List<SignIn> list) {
        List<SignInVO> VOList = new ArrayList<>();
        for (SignIn signIn : list) {
            VOList.add(convert2VO(signIn));
        }
        return VOList;
    }
}
