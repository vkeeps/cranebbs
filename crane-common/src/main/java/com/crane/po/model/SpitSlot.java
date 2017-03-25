package com.crane.po.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.crane.po.serializ.CustomDateSerializer;
import com.crane.po.vo.api.SpitSlotVO;
import com.crane.utils.Emotions;
import com.crane.utils.Emotions.Dev;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 吐槽
 * ClassName: SpitSlot
 * date: 2015年9月26日 下午5:47:34
 * @author 多多洛
 * @since JDK 1.7
 */
public class SpitSlot {
    private Integer id;

    private Integer userId;

    private String userIcon;

    private String userName;

    private String imageUrl;

    private String imageUrlSmall;

    private String musicUrl;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    private String content;

    private String showContent;

    private Integer commentCount;

    private Integer likeCount;

    private List<SpitSlotComment> commentList = new ArrayList<SpitSlotComment>();

    private String sourceFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SpitSlotComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<SpitSlotComment> commentList) {
        this.commentList = commentList;
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
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall == null ? null : imageUrlSmall.trim();
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl == null ? null : musicUrl.trim();
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
        this.content = content == null ? null : content.trim();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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


    public static SpitSlotVO convert2VO(SpitSlot spitSlot) {
        SpitSlotVO vo = new SpitSlotVO();
        vo.setId(spitSlot.getId());
        vo.setUserId(spitSlot.getUserId());
        vo.setUserIcon(spitSlot.getUserIcon());
        vo.setUserName(spitSlot.getUserName());
        vo.setImageUrlSmall(spitSlot.getImageUrlSmall());
        vo.setCreateTime(spitSlot.getCreateTime());
        vo.setContent(spitSlot.getShowContent());
        vo.setLikeCount(spitSlot.getLikeCount());
        vo.setCommentCount(spitSlot.getCommentCount());
        vo.setSourceFrom(spitSlot.getSourceFrom());
        return vo;
    }

    public static List<SpitSlotVO> convert2VOList(List<SpitSlot> list) {
        List<SpitSlotVO> VOList = new ArrayList<>();
        for (SpitSlot spitSlot : list) {
            VOList.add(convert2VO(spitSlot));
        }
        return VOList;
    }
}