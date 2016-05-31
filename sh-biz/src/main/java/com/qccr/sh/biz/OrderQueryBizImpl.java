package com.qccr.sh.biz;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qccr.sh.dao.OrderQueryDao;

@Service
@Transactional
public class OrderQueryBizImpl implements OrderQueryBiz {

	@Autowired
	private OrderQueryDao orderQueryDao;

	@Override
	public List<Map<String, Object>> queryOrder(Map<String, Object> map) {
		return orderQueryDao.queryOrders(map);
	}

	@Override
	public long queryOrderSize(Map<String, Object> map) {
		return orderQueryDao.queryOrdersSize(map);
	}

	@Override
	public List<Map<String, Object>> queryMyServerTypeByStoreId(long storeId) {
		return orderQueryDao.queryMyServerTypeByStoreId(storeId);
	}

	@Override
	public List<Map<String, Object>> queryServerOrder(Map<String, Object> map) {
		if(StringUtils.isBlank(map.get("storeId").toString())){
			return null;
		}
		return orderQueryDao.queryServerOrder(map);
	}

	@Override
	public long queryServerOrderCount(Map<String, Object> map) {
		if(StringUtils.isBlank(map.get("storeId").toString())){
			return 0;
		}
		return orderQueryDao.queryServerOrderCount(map);
	}

	@Override
	public List<Map<String, Object>> queryStoreServer(Map<String, Object> searchMap) {
		return orderQueryDao.queryStoreServer(searchMap);
	}

	@Override
	public List<Map<String, Object>> queryThirdpartyOrders(Map<String, Object> searchMap) {
		return orderQueryDao.queryThirdpartyOrders(searchMap);
	}

	@Override
	public List<Map<String, Object>> queryOrderSalePrice(Map<String, Object> searchMap) {
		return orderQueryDao.queryOrderSalePrice(searchMap);
	}

	@Override
	public List<Map<String, Object>> queryOrderServers(Map<String, Object> searchMap) {
		return orderQueryDao.queryOrderServers(searchMap);
	}

	@Override
	public List<Map<String, Object>> queryOrderGoods(Map<String, Object> searchMap) {
		return orderQueryDao.queryOrderGoods(searchMap);
	}

	@Override
	public List<Integer> queryOrderIds(Map<String, Object> searchMap) {
		return orderQueryDao.queryOrderIds(searchMap);
	}

	@Override
	public Map<String, Object> findLogisticsByOrderId(Integer orderId) {
		return orderQueryDao.findLogisticsByOrderId(orderId);
	}

	@Override
	public Map<String, Object> queryOneOrderById(Integer orderId) {
		return orderQueryDao.queryOneOrderById(orderId);
	}

	@Override
	public List<Map<String, Object>> queryServerPriceByorderId(Map<String, Object> searchMap) {
		return orderQueryDao.queryServerPriceByorderId(searchMap);
	}



}
