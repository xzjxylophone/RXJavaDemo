package cn.yiyizuche.common.sys.conf.service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.conf.service 
 * @Class : SysConfigService.java 
 * @Description :系统配置Service
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年4月12日 下午3:46:02 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface SysConfigService {
	
	/**
	 * 
	 * @Method: addConfig  
	 * @Description:添加配置
	 * @param sysConfig
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:48:54
	 */
	ResultMsg addConfig(SysConfig sysConfig);
	
	/**
	 * 
	 * @Method: deleteByConfigId  
	 * @Description:删除配置
	 * @param id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:48:57
	 */
	ResultMsg deleteByConfigId(int id);
	
	/**
	 * 
	 * @Method: updateConfig  
	 * @Description:更新配置
	 * @param sysConfig
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:49:01
	 */
	ResultMsg updateConfig(SysConfig sysConfig);

	/**
	 * 
	 * @Method: selectByConfigName  
	 * @Description:通过参数名称搜索配置
	 * @param name
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:47:10
	 */
	SysConfig selectByConfigName(String name);
	
	/**
	 * 
	 * @Method: selectByConfigKey  
	 * @Description:通过参数键搜索配置
	 * @param key
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:47:13
	 */
	SysConfig selectByConfigKey(String key);
	
	/**
	 * 
	 * @Method: selectByConfigId  
	 * @Description:通过Id搜索配置
	 * @param id
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:49:04
	 */
	SysConfig selectByConfigId(int id);
	
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
	 * @CreateDate : 2017年4月12日 下午3:47:08
	 */
	Page<SysConfig> selectConfigListByName(String name, String key, Page<SysConfig> page);
}
