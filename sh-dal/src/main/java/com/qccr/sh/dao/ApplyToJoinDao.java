package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ApplyToJoinDao {

	
	public abstract int insertSh_user_apply(Map<String,Object> map);
	
	public abstract int selectId();
	
	public abstract void insertSh_service_mapping(Map<String,Object> map);
	
	public List<Map<String, Object>> allProvice(@Param("serverId")int serverId);

	public List<Map<String, Object>> selectCitysByProvince(@Param("province")int province,@Param("serverId") int serverId);

	public List<Map<String, Object>> selectAreaByCondition(Map<String, Object> map);
	
}
