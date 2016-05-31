package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {

	
	public abstract List<String> selectRolesNameByUserId(Integer id);
	
	public abstract List<String> selectPermissionsNameByUserId(Integer id);
	
	public abstract Map<String,String> selectUserByUsername(String username);
	
	public abstract int updatePasswordByusername(Map<String,String> map);
	
	public abstract Map<String,String> checkUserByUsername(String username);
	
	
	
}
