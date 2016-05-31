package com.qccr.sh.external.carman;

import com.qccr.sh.external.carman.bo.StoreCommentBO;
import com.qccr.sh.external.carman.bo.StoreCommentCountBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户评价调用carman的接口
 * @author dongxc
 */
public interface CommentExt {

    /**
     * 根据门店ID查询门店评价的平均分
     * @param storeId
     * @return
     */
    public Response<Double> getCachedStoreAverageByStoreId(Integer storeId);
    /**
     * 查询门店的评价列表
     * @param storeId 门店ID，必填
     * @param serverIds 服务列表
     * @param commentType 评价类型  -1全部 0差评 1中评 2好评 3有图
     * @param startTime 评价开始时间
     * @param endTime 评价结束时间
     * @param pageNo 当前页 0开始
     * @param pageSize 每页记录数
     * @return
     */
    public Response<Pagination<StoreCommentBO>> queryCommentByStoreId(int storeId, List<Integer> serverIds,int commentType,
                                                                      Timestamp startTime, Timestamp endTime, int pageNo, int pageSize);

    /**
     * 查询门店评价总数，好评、中评、差评数
     * @param storeId 门店ID
     * @param serverIds  服务列表
     * @param startTime  评价开始时间
     * @param endTime    评价结束时间
     * @return
     */
    public Response<StoreCommentCountBO> queryStoreCommentCount(int storeId, List<Integer> serverIds,Timestamp startTime, Timestamp endTime);
}
