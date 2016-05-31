package com.qccr.sh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.memberCenter.bo.UserMerchantLoginBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qccr.sh.entity.Admin;
import com.qccr.sh.util.RedisUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/anon/login")
public class LoginController extends BaseModule  {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMerchantExt userMerchantExt;

	
    @RequestMapping(value="/login",method=RequestMethod.GET)  
    public String loginForm(Model model){  
    	System.out.println("login");
		model.addAttribute("useHttpsUrl",useHttpsUrl);
        if (SecurityUtils.getSubject().getPrincipal() != null){
			return "redirect:/index.jsp";
		}
		return "login.jsp";
    }  

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin amdin,HttpServletRequest request,BindingResult bindingResult, ModelMap modelMap) {
		modelMap.addAttribute("useHttpsUrl",useHttpsUrl);
		try {
			if (bindingResult.hasErrors()) {
				return "/login";
			}
			// 使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
			UserMerchantLoginBO loginBO = new UserMerchantLoginBO();
			loginBO.setIp(request.getRemoteHost());
			loginBO.setLoginTime( new Timestamp(System.currentTimeMillis()));
			Result<UserMerchantBO> boResult = userMerchantExt.login(amdin.getUsername(), amdin.getPassword(), loginBO);
            if(boResult!=null && boResult.isSuccess() && boResult.getData()!=null && boResult.getData().getId()!=0){
				SecurityUtils.getSubject().login(new UsernamePasswordToken(amdin.getUsername(), "1"));   //登录成功
			}else{
				SecurityUtils.getSubject().login(new UsernamePasswordToken(amdin.getUsername(), "0"));  //登录失败
			}
			HttpSession session = request.getSession();
			if(session.getAttribute("redirectUrl")!=null){
				String url = session.getAttribute("redirectUrl").toString();
				url=url.replace("https","http");
				session.removeAttribute("redirectUrl");
				return "redirect:/page/jump/jumpToSShop.jsp?redirectUrl="+url;
			}else{
				return "redirect:/index.jsp";
			}
		} catch (AuthenticationException e) {
			/*redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/index.jsp";*/
			log.error("login failed", e);
			modelMap.addAttribute("message", "用户名密码不匹配！");
			return "login.jsp";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		RedisUtil.removeObject("kefu_"+request.getRemoteUser());
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/index.jsp";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "403.jsp";
	}

	@RequestMapping("/getHeadTitle")
	@ResponseBody
	public Map<String,Object> getHeadTitle(){
		Map<String,Object> res = new HashMap<String,Object>();
		if (SecurityUtils.getSubject()!= null && SecurityUtils.getSubject().getPrincipal() != null){
			String title = getLoginUser().getStoreName();
			res.put("success",true);
			res.put("loginName",title);
		}else{
			res.put("success",false);
		}
		return res;
	}
}
