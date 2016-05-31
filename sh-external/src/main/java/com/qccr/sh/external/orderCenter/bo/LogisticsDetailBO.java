package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;

/**
 * Created by dongxc on 2015/12/7.
 */
public class LogisticsDetailBO implements Serializable {
    private static final long serialVersionUID = 7838945228534705346L;

    private String time;

    private String location;

    private String context;

    private String ftime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    @Override
    public String toString() {
        return "LogisticsDetailRo [time=" + time + ", location=" + location
                + ", context=" + context + ", ftime=" + ftime + "]";
    }
}
