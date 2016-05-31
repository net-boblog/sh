package com.qccr.sh.biz;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.orderCenter.OrderExt;
import com.qccr.sh.external.orderCenter.ServerOrderExt;
import com.qccr.sh.external.orderCenter.bo.GoodsOrderBO;
import com.qccr.sh.external.orderCenter.bo.OrderBO;
import com.qccr.sh.external.orderCenter.bo.ServerOrderBO;
import com.qccr.sh.response.Response;
import com.qccr.sh.util.BT;
import com.towell.carman.service.order.UpdateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class VerificationBizImpl implements VerificationBiz {

	@Autowired
	private OrderExt orderExt;
	
	@Resource(name = "updateOrderService")
	UpdateOrderService updateOrderService;

	@Autowired
	private UserMerchantExt userMerchantExt;

	@Autowired
	private ServerOrderExt serverOrderExt;

	@Override
	public Map<String, Object> findOrder(String smsCode, String username) {

		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
		if(boResult==null || boResult.isFailed() || boResult.getData()==null){
			throw new RuntimeException("查询不到该账户！");
		}
		UserMerchantBO user = boResult.getData();
		if(user.getStoreId()==0){
			throw new RuntimeException("查询不到该账户关联的门店！");
		}

		String content = "";
		String userinfo = "";
		String server_type = "";
		String seller_note = "";
		Response<OrderBO> orderBOResponse = orderExt.queryOrderDetailBySmscodeAndStoreid(smsCode+"",new Long(user.getStoreId()));
		if(orderBOResponse==null){
			throw new RuntimeException("验证码不存在！");
		}else if(!orderBOResponse.isSuccess()){
			throw new RuntimeException(orderBOResponse.getMessage());
		}else{
			OrderBO orderBO = orderBOResponse.getData();
			Long orderGoodsId = new Long(0);
			for(ServerOrderBO serverOrderBO : orderBO.getServerOrderBOList()){
				if(serverOrderBO.getSmsCode()!=null && serverOrderBO.getSmsCode().equals(smsCode)){
					orderGoodsId = serverOrderBO.getGoodsOrderId();
					server_type = serverOrderBO.getServerName();
					break;
				}
			}
			if(orderGoodsId==null){  // 服务订单
				content = server_type;
			}else{  // 轮胎安装
				for(GoodsOrderBO goodsOrderBO : orderBO.getGoodsOrderBOList()){
					if(goodsOrderBO.getId().longValue()==orderGoodsId.longValue()){
						content = goodsOrderBO.getGoodsName() + " * " + goodsOrderBO.getSaleNum();
						break;
					}
				}
			}
			userinfo = (BT.isEmpty(orderBO.getBuyerName()) ? "" : orderBO.getBuyerName()) + " " + BT.hidePhone(orderBO.getBuyerPhone());
			seller_note = BT.isEmpty(orderBO.getSellerNote()) ? "" : orderBO.getSellerNote();
		}
		
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("content", content);
		rMap.put("userinfo", userinfo);
		rMap.put("server_type", server_type);
		rMap.put("seller_note", seller_note);
		
		return rMap;
	}

	@Override
	public boolean verification(String smsCode, String username) {
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
		if(boResult==null || boResult.isFailed() || boResult.getData()==null){
			throw new RuntimeException("查询不到该账户！");
		}
		UserMerchantBO user = boResult.getData();
		if(user.getStoreId()==0){
			throw new RuntimeException("查询不到该账户关联的门店！");
		}

		Long orderServerId = new Long(0);
		Response<OrderBO> orderBOResponse = orderExt.queryOrderDetailBySmscodeAndStoreid(smsCode+"",new Long(user.getStoreId()));
		if(orderBOResponse==null){
			throw new RuntimeException("验证码不存在！");
		}else if(!orderBOResponse.isSuccess()){
			throw new RuntimeException(orderBOResponse.getMessage());
		}else{
			OrderBO orderBO = orderBOResponse.getData();
			for(ServerOrderBO serverOrderBO : orderBO.getServerOrderBOList()){
				if(serverOrderBO.getSmsCode()!=null && serverOrderBO.getSmsCode().equals(smsCode)){
					orderServerId = serverOrderBO.getId();
					break;
				}
			}
		}
        Result<String> verifyResult = serverOrderExt.verifyInit(smsCode, orderServerId, user.getStoreId());
		if(verifyResult!=null && verifyResult.isSuccess()){
			return true;
		}else{
			throw new RuntimeException(verifyResult != null ? verifyResult.getStatusText() : "");
		}
	}

}
