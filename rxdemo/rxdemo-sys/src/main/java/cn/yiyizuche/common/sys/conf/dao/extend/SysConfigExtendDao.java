package cn.yiyizuche.common.sys.conf.dao.extend;

import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.conf.dao.SysConfigDao;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.conf.dao.extend 
 * @Class : SysConfigExtendDao.java 
 * @Description :系统配置Extend Dao
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年4月12日 下午3:45:35 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface SysConfigExtendDao extends SysConfigDao {

	/**
	 * 
	 * @Method: selectByConfigName  
	 * @Description: 通过参数名称搜索配置
	 * @param name
	 * @return SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:46:21
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
	 * @CreateDate : 2017年4月12日 下午3:46:24
	 */
	SysConfig selectByConfigKey(String key);
	
	/**
	 * 
	 * @Method: selectConfigByPage  
	 * @Description:分页查询
	 * @param page
	 * @param paramsMap
	 * @return Page<SysConfig> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:46:28
	 */
	Page<SysConfig> selectConfigByPage(Page<SysConfig> page, Map<String, Object> paramsMap);
}
