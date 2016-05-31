package com.qccr.sh.remote.http;

import org.apache.http.client.methods.HttpGet;

import com.alibaba.fastjson.JSONObject;

public class GetMyAddrTest {

	public static void smokeTest() throws Exception {

		HttpGet get = new HttpGet();
		HttpUtil.setCommonHeader(get);
		
		
//		JSONObject obj = HttpUtil.execute("/order/update?type=2&smsCode=7H6SDQIN&orderId=114&serverOrderId=301", get);

		
		//Assert.assertEquals(obj.getIntValue("code"), 0);

	}

	public static void main(String[] args) throws Exception {
		// System.out.println(StringUtil.md5Encrypt("Leap"));
		System.out.println(123);
		GetMyAddrTest.smokeTest();
	}
}
