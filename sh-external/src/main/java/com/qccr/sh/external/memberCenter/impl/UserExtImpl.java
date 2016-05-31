package com.qccr.sh.external.memberCenter.impl;

import com.qccr.sh.external.memberCenter.UserExt;
import com.qccr.sh.external.memberCenter.bo.UserBO;
import com.qccr.sh.response.CodeTable;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BeanCopierUtils;
import com.toowell.membercenter.facade.base.MemberCenterResult;
import com.toowell.membercenter.facade.entity.user.UserInfoRo;
import com.toowell.membercenter.facade.service.user.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dongxc on 2015/11/18.
 */
@Service("userExt")
public class UserExtImpl implements UserExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Override
    public Response<UserBO> queryByUserId(int userId) {
        Response<UserBO> boResponse = new Response<UserBO>();
        logger.info("根据ID查用户信息,userId={}", userId);
        try {
            MemberCenterResult<UserInfoRo> result = userFacade.queryByUserId(userId);
            if(result!=null && result.isSuccess()){
                logger.info("根据ID查用户信息成功,userId={},code={},codedesc={}",userId,result.getCode(),result.getCodedesc());
                UserInfoRo userInfoRo = result.getData();
                UserBO userBO = new UserBO();
                BeanCopierUtils.copy(userInfoRo,userBO);
                boResponse.setData(userBO);
                boResponse.setCode(CodeTable.SUCCESS.getCode());
                boResponse.setMessage(CodeTable.SUCCESS.getMessage());
            }else{
                logger.info("根据ID查用户信息失败,userId={},code={},codedesc={}",
                        userId,result != null ? result.getCode() : "",result != null ? result.getCodedesc() : "");
                boResponse.setCode(CodeTable.ERROR.getCode());
                boResponse.setMessage(CodeTable.ERROR.getMessage());
            }
        } catch (Exception e) {
            logger.error("根据ID查用户信息异常,userId=" + userId, e);
            return ResponseUtil.exception(e.getMessage());
        }
        return boResponse;
    }

    @Override
    public Response<UserBO> queryByUsername(String username) {
        Response<UserBO> boResponse = new Response<UserBO>();
        logger.info("根据账号查用户信息,username={}", username);
        try {
            MemberCenterResult<UserInfoRo> result = userFacade.queryByUsername(username);
            if(result!=null && result.isSuccess()){
                logger.info("根据账号查用户信息成功,username={},code={},codedesc={}",username,result.getCode(),result.getCodedesc());
                UserInfoRo userInfoRo = result.getData();
                UserBO userBO = new UserBO();
                BeanCopierUtils.copy(userInfoRo, userBO);
                boResponse.setData(userBO);
                boResponse.setCode(CodeTable.SUCCESS.getCode());
                boResponse.setMessage(CodeTable.SUCCESS.getMessage());
            }else{
                logger.info("根据账号查用户信息失败,username={},code={},codedesc={}",
                        username,result != null ? result.getCode() : "",result != null ? result.getCodedesc() : "");
                boResponse.setCode(CodeTable.ERROR.getCode());
                boResponse.setMessage(CodeTable.ERROR.getMessage());
            }
        } catch (Exception e) {
            logger.error("根据账号查用户信息异常,username=" + username, e);
            return ResponseUtil.exception(e.getMessage());
        }
        return boResponse;
    }
}
