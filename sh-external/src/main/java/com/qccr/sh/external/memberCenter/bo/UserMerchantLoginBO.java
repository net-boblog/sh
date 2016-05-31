package com.qccr.sh.external.memberCenter.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dongxc on 2015/12/29.
 */
public class UserMerchantLoginBO implements Serializable {
    private static final long serialVersionUID = -9146424450436205083L;
    /** id */
    private long id;
    /** 用户id */
    private int userId;
    /** 来源 */
    private int source;
    /** 用户唯一标识 */
    private String imei;
    /** 经度 */
    private double lat;
    /** 纬度 */
    private double lng;
    /** ip */
    private String ip;
    /** 上线时间 */
    private Timestamp loginTime;
    /** 下线时间 */
    private Timestamp logoffTime;
    /** 登录渠道 */
    private String platform;
    /** 是否已下线 */
    private boolean isoff;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoffTime() {
        return logoffTime;
    }

    public void setLogoffTime(Timestamp logoffTime) {
        this.logoffTime = logoffTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isoff() {
        return isoff;
    }

    public void setIsoff(boolean isoff) {
        this.isoff = isoff;
    }
}
