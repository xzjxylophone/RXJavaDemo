package cn.yiyizuche.common.sys.conf.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.ou.user.service.impl.UserServiceTest;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;
import cn.yiyizuche.common.sys.conf.service.SysConfigService;

public class SysConfigServiceTest extends BaseTest {

	@Autowired
	private SysConfigService sysConfigService;
	@Test
	public void test_sysConfig() {
		
		SysConfig sysConfig = this.sysConfigService.selectByConfigId(1);
		
		_log.info("config name:" + sysConfig.getConfName());
		
	}
	
	
	private static final Log _log = LogFactory.getLog(SysConfigServiceTest.class);

	
}
