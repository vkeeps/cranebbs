package com.crane.po.vo.api;

import java.util.Date;

import com.crane.po.serializ.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SpitSlotCommentVO {

    private Integer id;

    private Integer spitSlotId;

    private String content;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    private Integer userId;

    private String userName;

    private String userIcon;

    private String sourceFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpitSlotId() {
        return spitSlotId;
    }

    public void setSpitSlotId(Integer spitSlotId) {
        this.spitSlotId = spitSlotId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

}
