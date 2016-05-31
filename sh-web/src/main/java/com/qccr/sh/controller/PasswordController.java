package com.qccr.sh.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.biz.UserTraceBiz;

/**
 * 
 * @author 钱丽朋 ~ 2015-4-8 14:23:03
 */
@Controller
@RequestMapping(value = "/page/login")
public class PasswordController extends  BaseModule {

	private static final Logger log = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	private UserBiz userBiz;

	@Autowired
	UserTraceBiz userTraceBiz;

	@Autowired
	private UserMerchantExt userMerchantExt;

	@RequestMapping(value = "/modify_password")
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {

		try {
			String pwd = URLDecoder.decode(request.getParameter("pwd"), "UTF-8");
			String newpwd = URLDecoder.decode(request.getParameter("newpwd"), "UTF-8");

			userBiz.modifyPassword(request.getRemoteUser(), pwd, newpwd);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "修改成功。");

			log.info(request.getRemoteUser() + "修改了密码");
			userTraceBiz.recordTrace(request.getRemoteUser(), "修改了密码", request.getRemoteAddr());

			IOUtils.responsePrint(response, jsonObject.toString());
		} catch (Exception e) {
			log.error("调用密码修改接口PasswordController.modifyPassword异常", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", e.getMessage());
			IOUtils.responsePrint(response, jsonObject.toString());
		}

	}

	@RequestMapping(value = "/checksimple")
	public void queryFinance(HttpServletRequest request, HttpServletResponse response) {

		try {
			//根据用户名查门店用户
			Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(request.getRemoteUser());
			if(boResult==null || boResult.isFailed() || boResult.getData()==null || boResult.getData().getId()==0){
				throw new RuntimeException("用户不存在！");
			}
			UserMerchantBO user = boResult.getData();

			if ("e10adc3949ba59abbe56e057f20f883e".equals(user.getPassword())) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("flag", "0");
				jsonObject.put("info", "您的密码过于简单，必须修改密码！");

				IOUtils.responsePrint(response, jsonObject.toString());

			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("flag", "1");
				jsonObject.put("info", "密码够复杂！");

				IOUtils.responsePrint(response, jsonObject.toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("检查简单密码时异常了,",e);
		}

	}

	@RequestMapping(value = "/modifyPasswordPage")
	public String modifyPasswordPage(Model model){
		model.addAttribute("useHttpsUrl",useHttpsUrl);
		return "modify_password.jsp";
	}

}
