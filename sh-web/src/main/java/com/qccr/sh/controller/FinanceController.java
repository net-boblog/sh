package com.qccr.sh.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qccr.knife.result.Result;
import com.qccr.ordercenter.facade.entity.store.AchievementAnalysisRo;
import com.qccr.ordercenter.facade.service.order.ServerOrderFacade;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.FinanceBiz;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Controller
@RequestMapping(value = "/page/finance")
public class FinanceController extends BaseModule {

	private static final Logger log = LoggerFactory.getLogger(FinanceController.class);

	@Autowired
	private FinanceBiz financeBiz;

	@Autowired
	private ServerOrderFacade serverOrderFacade;

	@RequestMapping(value = "/queryFinance")
	public void queryFinance(HttpServletRequest request, HttpServletResponse response) {

		try {

			String js_date_start = URLDecoder.decode(request.getParameter("js_date_start"), "UTF-8");
			String js_date_end = URLDecoder.decode(request.getParameter("js_date_end"), "UTF-8");
			String js_status = URLDecoder.decode(request.getParameter("js_status"), "UTF-8");
			String smstime_updown = URLDecoder.decode(request.getParameter("smstime_updown"), "UTF-8");
			String page_offset = URLDecoder.decode(request.getParameter("page_offset"), "UTF-8");
			String page_limit = URLDecoder.decode(request.getParameter("page_limit"), "UTF-8");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date start_d = (js_date_start == null || "".equals(js_date_start)) ? null : sdf.parse(js_date_start);

			Date end_d = null;
			if (js_date_end == null || "".equals(js_date_end)) {
				end_d = null;
			} else {
				end_d = sdf.parse(js_date_end);
				end_d = new Date(end_d.getTime() + 24 * 60 * 60 * 1000);
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", request.getRemoteUser());
			map.put("js_date_start", start_d);
			map.put("js_date_end", end_d);
			map.put("js_status", new Integer(js_status));
			map.put("smstime_updown", new Integer(smstime_updown));
			map.put("page_offset", new Integer(page_offset));
			map.put("page_limit", new Integer(page_limit));

			log.info("查询参数：" + map.toString());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "1");
			jsonObject.put("data", financeBiz.queryFinance(map));
			jsonObject.put("total", financeBiz.queryFinanceSize(map));
			log.info(jsonObject.toJSONString());
			IOUtils.responsePrint(response, jsonObject.toString());

		} catch (Exception e) {
			log.error("调用业绩分析接口FinanceController.queryFinance异常", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", e.getMessage());
			IOUtils.responsePrint(response, jsonObject.toString());
		}

	}

	@RequestMapping(value = "/queryFinanceDetail")
	public void queryFinanceDetail(HttpServletRequest request, HttpServletResponse response) {

		try {

			String clear_id = URLDecoder.decode(request.getParameter("clear_id"), "UTF-8");

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "1");
			jsonObject.put("info", "查询成功");
			jsonObject.put("data", financeBiz.queryFinanceDetail(Integer.parseInt(clear_id)));
			log.info(jsonObject.toJSONString());

			IOUtils.responsePrint(response, jsonObject.toString());

		} catch (Exception e) {
			log.error("调用业绩分析接口FinanceController.queryFinanceDetail异常", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "0");
			jsonObject.put("info", e.getMessage());
			IOUtils.responsePrint(response, jsonObject.toString());
		}

	}

	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServletOutputStream responOut = response.getOutputStream();
		File file = new File(OrderQueryController.class.getResource("/xls/FinanceDetailModel.xls").getPath());
		Workbook wb = null;
		try {

			String clear_id = URLDecoder.decode(request.getParameter("clear_id"), "UTF-8");

			List<Map<String, Object>> list = financeBiz.queryFinanceDetail(Integer.parseInt(clear_id));

			wb = Workbook.getWorkbook(file, new WorkbookSettings());

			WritableWorkbook wwb = Workbook.createWorkbook(responOut, wb, new WorkbookSettings());
			WritableSheet ws = wwb.getSheet(0);

			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> imap = list.get(i);

				ws.addCell(new Label(0, i + 1, "" + imap.get("no")));
				ws.addCell(new Label(1, i + 1, "" + imap.get("content")));
				ws.addCell(new Label(2, i + 1, "" + imap.get("sum")));
				ws.addCell(new Label(3, i + 1, "" + imap.get("userinfo")));
				ws.addCell(new Label(4, i + 1, "" + imap.get("server_type")));
				ws.addCell(new Label(5, i + 1, "" + imap.get("price")));
				ws.addCell(new Label(6, i + 1, "" + imap.get("sms_code")));
				ws.addCell(new Label(7, i + 1, "" + imap.get("sms_date")));

			}
			wb.close();
			Date ddd = new Date();
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");

			String filename = sdf3.format(ddd) + ".xls";

			downloadExcelFile(wwb, response, filename);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	public static boolean downloadExcelFile(WritableWorkbook wwb, HttpServletResponse response, String fileName)
			throws IOException {
		OutputStream filetoClient = null;
		try {
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
			response.setContentType("application/octet-stream; charset=utf-8");
			filetoClient = response.getOutputStream();
		} catch (IOException e) {
			log.error("can not download", e);
			// e.printStackTrace();
			response.sendError(405, "File cannot found!");
			return false;
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (WriteException e1) {
				log.error("can not download" + e1);
				response.sendError(405, "download error!");
			}
			try {
				if (filetoClient != null) {
					filetoClient.flush();
					filetoClient.close();
				}
				filetoClient = null;
			} catch (IOException e) {
				log.error("can not download", e);
				response.sendError(405, "download error!");
			}
		}
		return true;
	}

	@RequestMapping(value = "/performance_analysis")
	public String performanceAnalysis(Model model) {
		UserMerchantBO user = super.getLoginUser();
		if (user != null && user.getStoreId() > 0) {
			Result<AchievementAnalysisRo> achievementAnalysisRoResult = serverOrderFacade.getAchievementAnalysisData(user.getStoreId());
			if(achievementAnalysisRoResult!=null && achievementAnalysisRoResult.isSuccess() && achievementAnalysisRoResult.getData()!=null){
				AchievementAnalysisRo achievementAnalysisRo = achievementAnalysisRoResult.getData();
				model.addAttribute("performance_analysis", JSON.toJSONString(achievementAnalysisRo));
			}
		}

		return "/page/finance/performance_analysis.jsp";
	}
}
