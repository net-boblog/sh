/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.datacenter.facade.service.AreaServiceFacade;
import com.qccr.datacenter.facade.service.ro.AreaRO;
import com.qccr.knife.result.Result;
import com.qccr.marketcenter.facade.statecode.MarketcenterStateCode;
import com.qccr.sh.biz.ShopServiceBiz;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.crm.ProductExt;
import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.external.gotone.SmsServiceExt;
import com.qccr.sh.external.marketcenter.MerchantBuildPromotionExt;
import com.qccr.sh.external.marketcenter.bo.MerchantBuildPromotionBO;
import com.qccr.sh.external.marketcenter.bo.MerchantBuildPromotionQuery;
import com.qccr.sh.external.marketcenter.bo.MerchantPromotionAddBO;
import com.qccr.sh.external.marketcenter.bo.MerchantPromotionDetailBO;
import com.qccr.sh.external.marketcenter.bo.MerchantPromotionUpdateBO;
import com.qccr.sh.external.marketcenter.bo.PromotionConsumeHistoryBO;
import com.qccr.sh.external.marketcenter.bo.StoreActivityStatusEnum;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.util.Arith;
import com.toowell.crm.facade.store.entity.CategoryRo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动管理->我的活动相关
 * @author dongxuechai
 * @date 2016年2月29日 上午10:05
 */
@Controller
@RequestMapping("/page/myActivity")
public class MyActivityController extends BaseModule{
    private static final Logger log = LoggerFactory.getLogger(MyActivityController.class.getName());
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private MerchantBuildPromotionExt merchantBuildPromotionExt;
    @Autowired
    private SmsServiceExt smsServiceExt;
    @Autowired
    private UserMerchantExt userMerchantExt;
    @Autowired
    private ProductExt productExt;
    @Autowired
    private StoreAccountExt storeAccountExt;
    @Autowired
    private ShopServiceBiz shopServiceBiz;
    @Autowired
    private AreaServiceFacade areaServiceFacade;

    /**
     * 指向列表页面
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView page(Model model){
        //活动状态列表
        model.addAttribute("statusSelect", StoreActivityStatusEnum.values());
        return new ModelAndView("/page/myActivity/my_activity_list.jsp");
    }

    /**
     * 查询我发布的活动列表
     * @param pageOffset                    当前页
     * @param pageSize                      每页记录数
     * @param name                          活动名称
     * @param firstCategoryId               一级服务项目ID
     * @param secondCategoryId              二级服务项目ID
     * @param promotionTimeFrom             活动时间开始
     * @param promotionTimeTo               活动时间结束
     * @param buildPromotionStatusConstants 活动状态
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryActivityList(@RequestParam(defaultValue = "0", required = false)   int    pageOffset,
                                                 @RequestParam(defaultValue = "20", required = false)  int    pageSize,
                                                 @RequestParam(defaultValue = "", required = false) String    name,
                                                 @RequestParam(defaultValue = "", required = false) String    firstCategoryId,
                                                 @RequestParam(defaultValue = "", required = false) String    secondCategoryId,
                                                 @RequestParam(defaultValue = "", required = false) String    promotionTimeFrom,
                                                 @RequestParam(defaultValue = "", required = false) String    promotionTimeTo,
                                                 @RequestParam(defaultValue = "", required = false) String    buildPromotionStatusConstants){
        JSONObject jsonObject = new JSONObject();
        try{
            Integer storeId = userBiz.getStoreId(request.getRemoteUser());
            MerchantBuildPromotionQuery promotionQuery = new MerchantBuildPromotionQuery();
            promotionQuery.setPageNo(pageOffset + 1);
            promotionQuery.setPageSize(pageSize);
            promotionQuery.setName(name);
            if(firstCategoryId!=null&&!firstCategoryId.equals("")){
                promotionQuery.setFirstCategoryId(firstCategoryId);
            }
            if(secondCategoryId!=null&&!secondCategoryId.equals("")){
                promotionQuery.setSecondCategoryId(secondCategoryId);
            }
            Timestamp startTime = null;
            if(promotionTimeFrom!=null && !promotionTimeFrom.equals("")){
                startTime = Timestamp.valueOf(promotionTimeFrom+" 00:00:00");
            }
            //查询结束时间非空时往后延一天
            Timestamp endTime = null;
            if(promotionTimeTo!=null && !promotionTimeTo.equals("")){
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date sEndTime = df.parse(promotionTimeTo);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sEndTime);
                calendar.add(Calendar.DATE, 1);
                promotionTimeTo = df.format(calendar.getTime());
                endTime = Timestamp.valueOf(promotionTimeTo + " 00:00:00");
            }
            promotionQuery.setPromotionTimeFrom(startTime);
            promotionQuery.setPromotionTimeTo(endTime);
            if(buildPromotionStatusConstants!=null && !buildPromotionStatusConstants.equals("")){
                promotionQuery.setBuildPromotionStatusConstants(new Integer(buildPromotionStatusConstants));
            }
            promotionQuery.setStoreId(storeId);

            Result<Pagination<MerchantBuildPromotionBO>> result = merchantBuildPromotionExt.pageQueryMerchantPromotion(promotionQuery);
            if(result!=null && result.isSuccess() && result.getData()!=null){
                Pagination<MerchantBuildPromotionBO> pagination = result.getData();
                jsonObject.put("data", pagination.getDataList());
                jsonObject.put("total", pagination.getTotalCount());
                jsonObject.put("success",true);
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","查询我发布的活动列表失败");
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","查询我发布的活动列表时异常了");
            log.error("查询我发布的活动列表时异常了",ex);
        }
        return jsonObject;
    }
    /**
     * 下线我发布的活动
     * @param promotionId
     * @return
     */
    @RequestMapping("/offlineActivity")
    @ResponseBody
    public Map<String, Object> offlineActivity(@RequestParam(required = true)String promotionId)
    {
        JSONObject jsonObject = new JSONObject();
        try{
            Integer storeId = userBiz.getStoreId(request.getRemoteUser());
            Result<Boolean> result = merchantBuildPromotionExt.offlineMerchantPromotion(new Long(promotionId),storeId,request.getRemoteUser());
            if(result!=null && result.isSuccess() && result.getData()!=null && result.getData()==true){
                jsonObject.put("success",true);
                jsonObject.put("info",result.getStatusText());
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info", result == null ? "下线我发布的活动时异常了" : result.getStatusText());
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","下线我发布的活动时异常了");
            log.error("下线我发布的活动时异常了",ex);
        }
        return jsonObject;
    }

    /**
     * 指向编辑活动页面
     * @return
     */
    @RequestMapping("/editPage")
    public ModelAndView editPage(@RequestParam(required = true)Long promotionId, Model model){
        Result<MerchantPromotionDetailBO> detailBOResult = merchantBuildPromotionExt.queryMerchantPromotionDetail(promotionId);
        if(detailBOResult!=null && detailBOResult.isSuccess() && detailBOResult.getData()!=null){
            model.addAttribute("activity",detailBOResult.getData());
        }
        return new ModelAndView("/page/myActivity/edit_activity.jsp");
    }

    /**
     * 保存我发布的活动的修改
     * @param promotionId        活动ID
     * @param name               活动名称
     * @param startTime          活动开始时间
     * @param endTime            活动结束时间
     * @param firstCategoryId    一级类目ID
     * @param firstCategoryName  一级类目名称
     * @param secondCategoryId   二级类目ID
     * @param secondCategoryName 二级类目名称
     * @param saleNumber         销售数量
     * @param cycleDays          促销频次N天
     * @param cycleTimes         促销频次M次
     * @param merchantAllowance  商家补贴
     * @param request
     * @return
     */
    @RequestMapping("/updateActivity")
    @ResponseBody
    public Map<String, Object> updateActivity(@RequestParam(required = true)String promotionId,
                                              @RequestParam(required = true)String name,
                                              @RequestParam(required = true)String startTime,
                                              @RequestParam(required = true)String endTime,
                                              @RequestParam(required = true)String firstCategoryId,
                                              @RequestParam(required = true)String firstCategoryName,
                                              @RequestParam(required = true)String secondCategoryId,
                                              @RequestParam(required = true)String secondCategoryName,
                                              @RequestParam(required = true)String saleNumber,
                                              @RequestParam(required = true)String cycleDays,
                                              @RequestParam(required = true)String cycleTimes,
                                              @RequestParam(required = true)String merchantAllowance,
                                              HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            UserMerchantBO userMerchantBO = new UserMerchantBO();
            Result<UserMerchantBO> userMerchantBOResult = userMerchantExt.queryByUsername(request.getRemoteUser());
            if(userMerchantBOResult!=null&&userMerchantBOResult.isSuccess()&&userMerchantBOResult.getData()!=null){
                userMerchantBO = userMerchantBOResult.getData();
            }
            MerchantPromotionUpdateBO merchantPromotionUpdateBO = new MerchantPromotionUpdateBO();
            merchantPromotionUpdateBO.setStoreId(userMerchantBO.getStoreId());
            merchantPromotionUpdateBO.setPromotionId(new Long(promotionId));
            merchantPromotionUpdateBO.setName(name);
            Timestamp startTimeNew = null;
            if(startTime!=null && !startTime.equals("")){
                startTimeNew = Timestamp.valueOf(startTime+" 00:00:00");
            }
            Timestamp endTimeNew = null;
            if(startTime!=null && !endTime.equals("")){
                endTimeNew = Timestamp.valueOf(endTime+" 23:59:59");
            }
            merchantPromotionUpdateBO.setStartTime(startTimeNew);
            merchantPromotionUpdateBO.setEndTime(endTimeNew);
            merchantPromotionUpdateBO.setFirstCategoryId(firstCategoryId);
            merchantPromotionUpdateBO.setFirstCategoryName(firstCategoryName);
            merchantPromotionUpdateBO.setSecondCategoryId(secondCategoryId);
            merchantPromotionUpdateBO.setSecondCategoryName(secondCategoryName);
            merchantPromotionUpdateBO.setSaleNumber(new Integer(saleNumber));
            merchantPromotionUpdateBO.setCycleDays(new Integer(cycleDays));
            merchantPromotionUpdateBO.setCycleTimes(new Integer(cycleTimes));
            merchantPromotionUpdateBO.setMerchantAllowance(Arith.convertYuanToCent(Double.valueOf(merchantAllowance)));
            merchantPromotionUpdateBO.setUpdatePerson(userMerchantBO.getUsername());
            merchantPromotionUpdateBO.setStoreName(userMerchantBO.getStoreName());
            merchantPromotionUpdateBO.setProvinceId(userMerchantBO.getProvince());
            merchantPromotionUpdateBO.setCityId(userMerchantBO.getCity());
            //结算价格=签约价格-门店补贴
            int promotionClearAmt = 0;
            Result<Double> contractPriceResult = productExt.getProductContractPrice(request.getRemoteUser(), secondCategoryId);
            if(contractPriceResult!=null && contractPriceResult.isSuccess() && contractPriceResult.getData()!=null){
                promotionClearAmt = Arith.convertYuanToCent(contractPriceResult.getData()) - merchantPromotionUpdateBO.getMerchantAllowance();
                if(promotionClearAmt<=100){
                    jsonObject.put("success",false);
                    jsonObject.put("info","服务"+secondCategoryName+"的销售价必须要大于1元！");
                    return jsonObject;
                }
                merchantPromotionUpdateBO.setPromotionClearAmt(promotionClearAmt);
                //销售价格=签约价格-门店补贴-平台补贴(0)
                merchantPromotionUpdateBO.setPromotionAmt(promotionClearAmt);
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","服务"+secondCategoryName+"不存在签约价！");
                return jsonObject;
            }
            Result<Long> result = merchantBuildPromotionExt.updateMerchantPromotion(merchantPromotionUpdateBO,userMerchantBO.getStoreId());
            if(result==null){
                jsonObject.put("success",false);
                jsonObject.put("info","修改我发布的活动失败");
                log.error("修改我发布的活动时返回空");
            }else if(result.isSuccess() && result.getData()!=null && result.getData()!=null){
                //屏蔽短信发送,统一由carman发送
                /*Result<MerchantPromotionDetailBO> detailBOResult =
                        merchantBuildPromotionExt.queryMerchantPromotionDetail(new Long(promotionId));
                if(detailBOResult!=null && detailBOResult.isSuccess() && detailBOResult.getData()!=null){
                    //发送短信
                    MerchantPromotionDetailBO detailBO = detailBOResult.getData();
                    Map<String,String> smsData = new HashMap<String,String>();
                    smsData.put("activity",detailBO.getName());    //活动名称
                    smsData.put("subsidy", Arith.scale(Arith.convertCentToYuan(detailBO.getMerchantAllowance())));     //商户补贴价
                    smsData.put("settlement",Arith.scale(Arith.convertCentToYuan(detailBO.getPromotionClearAmt())));  //结算价
                    smsData.put("sale", Arith.scale(Arith.convertCentToYuan(detailBO.getPromotionAmt())));        //销售价
                    smsServiceExt.sendMerchantActivitySMS(userMerchantBO.getPhone(), smsData);
                }*/
                jsonObject.put("success",true);
                jsonObject.put("info",result.getStatusText());
            }else{
                jsonObject.put("success",false);
                if(result.getStateCode()==null){
                    jsonObject.put("info","修改我发布的活动失败");
                }else if(result.getStateCode().getCode()== MarketcenterStateCode.MERCHANT_HAS_ONLINE_PROMOTION.getCode()){
                    jsonObject.put("info","该服务还存在未结束的活动，同一服务只能有一个活动上线");   //同一服务只能同时创建一个活动
                }else{
                    jsonObject.put("info",result.getStateCode().getDesc());
                }
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","修改我发布的活动时异常了");
            log.error("修改我发布的活动时异常了",ex);
        }
        return jsonObject;
    }
    /**
     * 指向创建活动页面
     * @return
     */
    @RequestMapping("/createPage")
    public ModelAndView createPage(){
        return new ModelAndView("/page/myActivity/create_activity.jsp");
    }

    /**
     * 新建自建活动
     * @param name               活动名称
     * @param startTime          活动开始时间
     * @param endTime            活动结束时间
     * @param firstCategoryId    一级类目ID
     * @param firstCategoryName  一级类目名称
     * @param secondCategoryId   二级类目ID
     * @param secondCategoryName 二级类目名称
     * @param saleNumber         销售数量
     * @param cycleDays          促销频次N天
     * @param cycleTimes         促销频次M次
     * @param merchantAllowance  商家补贴
     * @param request
     * @return
     */
    @RequestMapping("/insertActivity")
    @ResponseBody
    public Map<String, Object> insertActivity(@RequestParam(required = true)String name,
                                              @RequestParam(required = true)String startTime,
                                              @RequestParam(required = true)String endTime,
                                              @RequestParam(required = true)String firstCategoryId,
                                              @RequestParam(required = true)String firstCategoryName,
                                              @RequestParam(required = true)String secondCategoryId,
                                              @RequestParam(required = true)String secondCategoryName,
                                              @RequestParam(required = true)String saleNumber,
                                              @RequestParam(required = true)String cycleDays,
                                              @RequestParam(required = true)String cycleTimes,
                                              @RequestParam(required = true)String merchantAllowance,
                                              HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            UserMerchantBO userMerchantBO = new UserMerchantBO();
            Result<UserMerchantBO> userMerchantBOResult = userMerchantExt.queryByUsername(request.getRemoteUser());
            if(userMerchantBOResult!=null&&userMerchantBOResult.isSuccess()&&userMerchantBOResult.getData()!=null){
                userMerchantBO = userMerchantBOResult.getData();
            }
            MerchantPromotionAddBO merchantPromotionAddBO = new MerchantPromotionAddBO();
            merchantPromotionAddBO.setStoreId(userMerchantBO.getStoreId());
            merchantPromotionAddBO.setName(name);
            Timestamp startTimeNew = null;
            if(startTime!=null && !startTime.equals("")){
                startTimeNew = Timestamp.valueOf(startTime+" 00:00:00");
            }
            Timestamp endTimeNew = null;
            if(startTime!=null && !endTime.equals("")){
                endTimeNew = Timestamp.valueOf(endTime+" 23:59:59");
            }
            merchantPromotionAddBO.setStartTime(startTimeNew);
            merchantPromotionAddBO.setEndTime(endTimeNew);
            merchantPromotionAddBO.setFirstCategoryId(firstCategoryId);
            merchantPromotionAddBO.setFirstCategoryName(firstCategoryName);
            merchantPromotionAddBO.setSecondCategoryId(secondCategoryId);
            merchantPromotionAddBO.setSecondCategoryName(secondCategoryName);
            merchantPromotionAddBO.setSaleNumber(new Integer(saleNumber));
            merchantPromotionAddBO.setCycleDays(new Integer(cycleDays));
            merchantPromotionAddBO.setCycleTimes(new Integer(cycleTimes));
            merchantPromotionAddBO.setMerchantAllowance(Arith.convertYuanToCent(Double.valueOf(merchantAllowance)));
            merchantPromotionAddBO.setCreatePerson(userMerchantBO.getUsername());
            merchantPromotionAddBO.setStoreName(userMerchantBO.getStoreName());
            merchantPromotionAddBO.setProvinceId(userMerchantBO.getProvince());
            merchantPromotionAddBO.setCityId(userMerchantBO.getCity());
            //结算价格=签约价格-门店补贴
            int promotionClearAmt = 0;
            Result<Double> contractPriceResult = productExt.getProductContractPrice(request.getRemoteUser(), secondCategoryId);
            if(contractPriceResult!=null && contractPriceResult.isSuccess() && contractPriceResult.getData()!=null){
                promotionClearAmt = Arith.convertYuanToCent(contractPriceResult.getData()) - merchantPromotionAddBO.getMerchantAllowance();
                if(promotionClearAmt<=100){
                    jsonObject.put("success",false);
                    jsonObject.put("info","服务"+secondCategoryName+"的销售价必须要大于1元！");
                    return jsonObject;
                }
                merchantPromotionAddBO.setPromotionClearAmt(promotionClearAmt);
                //销售价格=签约价格-门店补贴-平台补贴(0)
                merchantPromotionAddBO.setPromotionAmt(promotionClearAmt);
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","服务"+secondCategoryName+"不存在签约价！");
                return jsonObject;
            }
            Result<Long> result = merchantBuildPromotionExt.createMerchantPromotion(merchantPromotionAddBO);
            if(result==null){
                jsonObject.put("success",false);
                jsonObject.put("info","修改我发布的活动失败");
                log.error("修改我发布的活动时返回空");
            }else if(result.isSuccess() && result.getData()!=null && result.getData()!=null){
                //屏蔽短信发送,由carman统一发送
                /*Result<MerchantPromotionDetailBO> detailBOResult =
                        merchantBuildPromotionExt.queryMerchantPromotionDetail(result.getData());
                if(detailBOResult!=null && detailBOResult.isSuccess() && detailBOResult.getData()!=null){
                    MerchantPromotionDetailBO detailBO = detailBOResult.getData();
                    Map<String,String> smsData = new HashMap<String,String>();
                    smsData.put("activity",detailBO.getName());    //活动名称
                    smsData.put("subsidy", Arith.scale(Arith.convertCentToYuan(detailBO.getMerchantAllowance())));     //商户补贴价
                    smsData.put("settlement",Arith.scale(Arith.convertCentToYuan(detailBO.getPromotionClearAmt())));  //结算价
                    smsData.put("sale",Arith.scale(Arith.convertCentToYuan(detailBO.getPromotionAmt())));        //销售价
                    smsServiceExt.sendMerchantActivitySMS(userMerchantBO.getPhone(), smsData);
                }*/
                jsonObject.put("success",true);
                jsonObject.put("info",result.getStatusText());
            }else{
                jsonObject.put("success", false);
                if(result.getStateCode()==null){
                    jsonObject.put("info","修改我发布的活动失败");
                }else if(result.getStateCode().getCode()== MarketcenterStateCode.MERCHANT_HAS_ONLINE_PROMOTION.getCode()){
                    jsonObject.put("info","该服务还存在未结束的活动，同一服务只能有一个活动上线");   //同一服务只能同时创建一个活动
                }else{
                    jsonObject.put("info",result.getStateCode().getDesc());
                }
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","修改我发布的活动时异常了");
            log.error("修改我发布的活动时异常了",ex);
        }
        return jsonObject;
    }

    /**
     * 查看自建活动详情页面
     * @param request
     * @return
     */
    @RequestMapping("/viewPage")
    public ModelAndView viewPage(@RequestParam(required = true)Long promotionId,
                                 HttpServletRequest request,Model model){
        //活动详情信息
        Result<MerchantPromotionDetailBO> detailBOResult = merchantBuildPromotionExt.queryMerchantPromotionDetail(promotionId);
        UserMerchantBO userMerchantBO = getLoginUser();
        if(userMerchantBO != null && detailBOResult!=null && detailBOResult.isSuccess() && detailBOResult.getData()!=null){
            if (userMerchantBO.getStoreId() == detailBOResult.getData().getStoreId()){
                model.addAttribute("activity",detailBOResult.getData());
                //查询省份
                Result<AreaRO> province = areaServiceFacade.queryAreaById(detailBOResult.getData().getProvinceId()+"");
                String provinceName = province.getData().getAreaName();
                model.addAttribute("provinceName",provinceName);
                //查询地市
                Result<AreaRO> city = areaServiceFacade.queryAreaById(detailBOResult.getData().getCityId()+"");
                String cityName = city.getData().getAreaName();
                model.addAttribute("cityName",cityName);
                //签约价格
                Result<Double> priceResult = productExt.getProductContractPrice(request.getRemoteUser(),detailBOResult.getData().getSecondCategoryId());
                if(priceResult!=null && priceResult.isSuccess() && priceResult.getData()!=null){
                    model.addAttribute("contractPrice",priceResult.getData());
                }
                //获取门店图片
                Response<StoreInfoBO> storeInfoBOResponse = storeAccountExt.getStoreInfo(detailBOResult.getData().getStoreId());
                if(storeInfoBOResponse!=null && storeInfoBOResponse.isSuccess() && storeInfoBOResponse.getData()!=null){
                    model.addAttribute("storePic", staticUrl1 + storeInfoBOResponse.getData().getTitlePhotoPath());
                }
            }else{
                return new ModelAndView("/page/common/error.jsp");
            }
        }else{
            model.addAttribute("activity",new MerchantPromotionDetailBO());
            return new ModelAndView("/page/common/error.jsp");
        }
        return new ModelAndView("/page/myActivity/view_activity.jsp");
    }

    /**
     * 查询活动的收入趋势
     * @param promotionId
     * @return
     */
    @RequestMapping("/consumeHistory")
    @ResponseBody
    public Map<String, Object> queryActivityConsumeHistory(@RequestParam(required = true)String  promotionId,
                                                           @RequestParam(required = true)String  dayRange,
                                                           @RequestParam(required = true)String  productId){
        JSONObject jsonObject = new JSONObject();
        try{
            Result<PromotionConsumeHistoryBO> result = merchantBuildPromotionExt.queryPromotionConsumeHistory(new Long(promotionId),new Integer(dayRange),new Integer(productId));
            if(result==null){
                jsonObject.put("success",false);
                jsonObject.put("info","查询活动的收入趋势失败");
                log.error("查询活动的收入趋势时返回空");
            }else if(result.isSuccess() && result.getData()!=null && result.getData()!=null){
                jsonObject.put("success",true);
                jsonObject.put("info",result.getData());
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info",result.getStatusText());
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","查询活动的收入趋势时异常了");
            log.error("查询活动的收入趋势时异常了",ex);
        }
        return jsonObject;
    }



    /**
     * 查询商户提供的服务列表
     */
    @RequestMapping("/findService")
    @ResponseBody
    public Map<String,Object> findService(@RequestParam(defaultValue = "", required = false) String serviceParentId,
                                          HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(userBiz.getStoreId(request.getRemoteUser()), serviceParentId);
            if(categoryRoList!=null && !categoryRoList.isEmpty()){
                result.put("success",true);
                //自建活动先只允许建汽车美容类型的服务
                if(serviceParentId==null||serviceParentId.equals("")){
                    List<CategoryRo> returnRoList = new ArrayList<CategoryRo>();
                    for(CategoryRo categoryRo:categoryRoList){
                         if(categoryRo.getCategoryId()!=null && categoryRo.getCategoryId().equals("1")){
                             returnRoList.add(categoryRo);
                         }
                    }
                    result.put("list",returnRoList);
                }else{
                    result.put("list",categoryRoList);
                }
            }else{
                result.put("success",false);
                result.put("info","查询商户提供的服务列表无数据");
            }
        }catch (Exception ex){
            log.error("查询商户提供的服务列表时异常了",ex);
        }
        return result;
    }


}
