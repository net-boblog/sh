package com.qccr.sh.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qccr.sh.domain.Config;
import com.qccr.sh.mapper.ConfigMapper;

@Service
@Transactional
public class ConfigBizImpl implements ConfigBiz {

	@Autowired
	private ConfigMapper configMapper;

	@Override
	@Cacheable(value = "config")  
	public String getValue(String key) {

		Config config = configMapper.selectByPrimaryKey(key);
		if (null != config) {
			return config.getCv();
		}
		return null;
	}

}
