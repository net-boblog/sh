package com.qccr.sh.external.carman.bo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dongxc on 2015/11/27.
 */
public class StoreCommentBO implements Serializable{
    private static final long serialVersionUID = -2056609865433863199L;

    /** 账号ID */
    private int userId;
    /** 评价 */
    private String comment;
    /** 评价时间 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp commentTime;
    /** 评分 */
    private double score;
    /** 评价用户名 */
    private String username;
    /** 图片 */
    private String imgs;
    // 客服回复内容
    private String reply;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
