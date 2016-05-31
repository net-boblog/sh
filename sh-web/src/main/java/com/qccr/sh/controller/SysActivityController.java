/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */

package com.qccr.sh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qccr.datacenter.facade.service.AreaServiceFacade;
import com.qccr.datacenter.facade.service.ro.AreaRO;
import com.qccr.knife.result.Result;
import com.qccr.marketcenter.facade.constant.promotion.PromotionStatusConstants;
import com.qccr.marketcenter.facade.ro.PagedDataRO;
import com.qccr.marketcenter.facade.ro.promotion.*;
import com.qccr.marketcenter.facade.service.promotion.ServicePromotionFacade;
import com.qccr.sh.external.crm.ProductExt;
import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.external.marketcenter.ActPromotionExt;
import com.qccr.sh.external.marketcenter.bo.*;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.product.entity.ProductRo;
import com.toowell.crm.facade.product.service.ProductFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 超人活动相关页面功能
 *
 * @author zhangzhonghua
 * @version 2016/2/29 18:34.
 */
// TODO: 2016/3/2 待外部接口开放时，去掉注释 
@Controller
@RequestMapping("/page/activity")
public class SysActivityController extends BaseModule{
    private static final Logger log = LoggerFactory.getLogger(SysActivityController.class);

    @Resource(name="servicePromotionFacade")
    private ServicePromotionFacade servicePromotionFacade;
    @Autowired
    private ActPromotionExt actPromotionExt;

    @Resource(name="productFacade")
    private ProductFacade productFacade;

    @Resource(name="areaServiceFacade")
    private AreaServiceFacade areaServiceFacade;

    @Autowired
    private StoreAccountExt storeAccountExt;

    @Autowired
    private ProductExt productExt;

    /**  
     * 查询商户归属地区的所有有效平台活动
     *
     * @param
     * @param
     * @return
     * @author:zhangzhonghua 2016/2/29 18:52
     */
    @RequestMapping(value = "/list")
    public ModelAndView getActList(
            @RequestParam(defaultValue="0",required=false)int page_offset,
            @RequestParam(defaultValue="5",required=false)int page_limit,
            @RequestParam(defaultValue="",required=false)String act_name,
            @RequestParam(defaultValue="",required=false)String first_service,
            @RequestParam(defaultValue="",required=false)String second_service,
            @RequestParam(defaultValue="",required=false)String date_start,
            @RequestParam(defaultValue="",required=false)String date_end,
            @RequestParam(defaultValue="",required=false)String act_state,
            @RequestParam(defaultValue="",required=false)String order_type) {

        Result<PagedDataRO<EffactivePlatformPromotionRO>>  result = null;
        List<EffactivePlatformPromotionRO> resultData = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("page_limit", page_limit);
        paramMap.put("page_offset", page_offset);
        paramMap.put("act_name", act_name);
        paramMap.put("act_state", act_state);
        paramMap.put("first_service", first_service);
        paramMap.put("second_service", second_service);
        paramMap.put("order_type", order_type);
        paramMap.put("date_start", date_start);
        paramMap.put("date_end", date_end);

        //图片访问地址
        paramMap.put("staticUrl", staticUrl1);

        try{
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                log.warn("SysActivityController.getActList:非登录用户不能访问。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询门店信息
            Response<StoreInfoBO> store =  storeAccountExt.getStoreInfo(user.getStoreId());
            if(store==null || !store.isSuccess() || store.getData() == null){
                log.warn("SysActivityController.getActList:查询门店信息失败。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询省份
            Result<AreaRO> province = areaServiceFacade.queryAreaById(store.getData().getProvinceCode());
            String provinceName = province.getData().getAreaName();

            //查询地市
            Result<AreaRO> city = areaServiceFacade.queryAreaById(store.getData().getCityCode());
            String cityName = city.getData().getAreaName();

            //设置查询条件
            PlatformPromotionQueryRO platformPromotionQueryRO = new PlatformPromotionQueryRO();

            platformPromotionQueryRO.setCityId(user.getCity());

            if(act_name!=null && act_name.length()>0){
                platformPromotionQueryRO.setName(act_name);
            }

            if(first_service!=null && first_service.length()>0 && !first_service.equals("0")){
                platformPromotionQueryRO.setFirstCategoryId(first_service);
            }

            if(second_service!=null && second_service.length()>0 && !second_service.equals("0")){
                platformPromotionQueryRO.setSecondCategoryId(second_service);
            }

            SimpleDateFormat sdf = null;
            if(date_start!=null && date_start.length()>7){
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                platformPromotionQueryRO.setPromotionTimeFrom(sdf.parse(date_start));
            }

            if(date_end!=null && date_end.length()>7){
                if(sdf==null){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                platformPromotionQueryRO.setPromotionTimeTo(sdf.parse(date_end));
            }

            if(act_state!=null && act_state.length()>0){
                platformPromotionQueryRO.setPromotionStatus(Integer.parseInt(act_state));
            }

            if(order_type!=null && order_type.length()>0){
                platformPromotionQueryRO.setOrderType(Integer.parseInt(order_type));

                //按活动时间排序，相反排序
                if(platformPromotionQueryRO.getOrderType()==2){
                    platformPromotionQueryRO.setOrderType(3);
                }else if(platformPromotionQueryRO.getOrderType()==3){
                    platformPromotionQueryRO.setOrderType(2);
                }
            }

            platformPromotionQueryRO.setPageNo(page_offset+1);
            platformPromotionQueryRO.setPageSize(page_limit);
            platformPromotionQueryRO.setStoreId(user.getStoreId());

            //查询商户归属地区的所有有效平台活动
            result = servicePromotionFacade.pageQueryPlatformPromotion(platformPromotionQueryRO);

            if(result!=null && result.isSuccess() && result.getData()!=null && result.getData().getResultList()!=null) {
                paramMap.put("total", result.getData().getTotalSize());

                resultData = result.getData().getResultList();
            }else{
                paramMap.put("total","0");
            }

            paramMap.put("province", provinceName);
            paramMap.put("city", cityName);

            //当前时间
            paramMap.put("nowTime", new Date());

        }catch(Exception e){
            log.error("SysActivityController.getActList:查询商户归属地区的所有有效平台活动异常", e);
            return new ModelAndView("/page/common/error.jsp");
        }

        if(resultData!=null) {
            return new ModelAndView("/page/activity/act-list.jsp")
                    .addObject("resultMap", resultData)
                    .addObject("paramMap", paramMap)
                    .addObject("auditMap", ActivityStatusEnum.values());
        }else{
            return new ModelAndView("/page/activity/act-list.jsp")
                    .addObject("paramMap", paramMap)
                    .addObject("auditMap", ActivityStatusEnum.values());
        }
    }

    /**  
     * 查询活动详情（未报名）
     *
     * @param request
     * @param
     * @return 
     * @author:zhangzhonghua 2016/2/29 20:08
     */
    @RequestMapping(value = "/detail")
    public ModelAndView getActDetail(
            @RequestParam String act_id,
            @RequestParam(defaultValue="0")String reapply,
            HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        Result<CompactPlatformPromotionRO> result = null; //活动明细
        String hasProduct="0"; //门店是否已签约活动关联的服务，0：无；1：有
        Integer actProdPrice=0;
        Integer merchantPrice=0; //商家补贴
        Integer promotionClearAmt =0; //商家结算价
        Integer promotionAmt = 0; //销售价

        try {
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                log.warn("SysActivityController.getActDetail:非登录用户不能访问。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询门店信息
            Response<StoreInfoBO> store =  storeAccountExt.getStoreInfo(user.getStoreId());
            if(store==null){
                log.warn("SysActivityController.getActDetail:查询门店信息失败。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询省份
            Result<AreaRO> province = areaServiceFacade.queryAreaById(store.getData().getProvinceCode());
            String provinceName = province.getData().getAreaName();

            //查询地市
            Result<AreaRO> city = areaServiceFacade.queryAreaById(store.getData().getCityCode());
            String cityName = city.getData().getAreaName();

            //超人活动明细查询（未报名）
            result = servicePromotionFacade.queryPlatformPromotionDetail(Long.parseLong(act_id), user.getStoreId());

            if(result!=null && result.isSuccess() && result.getData().getServicePromotionRO()!=null &&
                    result.getData().getPlatformPromotionRO()!=null) {
                //商家补贴
                merchantPrice = result.getData().getPlatformPromotionRO().getMerchantAllowanceGrade().get(0);
            }else{
                log.error("SysActivityController.getActDetail:查询商户归属地区的所有有效平台活动失败");
                return new ModelAndView("/page/common/error.jsp");
            }

            //获取商户已签约的服务类目，用于判定该门店是否已签约活动对应的服务
            Result<List<ProductRo>> products = productFacade.getProductsByStoreId(user.getStoreId());
            if(products!=null && products.getData().size()>0){
                for (ProductRo product:products.getData()) {
                    if(product.getSecondCategory().equals(
                            result.getData().getServicePromotionRO().getSecondCategoryId())){
                        hasProduct="1";
                    }
                }
            }

            if(hasProduct.equals("1")){
                //查询签约价
                Result<Double> actProd = productExt.getProductContractPrice(request.getRemoteUser(),
                        result.getData().getServicePromotionRO().getSecondCategoryId() );
                if(actProd!=null)
                    actProdPrice = Double.valueOf(actProd.getData().doubleValue()*100).intValue();
                else{
                    hasProduct="0"; //查不到签约价，当做该服务没签约
                }
            }

            //结算价=签约价-商户补贴价
            promotionClearAmt = actProdPrice - merchantPrice;
            //销售价=签约价-商户补贴价-平台补贴价
            promotionAmt = actProdPrice - merchantPrice -
                    result.getData().getPlatformPromotionRO().getPlatformAllowance();

            paramMap.put("hasProduct", hasProduct);
            paramMap.put("actProdPrice", actProdPrice);
            paramMap.put("province", provinceName);
            paramMap.put("city", cityName);

            paramMap.put("staticUrl", staticUrl1);
            paramMap.put("hasApply","0");
            paramMap.put("hasEnd","0");
            paramMap.put("nowTime", new Date());


            //针对已报名或报名截止活动，不需要再选择商家补贴和服务数量
            if(result.getData().getMerchantAllowance()!=null){
                //商家补贴
                merchantPrice = result.getData().getMerchantAllowance();
                //结算价
                promotionClearAmt = result.getData().getPromotionClearAmt();
                //销售价
                promotionAmt =result.getData().getPromotionAmt();
                paramMap.put("hasApply", "1");
            }else if(result.getData().getPlatformPromotionRO().
                    getEndRegistrationTime().getTime() < new Date().getTime()){ //报名已截止
                paramMap.put("hasEnd","1");
            }

            //金额不能小于0
            if(merchantPrice<0){
                merchantPrice = 0;
            }
            if(promotionClearAmt<0){
                promotionClearAmt =0;
            }
            if(promotionAmt<0){
                promotionAmt = 0;
            }

            paramMap.put("promotionClearAmt", promotionClearAmt);
            paramMap.put("promotionAmt", promotionAmt);
            paramMap.put("merchantPrice", merchantPrice);

            //如果是已报名，判断是否是审核失败引起，如果是，则需要支持再次报名
            //审核失败时，再次报名时需要显示审核失败原因
            if(result.getData().getServicePromotionRO().getEnrollPromotionStatus().intValue()==
                    ActivityStatusEnum.ENROLLED.getValue() || reapply.equals("1")){
                //查询审核状态
                Result<PromotionEnrollRO> applyResult = null; //活动明细
                applyResult = servicePromotionFacade.queryEnrollPromotionDetail(
                        Long.parseLong(act_id), user.getStoreId());

                if(applyResult!=null && applyResult.isSuccess() && applyResult.getData()!=null
                        && applyResult.getData().getPromotionStatus() != null
                        && applyResult.getData().getPromotionStatus().intValue()==
                            ActivityResultStatusEnum.APPROVE_FAILED.getValue() ){
                    reapply = "1";
                    if(applyResult.getData().getApprovaRemark()!=null &&
                            applyResult.getData().getApprovaRemark().length()>0)
                        paramMap.put("approveMsg", applyResult.getData().getApprovaRemark());
                }
            }

            paramMap.put("reapply", reapply);


            // TODO: 2016/3/1 等待产品提供活动协议内容

        }catch(Exception e){
            log.error("SysActivityController.getActDetail:查询商户归属地区的所有有效平台活动异常", e);
            return new ModelAndView("/page/common/error.jsp");
        }

        return new ModelAndView("/page/activity/act-apply.jsp")
                .addObject("servPromDetail", result.getData().getServicePromotionRO())
                .addObject("platPromDetail", result.getData().getPlatformPromotionRO())
                .addObject("paramMap", paramMap);
    }

    /**  
     * 报名超人活动
     *
     * @return
     * @author:zhangzhonghua 2016/2/29 20:10
     */
    @RequestMapping(value = "/apply")
    @ResponseBody
    public Map<String, Object> applyAct(
            @RequestParam(defaultValue="",required=true)String sale_number,
            @RequestParam(defaultValue="",required=true)String act_id,
            @RequestParam(defaultValue="",required=true)String prom_money,
            @RequestParam(defaultValue="",required=true)String sale_money,
            @RequestParam(defaultValue="",required=true)String clear_money) {

        JSONObject jsonObject = new JSONObject();

        try{
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                jsonObject.put("success",false);
                jsonObject.put("info","非登录用户不能访问");
                return jsonObject;
            }

            PromotionEnrollAddRO promotionEnrollAddRO = new PromotionEnrollAddRO();

            promotionEnrollAddRO.setStoreId(user.getStoreId());
            promotionEnrollAddRO.setSaleNumber(Integer.parseInt(sale_number));
            promotionEnrollAddRO.setPromotionId(Long.parseLong(act_id));
            promotionEnrollAddRO.setMerchantAllowance(Integer.parseInt(prom_money));
            promotionEnrollAddRO.setMerchantId(user.getId());
            promotionEnrollAddRO.setMerchantName(user.getUsername());
            promotionEnrollAddRO.setPromotionAmt(Double.valueOf(Double.parseDouble(sale_money)*100).intValue());
            promotionEnrollAddRO.setPromotionClearAmt(Double.valueOf(Double.parseDouble(clear_money)*100).intValue());
            promotionEnrollAddRO.setCityId(user.getCity());
            promotionEnrollAddRO.setProvinceId(user.getProvince());

            //获取门店信息
            Response<StoreInfoBO> storeInfo = storeAccountExt.getStoreInfo(user.getStoreId());
            if(storeInfo==null || storeInfo.getData()==null || storeInfo.getData().getStoreName()==null){
                jsonObject.put("success",false);
                jsonObject.put("info","报名活动失败");
                log.error("报名活动时查询门店信息失败");
                return jsonObject;
            }

            promotionEnrollAddRO.setStoreName(storeInfo.getData().getStoreName());

            //超人活动报名
            Result<Boolean> result =  servicePromotionFacade.enrollPlatformPromotion(promotionEnrollAddRO);

            if(result!=null && result.isSuccess()) {
                jsonObject.put("success", true);
                jsonObject.put("info", "报名成功");
            }else{
                if (result != null && result.getStateCode() != null){
                    if(result.getStateCode().getCode()==ActivityStateCode.REPEAT_REGISTER.getValue()) {
                        jsonObject.put("info", ActivityStateCode.REPEAT_REGISTER.getName());
                    }else if(result.getStateCode().getCode()==ActivityStateCode.MERCHANT_HAS_BEEN_OFFLINED.getValue()){
                        jsonObject.put("info", ActivityStateCode.MERCHANT_HAS_BEEN_OFFLINED.getName());
                    }else{
                        jsonObject.put("info","报名失败");
                    }
                }
                jsonObject.put("success",false);
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","报名活动失败");
            log.error("报名活动时异常了",ex);
        }

        return jsonObject;
    }

    /**
     * 查询活动详情（已报名）
     *
     * @param request
     * @param
     * @return
     * @author:zhangzhonghua 2016/2/29 20:08
     */
    @RequestMapping(value = "/my-detail")
    public ModelAndView getActMyDetail(
            @RequestParam(defaultValue="",required=true)String act_id,
            @RequestParam(defaultValue="",required=false)String day_range,
            HttpServletRequest request ) {

        Result<PromotionEnrollRO> result = null; //活动明细
        Result<PromotionConsumeHistoryRO> resultStat = null; //销售统计
        AchievementAnalysisBo achievementAnalysis = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String actProdPrice="0";

        try {
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                log.warn("SysActivityController.getActMyDetail:非登录用户不能访问。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询门店信息
            Response<StoreInfoBO> store =  storeAccountExt.getStoreInfo(user.getStoreId());
            if(store==null){
                log.warn("SysActivityController.getActDetail:查询门店信息失败。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询省份
            Result<AreaRO> province = areaServiceFacade.queryAreaById(store.getData().getProvinceCode());
            String provinceName = province.getData().getAreaName();

            //查询地市
            Result<AreaRO> city = areaServiceFacade.queryAreaById(store.getData().getCityCode());
            String cityName = city.getData().getAreaName();

            //超人活动明细查询（已报名）
            result = servicePromotionFacade.queryEnrollPromotionDetail(Long.parseLong(act_id), user.getStoreId());

            if(result==null || result.isFailed()){
                log.error("SysActivityController.getActMyDetail:查询商户已报名活动详情失败");
                return new ModelAndView("/page/common/error.jsp");
            }

            //查询签约价
            Result<Double> actProd = productExt.getProductContractPrice(request.getRemoteUser(),
                    result.getData().getSecondCategoryId() );
            if(actProd!=null)
                actProdPrice = String.valueOf(Double.valueOf(actProd.getData().doubleValue()*100).intValue());

            //获取活动协议
            // TODO: 2016/3/1 后续可能需要通过CRM提供活动协议内容

            //活动开始时间<当前时间， 且活动状态已开始 活动开始需要统计销售情况
            if(result.getData().getStartTime().getTime()<new Date().getTime() &&
                    (result.getData().getPromotionStatus().intValue()==ActivityResultStatusEnum.STARTED.getValue() ||
                            result.getData().getPromotionStatus().intValue()==ActivityResultStatusEnum.END.getValue()) ){

                resultStat =  servicePromotionFacade.queryPromotionConsumeHistory(
                        Long.parseLong(act_id),
                        result.getData().getProductId(),
                        Integer.parseInt(day_range));

                if(resultStat!=null) {
                    achievementAnalysis = getActStat(resultStat,Integer.parseInt(day_range));
                }

                paramMap.put("hasStat", "1");
            }else{
                paramMap.put("hasStat", "0");
            }

            paramMap.put("staticUrl", staticUrl1);
            paramMap.put("day_range", day_range);
            paramMap.put("act_id", act_id);
            paramMap.put("province_name", provinceName);
            paramMap.put("city_name", cityName);

        }catch(Exception e){
            log.error("SysActivityController.getActMyDetail:查询商户已报名活动详情异常", e);
            return new ModelAndView("/page/common/error.jsp");
        }

        if(achievementAnalysis==null) {
            return new ModelAndView("/page/activity/act-my-detail.jsp")
                    .addObject("detail", result.getData())
                    .addObject("prodPrice", actProdPrice)
                    .addObject("paramMap", paramMap);

        }else{
            return new ModelAndView("/page/activity/act-my-detail.jsp")
                    .addObject("detail", result.getData())
                    .addObject("prodPrice", actProdPrice)
                    .addObject("analysis", JSON.toJSONString(achievementAnalysis))
                    .addObject("stat", resultStat.getData())
                    .addObject("paramMap", paramMap);
        }
    }

    /**
     * 查询活动详情（已报名）
     *
     * @param request
     * @return
     * @author:zhangzhonghua 2016/2/29 20:08
     */
    @RequestMapping(value = "/to-edit")
    public ModelAndView toEdit(
            @RequestParam(defaultValue="",required=true)String act_id,
            HttpServletRequest request ) {

        Result<PromotionEnrollRO> result = null; //活动明细

        String actProdPrice="0";

        try {
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                log.warn("SysActivityController.toEdit:非登录用户不能访问。");
                return new ModelAndView("/page/common/error.jsp");
            }

            //超人活动明细查询（已报名）
            result = servicePromotionFacade.queryEnrollPromotionDetail(Long.parseLong(act_id), user.getStoreId());

            //查询签约价
            Result<Double> actProd = productExt.getProductContractPrice(request.getRemoteUser(),
                    result.getData().getSecondCategoryId() );

            if(actProd!=null)
                actProdPrice = String.valueOf(Double.valueOf(actProd.getData().doubleValue()*100).intValue());

            //获取活动协议
            // TODO: 2016/3/1 等待产品提供活动协议内容

        }catch(Exception e){
            log.error("SysActivityController.toEdit:查询商户已报名活动详情异常", e);
            return new ModelAndView("/page/common/error.jsp");
        }

        return new ModelAndView("/page/activity/act-edit.jsp")
                .addObject("detail", result.getData())
                .addObject("prodPrice", actProdPrice);
    }

    /**
     * 指向列表页面
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView page(Model model){
        //活动状态列表
        model.addAttribute("statusSelect", ActivityResultStatusEnum.values());
        return new ModelAndView("/page/activity/act-my-list.jsp");
    }

    /**
     * 我报名的超人活动列表查询
     *
     * @return
     * @author:zhangzhonghua 2016/2/29 20:08
     */
    @RequestMapping(value = "/my-list")
    @ResponseBody
    public Map<String, Object> getMyActList(
            @RequestParam(defaultValue="0",required=false)int page_offset,
            @RequestParam(defaultValue="5",required=false)int page_limit,
            @RequestParam(defaultValue="",required=false)String act_name,
            @RequestParam(defaultValue="",required=false)String first_service,
            @RequestParam(defaultValue="",required=false)String second_service,
            @RequestParam(defaultValue="",required=false)String date_start,
            @RequestParam(defaultValue="",required=false)String date_end,
            @RequestParam(defaultValue="",required=false)String act_state) {

        Result<Pagination<ActPromotionBO>> result = null;
        ActPromotionQuery actPromotionQuery = new ActPromotionQuery();
        JSONObject jsonObject = new JSONObject();

        actPromotionQuery.setPageNo(page_offset);
        actPromotionQuery.setPageSize(page_limit);

        try {
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if (user == null) {
                log.warn("SysActivityController.getMyActList:非登录用户不能访问。");
                jsonObject.put("success",false);
                jsonObject.put("total", "0");
                jsonObject.put("info","非登录用户不能访问");
                return jsonObject;
            }

            actPromotionQuery.setStoreId(user.getStoreId());

            if(act_name!=null && act_name.length()>0){
                actPromotionQuery.setName(act_name);
            }

            if(first_service!=null && first_service.length()>0){
                actPromotionQuery.setFirstCategoryId(first_service);
            }

            if(second_service!=null && second_service.length()>0){
                actPromotionQuery.setSecondCategoryId(second_service);
            }

            SimpleDateFormat sdf = null;
            if(date_start!=null && date_start.length()>7){
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                actPromotionQuery.setPromotionTimeFrom(sdf.parse(date_start));
            }

            if(date_end!=null && date_end.length()>7){
                if(sdf==null){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                actPromotionQuery.setPromotionTimeTo(sdf.parse(date_end));
            }

            if(act_state!=null && act_state.length()>0){
                actPromotionQuery.setEnrollPromotionStatus(Integer.parseInt(act_state));
            }

            //我报名的超人活动列表查询
            result = actPromotionExt.pageQueryEnrollPlatformPromotion(actPromotionQuery);

            if(result!=null && result.isSuccess() && result.getData()!=null){
                jsonObject.put("data", result.getData().getDataList());
                jsonObject.put("total", result.getData().getTotalCount());
                jsonObject.put("success",true);
            }else{
                jsonObject.put("success",false);
                jsonObject.put("total", "0");
                jsonObject.put("info","查不到商户已报名活动");
            }

        }catch(Exception e){
            log.error("SysActivityController.getMyActList:查询商户已报名活动列表异常", e);
            jsonObject.put("success",false);
            jsonObject.put("total", "0");
            jsonObject.put("info","查询商户已报名活动列表异常");
            return jsonObject;
        }

        return jsonObject;
    }

    /**
     * 报名截止前，编辑超人活动
     *
     * @return
     * @author:zhangzhonghua 2016/3/1 15:34
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Map<String, Object> editAct(
            @RequestParam(defaultValue="",required=true)String sale_number,
            @RequestParam(defaultValue="",required=true)String act_id,
            @RequestParam(defaultValue="",required=true)String prom_money,
            HttpServletRequest request ) {

        Result<PromotionEnrollRO> resultProm = null; //活动明细

        JSONObject jsonObject = new JSONObject();
        Integer actProdPrice = 0;

        try{
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                jsonObject.put("success",false);
                jsonObject.put("info","非登录用户不能访问");
                return jsonObject;
            }

            //超人活动明细查询（已报名）
            resultProm = servicePromotionFacade.queryEnrollPromotionDetail(Long.parseLong(act_id), user.getStoreId());

            if(resultProm==null || resultProm.isFailed()){
                jsonObject.put("success",false);
                jsonObject.put("info","编辑报名信息失败");

                return jsonObject;
            }

            //查询签约价
            Result<Double> actProd = productExt.getProductContractPrice(request.getRemoteUser(),
                    resultProm.getData().getSecondCategoryId() );
            if(actProd!=null)
                actProdPrice = Double.valueOf(actProd.getData().doubleValue()*100).intValue();

            PromotionEnrollUpdateRO promotionEnrollUpdateRO = new PromotionEnrollUpdateRO();

            //计算销售价=签约价-平台补贴-门店铺贴
            promotionEnrollUpdateRO.setPromotionAmt(actProdPrice-Integer.valueOf(prom_money)-
                resultProm.getData().getPlatformAllowance());
            if(promotionEnrollUpdateRO.getPromotionAmt().intValue()<0){
                promotionEnrollUpdateRO.setPromotionAmt(0);
            }

            promotionEnrollUpdateRO.setMerchantAllowance(Integer.valueOf(prom_money));
            promotionEnrollUpdateRO.setPromotionId(Long.parseLong(act_id));
            promotionEnrollUpdateRO.setSaleNumber(Integer.parseInt(sale_number));
            promotionEnrollUpdateRO.setStoreId(user.getStoreId());

            //超人活动编辑
            Result<Boolean> result =
                    servicePromotionFacade.updateEnrollPlatformPromotion(promotionEnrollUpdateRO);

            if(result!=null && result.isSuccess()) {
                jsonObject.put("success", true);
                jsonObject.put("info", "编辑报名信息成功");
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","编辑报名信息失败");
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","编辑超人活动时异常了");
            log.error("编辑超人活动时异常了",ex);
        }

        return jsonObject;
    }

    /**  
     * 活动开始前，取消超人活动
     *
     * @param
     * @return 
     * @author:zhangzhonghua 2016/3/1 15:34
     */
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public Map<String, Object> cancelAct(
            @RequestParam(defaultValue="",required=true)String act_id) {

        JSONObject jsonObject = new JSONObject();

        try{
            //获取登录用户信息
            UserMerchantBO user = super.getLoginUser();
            if(user==null){
                jsonObject.put("success",false);
                jsonObject.put("info","非登录用户不能访问");
                return jsonObject;
            }

            //超人活动取消
            Result<Boolean> result =  servicePromotionFacade.quitPlatformPromotion(
                    Long.parseLong(act_id), user.getStoreId());

            if(result!=null && result.isSuccess()) {
                jsonObject.put("success", true);
                jsonObject.put("info", "退出成功");
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","退出失败");
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","退出失败");
            log.error("退出超人活动时异常了",ex);
        }

        return jsonObject;
    }

    /**  
     * 设置近期销售统计数据，便于前端展现
     *
     * @param
     * @return 
     * @author:zhangzhonghua 2016/3/2 18:51
     */
    private AchievementAnalysisBo getActStat(Result<PromotionConsumeHistoryRO> statHistorys, int dayRange){

        AchievementAnalysisBo achievementAnalysisBo = new AchievementAnalysisBo();
        List<StoreAchievementAnalysisBo> storeAchievementAnalysisList = new ArrayList();

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -dayRange);

        //无销售数据，显示0
        if(statHistorys.getData()==null || statHistorys.getData().getDailyPromotionConsumeROs()==null ||
            statHistorys.getData().getDailyPromotionConsumeROs().size()<=0){

            for (int i = 0; i < dayRange ; i++) {
                StoreAchievementAnalysisBo analys = new StoreAchievementAnalysisBo();
                cal.add(Calendar.DAY_OF_MONTH, 1);
                analys.setDate_time(cal.getTime());
                analys.setNomalCount(0);
                analys.setProfit(0.0);
                storeAchievementAnalysisList.add(analys);
            }
        }else {
            Calendar recordCal = Calendar.getInstance();

            for (int i = 0; i < dayRange ; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                StoreAchievementAnalysisBo analys = null;

                for (DailyPromotionConsumeRO item : statHistorys.getData().getDailyPromotionConsumeROs()) {

                    recordCal.setTime(item.getRecordDate());
                    if(cal.get(Calendar.YEAR)==recordCal.get(Calendar.YEAR) &&
                            cal.get(Calendar.MONTH) == recordCal.get(Calendar.MONTH) &&
                            cal.get(Calendar.DAY_OF_MONTH) == recordCal.get(Calendar.DAY_OF_MONTH)){

                        analys = new StoreAchievementAnalysisBo();
                        analys.setDate_time(item.getRecordDate());
                        analys.setNomalCount(item.getDailySoldNumber());
                        analys.setProfit(item.getDailyIncome() / 100.0);
                        storeAchievementAnalysisList.add(analys);

                        break;
                    }
                }

                if(analys==null){
                    analys = new StoreAchievementAnalysisBo();
                    analys.setDate_time(cal.getTime());
                    analys.setNomalCount(0);
                    analys.setProfit(0.0);
                    storeAchievementAnalysisList.add(analys);
                }
            }
        }

        achievementAnalysisBo.setStoreAchievementAnalysisList(storeAchievementAnalysisList);

        return achievementAnalysisBo;
    }

    private Result<CompactPlatformPromotionRO> genMyActDetail(){
        Result<CompactPlatformPromotionRO> result = new Result<>();
        CompactPlatformPromotionRO item = new CompactPlatformPromotionRO();

        item.setName("年底大促销-精洗大促销");
        item.setApplyNumber(10);
        item.setCycle("7:1");
        Calendar c = Calendar.getInstance();
        item.setStartTime(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 1);
        item.setEndTime(c.getTime());

        item.setPromotionId(Long.valueOf(10));
        item.setEnrollPromotionStatus(PromotionStatusConstants.STARTED);
        item.setSecondCategoryName("普通洗车");
        item.setSecondCategoryId("1");

        List<Integer> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(3));
        item.setMerchantAllowanceGrade(list);
        item.setPlatformAllowance(Integer.valueOf(0));
        item.setMerchantAllowance(Integer.valueOf(0));
        item.setSaleNumber(Integer.valueOf(0));
        item.setSaleMinNumber(Integer.valueOf(0));


        result.setData(item);

        return result;
    }
}
