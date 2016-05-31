package com.qccr.sh.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.ConfigBiz;

@Controller
@RequestMapping("/page/jump")
public class JumpToSShopController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ConfigBiz configBiz;
	@Autowired
	private UserMerchantExt userMerchantExt;
	
	@RequestMapping(value = "/toSShop")
	@ResponseBody
	public Map<String, Object> toSShop(HttpServletRequest request) {
		try {
			JSONObject jsonObject = new JSONObject();
			String path = configBiz.getValue("sshop_url");
			Map<String,Object> map = new HashMap<String,Object>();

			String userName = request.getRemoteUser();
			Result<UserMerchantBO> userMerchantBOResult = userMerchantExt.queryByUsername(userName);
			if(userMerchantBOResult!=null && userMerchantBOResult.isSuccess() && userMerchantBOResult.getData()!=null){
				UserMerchantBO userMerchantBO = userMerchantBOResult.getData();
				if(!userMerchantBO.isWholeseler()){
					path += "/account/toApply.s?haveStore=1";
				}
			}
			map.put("url", path);
			jsonObject.put("flag", 1);
			jsonObject.put("info", "success");
			jsonObject.put("data", map);	
		
			return jsonObject;
		} catch (Exception e) {
			log.error("发生异常",e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", 0);
			jsonObject.put("info", "内部服务异常");
			return jsonObject;
		}
	}
	
}
