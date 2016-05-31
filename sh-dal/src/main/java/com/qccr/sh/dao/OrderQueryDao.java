package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

public interface OrderQueryDao {

	List<Map<String, Object>> queryOrders(Map<String, Object> map);

	long queryOrdersSize(Map<String, Object> map);
	
	List<Map<String, Object>> queryWashOrderByForVerification(long id);
	
	List<Map<String, Object>> queryTireOrderByForVerification(long id);
	
	List<Map<String, Object>> queryMyServerTypeByStoreId(long storeId);
	
	List<Map<String, Object>> queryMyServerTypeByMerchantId(long merchantId);
    
	
	/**
	 * 查询商户 已验证的服务订单
	 * @param map:{storId:商户id（必填）}
	 * @return
	 */
	List<Map<String, Object>> queryServerOrder(Map<String, Object> map);
    
	/**
	 * 查询商户 已验证的服务订单
	 * @param map:{storId:商户id（必填）}
	 * @return
	 */
	long queryServerOrderCount(Map<String, Object> map);

	List<Map<String, Object>> queryStoreServer(Map<String, Object> searchMap);
	
	/**
	 * 查询发往门店的待收货订单
	 * @param searchMap {storId:必填}
	 * @return 订单信息{o.id,o.status,o.create_time,o.no}
	 */
	public abstract List<Map<String, Object>> queryThirdpartyOrders(Map<String, Object> searchMap);
	
	/**
	 * 查询出和门店有关的服务单id和服务总费用
	 * @param searchMap {storId:必填，orderIds：可空}
	 * @return 复合订单信息{orderId,sale_price}
	 */
	public abstract List<Map<String, Object>> queryOrderSalePrice(Map<String, Object> searchMap);
	/**
	 * 查询出服务单相关的订单
	 * @param searchMap {status:订单状态，必填、orderIds:订单id 必填}
	 * @return 订单信息{o.id,o.status,o.create_time,o.no}
	 */
	public abstract List<Map<String, Object>> queryOrderServers(Map<String, Object> searchMap);
	/**
	 * 查询出订单下面的商品
	 * @param searchMap
	 * @return{og.id as id,og.goodsName,og.img,og.saleNum,og.order_id orderId}
	 */
	public abstract List<Map<String, Object>> queryOrderGoods(Map<String, Object> searchMap);
    
	/**
	 * 查询出相关订单id
	 * @param searchMap
	 * @return
	 */
	List<Integer> queryOrderIds(Map<String, Object> searchMap);
    
	/**
	 * 查询物流
	 * @param orderId 订单id
	 * @return
	 */
	Map<String, Object> findLogisticsByOrderId(Integer orderId);

	Map<String, Object> queryOneOrderById(Integer orderId);

	List<Map<String, Object>> queryServerPriceByorderId(Map<String, Object> searchMap);
    
	
}
