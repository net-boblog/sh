package com.qccr.sh.biz;

import java.util.List;
import java.util.Map;

public interface FinanceBiz {

	public abstract List<Map<String, Object>> queryFinance(Map<String, Object> map);
	
	public abstract long queryFinanceSize(Map<String, Object> map);

	public abstract List<Map<String, Object>> queryFinanceDetail(long id);

}
