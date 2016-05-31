package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dongxc on 2015/12/7.
 */
public class LogisticsKD100VOBO implements Serializable {

    private static final long serialVersionUID = 4959242476796391494L;

    /**
     * 快递公司名称
     */
    private String company;

    /**
     * 快递公司icon
     */
    private String img;

    /**
     * 快递单号
     */
    private String no;

    /**
     * 物流详情
     */
    private List<LogisticsDetailBO> data;
    /**
     * 状态 0物流不存在 1物流中  2已签收
     */
    private Integer status;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<LogisticsDetailBO> getData() {
        return data;
    }

    public void setData(List<LogisticsDetailBO> data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
