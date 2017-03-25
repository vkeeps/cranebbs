package com.crane.po.vo.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.crane.po.serializ.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SpitSlotVO {
    private Integer id;

    private Integer userId;

    private String userIcon;

    private String userName;

    private String imageUrlSmall;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    private String content;

    private Integer likeCount;

    private Integer commentCount;

    private String sourceFrom;

    private List<SpitSlotCommentVO> commentList = new ArrayList<>();

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

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public List<SpitSlotCommentVO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<SpitSlotCommentVO> commentList) {
        this.commentList = commentList;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
