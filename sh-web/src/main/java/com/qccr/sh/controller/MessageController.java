package com.qccr.sh.controller;

import java.util.List;

import javax.annotation.Resource;

import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.towell.carman.entity.message.Message;
import com.towell.carman.service.message.MessageService;

@Controller
@RequestMapping("/page/message")
public class MessageController extends BaseModule{
	
	Logger logger=LoggerFactory.getLogger(getClass());

	@Resource(name="messageService")
	private MessageService messageService;
	
	/**
	 * 分页获取数据
	 * */
	@RequestMapping("messageList")
	@ResponseBody
	public JSONObject messageList(@RequestParam(defaultValue="0",required=false)int pageStart,@RequestParam(defaultValue="10",required=false)int pageSize){
		JSONObject object=new JSONObject();
		UserMerchantBO user=super.getLoginUser();
		if(user!=null){
			try {
				List<Message> listMessage=messageService.queryByMyMessage(user.getId(), null, pageStart, pageSize);

				object.put("listMessage", listMessage);
			} catch (Exception e) {
				logger.error("get message list has a exception", e);
			}
		}else{
			logger.error("user is null");
		}
		return object;
	}
	
	/**
	 * 根据id获取单条信息
	 * @param messageId
	 * */
	@RequestMapping("message")
	@ResponseBody
	public JSONObject message(@RequestParam(defaultValue="0",required=false)int messageId){
		JSONObject object=new JSONObject();
		Message message=messageService.queryById(messageId); 
		object.put("message", message);
		return object;
	}
	
	
	/**
	 * 把该条信息标示为已读
	 * @param messageId
	 * */
	@RequestMapping("market")
	@ResponseBody
	public JSONObject market(@RequestParam(defaultValue="0",required=false)int messageId){
		JSONObject object=new JSONObject();
		UserMerchantBO user=super.getLoginUser();
		if(user!=null&&messageId!=0){
			try {
				messageService.markRead(messageId,user.getId());
				object.put("flag",true);
			} catch (Exception e) {
				logger.error("marked message is read failed",e );
				object.put("flag",false);
			}
		}else{
			logger.error("user is null");
			object.put("flag",false);
		}
		return object;
	}
	
	/**
	 * 获取页面
	 * */
	@RequestMapping("page")
	public String getPage(){
		return "/page/message/message.jsp";
	}
	
	/**
	 * 查询所有未读信息
	 * */
	@RequestMapping("unreadMessage")
	@ResponseBody
	public JSONObject getUnReadMessage(){
		JSONObject object=new JSONObject();
		UserMerchantBO user=super.getLoginUser();
		if(user!=null){
			try {
				int num=messageService.countByMyUnReadMessage(user.getId(), null, null);
				object.put("flag",true);
				object.put("info",num);
			} catch (Exception e) {
				logger.error("marked message is read failed", e);
				object.put("flag",false);
			}
		}else{
			logger.error("user is null");
			object.put("flag",false);
		}
		return object;
	}
	
	
	/**
	 * 查询所有信息总数
	 * */
	@RequestMapping("countMessage")
	@ResponseBody
	public JSONObject countMessage(){
		JSONObject object=new JSONObject();
		UserMerchantBO user=super.getLoginUser();
		if(user!=null){
			try {
				int num=messageService.countQueryByMyMessage(user.getId(), null);
				object.put("flag",true); 
				object.put("info",num);
			} catch (Exception e) {
				logger.error("marked message is read failed", e);
				object.put("flag",false);
			}
		}else{
			logger.error("user is null");
			object.put("flag",false);
		}
		return object;
	}
	
}
