package com.qccr.sh.external.carman.bo;

import com.qccr.sh.util.BT;

import java.util.Date;

/**
 * Created by xianchao.yan on 2015/11/2.
 */
public class DrawRecordBO {

    private int id;
    private int storeId;
    private double sum;
    private int status;
    private Date clearTime;
    private Date createTime;
    private String accountNo;
    private String accountName;
    private String accountOperator;
    private String no;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getClearTime() {
        return clearTime;
    }

    public void setClearTime(Date clearTime) {
        this.clearTime = clearTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountOperator() {
        return accountOperator;
    }

    public void setAccountOperator(String accountOperator) {
        this.accountOperator = accountOperator;
    }

    public String getStatusDesc() {
        if (this.status == 1) {
            return "申请中";
        } else if (this.status == 2) {
            return "结算中";
        } else if (this.status == 3) {
            return "已结算";
        } else if (this.status == 4) {
            return "拒绝";
        } else {
            return "";
        }
    }

    public String getCreateTimeStr() {
        if (this.createTime == null) {
            return "";
        }
        return BT.dateFormat(this.createTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getClearTimeStr() {
        if (this.clearTime == null) {
            return "";
        }
        return BT.dateFormat(this.clearTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
