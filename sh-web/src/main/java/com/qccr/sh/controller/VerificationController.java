package com.qccr.sh.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.UserTraceBiz;
import com.qccr.sh.biz.VerificationBiz;

/**
 * 
 * @author 钱丽朋 ~ 2015-4-8 14:23:03
 */
@Controller
@RequestMapping(value = "/page/validation")
public class VerificationController {

	private static final Logger log = LoggerFactory.getLogger(VerificationController.class);

	@Autowired
	private VerificationBiz verificationBiz;

	@Autowired
	UserTraceBiz userTraceBiz;

	@RequestMapping(value = "/query_order")
	public void getOrderInfo(HttpServletRequest request, HttpServletResponse response) {

		try {
			String sms_code = URLDecoder.decode(request.getParameter("sms_code"), "UTF-8");

			Map<String, Object> map = verificationBiz.findOrder(sms_code, request.getRemoteUser());

			log.info("查询参数：" + map);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "查询成功。");
			jsonObject.put("data", map);

			log.info("返回结果：" + jsonObject.toJSONString());

			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonObject.toString());
		} catch (Exception e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", e.getMessage());
			response.setCharacterEncoding("UTF-8");
			log.error(e.getMessage(),e);
			try {
				response.getWriter().print(jsonObject.toString());
			} catch ( Exception e1) {
				log.error(e1.getMessage(),e1);
			}
		}

	}

	@RequestMapping(value = "/verification")
	public void verification(HttpServletRequest request, HttpServletResponse response) {

		try {

			String sms_code = URLDecoder.decode(request.getParameter("sms_code"), "UTF-8");

			verificationBiz.verification(sms_code, request.getRemoteUser());

			log.info("验证核销码：" + sms_code);
			userTraceBiz.recordTrace(request.getRemoteUser(), "验证核销码：" + sms_code, request.getRemoteAddr());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "验证通过");

			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonObject.toString());

		} catch (Exception e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", e.getMessage());
			response.setCharacterEncoding("UTF-8");
			log.error(e.getMessage(),e);
			try {
				response.getWriter().print(jsonObject.toString());
			} catch (Exception e1) {
				log.error(e1.getMessage(),e1);
			}
		}
	}

}
