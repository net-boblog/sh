package com.qccr.sh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 商户服务 控制器
 * @author user
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.OrderQueryBiz;
import com.qccr.sh.biz.ShopServiceBizImpl;
import com.toowell.crm.facade.store.entity.CategoryRo;
@Controller
@RequestMapping("/page/storeServer")
public class StoreServerController extends BaseModule{
	
	private static final Logger log = LoggerFactory.getLogger(StoreServerController.class.getName());
	
	
	@Autowired
	private ShopServiceBizImpl ShopServiceBizImpl;
	

	@Autowired
	private OrderQueryBiz orderQueryBiz;
		
	/**
	 * 获取商户提供商的服务
	 * @param serverId 商户服务1级类目id ，可空
	 * @return
	 */
	@RequestMapping("/getServer")
	public @ResponseBody JSONObject getServer(@RequestParam(defaultValue="0",required=false) String serverId){
		JSONObject json = new JSONObject();
		try {
			if(serverId.equals("0")) serverId="";
		    List<CategoryRo> categorys = ShopServiceBizImpl.queryStoreService(super.getLoginUser().getStoreId(), serverId);
		    json.put("success", true); 
		    json.put("server", categorys);
		    
//		    Map<String, Object> searchMap = new HashMap<String, Object>();
//		    searchMap.put("storeId", super.getLoginUser().getStoreId());
//		    searchMap.put("serverId", serverId);
//		    List<Map<String, Object>> server = orderQueryBiz.queryStoreServer(searchMap);
//		    json.put("success", true); 
//		    json.put("server", server);
		} catch (Exception e) {
			log.error("StoreServerController.getServer:获取商户户提供服务异常", e);
			json.put("success", false);
			json.put("info", e.getMessage());
		}
	    return json;
	}
	
    
}
