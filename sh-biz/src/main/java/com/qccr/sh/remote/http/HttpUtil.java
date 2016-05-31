package com.qccr.sh.remote.http;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.util.BT;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * 这里基本上测试一次就重新加载，所以httpClient就每次去创建与释放
 * @author 庞健松 ~ 2014-1-22 上午11:53:26
 */
public class HttpUtil {
	private static final String ENCODING = "utf-8";
	
	public static final String domain = BT.getProperty("domain", "/http.properties");
	
	/**
	 * 设置公用报头信息（clientAgent,userId,apiVersion）
	 * userId = 1000007001240
	 * @param method
	 */
	public static void setCommonHeader(HttpMessage method){
		method.setHeader("Token", BT.getProperty("Token", "/http.properties"));
		method.setHeader("Version", BT.getProperty("Version", "/http.properties"));
		method.setHeader("Source",BT.getProperty("Source", "/http.properties"));
		method.setHeader("Net",BT.getProperty("Net", "/http.properties"));
		method.setHeader("Channel",BT.getProperty("Channel", "/http.properties"));
		method.setHeader("UserId",BT.getProperty("UserId", "/http.properties"));
		method.setHeader("IMEI",BT.getProperty("IMEI", "/http.properties"));
	}
	
	public static JSONObject execute(String domain, String path, HttpRequestBase request){
		return executeInit(domain+path, request);
	}
	
	public static JSONObject execute(String path, HttpRequestBase request){
		return executeInit(domain+path, request);
	}
	
	/**
	 * 执行请求
	 * @param url
	 * @param request
	 */
	private static JSONObject executeInit(String url, HttpRequestBase request){
		
		CloseableHttpClient httpClient = null;
		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			System.out.println("now execute ==>> "+request.getRequestLine());
			System.out.println(url);
			/*if("POST".equals(request.getMethod())){
				System.out.println(is2String(((HttpPost)request).getEntity().getContent()));
			}*/
			request.setURI(URI.create(url));
			long a = System.nanoTime();
			System.out.println("==========>>> This is result =======");
	//		HttpContext localContext = new BasicHttpContext();
	        response = httpClient.execute(request);
	        System.out.println("Execute Time : "+(System.nanoTime()-a)/1000000 +"ms");
	        System.out.println("Http Status Code : "+response.getStatusLine().getStatusCode());
	        entity = response.getEntity();
	        if (response.getStatusLine().getStatusCode() == 200) {
//				showCommon(response);
//				String contentMimeType = ContentType.getOrDefault(entity).getMimeType();  
//			    System.out.println(contentMimeType);
			    String result = EntityUtils.toString(entity, ENCODING);
			    System.out.println(result);
			    return JSONObject.parseObject(result);
	        }
		}catch (IOException e) {
			throw new RuntimeException(e);
		} finally{
             IOUtils.closeQuietly(response);
             IOUtils.closeQuietly(httpClient);
		}
		return new JSONObject();
	}
	private static String is2String(InputStream is) throws IOException{
	    BufferedReader in = new BufferedReader(new InputStreamReader(is));
	    StringBuilder buffer = new StringBuilder();
	    String line = "";
	    while ((line = in.readLine()) != null){
	      buffer.append(line);
	    }
	    return buffer.toString();
	}
	private static void showCommon(){
//		String stateCode = response.getFirstHeader("result-code").getValue();
//		System.out.println(new StringBuilder("结果码 : ").append(stateCode).append("	---	").append(Config.getValue(stateCode)).toString());
	}
}
