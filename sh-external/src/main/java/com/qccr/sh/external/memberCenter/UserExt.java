package com.qccr.sh.external.memberCenter;

import com.qccr.sh.external.memberCenter.bo.UserBO;
import com.qccr.sh.response.Response;

/**
 * member_center的用户接口
 * @author dongxc
 */
public interface UserExt {

    /**
     * @Description: 根据用户ID查询用户信息
     * @author dongxc
     * @param userId 用户ID
     * @return 用户对象
     */
    public Response<UserBO> queryByUserId(int userId);

    /**
     * @Description: 根据用户账号查询用户信息
     * @author dongxc
     * @param username 账号
     * @return 用户对象
     */
    public Response<UserBO> queryByUsername(String username);
}
