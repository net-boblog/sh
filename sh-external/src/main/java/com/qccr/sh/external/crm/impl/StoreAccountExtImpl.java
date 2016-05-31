package com.qccr.sh.external.crm.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.qccr.sh.external.crm.bo.StoreSafetyBo;
import com.toowell.crm.facade.store.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.response.CodeTable;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.base.BatchResult;
import com.toowell.crm.facade.base.RemoteProjectEnum;
import com.toowell.crm.facade.base.Result;
import com.toowell.crm.facade.store.service.StoreFacade;

@Service("storeAccountExt")
public class StoreAccountExtImpl implements StoreAccountExt{
	
	private static final Logger log = LoggerFactory.getLogger(StoreAccountExtImpl.class);
	
    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;

	@Override
	public Response<StoreInfoBO> getStoreInfo(Integer storeId) {
		StoreInfoBO storeInfoBO = new StoreInfoBO();
		Response<StoreInfoBO> res = new Response<StoreInfoBO>();
		try{
			if(storeId==null){
				res.setCode(CodeTable.INVALID_ARGS.getCode());
				res.setMessage(CodeTable.INVALID_ARGS.getMessage());
				return res;
			}
			com.qccr.knife.result.Result<StoreRo> result = storeFacade.getStoreById(storeId);
			if(result.isSuccess()){
	    		log.info("获取crm成功返回的信息");
				if(result.getData() == null){
					res.setCode(result.getStateCode().getCode());
					res.setMessage(result.getStatusText());
					return  res;
				}
	    		StoreRo storeInfo = result.getData();
				StoreSafetyBo storeSafetyBo=new StoreSafetyBo();
	    		BeanUtils.copyProperties(storeInfo, storeInfoBO);
				BeanUtils.copyProperties(storeInfo.getStoreSafetyRo(),storeSafetyBo);
				storeInfoBO.setStoreSafetyBo(storeSafetyBo);
	    		res.setCode(CodeTable.SUCCESS.getCode());
	    		Result<Integer> changeCount = storeFacade.couldUpdateStoreSafetyInfo(storeId.toString(), RemoteProjectEnum.SH);
	    		if(changeCount.isSuccess()){
	    			storeInfoBO.setChangeCount(changeCount.getData());
	    		}else{
	    			log.info("获取当前账户信息修改次数失败");
		    		res.setCode(changeCount.getCode());
		    		res.setMessage(changeCount.getError());
	    		}
				com.qccr.knife.result.Result<StoreOutBizRo> result1=storeFacade.getStoreOutBiz(storeId);
				if(result1.isSuccess() && result1.getData()!=null){
					storeInfoBO.setOutBizStartDate(result1.getData().getOutBizStartDate());
					storeInfoBO.setOutBizEndDate(result1.getData().getOutBizEndDate());
				}
	    		res.setData(storeInfoBO);
	    		res.setMessage(CodeTable.SUCCESS.getMessage());
	    		return res;
	    	}else{
	    		log.info("获取crm返回的错误信息或者内容为空");
	    		res.setCode(result.getStateCode().getCode());
	    		res.setMessage(result.getStatusText());
	    		return res;
	    	}
		}catch(Exception e){
			log.error("获取门店信息时异常",e);
			res.setCode(CodeTable.EXCEPTION.getCode());
			res.setMessage(CodeTable.EXCEPTION.getMessage());
			return res;
		}
		
	}

	@Override
	public Response<Integer> updateStoreInfo(StoreInfoBO storeInfoBO) {
		Response<Integer> res = new Response<Integer>();
		try{
			if(StringUtils.isEmpty(storeInfoBO.getStoreId())){
				res.setCode(CodeTable.INVALID_ARGS.getCode());
				res.setMessage(CodeTable.INVALID_ARGS.getMessage());
				return res;
			}
			StoreRo storeRo = new StoreRo();

			String bizEndTime=storeInfoBO.getBizEndTime();
			String bizStartTime=storeInfoBO.getBizStartTime();
			String outBizEndDate=storeInfoBO.getOutBizEndDate();
			String outBizStartDate=storeInfoBO.getOutBizStartDate();
			String closedCycle=storeInfoBO.getClosedCycle();
			List<ClosedCycleEnum> closedCycleEnums=new LinkedList<ClosedCycleEnum>();
			if(closedCycle!=null && closedCycle.length()>0){
				String[] closedCycles=closedCycle.split(",");
				for(String s:closedCycles){
					closedCycleEnums.add(ClosedCycleEnum.valueOf(s));
				}
			}

			storeInfoBO.setOutBizEndDate(null);
			storeInfoBO.setOutBizStartDate(null);
			storeInfoBO.setClosedCycle(null);

			BeanUtils.copyProperties(storeInfoBO, storeRo);
			log.info("调用crm修改门店信息方法开始");
			Result<Integer> result = storeFacade.updateStoreWithoutSafetyInfo(storeRo, RemoteProjectEnum.SH);
			log.info("调用crm修改门店信息方法结束");

			log.info("调用crm修改营业时间开始");
			com.qccr.knife.result.Result<Boolean> result1= storeFacade.storeBiz(Integer.parseInt(storeInfoBO.getStoreId()),bizStartTime,bizEndTime,closedCycleEnums);
			log.info("调用crm修改营业时间结束");

			log.info("调用crm修改休业时间开始");
			com.qccr.knife.result.Result<Boolean> result2= storeFacade.storeOutBiz(Integer.parseInt(storeInfoBO.getStoreId()),outBizStartDate,outBizEndDate,"",OutBizTypeEnum.OTHER);
			log.info("调用crm修改休业时间结束");

			if(result.isSuccess() && result1.isSuccess() && result2.isSuccess()){
				res.setCode(CodeTable.SUCCESS.getCode());
				res.setMessage(CodeTable.SUCCESS.getMessage());
				return res;
			}else{
				String error=result.getError();
				if(result.isSuccess() && !result1.isSuccess()){
					error=result1.getStatusText();
				}else if(result.isSuccess() && !result2.isSuccess()){
					error=result2.getStatusText();
				}
				res.setCode(result.getCode());
				res.setMessage(error);
				return res;
			}
		}catch(Exception e){
			log.error("调用crm修改门店异常",e);
			res.setCode(CodeTable.EXCEPTION.getCode());
			res.setMessage(CodeTable.EXCEPTION.getMessage());
			return res;
		}
	}

	@Override
	public Response<Integer> updateBankAccount(StoreInfoBO storeInfoBO) {
		Response<Integer> res = new Response<Integer>();
		try{
			if(StringUtils.isEmpty(storeInfoBO.getStoreId())){
				res.setCode(CodeTable.INVALID_ARGS.getCode());
				res.setMessage(CodeTable.INVALID_ARGS.getMessage());
				return res;
			}
			StoreSafetyRo storeSafetyRo = new StoreSafetyRo();
			BeanUtils.copyProperties(storeInfoBO, storeSafetyRo);
			log.info("调用crm修改收款信息方法");
			Result<Integer> result = storeFacade.updateStoreSafetyInfo(storeSafetyRo, RemoteProjectEnum.SH);
			if(result.isSuccess()){
				res.setCode(CodeTable.SUCCESS.getCode());
				res.setMessage(CodeTable.SUCCESS.getMessage());
				return res;
			}else{
				res.setCode(result.getCode());
				res.setMessage(result.getError());
				return res;
			}
		}catch(Exception e){
			log.error("调用crm修改收款账号异常",e);
			res.setCode(CodeTable.EXCEPTION.getCode());
			res.setMessage(CodeTable.EXCEPTION.getMessage());
			return res;
		}
	}

	@Override
	public Response<List<BankRo>> getBankList() {
		Response<List<BankRo>> res = new Response<List<BankRo>>();
		try{
			BatchResult<BankRo> result = storeFacade.getBankList();
			if(result.isSuccess()){
				res.setCode(CodeTable.SUCCESS.getCode());
				res.setMessage(CodeTable.SUCCESS.getMessage());
				List<BankRo> bank = result.getData();
				res.setData(bank);
				return res;
			}else{
				res.setCode(result.getCode());
				res.setMessage(result.getError());
				return res;
			}
		}catch(Exception e){
			log.error("调用crm查询银行列表异常",e);
			res.setCode(CodeTable.EXCEPTION.getCode());
			res.setMessage(CodeTable.EXCEPTION.getMessage());
			return res;
		}
		
	}

	@Override
	public Response<List<CodeDescRo>> getProvinceorCity(String code) {
		Response<List<CodeDescRo>> res = new Response<List<CodeDescRo>>();
		try{
			BatchResult<CodeDescRo> result = storeFacade.getProvinceOrCityList(code);
			if(result.isSuccess()){
				res.setCode(CodeTable.SUCCESS.getCode());
				res.setMessage(CodeTable.SUCCESS.getMessage());
				List<CodeDescRo> list = result.getData();
				res.setData(list);
				return res;
			}else{
				res.setCode(result.getCode());
				res.setMessage(result.getError());
				return res;
			}
		}catch(Exception e){
			log.error("调用crm查询省或市列表异常",e);
			res.setCode(CodeTable.EXCEPTION.getCode());
			res.setMessage(CodeTable.EXCEPTION.getMessage());
			return res;
		}
	}

}
