package cn.yiyizuche.common.sys.dic.dao.extend;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.sys.dic.dao.DicItemDao;
import cn.yiyizuche.common.sys.dic.entity.DicItem;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.dao.extend 
 * @Class : DicItemExtendDao.java 
 * @Description : 字典项扩展数据层接口 
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:25:05 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface DicItemExtendDao extends DicItemDao{
	/**
	 * 
	 * @Method: deleteByDicId  
	 * @Description: 根据字典id删除字典项
	 * @param dicId 字典id
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午4:36:47
	 */
	void deleteByDicId(int dicId);

	/**
	 * 
	 * @Method: searchDicItemByPage  
	 * @Description: 分页查询字典项
	 * @param page
	 * @param paramsMap
	 * @return Page<DicItem> (返回类型描述) 
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:02:56
	 */
	Page<DicItem> searchDicItemByPage(Page<DicItem> page, Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @Method: updateDicItemStatus  
	 * @Description: 启用、禁用字典项
	 * @param params void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:42:33
	 */
	void updateDicItemStatus(Map<String, Object> params);

	/**
	 * 
	 * @Method: selectSameDicItemNameCount  
	 * @Description: 查询数据库中同一字典下同名的字典项数量
	 * @param param
	 * @return int 同名的字典项数量
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午4:40:35
	 */
	int selectSameDicItemNameCount(Map<String, Object> param);

	/**
	 * 
	 * @Method: selectDicItemListByDicId  
	 * @Description: 根据字典id查询字典项集合
	 * @param params
	 * @return List<DicItem> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午6:46:25
	 */
	List<DicItem> selectDicItemListByDicId(Map<String, Object> params);
	
}