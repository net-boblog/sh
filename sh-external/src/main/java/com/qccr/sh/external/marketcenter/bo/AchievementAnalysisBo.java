/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */

package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzhonghua
 * @version 2016/3/9 23:43.
 */
public class AchievementAnalysisBo implements Serializable {
    private static final long serialVersionUID = -4268792863545710535L;

    private List<StoreAchievementAnalysisBo> storeAchievementAnalysisList;

    public AchievementAnalysisBo() {
    }

    public List<StoreAchievementAnalysisBo> getStoreAchievementAnalysisList() {
        return this.storeAchievementAnalysisList;
    }

    public void setStoreAchievementAnalysisList(List<StoreAchievementAnalysisBo> storeAchievementAnalysisList) {
        this.storeAchievementAnalysisList = storeAchievementAnalysisList;
    }

    public String toString() {
        return "AchievementAnalysisBo [storeAchievementAnalysisList=" + this.storeAchievementAnalysisList + "]";
    }
}
