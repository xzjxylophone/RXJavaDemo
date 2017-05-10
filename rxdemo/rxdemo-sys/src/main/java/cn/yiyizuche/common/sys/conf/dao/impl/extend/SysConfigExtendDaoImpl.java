package cn.yiyizuche.common.sys.conf.dao.impl.extend;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.conf.dao.extend.SysConfigExtendDao;
import cn.yiyizuche.common.sys.conf.dao.impl.SysConfigDaoImpl;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.conf.dao.impl.extend 
 * @Class : SysConfigExtendDaoImpl.java 
 * @Description :系统配置Extend Dao Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年4月12日 下午3:45:53 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="sysConfigExtendDao")
public class SysConfigExtendDaoImpl extends SysConfigDaoImpl implements SysConfigExtendDao {

	/**
	 * 通过参数名称搜索配置(非 Javadoc) 
	 *   
	 * @param name
	 * @return  
	 * @see cn.yiyizuche.common.sys.conf.dao.extend.SysConfigExtendDao#selectByConfigName(java.lang.String)
	 */
	@Override
	public SysConfig selectByConfigName(String name) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByConfigName", name);
	}

	/**
	 * 通过参数键搜索配置(非 Javadoc) 
	 *   
	 * @param key
	 * @return  
	 * @see cn.yiyizuche.common.sys.conf.dao.extend.SysConfigExtendDao#selectByConfigKey(java.lang.String)
	 */
	@Override
	public SysConfig selectByConfigKey(String key) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByConfigKey", key);
	}

	/**
	 * 分页查询(非 Javadoc) 
	 *   
	 * @param page
	 * @param paramsMap
	 * @return  
	 * @see cn.yiyizuche.common.sys.conf.dao.extend.SysConfigExtendDao#selectConfigByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<SysConfig> selectConfigByPage(Page<SysConfig> page, Map<String, Object> paramsMap) {
		this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", paramsMap);
		return page;
	}
}
