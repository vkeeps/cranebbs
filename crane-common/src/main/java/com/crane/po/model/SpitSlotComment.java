package com.crane.po.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.crane.po.serializ.CustomDateSerializer;
import com.crane.po.vo.api.SpitSlotCommentVO;
import com.crane.utils.Emotions;
import com.crane.utils.Emotions.Dev;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SpitSlotComment {
    private Integer id;

    private Integer spitSlotId;

    private String content;

    private String showContent;

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
        this.content = content == null ? null : content.trim();
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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getShowContent() {
        this.showContent = Emotions.formatEmotion(this.content, Dev.WEB);
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }


    public static SpitSlotCommentVO convert2VO(SpitSlotComment comment) {
        SpitSlotCommentVO vo = new SpitSlotCommentVO();
        vo.setId(comment.getId());
        vo.setSpitSlotId(comment.getSpitSlotId());
        vo.setContent(comment.getShowContent());
        vo.setCreateTime(comment.getCreateTime());
        vo.setUserId(comment.getUserId());
        vo.setUserName(comment.getUserName());
        vo.setUserIcon(comment.getUserIcon());
        vo.setSourceFrom(comment.getSourceFrom());
        return vo;
    }

    public static List<SpitSlotCommentVO> convert2VOList(List<SpitSlotComment> list) {
        List<SpitSlotCommentVO> VOList = new ArrayList<>();
        for (SpitSlotComment comment : list) {
            VOList.add(convert2VO(comment));
        }
        return VOList;
    }
}