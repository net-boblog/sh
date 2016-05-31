package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.knife.result.Result;
import com.qccr.sh.biz.OrderQueryBiz;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.orderCenter.LogisticsExt;
import com.qccr.sh.external.orderCenter.OrderExt;
import com.qccr.sh.external.orderCenter.bo.LogisticsBO;
import com.qccr.sh.external.orderCenter.bo.LogisticsDetailBO;
import com.qccr.sh.external.orderCenter.bo.LogisticsKD100VOBO;
import com.qccr.sh.external.orderCenter.bo.OrderBO;
import com.qccr.sh.external.orderCenter.bo.OrderQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.towell.carman.common.StateCode;
import com.towell.carman.service.order.OrderService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 代收货订单查询控制器
 * @author zhiwen.li
 */
@Controller
@RequestMapping(value = "/page/order")
public class OrderQueryController extends BaseModule{

	private static final Logger log = LoggerFactory.getLogger(OrderQueryController.class);

	@Autowired
	private OrderQueryBiz orderQueryBiz;

	@Resource(name="orderService")
	private OrderService orderService;

	@Autowired
	private UserBiz userBiz;

	@Autowired
	private OrderExt orderExt;

	@Resource(name="logisticsExt")
	private LogisticsExt logisticsExt;


	private static final Integer STATUS_0 = 0;
	private static final Integer STATUS_1 = 1;
	private static final Integer STATUS_2 = 2;

	/**
	 * 指向列表页面
	 * @param status  订单状态
	 * @param orderCode 订单号
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView queryServerOrderList(@RequestParam(defaultValue="",required=false)String orderCode){
		Map<Integer, String> statusMap = new HashMap<Integer, String>();
		statusMap.put(STATUS_0, "全部");
		statusMap.put(STATUS_1, "待收货");
		statusMap.put(STATUS_2, "待服务");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderCode", orderCode);
		paramMap.put("statusMap", statusMap);
		return new ModelAndView("/page/order/order_list.jsp")
				.addObject("paramMap", paramMap);
	}

	/**
	 * 查询代收订单列表
	 * @param pageStart  记录偏移
	 * @param pageSize   查询记录数
	 * @param status     状态
	 * @param orderCode  订单号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deliveryOrderList")
	@ResponseBody
	public Map<String, Object> deliveryOrderList(@RequestParam(defaultValue = "0", required = false) int pageStart,
									             @RequestParam(defaultValue = "20", required = false) int pageSize,
												 @RequestParam(defaultValue="0",required=false)Integer status,
												 @RequestParam(defaultValue="",required=false)String orderCode,
												 HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try{
			Integer storeId = userBiz.getStoreId(request.getRemoteUser());

			//查询代收订单列表
			OrderQuery orderQuery = new OrderQuery();
			orderQuery.setPageNumber(pageStart);
			orderQuery.setPageSize(pageSize);
			orderQuery.setAddressId(new Long(storeId));
			orderQuery.setStoreId(storeId);
			orderQuery.setType(new Long(status));
			orderQuery.setNo(orderCode);
			Response<Pagination<OrderBO>> orderPageResponse = orderExt.queryDeliveryOrderList(orderQuery);

			if(orderPageResponse!=null && orderPageResponse.isSuccess() && orderPageResponse.getData()!=null){
				Pagination<OrderBO> pagination = orderPageResponse.getData();
				jsonObject.put("data", pagination.getDataList());
				jsonObject.put("total", pagination.getTotalCount());
				jsonObject.put("success",true);
			}else{
				jsonObject.put("success",false);
				jsonObject.put("info","查询代收订单列表失败");
			}
		}catch (Exception ex){
			jsonObject.put("success",false);
			jsonObject.put("info","查询代收订单列表时异常了");
			log.error("查询代收订单列表时异常了",ex);
		}
		return jsonObject;
	}

	@RequestMapping("order_detail")
	public ModelAndView orderDetail(@RequestParam(required=true)Long orderId){
		if(orderId==null) return new ModelAndView("/page/common/error.jsp");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserMerchantBO user = super.getLoginUser();
		try{
			//查询代收订单箱
			OrderQuery orderQuery = new OrderQuery();
            orderQuery.setOrderId(orderId);
			orderQuery.setAddressId(new Long(user.getStoreId()));
			orderQuery.setStoreId(user.getStoreId());
			Response<OrderBO> orderPageResponse = orderExt.queryOrderDetail(orderQuery);
			if(orderPageResponse!=null && orderPageResponse.isSuccess() && orderPageResponse.getData()!=null){
				OrderBO orderBO = orderPageResponse.getData();
				resultMap.put("order", orderBO);
				resultMap.put("server", orderBO.getServerOrderBOList());
				resultMap.put("goods", orderBO.getGoodsOrderBOList());
				resultMap.put("salePrice", orderBO.getPrice());
				if(orderBO.getOrderStatus()==1){
					resultMap.put("statusName", "待收货");
				}else if(orderBO.getOrderStatus() == 2){
					resultMap.put("statusName", "待服务");
				}else if(orderBO.getOrderStatus() == 3){
					resultMap.put("statusName", "已完成");
				}else if(orderBO.getOrderStatus() == 4){
					resultMap.put("statusName", "改派到其他门店");
				}
				resultMap.put("status", orderBO.getStatus().intValue());
				resultMap.put("orderId", orderId);
				resultMap.put("rowNum", (orderBO.getGoodsOrderBOList() == null ? 0 : orderBO.getGoodsOrderBOList().size()) + (orderBO.getGoodsOrderBOList()==null?0:orderBO.getGoodsOrderBOList().size()));
				Response<LogisticsKD100VOBO> logisticsKD100VOBOResponse = logisticsExt.queryKuaiDi100Data(orderId);
				LogisticsKD100VOBO kuaiDi = new LogisticsKD100VOBO();
				if(logisticsKD100VOBOResponse!=null && logisticsKD100VOBOResponse.isSuccess() && logisticsKD100VOBOResponse.getData()!=null){
					kuaiDi = logisticsKD100VOBOResponse.getData();
					if(kuaiDi!=null && kuaiDi.getData()!=null && kuaiDi.getData().size()>0){
						Collections.sort(kuaiDi.getData(), new Comparator<LogisticsDetailBO>() {
							@Override
							public int compare(LogisticsDetailBO o1, LogisticsDetailBO o2) {
								return o1.getTime().compareTo(o2.getTime());
							}
						});
					}
				}
				resultMap.put("kuaiDi",kuaiDi);
				Response<LogisticsBO> logisticsBOResponse = logisticsExt.queryByOrderId(orderId);
				LogisticsBO logisticsBO = new LogisticsBO();
				if(logisticsBOResponse!=null && logisticsBOResponse.isSuccess() && logisticsBOResponse.getData()!=null){
					logisticsBO = logisticsBOResponse.getData();
				}
				resultMap.put("logistics", logisticsBO);
			}else{
				return new ModelAndView("/page/common/error.jsp");
			}
		}catch (Exception ex){
			log.error("查询待验证订单详情异常了",ex);
			return new ModelAndView("/page/common/error.jsp");
		}
		return new ModelAndView("/page/order/order_detail.jsp").addObject("resultMap", resultMap);
	}

	/**
	 * 确认收货订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/orderConfirm")
	public @ResponseBody JSONObject orderConfirm(
			@RequestParam(required=true)Long orderId
	){
		JSONObject json = new JSONObject();
		json.put("success", false);
		if(orderId==null){
			json.put("msg", "订单ID不能为空");
			return json;
		}
		UserMerchantBO user = super.getLoginUser();
		Result<OrderBO> boResult = orderExt.queryByOrderId(orderId);
		if(boResult!=null && boResult.isSuccess() && boResult.getData()!=null && StringUtils.isNotEmpty(boResult.getData().getAddressId()+"")
				&& boResult.getData().getAddressId().intValue() == user.getStoreId()){
			StateCode code =   orderService.merchantInsteadConfirm(orderId, user.getStoreId());
			if(code!=null && code.getCode()==0){
				json.put("success", true);
			}else{
				json.put("msg", code==null?"确认收货失败：接口调用异常":"确认收货失败："+code.getDescription());
			}
		}else{
			json.put("msg", "确认收货失败：订单不存在");
			return json;
		}
		return json;
	}
}
