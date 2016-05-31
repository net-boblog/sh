/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.marketcenter.bo.*;
import com.qccr.sh.page.Pagination;

/**
 * 超人活动服务接口
 * @author zhangzhonghua
 * @date 2016年3月01日 上午10:12
 */
public interface ActPromotionExt {
    /**
     * 分页查询商户报名的活动列表
     * @param actPromotionQuery 商户报名活动查询对象
     * @return
     */
    public Result<Pagination<ActPromotionBO>> pageQueryEnrollPlatformPromotion(ActPromotionQuery actPromotionQuery);
}
