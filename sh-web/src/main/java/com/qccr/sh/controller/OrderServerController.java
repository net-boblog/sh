package com.qccr.sh.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.qccr.knife.result.Result;
import com.qccr.marketcenter.facade.service.promotion.PromotionFacade;
import com.qccr.sh.biz.ShopServiceBiz;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.external.orderCenter.ServerOrderExt;
import com.qccr.sh.external.orderCenter.bo.ServerOrderBO;
import com.qccr.sh.external.orderCenter.bo.ServerOrderQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.util.FileUtil;
import com.toowell.crm.facade.store.entity.CategoryRo;
import com.toowell.crm.facade.user.entity.UserInfoRo;
import com.toowell.crm.facade.user.service.UserFacade;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toowell.crm.facade.store.entity.StoreRo;
import com.toowell.crm.facade.store.service.StoreFacade;
import com.towell.carman.common.Status;

/**
 * 已验证订单（服务）查询 控制器
 * @author zhiwen.li
 *
 */
@Controller
@RequestMapping("/page/serverOrder")
public class OrderServerController extends BaseModule{
	
	private static final Logger log = LoggerFactory.getLogger(OrderServerController.class.getName());

	@Resource(name="promotionFacade")
	private PromotionFacade promotionFacade;
	
	@Resource(name="storeFacade")
	private StoreFacade storeFacade;

	@Autowired
	private ShopServiceBiz shopServiceBiz;

	@Autowired
	private ServerOrderExt serverOrderExt;

	@Resource(name="memchantUserFacade")
	private UserFacade memchantUserFacade;
	
	/**
	 * 指向列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView queryServerOrderList(
			   @RequestParam(defaultValue="0",required=false)int page_offset,
			   @RequestParam(defaultValue="12",required=false)int page_limit,
			   @RequestParam(defaultValue="",required=false)String smsCode,
			   @RequestParam(defaultValue="",required=false)String orderCode,
			   @RequestParam(defaultValue="0",required=false)int service_type_one,
			   @RequestParam(defaultValue="0",required=false)int service_type_two,
			   @RequestParam(defaultValue="",required=false)String date_start,
			   @RequestParam(defaultValue="",required=false)String date_end,
			   @RequestParam(defaultValue="-1",required=false)String audit_status,
			   @RequestParam(defaultValue="0",required=false)Integer smstime_updown){
		Map<String, String> auditMap = new LinkedHashMap<String, String>();
		auditMap.put(String.valueOf(-1), "全部");
		auditMap.put(String.valueOf(Status.AuditServerOrder.AUDIT), "待审核");
		auditMap.put(String.valueOf(Status.AuditServerOrder.NORMAL), "审核通过");
		auditMap.put(String.valueOf(Status.AuditServerOrder.ABNORMAL), "异常单");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ServerOrderBO> serverOrderBOList = new LinkedList<ServerOrderBO>();
		UserMerchantBO user = super.getLoginUser();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start_d = (date_start.equals("")) ? null : sdf.parse(date_start);
			Date end_d = null;
			if (date_end.equals("")) {
				end_d = null;
			} else {
				end_d = sdf.parse(date_end);
				end_d = new Date(end_d.getTime() + 24 * 60 * 60 * 1000);
			}
			paramMap.put("page_offset", page_offset);
			paramMap.put("startSize", page_offset*page_limit);
			paramMap.put("page_limit", page_limit);
			paramMap.put("smsCode", smsCode);
			paramMap.put("orderCode", orderCode);
			paramMap.put("service_type_one", service_type_one);
			paramMap.put("service_type_two", service_type_two);
			List<Long> serviceTypes = new ArrayList<Long>();
			if(service_type_one!=0){
				if(service_type_two!=0){
					serviceTypes.add(new Long(service_type_two));
				}else{
                    //获取1级服务下面的所有二级服务
					List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(user.getStoreId(), service_type_one+"");
					if(categoryRoList!=null && categoryRoList.size()>0){
						for(int i=0;i<categoryRoList.size();i++){
							serviceTypes.add(new Long(categoryRoList.get(i).getCategoryId()));
						}
					}
				}
			}
			paramMap.put("start_date", start_d);
			paramMap.put("end_date", end_d);
			paramMap.put("audit_status", audit_status);
			paramMap.put("storeId", user.getStoreId());
			paramMap.put("smstime_updown", new Integer(smstime_updown));

			//记录是否有异常单
			int errorOrder = 0;

			ServerOrderQuery serverOrderQuery = new ServerOrderQuery();
			serverOrderQuery.setPageNo(page_offset);
			serverOrderQuery.setPageSize(page_limit);
			if(smsCode!=null && !smsCode.equals("")){
				serverOrderQuery.setSmsCode(smsCode);
			}
			serverOrderQuery.setOrderNo(orderCode);
			if(serviceTypes!=null && serviceTypes.size()>0){
				Long[] serviceTypeArr = new Long[serviceTypes.size()];
				serviceTypes.toArray(serviceTypeArr);
				serverOrderQuery.setServerIdArr(serviceTypeArr);
			}
			if(start_d!=null){
				serverOrderQuery.setBeginDate(new Timestamp(start_d.getTime()));
			}
			if(end_d!=null){
				serverOrderQuery.setEndDate(new Timestamp(end_d.getTime()));
			}
			if(audit_status!=null && !audit_status.equals("-1")){
				serverOrderQuery.setAuditStatus(new Integer(audit_status));
			}
			serverOrderQuery.setStoreId(new Long(user.getStoreId()));
            //待增加排序字段smstime_updown
			serverOrderQuery.setSortType(smstime_updown);
			Response<Pagination<ServerOrderBO>> paginationResponse = serverOrderExt.queryByStoreIdAndSmscode(serverOrderQuery);
			if(paginationResponse!=null && paginationResponse.isSuccess() && paginationResponse.getData()!=null){
				paramMap.put("total", paginationResponse.getData().getTotalCount());
				serverOrderBOList = paginationResponse.getData().getDataList();
				if(serverOrderBOList!=null){
                    for(ServerOrderBO serverOrderBO : serverOrderBOList){

						Integer auditStatus = serverOrderBO.getAuditStatus();
                        if(auditStatus.equals(Status.AuditServerOrder.ABNORMAL)){
                            errorOrder = errorOrder+1;
                        }
					}
				}
			}else{
				paramMap.put("total", 0);
			}
			//页面渲染，返回时间修改
			paramMap.put("date_start", date_start);
			paramMap.put("date_end", date_end);
			//查询门店的信息
			if(errorOrder!=0){
				//com.toowell.crm.facade.base.Result<StoreRo> resultStore = storeFacade.getStoreByStoreId(user.getStoreId()+"", RemoteProjectEnum.SH);
				com.qccr.knife.result.Result<StoreRo> resultStore = storeFacade.getStoreById(user.getStoreId());
				if(resultStore.isSuccess() && resultStore.getData() != null){
					StoreRo  store = resultStore.getData();
					Result<UserInfoRo> userInfoRoResult = memchantUserFacade.getUserInfoByUserId(store.getBelongUser());
					if(userInfoRoResult.isSuccess() && userInfoRoResult.getData() != null){
						UserInfoRo userInfoRo = userInfoRoResult.getData();
						paramMap.put("belongUserName", userInfoRo.getName());//门店签约人信息
						paramMap.put("belongUserPhone", userInfoRo.getPhone());
					}
//					paramMap.put("belongUserName", store.getBelongUserName());//门店签约人信息
//					paramMap.put("belongUserPhone", store.getBelongUserPhone());
				}
			}
		} catch (Exception e) {
			log.error("OrderServerController.queryServerOrderList:商户待验证订单查询异常", e);
			return new ModelAndView("/page/common/error.jsp");
		}

		return new ModelAndView("/page/orderServer/orderServer_list.jsp")
				.addObject("auditMap", auditMap)
				.addObject("paramMap", paramMap)
				.addObject("resultMap", serverOrderBOList);
	}

    //导出数据
	@RequestMapping("downLoad")
	public void downLoad(
			@RequestParam(defaultValue="0",required=false)int page_offset,
			@RequestParam(defaultValue="12",required=false)int page_limit,
			@RequestParam(defaultValue="",required=false)String smsCode,
			@RequestParam(defaultValue="",required=false)String orderCode,
			@RequestParam(defaultValue="0",required=false)int service_type_one,
			@RequestParam(defaultValue="0",required=false)int service_type_two,
			@RequestParam(defaultValue="",required=false)String date_start,
			@RequestParam(defaultValue="",required=false)String date_end,
			@RequestParam(defaultValue="-1",required=false)String audit_status,
			@RequestParam(defaultValue="0",required=false)Integer smstime_updown,
			HttpServletResponse response) throws IOException {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ServerOrderBO> serverOrderBOList = null;
		UserMerchantBO user = super.getLoginUser();

		try {
			//查询条件处理开始
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start_d = (date_start.equals("")) ? null : sdf.parse(date_start);
			Date end_d = null;
			if (date_end.equals("")) {
				end_d = null;
			} else {
				end_d = sdf.parse(date_end);
				end_d = new Date(end_d.getTime() + 24 * 60 * 60 * 1000);
			}
			paramMap.put("page_offset", page_offset);
			paramMap.put("startSize", page_offset*page_limit);
			paramMap.put("page_limit", page_limit);
			paramMap.put("smsCode", smsCode);
			paramMap.put("orderCode", orderCode);
			paramMap.put("service_type_one", service_type_one);
			paramMap.put("service_type_two", service_type_two);
			List<Long> serviceTypes = new ArrayList<Long>();
			if(service_type_one!=0){
				if(service_type_two!=0){
					serviceTypes.add(new Long(service_type_two));
				}else{
					//获取1级服务下面的所有二级服务
					List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(user.getStoreId(), service_type_one+"");
					if(categoryRoList!=null && categoryRoList.size()>0){
						for(int i=0;i<categoryRoList.size();i++){
							serviceTypes.add(new Long(categoryRoList.get(i).getCategoryId()));
						}
					}
				}
			}
			paramMap.put("start_date", start_d);
			paramMap.put("end_date", end_d);
			paramMap.put("audit_status", audit_status);
			paramMap.put("storeId", user.getStoreId());
			paramMap.put("smstime_updown", new Integer(smstime_updown));

			ServerOrderQuery serverOrderQuery = new ServerOrderQuery();
			serverOrderQuery.setPageNo(0);
			serverOrderQuery.setPageSize(1);
			if(smsCode!=null && !smsCode.equals("")){
				serverOrderQuery.setSmsCode(smsCode);
			}
			serverOrderQuery.setOrderNo(orderCode);
			if(serviceTypes!=null && serviceTypes.size()>0){
				Long[] serviceTypeArr = new Long[serviceTypes.size()];
				serviceTypes.toArray(serviceTypeArr);
				serverOrderQuery.setServerIdArr(serviceTypeArr);
			}
			if(start_d!=null){
				serverOrderQuery.setBeginDate(new Timestamp(start_d.getTime()));
			}
			if(end_d!=null){
				serverOrderQuery.setEndDate(new Timestamp(end_d.getTime()));
			}
			if(audit_status!=null && !audit_status.equals("-1")){
				serverOrderQuery.setAuditStatus(new Integer(audit_status));
			}
			serverOrderQuery.setStoreId(new Long(user.getStoreId()));
			//待增加排序字段smstime_updown
			serverOrderQuery.setSortType(smstime_updown);
            //查询条件处理结束

			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setGCDisabled(true);
			Workbook wb = Workbook.getWorkbook(new File(OrderServerController.class.getResource("/xls/VerifiedOrderServerModel.xls").getPath()), wbs);
			WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream(), wb);
			wb.close();
			wbs.setWriteAccess(null);
			WritableSheet ws = wwb.getSheet(0);

			Response<Pagination<ServerOrderBO>> paginationResponse = serverOrderExt.queryByStoreIdAndSmscode(serverOrderQuery);
			if(paginationResponse!=null && paginationResponse.isSuccess() && paginationResponse.getData()!=null){
				paramMap.put("total", paginationResponse.getData().getTotalCount());
				serverOrderBOList = paginationResponse.getData().getDataList();
				if(serverOrderBOList!=null){
					serverOrderQuery.setPageNo(0);
					serverOrderQuery.setPageSize(paginationResponse.getData().getTotalCount());
					paginationResponse = serverOrderExt.queryByStoreIdAndSmscode(serverOrderQuery);
					if(paginationResponse!=null && paginationResponse.isSuccess() && paginationResponse.getData()!=null){
						serverOrderBOList = paginationResponse.getData().getDataList();
						if(serverOrderBOList!=null){
							for (int i = 0, rowIndex = 1, rowSize = serverOrderBOList.size(); i < rowSize; i++, rowIndex = i + 1) {
								this.insertRow(ws, rowIndex, serverOrderBOList.get(i));
							}
						}
					}
				}
			}
			String filename = "已验证订单" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xls";
			FileUtil.downFile(wwb, response, filename);
		} catch (Exception e) {
			log.error("OrderServerController.queryServerOrderList:商户待验证订单查询异常", e);
		}
	}

	private void insertRow(WritableSheet ws, int rowIndex, ServerOrderBO serverOrderBO)
			throws RowsExceededException, WriteException {
		int cellIndex = 0;
		if(serverOrderBO!=null){
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(serverOrderBO.getOrderNo())));
			cellIndex++;
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(serverOrderBO.getServerName())));
			cellIndex++;
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(serverOrderBO.getSaleNum())));
			cellIndex++;
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(serverOrderBO.getSaleSprice())));
			cellIndex++;
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(serverOrderBO.getSmsCode())));
			cellIndex++;
			String smsTimeStr ="";
			if(serverOrderBO.getSmsTime()!=null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				smsTimeStr = dateFormat.format(serverOrderBO.getSmsTime());
			}
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(smsTimeStr)));
			cellIndex++;
			String auditStatusName = "";
			int auditStatus = 0;
			if(serverOrderBO.getAuditStatus()!=null){
				auditStatus = serverOrderBO.getAuditStatus().intValue();
				if(auditStatus==0){
					auditStatusName = "审核通过";
				}else if(auditStatus==1){
					auditStatusName = "异常";
				}else if(auditStatus==2){
					auditStatusName = "待审核";
				}
			}
			ws.addCell(new Label(cellIndex, rowIndex, ObjectUtils.toString(auditStatusName)));
			cellIndex++;
		}
	}
}
