package com.qccr.sh.remote.sms;

import cn.ld.sdk.client.api.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 短信业务管理类
 * 
 * @author 庞健松 ~ 2015-4-14 下午03:22:05
 */
public class SMSSender {

	private static final Logger log = LoggerFactory.getLogger(SMSSender.class);

	private Client client = null;

	public SMSSender() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("emay");
		try {
			// 对方说用key，那就用key好了
			client = new Client(bundle.getString("softwareSerialNo"), bundle.getString("key"));
		} catch (Exception e) {
			log.error("创建短信客户端实例异常");
			throw new RuntimeException("创建短信客户端实例异常", e);
		}
	}

	public void sendSMS(final String phoneNo, final String content) {

		log.info("SMS send to " + phoneNo + ". Content as follows:");
		log.info(content);
		try {
			new Thread() {
				public void run() {
					String[] phoneNums = new String[] { phoneNo };
					int i = client.sendSMS(phoneNums, content, 3);// 带扩展码
					if (i != 0)
						log.error("send SMS [fail]. 手机号 : " + phoneNums[0] + ",返回码： " + i);
					else
						log.info("send SMS [success]. 手机号 : " + phoneNums[0]);
				}
			}.start();
		} catch (RuntimeException e) {
			log.error("SMSSender.sendSMS(String phoneNo, String content) error ", e);
		}
	}

}
