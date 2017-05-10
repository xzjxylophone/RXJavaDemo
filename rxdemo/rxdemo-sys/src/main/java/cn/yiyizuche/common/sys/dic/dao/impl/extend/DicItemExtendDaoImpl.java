package cn.yiyizuche.common.sys.dic.dao.impl.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao;
import cn.yiyizuche.common.sys.dic.dao.impl.DicItemDaoImpl;
import cn.yiyizuche.common.sys.dic.entity.DicItem;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.dao.impl.extend 
 * @Class : DicItemExtendDaoImpl.java 
 * @Description : 字典项扩展数据层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:31:42 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="dicItemExtendDao")
public class DicItemExtendDaoImpl extends DicItemDaoImpl implements DicItemExtendDao{
	protected static final String NAMESPACE="DicItemMapper";
	/**
	 * (非 Javadoc) 
	 * 根据字典id删除字典项
	 *   
	 * @param dicId  字典id
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao#deleteByDicId(int)
	 */
	@Override
	public void deleteByDicId(int dicId) {
		this.getSqlSession().delete(NAMESPACE+".deleteByDicId", dicId);
	}
	/**
	 * (非 Javadoc) 
	 * 分页查询字典项
	 *   
	 * @param page
	 * @param paramsMap
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao#searchDicItemByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<DicItem> searchDicItemByPage(Page<DicItem> page, Map<String, Object> paramsMap) {
		return this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", paramsMap);
	}
	/**
	 * (非 Javadoc) 
	 *   启用、禁用字典项
	 *   
	 * @param params  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao#updateDicItemStatus(java.util.Map)
	 */
	@Override
	public void updateDicItemStatus(Map<String, Object> params) {
		this.getSqlSession().update(NAMESPACE + ".updateDicItemStatus", params);
	}
	/**
	 * (非 Javadoc) 
	 * 查询数据库中同一字典下同名的字典项数量
	 *   
	 * @param param
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao#selectSameDicItemNameCount(java.util.Map)
	 */
	@Override
	public int selectSameDicItemNameCount(Map<String, Object> param) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectSameDicItemNameCount", param);
	}
	/**
	 * (非 Javadoc) 
	 * 根据字典id查询字典项集合
	 *   
	 * @param params
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao#selectDicItemListByDicId(java.util.Map)
	 */
	@Override
	public List<DicItem> selectDicItemListByDicId(Map<String, Object> params) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectDicItemListByDicId", params);
	}

}
