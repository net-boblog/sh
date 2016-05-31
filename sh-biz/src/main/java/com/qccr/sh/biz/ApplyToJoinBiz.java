package com.qccr.sh.biz;

import java.util.List;
import java.util.Map;


public interface ApplyToJoinBiz {

	void insertSh_user_apply(Map<String,Object> map);
	
	void insertSh_service_mapping(Map<String,Object> map);

	List<Map<String, Object>> allProvice(int serverId);

	List<Map<String, Object>> selectCitysByProvince(int province, int serverId);

	List<Map<String, Object>> selectAreaByCondition(Map<String, Object> map);
	

}
