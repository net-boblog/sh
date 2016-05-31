package com.qccr.sh.external.carman.bo;

/**
 * Created by dongxc on 2015/11/27.
 */
public class StoreCommentCountBO {
    /** 总的评价数量 */
    private int totalCount;
    /** 差评数量 */
    private int badCommentCount;
    /** 中评数量 */
    private int mediumCommentCount;
    /** 好评数量 */
    private int goodCommentCount;
    /** 有图数量 */
    private int imageCommentCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getBadCommentCount() {
        return badCommentCount;
    }

    public void setBadCommentCount(int badCommentCount) {
        this.badCommentCount = badCommentCount;
    }

    public int getMediumCommentCount() {
        return mediumCommentCount;
    }

    public void setMediumCommentCount(int mediumCommentCount) {
        this.mediumCommentCount = mediumCommentCount;
    }

    public int getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(int goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public int getImageCommentCount() {
        return imageCommentCount;
    }

    public void setImageCommentCount(int imageCommentCount) {
        this.imageCommentCount = imageCommentCount;
    }
}
