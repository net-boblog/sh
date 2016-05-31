package com.qccr.sh.biz;

public interface UserTraceBiz {

	public abstract void recordTrace(String username, String tracecontent, String clientip);

}
