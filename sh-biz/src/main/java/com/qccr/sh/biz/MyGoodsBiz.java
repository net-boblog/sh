package com.qccr.sh.biz;

import java.util.List;
import com.qccr.sh.external.crm.bo.ProductBO;

public interface MyGoodsBiz {

	public abstract List<ProductBO> queryGoodsByUsername(String username);

}
