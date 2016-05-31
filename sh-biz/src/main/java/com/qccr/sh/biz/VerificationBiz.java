package com.qccr.sh.biz;

import java.util.Map;

public interface VerificationBiz {

	public abstract Map<String, Object> findOrder(String smsCode, String username);

	public abstract boolean verification(String smsCode, String username);

}
