package com.qccr.sh.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.toowell.crm.facade.store.entity.ClosedCycleEnum;
import com.toowell.crm.facade.store.service.StoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.store.entity.BankRo;
import com.toowell.crm.facade.store.entity.CodeDescRo;

@Controller
@RequestMapping("/page/account")
public class AccountInfoController {
	
	 private static final Logger log = LoggerFactory.getLogger(AccountInfoController.class);
	 
     @Autowired
     private UserBiz userBiz;
     
     @Autowired
     private StoreAccountExt storeAccountExt;

	@Autowired
	private StoreFacade storeFacade ;

	 /**
     * 查询门店总信息
     */
    @RequestMapping("/allStoreInfo")
    public String allStoreInfo(HttpServletRequest request,Model model){
        try{
        	String userName = request.getRemoteUser();
        	Integer storeId = userBiz.getStoreId(userName);
			String isModify=request.getParameter("isModify")==null?"false":request.getParameter("isModify").toString();
        	Response<StoreInfoBO> res = storeAccountExt.getStoreInfo(storeId);
        	if(res.isSuccess()){
        		StoreInfoBO storeInfoBO = res.getData();
				List<ClosedCycleEnum> closedCycleEnums=new LinkedList<ClosedCycleEnum>();
				String closedCycle=storeInfoBO.getClosedCycle();
				if(closedCycle!=null && closedCycle.length()>0){
					String[] closedCycles=closedCycle.split(",");
					for(String s:closedCycles){
						closedCycleEnums.add(ClosedCycleEnum.valueOf(s));
					}
				}
				List<ClosedCycleEnum> closedCycleEnumsAll=new LinkedList<ClosedCycleEnum>();
				closedCycleEnumsAll.add(ClosedCycleEnum.SUNDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.MONDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.TUESDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.WEDNESDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.THURSDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.FRIDAY);
				closedCycleEnumsAll.add(ClosedCycleEnum.SATURDAY);

        		String payWay = storeInfoBO.getStoreSafetyBo().getPayWay();
        		String payAccount = storeInfoBO.getStoreSafetyBo().getPayAccount();
        		StringBuffer no = new StringBuffer("");
        		if(payWay.equals("1")){
        			no.append("**** **** **** "+payAccount.substring(payAccount.length()-4, payAccount.length()));
        		}else{
        			if(payAccount.contains("@")){
        				no.append(payAccount.substring(0, 3)+"***@"+payAccount.split("@")[1]);
        			}else{
        				no.append(payAccount.substring(0, 3)+"***"+payAccount.substring(payAccount.length()-4, payAccount.length()));
        			}
        		}
				storeInfoBO.getStoreSafetyBo().setPayAccount(no.toString());
        		model.addAttribute("storeInfo",storeInfoBO);
				model.addAttribute("isModify",isModify);
				model.addAttribute("closedCycleEnums",closedCycleEnums);
				model.addAttribute("closedCycleEnumsAll",closedCycleEnumsAll);
				//休业时间取服务器时间
				Date date=new   Date();//取时间
				Calendar   calendar   =   new   GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
				date=calendar.getTime();   //这个时间就是日期往后推一天的结果
				model.addAttribute("outBizStartDate",new SimpleDateFormat("yyyy-MM-dd").format(date));

        	}else{
        		log.error("错误信息："+res.getMessage());
        		model.addAttribute("error",res.getMessage());
        		return "/page/common/error.jsp";
        	}
        }catch (Exception e){
            log.error("查询账户信息异常",e);
            model.addAttribute("error", "查询账户信息异常");
            return "/page/common/error.jsp";
        }
        return "/page/account/modify_account.jsp";
    }
    
    /**
     * 刷新门店信息
     */
    @RequestMapping("/refreshAccountInfo")
    @ResponseBody
    public Map<String,Object> refreshAccountInfo(HttpServletRequest request){
    	Map<String,Object> map = new HashMap<String,Object>();
        try{
        	
        	String storeId = request.getParameter("storeId");
        	Response<StoreInfoBO> res = storeAccountExt.getStoreInfo(Integer.parseInt(storeId));
        	if(res.isSuccess()){
        		StoreInfoBO storeInfoBO = res.getData();
        		String payWay = storeInfoBO.getPayWay();
        		String payAccount = storeInfoBO.getPayAccount();
        		StringBuffer no = new StringBuffer("");
        		if(payWay.equals("1")){
        			no.append("**** **** **** "+payAccount.substring(payAccount.length()-4, payAccount.length()));
        		}else{
        			if(payAccount.contains("@")){
        				no.append(payAccount.substring(0, 3)+"***@"+payAccount.split("@")[1]);
        			}else{
        				no.append(payAccount.substring(0, 3)+"***"+payAccount.substring(payAccount.length()-4, payAccount.length()));
        			}
        		}
        		storeInfoBO.setPayAccount(no.toString());
        		map.put("flag", 1);
        		map.put("data", storeInfoBO);
        	}else{
        		map.put("flag", 0);
        		map.put("info", res.getMessage());
        	}
        	
        }catch (Exception e){
            log.error("刷新信息异常",e);
            map.put("flag", 0);
    		map.put("info", "查询信息异常！");
            return map;
        }
        return map;
    }
    
    
	 /**
     * 修改门店信息
     */
    @RequestMapping("/updateStore")
    @ResponseBody
    public Map<String,Object> updateStore(HttpServletRequest request){
    	Map<String,Object> map = new HashMap<String,Object>();
        try{
			Integer storeId = userBiz.getStoreId(request.getRemoteUser());
			Response<StoreInfoBO> storeInfoRes = storeAccountExt.getStoreInfo(storeId);
			StoreInfoBO storeInfoBO = new StoreInfoBO();
        	String bizStartTime = request.getParameter("bizStartTime").trim();
        	String bizEndTime = request.getParameter("bizEndTime").trim();
        	String phone = request.getParameter("phone")==null ? null : request.getParameter("phone").trim();
        	String zonetel =  request.getParameter("zonetel")==null ? null : request.getParameter("zonetel").trim();
			if (storeInfoRes.isSuccess() && storeInfoRes.getData() != null){
				StoreInfoBO storeInfo =storeInfoRes.getData();
				if(! StringUtils.isEmpty(storeInfo.getTelephone())){
					storeInfoBO.setTelephone(storeInfo.getTelephone());
				}else{
					storeInfoBO.setTelephone(phone);
				}

				if (! StringUtils.isEmpty(storeInfo.getTel())){
					storeInfoBO.setTel(storeInfo.getTel());
				}else{
					storeInfoBO.setTel(zonetel);
				}
			}
        	String outBizStartDate=request.getParameter("outBizStartDate")==null ? null : request.getParameter("outBizStartDate");
			String outBizEndDate=request.getParameter("outBizEndDate")==null ? null : request.getParameter("outBizEndDate");
			String closedCycle=request.getParameter("closedCycle")==null ? null : request.getParameter("closedCycle");
        	storeInfoBO.setStoreId(storeId+"");
        	storeInfoBO.setBizStartTime(bizStartTime);
        	storeInfoBO.setBizEndTime(bizEndTime);
			storeInfoBO.setOutBizStartDate(outBizStartDate);
			storeInfoBO.setOutBizEndDate(outBizEndDate);
			storeInfoBO.setClosedCycle(closedCycle);
        	Response<Integer> res =storeAccountExt.updateStoreInfo(storeInfoBO);
        	if(res.isSuccess()){
        		map.put("flag", 1);
        		map.put("info", "修改门店信息成功！");
        	}else{
        		map.put("flag", 0);
        		map.put("info", res.getMessage());
        	}
        	
        }catch (Exception e){
            log.error("修改门店异常",e);
            map.put("flag", 0);
    		map.put("info", "修改门店异常！");
            return map;
        }
        return map;
    }
    
    
    /**
     * 查询银行列表及省列表
     */
    @RequestMapping("/getBankProList")
    @ResponseBody
    public Map<String,Object> getBankProList(){
    	Map<String,Object> map = new HashMap<String,Object>();
        try{
        	Response<List<BankRo>> bank = storeAccountExt.getBankList();
        	Response<List<CodeDescRo>> pro = storeAccountExt.getProvinceorCity(null);
        	if(bank.isSuccess()&& pro.isSuccess()){
        		map.put("flag", 1);
        		map.put("bank", bank.getData());
        		map.put("pro", pro.getData());
        		return map;
        	}else{
        		map.put("flag", 0);
        		if(!bank.isSuccess()){
        			map.put("info", bank.getMessage());
        		}
        		if(!pro.isSuccess()){
        			map.put("info", pro.getMessage());
        		}
        		return map;
        	}

        }catch (Exception e){
            log.error("查询银行列表异常",e);
            map.put("flag", 0);
    		map.put("info", "查询银行列表异常！");
            return map;
        }
    }
    
    /**
     * 查询市列表
     */
    @RequestMapping("/getCityList")
    @ResponseBody
    public Map<String,Object> getCityList(String code){
    	Map<String,Object> map = new HashMap<String,Object>();
        try{
        	Response<List<CodeDescRo>> city = storeAccountExt.getProvinceorCity(code);
        	if(city.isSuccess()){
        		map.put("flag", 1);
        		map.put("city", city.getData());
        		return map;
        	}else{
        		map.put("flag", 0);
        		map.put("info", city.getMessage());
        		return map;
        	}

        }catch (Exception e){
            log.error("查询银行列表异常",e);
            map.put("flag", 0);
    		map.put("info", "查询银行列表异常！");
            return map;
        }
    }
    
    /**
     * 修改收款信息
     */
    @RequestMapping("/updateAccount")
    @ResponseBody
    public Map<String,Object> updateAccount(HttpServletRequest request){
    	Map<String,Object> map = new HashMap<String,Object>();
        try{
        	String storeId = request.getParameter("storeId");
        	String bankcode = request.getParameter("bankcode").trim();
        	String bankname = request.getParameter("bankname")==null ? "" :request.getParameter("bankname").trim();
        	String provinceCode = request.getParameter("provinceCode").trim();
        	String cityCode =  request.getParameter("cityCode").trim();
        	String bankBranch = request.getParameter("bankBranch").trim();
        	String payAccount = request.getParameter("payAccount").trim();
        	
        	StoreInfoBO storeInfoBO = new StoreInfoBO();
        	storeInfoBO.setStoreId(storeId);
        	storeInfoBO.setBankCode(bankcode);
        	storeInfoBO.setBankName(bankname);
        	storeInfoBO.setBankBranch(bankBranch);
        	storeInfoBO.setPayAccount(payAccount);
        	storeInfoBO.setProvinceCode(provinceCode);
        	storeInfoBO.setCityCode(cityCode);
        	Response<Integer> res =storeAccountExt.updateBankAccount(storeInfoBO);
        	if(res.isSuccess()){
        		map.put("flag", 1);
        		map.put("info", "修改收款信息成功！");
        	}else{
        		map.put("flag", 0);
        		map.put("info", res.getMessage());
        	}
        	
        }catch (Exception e){
            log.error("修改收款异常",e);
            map.put("flag", 0);
    		map.put("info", "修改收款异常！");
            return map;
        }
        return map;
    }

	/**
	 * 是否是营业状态
	 * @param request
	 * @return
     */
	@RequestMapping("/isOpen")
	@ResponseBody
	public Map<String,Object> isOpen(HttpServletRequest request) {
		Map<String ,Object> map=new HashMap<String,Object>();
		try {
			String userName = request.getRemoteUser();
			Integer storeId = userBiz.getStoreId(userName);
			Response<StoreInfoBO> res = storeAccountExt.getStoreInfo(storeId);
				if (res.isSuccess()){
					if(res.getData() == null){
						map.put("flag",1);
						map.put("isOpen","门店已下线");//状态
						return  map;
					}
				Date nowDate=new Date();
				DateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				StoreInfoBO storeInfoBO = res.getData();
				String outBizStartDateStr= storeInfoBO.getOutBizStartDate();
				String outBizEndDateStr= storeInfoBO.getOutBizEndDate();
				if (!StringUtils.isEmpty(outBizStartDateStr)  && !StringUtils.isEmpty(outBizStartDateStr)){
					//1、当前时间在此范围内可以立刻营业，状态为休业 否则是营业状态
					Date outBizStartDate=df.parse(outBizStartDateStr+" 00:00:00");
					Date outBizEndDate=df.parse(outBizEndDateStr+" 23:59:59");
					if (nowDate.after(outBizStartDate) && nowDate.before(outBizEndDate)){
						map.put("isOpen","休业中");//状态
					}else{
						map.put("isOpen",isOpenOrClose(storeInfoBO));
					}
				}else{
					map.put("isOpen",isOpenOrClose(storeInfoBO));
				}
				map.put("flag",1);
				map.put("storeInfoBO",storeInfoBO);
			}else{
				map.put("flag",0);
				map.put("info","门店查询失败");
			}
		} catch (Exception ex) {
			log.error("门店查询失败", ex);
			map.put("flag",0);
			map.put("info","门店查询失败");
		}
		return  map;
	}

	@RequestMapping("storeRecover")
	@ResponseBody
	public Map<String,Object> storeRecover(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String userName = request.getRemoteUser();
			Integer storeId = userBiz.getStoreId(userName);
			com.qccr.knife.result.Result<java.lang.Boolean>  result=storeFacade.storeRecoveryBiz(storeId);
			if (result.isSuccess()){
				map.put("flag",1);
				map.put("info","立即营成功");
			}else{
				map.put("flag",0);
				map.put("info",result.getStatusText());
			}
		}catch (Exception ex){
			log.error("立即营业失败", ex);
			map.put("flag",0);
			map.put("info","立即营业失败");
		}
		return  map;
	}


	/**
	 * 判断当前营业状态
	 * @param storeInfoBO
	 * @return
     */
	public String isOpenOrClose(StoreInfoBO storeInfoBO){
		Locale.setDefault(Locale.CHINA);
		Date nowDate=new Date();
		String result="已打烊";
		String bizStartTime=storeInfoBO.getBizStartTime();
		String bizEndTime=storeInfoBO.getBizEndTime();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		int nowTime=calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE);
		int weekDay=calendar.get(Calendar.DAY_OF_WEEK)-1;

		int startTime=0;
		int endTime=0;

		//判断时间点
		//判断星期
		//营业时间点失效
		if(bizStartTime !=null && bizEndTime !=null && !StringUtils.isEmpty(bizStartTime) && !StringUtils.isEmpty(bizEndTime)){
			String[] startTimes =bizStartTime.split(":");
			String[] endTimes=bizEndTime.split(":");
			startTime=Integer.parseInt(startTimes[0])*60+Integer.parseInt(startTimes[1]);
			endTime=Integer.parseInt(endTimes[0])*60+Integer.parseInt(endTimes[1]);

			List<ClosedCycleEnum> closedCycleEnums=new LinkedList<ClosedCycleEnum>();
			String closedCycle=storeInfoBO.getClosedCycle();
			if(closedCycle!=null && closedCycle.length()>0){
				String[] closedCycles=closedCycle.split(",");
				for(String s:closedCycles){
					closedCycleEnums.add(ClosedCycleEnum.valueOf(s));
					if(weekDay==ClosedCycleEnum.valueOf(s).getValue()){
						if(startTime < nowTime && nowTime < endTime){
							result="营业中";//状态
						}
					}
				}
			}
		}
		return  result;
	}
}
