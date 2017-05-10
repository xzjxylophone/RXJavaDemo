package cn.yiyizuche.common.sys.dic.service;

import java.util.List;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.entity.DicItem;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service 
 * @Class : DicItemService.java 
 * @Description : 字典项业务逻辑层接口
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:36:55 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface DicItemService {
	/**
	 * 
	 * @Method: searchDicItemByPage  
	 * @Description: 分页查询字典项
	 * @param page
	 * @param dicId 字典id
	 * @param itemName 字典项名称
	 * @param itemStatus 字典项状态
	 * @return Page<DicItem> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午5:58:28
	 */
	Page<DicItem> searchDicItemByPage(Page<DicItem> page, int dicId, String itemName, int itemStatus);

	/**
	 * 
	 * @Method: updateDicItem  
	 * @Description: 修改字典
	 * @param dicItem 字典实体
	 * @return ResultMsg (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:42:42
	 */
	ResultMsg updateDicItem(DicItem dicItem);
	/**
	 * 
	 * @Method: selectByDicItemId  
	 * @Description: 根据字典项id查询字典项实体
	 * @param dicItemId 字典项id
	 * @return DicItem (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:42:59
	 */
	DicItem selectByDicItemId(int dicItemId);

	/**
	 * 
	 * @Method: deleteByDicItemId  
	 * @Description:根据字典项id删除字典项
	 * @param dicItemId 字典项id
	 * @return ResultMsg (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:43:27
	 */
	ResultMsg deleteByDicItemId(int dicItemId);
	/**
	 * 
	 * @Method: addDicItem  
	 * @Description: 添加字典
	 * @param dicItem 字典实体
	 * @return ResultMsg (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:43:59
	 */
	ResultMsg addDicItem(DicItem dicItem);
	/**
	 * 
	 * @Method: updateDicItemStatus  
	 * @Description: 启用、禁用字典项
	 * @param itemId 字典项id
	 * @param itemStatus 字典项状态
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:38:43
	 */
	ResultMsg updateDicItemStatus(int itemId, int itemStatus);

	/**
	 * 
	 * @Method: selectDicItemListByDicId  
	 * @Description: 根据字典id查询字典项集合
	 * @param dicId 字典id
	 * @return List<DicItem> 字典项集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午6:43:59
	 */
	List<DicItem> selectDicItemListByDicId(int dicId);

}
