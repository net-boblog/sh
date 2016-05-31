package com.qccr.sh.biz;

public interface UserBiz {

	public abstract void modifyPassword(String username, String oldPassword, String newPassword);

	/**
	 * 根据用户名查询仓库id
	 *
	 * @param userName
	 * @return
	 */
	Integer getStoreId(String userName);
	

}
