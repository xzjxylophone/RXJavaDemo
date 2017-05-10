package cn.yiyizuche.common.sys.conf.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.conf.dao.extend.SysConfigExtendDao;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;
import cn.yiyizuche.common.sys.conf.service.SysConfigService;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.conf.service.impl 
 * @Class : SysConfigServiceImpl.java 
 * @Description :系统配置Service Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年4月12日 下午3:46:13 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Resource
	private SysConfigExtendDao sysConfigExtendDao;

	/**
	 * 
	 * @Method: addConfig  
	 * @Description:添加配置
	 * @param sysConfig
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:15
	 */
	@Override
	public ResultMsg addConfig(SysConfig sysConfig) {
		ResultMsg resultMsg = new ResultMsg();
		
		if (sysConfig == null || sysConfig.getConfName() == null || sysConfig.getConfName().equals("")) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_NAME_EMPTY);
			return resultMsg;
		}
		
		SysConfig selectSysConfig = this.selectByConfigName(sysConfig.getConfName());
		if (selectSysConfig != null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_NAME_EXIST);
			return resultMsg;
		}
		
		if (sysConfig.getConfKey() == null || sysConfig.getConfKey().equals("")) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_KEY_EMPTY);
			return resultMsg;
		}

		SysConfig keySysConfig = this.selectByConfigKey(sysConfig.getConfKey());
		if (keySysConfig != null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_KEY_EXIST);
			return resultMsg;
		}
		
		this.sysConfigExtendDao.insert(sysConfig);		
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		return resultMsg;
	}

	/**
	 * 
	 * @Method: deleteByConfigId  
	 * @Description:删除配置
	 * @param id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:20
	 */
	@Override
	public ResultMsg deleteByConfigId(int id) {
		ResultMsg resultMsg = new ResultMsg();
		this.sysConfigExtendDao.deleteByPrimaryKey(id);
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		return resultMsg;
	}

	/**
	 * 
	 * @Method: updateConfig  
	 * @Description:更新配置
	 * @param sysConfig
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:22
	 */
	@Override
	public ResultMsg updateConfig(SysConfig sysConfig) {
		ResultMsg resultMsg = new ResultMsg();
		if (sysConfig == null || sysConfig.getConfName() == null || sysConfig.getConfName().equals("")) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_NAME_EMPTY);
			return resultMsg;
		}
		
		SysConfig selectSysConfig = this.selectByConfigName(sysConfig.getConfName());
		if (selectSysConfig != null && selectSysConfig.getId() != sysConfig.getId()) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_NAME_EXIST);
			return resultMsg;
		}
		
		if (sysConfig.getConfKey() == null || sysConfig.getConfKey().equals("")) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_KEY_EMPTY);
			return resultMsg;
		}

		SysConfig keySysConfig = this.selectByConfigKey(sysConfig.getConfKey());
		if (keySysConfig != null && keySysConfig.getId() != sysConfig.getId()) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.CONFIG_KEY_EXIST);
			return resultMsg;
		}
		
		SysConfig instance = this.selectByConfigId(sysConfig.getId());
		instance.setConfName(sysConfig.getConfName());
		instance.setConfType(sysConfig.getConfType());
		instance.setConfKey(sysConfig.getConfKey());
		instance.setConfValue(sysConfig.getConfValue());
		instance.setConfDesc(sysConfig.getConfDesc());
		instance.setUpdateUser(sysConfig.getUpdateUser());
		instance.setUpdateTime(new Date());
		this.sysConfigExtendDao.updateByPrimaryKey(instance);
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		return resultMsg;
	}

	/**
	 * 
	 * @Method: selectByConfigName  
	 * @Description:通过参数名称搜索配置
	 * @param name
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:28
	 */
	@Override
	public SysConfig selectByConfigName(String name) {
		return this.sysConfigExtendDao.selectByConfigName(name);
	}
	
	/**
	 * 
	 * @Method: selectByConfigKey  
	 * @Description:通过参数键搜索配置
	 * @param key
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:31
	 */
	@Override
	public  SysConfig selectByConfigKey(String key) {
		return this.sysConfigExtendDao.selectByConfigKey(key);
	}

	/**
	 * 
	 * @Method: selectByConfigId  
	 * @Description:通过Id搜索配置
	 * @param id
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:34
	 */
	@Override
	public SysConfig selectByConfigId(int id) {
		return this.sysConfigExtendDao.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @Method: selectConfigListByName  
	 * @Description:分页查询
	 * @param name
	 * @param key
	 * @param page
	 * @return Page<SysConfig> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:51:37
	 */
	@Override
	public Page<SysConfig> selectConfigListByName(String name, String key, Page<SysConfig> page) {
		Map<String,Object> parameMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(name)){
			parameMap.put("configName", name.trim());
		}
		if(StringUtils.isNotEmpty(key)){
			parameMap.put("configKey", key.trim());
		}
		return sysConfigExtendDao.selectConfigByPage(page, parameMap);
	}
}
