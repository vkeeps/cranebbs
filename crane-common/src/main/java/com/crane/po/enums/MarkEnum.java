package com.crane.po.enums;
/**
* @author  Crane:
* @version 5.0
* @time 2017年4月18日 下午6:18:10
* 
*/
public enum MarkEnum {
    MARK_SIGNIN(2, "签到"), MARK_TOPIC(5, "贴吧"), MARK_BLOG(5, "博客"), MARK_ASK(5, "问答"), MARK_SIGNIN_CONTINUE(10, "连续签到"), MARK_SPIT_SLOT(2, "吐槽"), MARK_SPIT_SLOT_COMMENT(1, "吐槽评论"), COMMENT(2, "评论");

    private int mark;
    private String desc;

    private MarkEnum(int mark, String desc) {
        this.mark = mark;
    }

    public int getMark() {
        return this.mark;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}

