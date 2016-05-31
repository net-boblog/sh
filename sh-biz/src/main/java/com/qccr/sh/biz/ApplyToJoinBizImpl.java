package com.qccr.sh.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qccr.sh.dao.ApplyToJoinDao;

@Service
public class ApplyToJoinBizImpl implements ApplyToJoinBiz  {

	@Autowired
	ApplyToJoinDao applyToJoinDao;
	
	public void insertSh_user_apply(Map<String,Object> map) {
		
		applyToJoinDao.insertSh_user_apply(map);
	}

	@Transactional
	public void insertSh_service_mapping(Map<String,Object> map){
		int id= applyToJoinDao.selectId();
		map.put("id", id);
		applyToJoinDao.insertSh_service_mapping(map);
		
	}
	
	public List<Map<String, Object>> allProvice(int serverId) {
		
		return applyToJoinDao.allProvice( serverId);
	}

	public List<Map<String, Object>> selectCitysByProvince(int province, int serverId) {
		// TODO Auto-generated method stub
		return applyToJoinDao.selectCitysByProvince(province,serverId);
	}

	public List<Map<String, Object>> selectAreaByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return applyToJoinDao.selectAreaByCondition(map);
	}


}
