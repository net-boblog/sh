package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

public interface MyGoodsDao {

	List<Map<String, Object>> queryTMerchandiseByMerchantid(long id);
	
	List<Map<String, Object>> queryTMerchandiseByStoreid(long id);

}
