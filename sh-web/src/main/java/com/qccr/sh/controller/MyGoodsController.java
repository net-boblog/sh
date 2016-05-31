package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.knife.result.Result;
import com.qccr.sh.biz.MyGoodsBiz;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.enums.Constants;
import com.qccr.sh.external.crm.ProductExt;
import com.qccr.sh.external.crm.bo.ItemAttrBo;
import com.qccr.sh.external.crm.bo.ItemAttrValueBo;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.external.orderCenter.ServerOrderExt;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.IOUtils;
import com.toowell.crm.facade.product.entity.ItemAttrRo;
import com.toowell.crm.facade.product.entity.ItemAttrValueRo;
import com.toowell.crm.facade.product.entity.ItemCategoryRo;
import com.toowell.crm.facade.product.entity.ProductRo;
import com.toowell.crm.facade.product.service.ItemAttrManagerFacade;
import com.toowell.crm.facade.product.service.ItemCategoryFacade;
import com.toowell.crm.facade.product.service.ProductFacade;
import com.toowell.crm.facade.store.entity.StoreRo;
import com.toowell.crm.facade.store.service.StoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 钱丽朋 ~ 2015-4-8 14:23:03
 */
@Controller
@RequestMapping(value = "/page/goods")

public class MyGoodsController {
	private static final Logger log = LoggerFactory.getLogger(MyGoodsController.class.getName());
	@Autowired
	private MyGoodsBiz myGoodsBiz;
	@Autowired
	private ProductExt productExt;
	@Autowired
	private StoreFacade storeFacade;
	@Autowired
	private ProductFacade productFacade;
	@Autowired
	private ItemCategoryFacade itemCategoryFacade;
	@Autowired
	private ItemAttrManagerFacade itemAttrManagerFacade;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private ServerOrderExt serverOrderExt;

	//钣金油漆服务
	private String secondCategoryCode=Constants.SECOND_PAINT_CODE.getCode();//二级服务编码
	private String categoryCode=Constants.CATEGORY_CODE.getCode();//二级服务编码
	private String firstOrgCategoryCode=Constants.FIRST_ORG_CATEGORY_CODE.getCode();
	private String secondOrgCategoryCode=Constants.SECOND_ORG_CATEGORY_CODE.getCode();

	@RequestMapping(value = "/query")
	public void queryGoods(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<ProductBO> listResult = myGoodsBiz.queryGoodsByUsername(request.getRemoteUser());
			Map<String, String> auditMap = new LinkedHashMap<String, String>();
			Response<List<ProductBO>> list= serverOrderExt.countOrder(listResult);
			//已服务订单数 1上线 0 下线
			auditMap.put("1","待提交");
			auditMap.put("2", "审核中");//待审核
			auditMap.put("3", "审核中");
			auditMap.put("4", "在线");
			auditMap.put("5", "审核失败");
			auditMap.put("6", "审核中");// 编辑后审核
			auditMap.put("7", "审核失败");
			auditMap.put("0","下线");

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "查询成功");
			jsonObject.put("data", list.getData());
			jsonObject.put("audit",auditMap);
			IOUtils.responsePrint(response, jsonObject.toString());
		} catch (Exception e) {
			log.error("调用我的服务接口MyGoodsController.queryGoods异常", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", "查询失败！");
			IOUtils.responsePrint(response, jsonObject.toString());
		}
	}

	@RequestMapping("/createPage")
	public ModelAndView createPage( Model model){
		//钣金油漆一级分类code ACAF 二级分类code ACAFAB
		Result<ItemCategoryRo> itemCategoryRo=itemCategoryFacade.getItemCategoryByCode(secondCategoryCode);
		Result<List<ItemAttrRo>> listResult=itemAttrManagerFacade.getAttrAndValueByCategoryId(secondCategoryCode);
		if(itemCategoryRo !=null && itemCategoryRo.getData() !=null && listResult !=null && listResult.getData() !=null){
			String categoryName=itemCategoryRo.getData().getCategoryName();
			ItemAttrRo chandi =listResult.getData().get(0) ;//默认产地
			ItemAttrRo danwei=listResult.getData().get(1);//默认单位
			model.addAttribute("categoryName",categoryName);
			model.addAttribute("chandi",chandi.getItemAttrValues());
			model.addAttribute("danwei",danwei.getItemAttrValues());
		}
		return  new ModelAndView("/page/goods/create_goods.jsp");
	}

	@RequestMapping("/updatePage")
	public ModelAndView updatePage(HttpServletRequest request,Model model){
		String productId= request.getParameter("productId");
		if(StringUtils.isEmpty(productId)){
			return new ModelAndView("/page/goods/query.jsp");
		}
		try {
			Result<ProductRo> productRoResult= productFacade.getProductById(Integer.parseInt(productId));
			if(productRoResult!=null && productRoResult.isSuccess() &&productRoResult.getData()!=null){
				ProductRo productRo=productRoResult.getData();
				String categoryCode=productRo.getCategoryCode();
				Result<ItemCategoryRo> itemCategoryRo=itemCategoryFacade.getItemCategoryByCode(categoryCode);
				String categoryName=itemCategoryRo.getData().getCategoryName();
				double marketAmt=Double.valueOf(productRo.getMarketAmt()).doubleValue()/100;
				String skuId=productRo.getSkuId();
				Result<List<Map<String, Object>>> itemAttrResult= itemAttrManagerFacade.getAttrAndValueBySkuId(skuId);
				Map<String,Object> chandi=itemAttrResult.getData().get(0);//默认第一位
				Map<String,Object> danwei=itemAttrResult.getData().get(1);//默认第二位
				model.addAttribute("productRo",productRo);
				model.addAttribute("marketAmt",marketAmt);
				model.addAttribute("categoryName",categoryName);
				model.addAttribute("chandi",chandi.get("ATTR_VALUE"));
				model.addAttribute("danwei",danwei.get("ATTR_VALUE"));
			}else{
				return new ModelAndView("/page/goods/goods_query.jsp");
			}
		}catch (Exception ex){
            log.error("调用我的服务接口MyGoodsController.updatePage", ex);
			return new ModelAndView("/page/goods/goods_query.jsp");
		}
		return new ModelAndView("/page/goods/update_goods.jsp");
	}

	//ProductRo 服务名称productName,门店storeId,一级服务code firstCategory，二级服务code secondCategory,商家价格==销售价 saleAmt，折扣8.5 discount，itemType==2，categoryCode==ACAFAB
	//List<ItemAttrValueRo> itemAttrList 二级服务属性 ，平面/米分类
	@RequestMapping("/saveGoods")
	public @ResponseBody Response<String> saveGoods(HttpServletRequest request){

		String marketAmtStr=request.getParameter("marketAmt");
		if(marketAmtStr==null || marketAmtStr.trim().length()<=0){
			return  ResponseUtil.error("请填写门店服务价格！");
		}
		Double marketAmtDou=Double.valueOf(marketAmtStr);

		String discount=request.getParameter("discount");
		if(discount==null || discount.trim().length()<=0){
			return  ResponseUtil.error("请填写服务折扣价格！");
		}
		double discountDou=Double.valueOf(discount);
		if(discountDou<0.1 || discountDou>9.9){
			return  ResponseUtil.error("请正确填写服务折扣价格！");
		}

		String userName = request.getRemoteUser();
		Integer storeId = userBiz.getStoreId(userName);
		if(storeId == null){
			return  ResponseUtil.error("获取门店失败！");
		}

		//com.toowell.crm.facade.base.Result<StoreRo> resultStore = storeFacade.getStoreByStoreId(storeId+"", RemoteProjectEnum.SH);
		com.qccr.knife.result.Result<StoreRo> resultStore = storeFacade.getStoreById(storeId);
		if(!resultStore.isSuccess() || resultStore.getData()==null){
			return  ResponseUtil.error("查询不到该账户");
		}
		String belongUser =resultStore.getData().getBelongUser();

		ProductBO productBO=new ProductBO();

		productBO.setBelongUser(belongUser);
		productBO.setCreateUser(belongUser);

		String productName="钣金喷漆";
		String itemType="2";//服务+材料

		productBO.setItemType(itemType);
		productBO.setFirstCategory(firstOrgCategoryCode);
		productBO.setCategoryCode(categoryCode);
		productBO.setSecondCategory(secondOrgCategoryCode);

		//二级服务属性
		List<ItemAttrBo> itemAttrBoList=new LinkedList<ItemAttrBo>();
		List<ItemAttrValueBo> itemAttrList1=new LinkedList<ItemAttrValueBo>();
		List<ItemAttrValueBo> itemAttrList2=new LinkedList<ItemAttrValueBo>();

		//二级服务属性值

		String itemAttrValueId1=request.getParameter("itemAttrValueId1");
		if(itemAttrValueId1==null || itemAttrValueId1.trim().length()<=0){
			return  ResponseUtil.error("请正确填写参数！");
		}

		String itemAttrValueId2=request.getParameter("itemAttrValueId2");
		if(itemAttrValueId2==null || itemAttrValueId2.trim().length()<=0){
			return  ResponseUtil.error("请正确填写参数！");
		}

		//产地
		ItemAttrValueBo itemAttr1=new ItemAttrValueBo();
		//单位
		ItemAttrValueBo itemAttr2=new ItemAttrValueBo();

		//钣金油漆二级服务及其属性查询
		Result<ItemCategoryRo> itemCategoryRo=itemCategoryFacade.getItemCategoryByCode(secondCategoryCode);

		Result<List<ItemAttrRo>> listResult=itemAttrManagerFacade.getAttrAndValueByCategoryId(itemCategoryRo.getData().getCategoryCode());
		ItemAttrRo chandi =listResult.getData().get(0) ;//默认产地
		ItemAttrRo danwei=listResult.getData().get(1);//默认单位

		for(ItemAttrValueRo itemAttrValueRo : chandi.getItemAttrValues()){
			if(itemAttrValueId1.equals(itemAttrValueRo.getItemAttrValueId())){
				itemAttr1.setItemAttrId(itemAttrValueRo.getItemAttrId());
				itemAttr1.setItemAttrValueId(itemAttrValueId1);
				itemAttr1.setItemAttrValue(itemAttrValueRo.getItemAttrValue());
			}
		}

		for(ItemAttrValueRo itemAttrValueRo : danwei.getItemAttrValues()){
			if(itemAttrValueId2.equals(itemAttrValueRo.getItemAttrValueId())){
				itemAttr2.setItemAttrId(itemAttrValueRo.getItemAttrId());
				itemAttr2.setItemAttrValueId(itemAttrValueId2);
				itemAttr2.setItemAttrValue(itemAttrValueRo.getItemAttrValue());
			}
		}

		productName+="-"+itemAttr1.getItemAttrValue()+"-"+itemAttr2.getItemAttrValue();
		productBO.setProductName(productName);
		itemAttrList1.add(itemAttr1);
		itemAttrList2.add(itemAttr2);
		ItemAttrBo itemAttrBo1=new ItemAttrBo();
		ItemAttrBo itemAttrBo2=new ItemAttrBo();
		itemAttrBo1.setItemAttrValues(itemAttrList1);
		itemAttrBo2.setItemAttrValues(itemAttrList2);
		itemAttrBoList.add(itemAttrBo1);
		itemAttrBoList.add(itemAttrBo2);

		productBO.setStoreId(storeId+"");
		productBO.setMarketAmt(marketAmtDou);
		productBO.setDiscount(discount);
		productBO.setItemAttrList(itemAttrBoList);

		Result<String> result=productExt.addProduct(productBO);
		if(!result.isSuccess()){
			String errstr="";
			if(result.getStateCode().getCode()==-3000301){
				errstr="门店服务已存在,请重新编辑";
			}else if (result.getStateCode().getCode()==-3000302){
				errstr="门店服务重复,请重新编辑";
			}else if(result.getStateCode().getCode()==-3000303){
				errstr="当前服务正在审核,不能编辑！";
			}else{
				errstr=result.getStatusText();
			}
			return ResponseUtil.error(errstr);
		}
		return ResponseUtil.success();
	}

	@RequestMapping("/updateGood")
	public @ResponseBody Response<String> updateGood(HttpServletRequest request,ProductBO productBO){

		if(productBO==null || productBO.getProductId()==null){
			return  ResponseUtil.error("请正确填写参数！");
		}

		Double marketAmtDou=productBO.getMarketAmt();//单位元

		if(marketAmtDou==null || marketAmtDou <=0){
			return  ResponseUtil.error("请填写门店服务价格！");
		}

		String discount=request.getParameter("discount");
		if(discount==null || discount.trim().length()<=0){
			return  ResponseUtil.error("请填写服务折扣价格！");
		}

		double discountDou=Double.valueOf(discount);
		if(discountDou<0.1 || discountDou>9.9){
			return  ResponseUtil.error("请正确填写服务折扣价格！");
		}

		String userName = request.getRemoteUser();
		Integer storeId = userBiz.getStoreId(userName);
		if(storeId == null){
			return  ResponseUtil.error("获取门店失败！");
		}

		//com.toowell.crm.facade.base.Result<StoreRo> resultStore = storeFacade.getStoreByStoreId(storeId+"", RemoteProjectEnum.SH);
		com.qccr.knife.result.Result<StoreRo> resultStore = storeFacade.getStoreById(storeId);
		if(!resultStore.isSuccess() || resultStore.getData()==null){
			return  ResponseUtil.error("查询不到该账户");
		}
		String belongUser =resultStore.getData().getBelongUser();
		productBO.setUpdateUser(belongUser);

		Result<Integer> result=productExt.modifyProduct(productBO);
		if(!result.isSuccess()){
			String errstr="";
			if(result.getStateCode().getCode()==-3000301){
				errstr="门店服务已存在";
			}else if (result.getStateCode().getCode()==-3000302){
				errstr="门店服务重复";
			}else if(result.getStateCode().getCode()==-3000303){
				errstr="当前服务正在审核,不能编辑！";
			}else{
				errstr=result.getStatusText();
			}
			return ResponseUtil.error(errstr);
		}
		return  ResponseUtil.success();
	}


	@RequestMapping("/deleteGood")
	public @ResponseBody Response<String> deleteGood(HttpServletRequest request){

		String productId=request.getParameter("productId");

		if(productId==null || productId.equals("")){
			return  ResponseUtil.error("参数不正确！");
		}

		String userName = request.getRemoteUser();
		Integer storeId = userBiz.getStoreId(userName);
		if(storeId == null){
			return  ResponseUtil.error("获取门店失败！");
		}

		Result<Boolean> result=productExt.deleteByProductId(productId+"",storeId+"");
		if(!result.isSuccess()){
			return ResponseUtil.error(result.getStatusText());
		}
		return ResponseUtil.success();
	}
}
