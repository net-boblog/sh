package com.qccr.sh.external.carman.impl;

import com.qccr.sh.external.carman.CommentExt;
import com.qccr.sh.external.carman.bo.StoreCommentBO;
import com.qccr.sh.external.carman.bo.StoreCommentCountBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BeanCopierUtils;
import com.towell.carman.entity.store.StoreComment;
import com.towell.carman.entity.store.StoreCommentCount;
import com.towell.carman.entity.store.StoreCommentList;
import com.towell.carman.service.store.StoreCommentService;
import com.towell.carman.service.store.StoreStatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户评价调用carman的接口
 * @author dongxc
 */
@Service("commentExt")
public class CommentExtImpl implements CommentExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreStatisticService storeStatisticService;
    @Autowired
    private StoreCommentService storeCommentService;

    @Override
    public Response<Double> getCachedStoreAverageByStoreId(Integer storeId) {
        if(storeId==null){
            return ResponseUtil.error("门店ID不能为空");
        }
        double avgScore = 0;
        try {
            avgScore = storeStatisticService.getCachedStoreAverageByStoreId(storeId);
            //保留一位小数
            BigDecimal avgScoreBig = new BigDecimal(avgScore);
            avgScore = avgScoreBig.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception ex) {
            logger.error("查询carman的查询门店评价平均分接口失败", ex);
            return ResponseUtil.exception("查询carman的查询门店评价平均分接口失败");
        }
        return ResponseUtil.success(avgScore);
    }
    @Override
    public Response<Pagination<StoreCommentBO>> queryCommentByStoreId(int storeId, List<Integer> serverIds,int commentType,
                                                                      Timestamp startTime, Timestamp endTime, int pageNo, int pageSize){
        if(storeId==0){
            return ResponseUtil.error("门店ID不能为空");
        }
        if(pageSize==0){
            pageSize = 20;
        }
        try {
            StoreCommentList storeCommentList = storeCommentService.queryCommentByStoreId(storeId,serverIds,commentType,
                    startTime,endTime,pageNo,pageSize);
            if(storeCommentList!=null){
                Pagination<StoreCommentBO> pagination = new Pagination<StoreCommentBO>(pageSize,pageNo,storeCommentList.getTotal());
                List<StoreComment> commentList = storeCommentList.getStoreCommentList();
                List<StoreCommentBO> commentBOList = new LinkedList<StoreCommentBO>();
                if(commentList!=null && commentList.size()>0){
                    for(StoreComment storeComment:commentList){
                        StoreCommentBO commentBO = new StoreCommentBO();
                        BeanCopierUtils.copy(storeComment,commentBO);
                        commentBOList.add(commentBO);
                    }
                    pagination.setDataList(commentBOList);
                }
                return ResponseUtil.success(pagination);
            }else{
                Pagination<StoreCommentBO> pagination = new Pagination<StoreCommentBO>(pageSize,pageNo,0);
                return ResponseUtil.success(pagination);
            }
        } catch (Exception ex) {
            logger.error("查询carman的门店评价分页数据、总记录数接口失败", ex);
            return ResponseUtil.exception("查询carman的门店评价分页数据、总记录数接口失败");
        }
    }
    @Override
    public Response<StoreCommentCountBO> queryStoreCommentCount(int storeId, List<Integer> serverIds,Timestamp startTime, Timestamp endTime){
        if(storeId==0){
            return ResponseUtil.error("门店ID不能为空");
        }
        try {
            StoreCommentCount storeCommentCount = storeCommentService.queryStoreCommentCount(storeId,serverIds,startTime,endTime);
            StoreCommentCountBO storeCommentCountBO = new StoreCommentCountBO();
            if(storeCommentCount!=null){
                BeanCopierUtils.copy(storeCommentCount,storeCommentCountBO);
            }
            return ResponseUtil.success(storeCommentCountBO);
        } catch (Exception ex) {
            logger.error("查询carman的门店评价数量统计接口失败", ex);
            return ResponseUtil.exception("查询carman的门店评价数量统计接口失败");
        }
    }
}
