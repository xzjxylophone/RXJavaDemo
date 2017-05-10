package cn.yiyizuche.common.sys.dic.dao.extend;

import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.dic.dao.DictionaryDao;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.dao.extend 
 * @Class : DictionaryExtendDao.java 
 * @Description : 字典扩展数据层接口
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:25:41 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface DictionaryExtendDao extends DictionaryDao{

	/**
	 * 
	 * @Method: selectDicByPage  
	 * @Description: 分页查询字典列表
	 * @param page 分页对象
	 * @param paramsMap 查询条件
	 * @return Page<Dictionary> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午5:22:43
	 */
	Page<Dictionary> selectDicByPage(Page<Dictionary> page, Map<String, Object> paramsMap);
	/**
	 * 
	 * @Method: updateDicStatus  
	 * @Description: 启用、禁用字典
	 * @param params 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:05:02
	 */
	void updateDicStatus(Map<String, Object> params);
	/**
	 * 
	 * @Method: selectSameDicNameCount  
	 * @Description: 查询字典同名数量
	 * @param param
	 * @return int 同名数量 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午3:46:27
	 */
	int selectSameDicNameCount(Map<String, Object> param);
	
}