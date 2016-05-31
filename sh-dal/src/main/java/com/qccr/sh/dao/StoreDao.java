package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by dongxc on 2015/11/2.
 */
public interface StoreDao {
    /**
     * 商户服务评价列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> selectCommentList(Map<String, Object> map);
    /**
     * 商户服务评价列表的数量
     * @param map
     * @return
     */
    public int countCommentList(Map<String, Object> map);
}
