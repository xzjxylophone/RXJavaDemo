package cn.yiyizuche.common.sys.dic.dao.impl.extend;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao;
import cn.yiyizuche.common.sys.dic.dao.impl.DictionaryDaoImpl;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.dao.impl.extend 
 * @Class : DictionaryExtendDaoImpl.java 
 * @Description : 字典扩展数据层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:31:18 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="dictionaryExtendDao")
public class DictionaryExtendDaoImpl extends DictionaryDaoImpl implements DictionaryExtendDao{
	protected static final String NAMESPACE="DictionaryMapper";
	/**
	 * (非 Javadoc) 
	 * 分页查询字典列表
	 *   
	 * @param page 分页对象
	 * @param paramsMap 查询条件
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao#selectDicByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<Dictionary> selectDicByPage(Page<Dictionary> page, Map<String, Object> paramsMap) {
		return this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", paramsMap);
	}
	/**
	 * (非 Javadoc) 
	 * 启用、禁用字典
	 *   
	 * @param params  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao#updateDicStatus(java.util.Map)
	 */
	@Override
	public void updateDicStatus(Map<String, Object> params) {
		this.getSqlSession().update(NAMESPACE + ".updateDicStatus", params);
	}
	/**
	 * (非 Javadoc) 
	 *   查询字典同名数量
	 *   
	 * @param param 
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao#selectSameDicNameCount(int, java.lang.String)
	 */
	@Override
	public int selectSameDicNameCount(Map<String, Object> param) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectSameDicNameCount", param);
	}


}
