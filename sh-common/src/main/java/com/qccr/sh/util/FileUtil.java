package com.qccr.sh.util;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static void downFile(WritableWorkbook wwb, HttpServletResponse response, String fileName)
			throws IOException {
		OutputStream filetoClient = null;
		try {
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
			response.setContentType("application/octet-stream; charset=utf-8");
			filetoClient = response.getOutputStream();
			wwb.write();
		} catch (IOException e) {
			log.error("can not download",e);
			response.sendError(405, "File cannot found!");
		} finally {
			try {
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
	}

	public static File uploadFile(CommonsMultipartFile srcFile, String parentPath) throws IOException {
		if (srcFile == null) {
			return null;
		}

		File fileUploadDirectory = new File(parentPath);
		FileUtils.forceMkdir(fileUploadDirectory);// 创建文件上传的目录

		String originalFilename = srcFile.getOriginalFilename();
		String fileName = originalFilename.substring(0, srcFile.getOriginalFilename().lastIndexOf("."))
				+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
				+ originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件名

		File diskFile = FileUtils.getFile(fileUploadDirectory, fileName);
		srcFile.transferTo(diskFile);

		return diskFile;
	}
}
