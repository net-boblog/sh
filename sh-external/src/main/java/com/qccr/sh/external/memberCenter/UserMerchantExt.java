package com.qccr.sh.external.memberCenter;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.memberCenter.bo.UserMerchantLoginBO;

/**
 * Created by dongxc on 2015/12/29.
 * 商家用户
 */
public interface UserMerchantExt {
    /**
     * 根据用户名查找门店用户的信息
     * @author  董雪钗
     * @param   username 用户名
     * @return  门店用户
     */
    public Result<UserMerchantBO> queryByUsername(String username);

    /**
     * 门店用户登录接口
     * @author 董雪钗
     * @param userName  用户名
     * @param password  密码
     * @param userMerchantLoginBO  登录对象
     * @return 登录结果，成功的话返回用户对象
     */
    public Result<UserMerchantBO> login(String userName,String password,UserMerchantLoginBO userMerchantLoginBO);

    /**
     * 门店用户修改密码
     * @author 董雪钗
     * @param userId       用户ID
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     * @return 修改结果，返回影响的记录数
     */
    public Result<Integer> modifyPwdById(int userId,String oldPassword,String newPassword);

    /**
     * 门店用户重置密码
     * @author 董雪钗
     * @param userName     用户名
     * @param newPassword  新密码
     * @return  修改结果，返回影响的记录数
     */
    public Result<Integer> resetPwdByUsername(String userName,String newPassword);
}
