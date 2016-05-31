package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.knife.result.Result;
import com.qccr.sh.biz.ApplyToJoinBiz;
import com.qccr.sh.common.Token;
import com.qccr.sh.enums.Constants;
import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.base.RemoteProjectEnum;
import com.toowell.crm.facade.cooperation.entity.CooperateProjectEnum;
import com.toowell.crm.facade.cooperation.entity.ServiceEnum;
import com.toowell.crm.facade.cooperation.entity.TBusinessCooperationRo;
import com.toowell.crm.facade.cooperation.service.TBusinessCooperationFacede;
import com.toowell.crm.facade.store.entity.CodeDescRo;
import com.towell.carman.service.common.SMSService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/anon/apply")
public class ApplyToJoinController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplyToJoinBiz applyToJoinBiz;

    @Resource(name = "smsService")
    private SMSService smsService;

    @Autowired
    private StoreAccountExt storeAccountExt;

    @Autowired
    private UserMerchantExt userMerchantExt;

    /*门店加盟接口*/
    @Autowired
    private TBusinessCooperationFacede businessCooperationFacede;

    @RequestMapping(value = "/apply_join")
    @Token(add = true)
    public String apply_join() {
        return "apply_join.jsp";
    }

    @RequestMapping(value = "/apply_joinDo", method = RequestMethod.POST)
    @Token(addDo = true)
    public String apply_joinDo(HttpServletRequest request, ModelMap modelMap) {
        try {
            String storeName = request.getParameter("storename");
            String store_owner = request.getParameter("store_owner");
            String store_tel = request.getParameter("store_tel");
            String province = request.getParameter("province");
			String city = request.getParameter("city");
			String areaId = request.getParameter("areaId");
            String store_addr_detail = request.getParameter("store_addr_detail");
            String store_square = request.getParameter("store_square");
            String store_staff_num = request.getParameter("store_staff_num");
            String store_age = request.getParameter("store_age");
            String store_main_brand = request.getParameter("store_main_brand");
            //
            String itemid = request.getParameter("itemid");
            String serviceid = request.getParameter("serviceid");
            if (serviceid == null) {
                serviceid = "";
            }
            //List<String> idArray = Arrays.asList(serviceid.split(","));

            //Map<String, Object> map = new HashMap<String, Object>();

            /*map.put("storeName", storeName);
            map.put("store_owner", store_owner);
            map.put("store_tel", store_tel);
            map.put("province", province);
            map.put("city", city);
            map.put("areaId", areaId);
            map.put("store_addr_detail", store_addr_detail);
            map.put("store_square", store_square);
            map.put("store_staff_num", store_staff_num);
            map.put("store_age", store_age);
            map.put("store_main_brand", store_main_brand);
            map.put("cooperation_item", itemid);
            map.put("serviceid", idArray);*/
            TBusinessCooperationRo tBusinessCooperationRo = new TBusinessCooperationRo();
            tBusinessCooperationRo.setStoreName(storeName);
            tBusinessCooperationRo.setLinkMan(store_owner);
            tBusinessCooperationRo.setLinkPhone(store_tel);
            tBusinessCooperationRo.setProvince(province);
            tBusinessCooperationRo.setCity(city);
            tBusinessCooperationRo.setDistrict(areaId);
            tBusinessCooperationRo.setDetailAdd(store_addr_detail);
            tBusinessCooperationRo.setStoreArea(store_square);
            if(store_staff_num==null || store_staff_num.length()<=0){
                tBusinessCooperationRo.setEmployees(0l);
            }else {
                tBusinessCooperationRo.setEmployees((long) Integer.parseInt(store_staff_num));
            }
            tBusinessCooperationRo.setBizDuration(store_age);
            tBusinessCooperationRo.setBizBrand(store_main_brand);
            tBusinessCooperationRo.setCreateUser(Constants.CREATE_USER.name());//设置创建人
            /*希望合作项目*/
            List<CooperateProjectEnum> cpEnums = new ArrayList<CooperateProjectEnum>();

            if(itemid!=null && itemid.length()>0){
                String[] itemids = itemid.split(",");
                for (int i=0;i<itemids.length;i++){
                    cpEnums.add(CooperateProjectEnum.valueOf(itemids[i]));
                }
                tBusinessCooperationRo.setCooperateProjectList(cpEnums);
            }

            /*自身服务内容*/
            if(serviceid.length()>0) {
                List<ServiceEnum> serviceEnums = new ArrayList<ServiceEnum>();
                String[] services = serviceid.split(",");
                for (int j = 0; j < services.length; j++) {
                    serviceEnums.add(ServiceEnum.valueOf(services[j]));
                }
                tBusinessCooperationRo.setServiceList(serviceEnums);
            }
            tBusinessCooperationRo.setCooperationProject(null);
            tBusinessCooperationRo.setStoresServiceContent(null);
			Result result = businessCooperationFacede.insert(tBusinessCooperationRo, RemoteProjectEnum.SH);//新增门店加盟信息
            if (result.getData() == true){
                modelMap.addAttribute("message", "您的资料已提交，工作人员会尽快审核并联系您！");
            }else{
                modelMap.addAttribute("message", result.getStatusText());
            }
//            applyToJoinBiz.insertSh_user_apply(map);
            // 选中自身服务，则进行插入到从表
            /*if (idArray.size() > 0 && !"".equals(idArray.get(0))) {
                applyToJoinBiz.insertSh_service_mapping(map);
            }*/

//            modelMap.addAttribute("message", "您的资料已提交，工作人员会尽快审核并联系您！");
            return "apply_join.jsp";
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            modelMap.addAttribute("message", "系统错误！");
            return "apply_join.jsp";
        }
    }

    /**
     * 查询省份
     */
    @RequestMapping("/provinces")
    @ResponseBody
    public List<CodeDescRo> provinces() {
        List<CodeDescRo> codeDescRoList = new ArrayList<CodeDescRo>();
        Response<List<CodeDescRo>> provinceResponse = storeAccountExt.getProvinceorCity(null);
        if (provinceResponse != null && provinceResponse.isSuccess() && provinceResponse.getData() != null) {
            codeDescRoList = provinceResponse.getData();
        }
        return codeDescRoList;
    }

    /**
     * 根据省份查询城市
     *
     * @param province
     */
    @RequestMapping("/city")
    @ResponseBody
    public List<CodeDescRo> citys(int province) {
        List<CodeDescRo> codeDescRoList = new ArrayList<CodeDescRo>();
        Response<List<CodeDescRo>> provinceResponse = storeAccountExt.getProvinceorCity(province + "");
        if (provinceResponse != null && provinceResponse.isSuccess() && provinceResponse.getData() != null) {
            codeDescRoList = provinceResponse.getData();
        }
        return codeDescRoList;
    }

    /**
     * 根据省份和城市查询区域
     *
     * @param city     城市编号
     */
    @RequestMapping("/area")
    @ResponseBody
    public List<CodeDescRo> area(int city) {
        List<CodeDescRo> codeDescRoList = new ArrayList<CodeDescRo>();
        Response<List<CodeDescRo>> provinceResponse = storeAccountExt.getProvinceorCity(city + "");
        if (provinceResponse != null && provinceResponse.isSuccess() && provinceResponse.getData() != null) {
            codeDescRoList = provinceResponse.getData();
        }
        return codeDescRoList;
    }

    @RequestMapping("/sendmsg")
    public void sendmsgs(HttpServletRequest request, HttpServletResponse response) {
        String msg = "";
        JSONObject jsonObject = new JSONObject();
        try {
            // 用户账号
            String username = request.getParameter("username");
            String authCode = "";
            if (request.getSession(false) != null && request.getSession(false).getAttribute("authCode") != null
                    && !request.getSession(false).getAttribute("authCode").equals("")) {
                authCode = (String) request.getSession(false).getAttribute("authCode");
            } else {
                //
                authCode = RandomStringUtils.randomNumeric(6);
            }
            request.getSession().setAttribute("authCode", authCode);

            // 根据用户账号获取手机号
            String tel = "";

            Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
            if (boResult != null && boResult.isSuccess() && boResult.getData() != null) {
                //电话号码用phone
                tel = boResult.getData().getPhone();
                smsService.sendAuthCode(tel, authCode);
                msg = "验证码已发送到账号绑定手机" + tel.replace(tel.substring(3, 7), "****");
            } else {
                msg = "用户名不存在，请重新检查！";
            }

            jsonObject.put("msg", msg);
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            msg = "用户出现异常，请联系管理员";
            jsonObject.put("msg", msg);
            try {
                response.getWriter().write(jsonObject.toJSONString());
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }

    @RequestMapping("/checkuser")
    public void checkuser(HttpServletRequest request, HttpServletResponse response) {

        // 验证码
        String identifying_code = request.getParameter("identifying_code");
        String authCode = (String) request.getSession(false).getAttribute("authCode");
        JSONObject jsonObject = new JSONObject();
        if (identifying_code == null) {
            jsonObject.put("flag", "failure");
        } else {
            if (identifying_code.equals(authCode)) {
                jsonObject.put("flag", "success");
            } else {
                jsonObject.put("flag", "failure");
            }
        }

        try {
            response.getWriter().write(jsonObject.toJSONString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @RequestMapping("/password_reset")
    public String password_reset(HttpServletRequest request, ModelMap modleMap) {
        // 用户账号
        String username = request.getParameter("username");
        modleMap.put("username", username);
        return "password_reset.jsp";
    }

    @RequestMapping("/password_resetDo")
    public void password_resetDo(HttpServletRequest request, HttpServletResponse response) {
        // 用户账号
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject jsonObject = new JSONObject();
        try {
            Result<Integer> result = userMerchantExt.resetPwdByUsername(username, password);
            if (result != null && result.isSuccess() && result.getData() != null) {
                jsonObject.put("flag", "success");
            } else {
                jsonObject.put("flag", result==null? "密码设置失败啦，call客服吧！" : result.getStatusText());
            }
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            jsonObject.put("flag", "密码设置失败啦，call客服吧！");
        }

    }
}
