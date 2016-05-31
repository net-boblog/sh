package com.qccr.sh.dao;

import java.util.List;
import java.util.Map;

public interface FinanceQueryDao {

	public abstract List<Map<String, Object>> queryFinance(Map<String, Object> map);
	
	long queryFinanceSize(Map<String, Object> map);

	public abstract List<Map<String, Object>> queryFinanceDetail(long id);
	
	//public abstract BigDecimal queryFinanceClearTotal(List<Integer> idlist);

}
