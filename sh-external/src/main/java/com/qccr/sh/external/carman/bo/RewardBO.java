package com.qccr.sh.external.carman.bo;

import com.qccr.sh.util.BT;
import java.util.Date;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
public class RewardBO {

    private long id;
    private String no;
    private int storeId;
    private int clearId;
    private int type;
    private Double sum;
    private int status;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private String operator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getClearId() {
        return clearId;
    }

    public void setClearId(int clearId) {
        this.clearId = clearId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTypeStr() {
        if (this.getType() == 1) {
            return "订单奖励";
        } else {
            return "";
        }
    }

    public String getCreateTimeStr(){
        if (this.createTime == null) {
            return "";
        }
        return BT.dateFormat(this.createTime, "yyyy-MM-dd HH:mm:ss");
    }

}
