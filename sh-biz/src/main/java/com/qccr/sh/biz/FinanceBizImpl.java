package com.qccr.sh.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qccr.sh.dao.FinanceQueryDao;
import com.qccr.sh.domain.OrderServer;
import com.qccr.sh.domain.OrderServerExample;

@Service
@Transactional
public class FinanceBizImpl implements FinanceBiz {

	@Autowired
	private FinanceQueryDao financeQueryDao;
	

	@Override
	public List<Map<String, Object>> queryFinance(Map<String, Object> map) {

		return financeQueryDao.queryFinance(map);
	}

	@Override
	public long queryFinanceSize(Map<String, Object> map) {
		return financeQueryDao.queryFinanceSize(map);
	}
	
	@Override
	public List<Map<String, Object>> queryFinanceDetail(long id) {
		
//		OrderServerExample example = new OrderServerExample();
//		example.or().andClearIdEqualTo((int)id);
//		List<OrderServer> list = orderServerMapper.selectByExample(example);
		
		
		
		
		

		return financeQueryDao.queryFinanceDetail(id);
	}


}
