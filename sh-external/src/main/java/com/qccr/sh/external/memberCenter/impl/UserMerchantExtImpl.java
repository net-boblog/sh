package com.qccr.sh.external.memberCenter.impl;

import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.memberCenter.bo.UserMerchantLoginBO;
import com.qccr.sh.util.BaseConvert;
import com.toowell.membercenter.facade.base.MemberCenterResult;
import com.toowell.membercenter.facade.base.MerchantUserType;
import com.toowell.membercenter.facade.entity.merchant.UserMerchantLoginRo;
import com.toowell.membercenter.facade.entity.merchant.UserMerchantRo;
import com.toowell.membercenter.facade.service.merchant.UserMerchantFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongxc on 2015/12/29.
 */
@Service("userMerchantExt")
public class UserMerchantExtImpl implements UserMerchantExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMerchantFacade userMerchantFacade;

    @Override
    public Result<UserMerchantBO> queryByUsername(String userName){
        if(userName==null || userName.equals("")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"用户名不能为空");
        }
        try {
            logger.info("调用membercenter的UserMerchantFacade.queryByUsername接口，根据用户名查询门店用户信息，username={},merchantUserType={}",userName, MerchantUserType.STORE);
            MemberCenterResult<UserMerchantRo> memberCenterResult = userMerchantFacade.queryByUsername(userName,MerchantUserType.STORE);
            if(memberCenterResult!=null && memberCenterResult.isSuccess()){
                logger.info("调用membercenter的UserMerchantFacade.queryByUsername接口成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                UserMerchantRo userMerchantRo = memberCenterResult.getData();
                UserMerchantBO userMerchantBO = BaseConvert.beanConvert(userMerchantRo,UserMerchantBO.class);
                return Results.newSuccessResult(userMerchantBO);
            }else if(memberCenterResult!=null){
                logger.info("调用membercenter的UserMerchantFacade.queryByUsername接口失败,code={},codeDesc={},error={}",
                        memberCenterResult.getCode(),memberCenterResult.getCodedesc(),memberCenterResult.getError());
                return Results.newFailedResult(memberCenterResult.getStateCode(),memberCenterResult.getError());
            }else{
                logger.error("调用membercenter的UserMerchantFacade.queryByUsername接口时返回空");
                return Results.newFailedResult(CommonStateCode.FAILED);
            }
        } catch (Exception ex) {
            logger.error("调用membercenter的UserMerchantFacade.queryByUsername接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }

    @Override
    public Result<UserMerchantBO> login(String userName,String password,UserMerchantLoginBO userMerchantLoginBO){
        if(userName==null || userName.equals("") || password==null || password.equals("")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"用户名与密码不能为空");
        }
        try {
            logger.info("调用membercenter的UserMerchantFacade.login登录接口，username={},password={},merchantUserType={}",userName,password, MerchantUserType.STORE);
            UserMerchantLoginRo userMerchantLoginRo = BaseConvert.beanConvert(userMerchantLoginBO,UserMerchantLoginRo.class);
            MemberCenterResult<UserMerchantRo> memberCenterResult = userMerchantFacade.login(userName, password, MerchantUserType.STORE, userMerchantLoginRo);
            if(memberCenterResult!=null && memberCenterResult.isSuccess()){
                logger.info("调用membercenter的UserMerchantFacade.login接口成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                UserMerchantRo userMerchantRo = memberCenterResult.getData();
                UserMerchantBO userMerchantBO = BaseConvert.beanConvert(userMerchantRo,UserMerchantBO.class);
                return Results.newSuccessResult(userMerchantBO);
            }else if(memberCenterResult!=null){
                logger.info("调用membercenter的UserMerchantFacade.login接口失败,code={},codeDesc={},error={}",
                        memberCenterResult.getCode(),memberCenterResult.getCodedesc(),memberCenterResult.getError());
                return Results.newFailedResult(memberCenterResult.getStateCode(),memberCenterResult.getError());
            }else{
                logger.error("调用membercenter的UserMerchantFacade.login登录接口时返回空");
                return Results.newFailedResult(CommonStateCode.FAILED);
            }
        } catch (Exception ex) {
            logger.error("调用membercenter的UserMerchantFacade.login登录接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }

    @Override
    public Result<Integer> modifyPwdById(int userId,String oldPassword,String newPassword){
        if(userId==0 || oldPassword==null || oldPassword.equals("")
                || newPassword==null || newPassword.equals("")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"参数均不能为空");
        }
        if(oldPassword.equals(newPassword)){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"新密码不能与旧密码相同");
        }
        try {
            logger.info("调用membercenter的UserMerchantFacade.login登录接口，userId={},oldPassword={},newPassword={},merchantUserType={}",
                    userId,oldPassword,newPassword, MerchantUserType.STORE);
            MemberCenterResult<Integer> memberCenterResult = userMerchantFacade.modifyPwdById(userId, oldPassword, newPassword);
            if(memberCenterResult!=null && memberCenterResult.isSuccess()){
                Integer rows = memberCenterResult.getData();
                if(rows!=null && rows.intValue()>0){
                    logger.info("调用membercenter的UserMerchantFacade.modifyPwdById接口成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                    return Results.newSuccessResult(rows);
                }else{
                    logger.info("调用membercenter的UserMerchantFacade.modifyPwdById接口不成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                    return Results.newFailedResult(CommonStateCode.FAILED,"更新失败，影响的记录数为0");
                }
            }else if(memberCenterResult!=null){
                logger.info("调用membercenter的UserMerchantFacade.modifyPwdById接口失败,code={},codeDesc={},error={}",
                        memberCenterResult.getCode(),memberCenterResult.getCodedesc(),memberCenterResult.getError());
                return Results.newFailedResult(memberCenterResult.getStateCode(),memberCenterResult.getError());
            }else{
                logger.error("调用membercenter的UserMerchantFacade.modifyPwdById接口修改密码时返回空");
                return Results.newFailedResult(CommonStateCode.FAILED);
            }
        } catch (Exception ex) {
            logger.error("调用membercenter的UserMerchantFacade.modifyPwdById接口修改密码时异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }

    @Override
    public Result<Integer> resetPwdByUsername(String userName,String newPassword){
        if(userName==null || userName.equals("") || newPassword==null || newPassword.equals("")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"参数均不能为空");
        }
        try {
            logger.info("调用membercenter的UserMerchantFacade.resetPwdByUsername登录接口，userName={},newPassword={},merchantUserType={}",
                    userName,newPassword, MerchantUserType.STORE);
            MemberCenterResult<Integer> memberCenterResult = userMerchantFacade.resetPwdByUsername(userName,newPassword,MerchantUserType.STORE);
            if(memberCenterResult!=null && memberCenterResult.isSuccess()){
                Integer rows = memberCenterResult.getData();
                if(rows!=null && rows.intValue()>0){
                    logger.info("调用membercenter的UserMerchantFacade.resetPwdByUsername接口成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                    return Results.newSuccessResult(rows);
                }else{
                    logger.info("调用membercenter的UserMerchantFacade.resetPwdByUsername接口不成功,code={},codeDesc={}",memberCenterResult.getCode(),memberCenterResult.getCodedesc());
                    return Results.newFailedResult(CommonStateCode.FAILED,"重置失败，影响的记录数为0");
                }
            }else if(memberCenterResult!=null){
                logger.info("调用membercenter的UserMerchantFacade.resetPwdByUsername接口失败,code={},codeDesc={},error={}",
                        memberCenterResult.getCode(),memberCenterResult.getCodedesc(),memberCenterResult.getError());
                return Results.newFailedResult(memberCenterResult.getStateCode(),memberCenterResult.getError());
            }else{
                logger.error("调用membercenter的UserMerchantFacade.resetPwdByUsername接口重置密码时返回空");
                return Results.newFailedResult(CommonStateCode.FAILED);
            }
        } catch (Exception ex) {
            logger.error("调用membercenter的UserMerchantFacade.resetPwdByUsername接口重置密码时异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }
}
