package cn.yiyizuche.common.sys.dic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.entity.DicItem;
import cn.yiyizuche.common.sys.dic.service.DicItemService;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dicItem.controller 
 * @Class : DicItemController.java 
 * @Description : 字典项控制层
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午4:00:27 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@RequestMapping("/sys/dicItem")
@Controller
public class DicItemController {
	@Autowired
	private DicItemService dicItemService;
	
	/**
	 * 
	 * @Method: searchDicItemByPage  
	 * @Description: 分页查询字典项
	 * @param dicId 字典id
	 * @param itemName 字典项名称
	 * @param current
	 * @param rowCount
	 * @return Map<String,Object> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午5:57:25
	 */
	@RequestMapping(value = "/searchDicItemByPage",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> searchDicItemByPage(int dicId, String itemName ,int itemStatus, int current, int rowCount){
		Page<DicItem> page = new Page<DicItem>(current, rowCount);
		Page<DicItem> pageResult = this.dicItemService.searchDicItemByPage(page, dicId, itemName, itemStatus);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	 }
	
	/**
	 * 
	 * @Method: addDicItem  
	 * @Description: 添加字典项
	 * @param dicItem 字典项实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:37:07
	 */
	@RequestMapping(value = "/addDicItem",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addDicItem(DicItem dicItem){
		return dicItemService.addDicItem(dicItem);
	}
	
	/**
	 * 
	 * @Method: updateDicItem  
	 * @Description: 修改字典项
	 * @param dicItem 字典项实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:38:28
	 */
	@RequestMapping(value = "/updateDicItem",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateDicItem(DicItem dicItem){
		return dicItemService.updateDicItem(dicItem);
	}
	
	/**
	 * 
	 * @Method: selectByDicItemId  
	 * @Description: 根据字典项id查询字典项实体
	 * @param dicItemId 字典项id
	 * @return DicItem 字典项实体
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:39:42
	 */
	@RequestMapping(value = "/selectByDicItemId",method = RequestMethod.POST)
	@ResponseBody
	public DicItem selectByDicItemId(int dicItemId){
		return dicItemService.selectByDicItemId(dicItemId);
	}
	
	/**
	 * 
	 * @Method: deleteByDicItemId  
	 * @Description: 根据字典项Id删除字典项
	 * @param dicItemId 字典项id
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午6:41:26
	 */
	@RequestMapping(value = "/deleteByDicItemId",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteByDicItemId(int dicItemId){
		return dicItemService.deleteByDicItemId(dicItemId);
	}
	
	/**
	 * 
	 * @Method: updateDicItemStatus  
	 * @Description: 启用、禁用字典项
	 * @param itemId 字典项id
	 * @param itemStatus 字典项状态
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:34:13
	 */
	@RequestMapping(value = "/updateDicItemStatus",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateDicItemStatus(int itemId, int itemStatus){
		return dicItemService.updateDicItemStatus(itemId, itemStatus);
	}
	
	/**
	 * 
	 * @Method: selectDicItemListByDicId  
	 * @Description: 根据字典id查询字典项集合
	 * @param dicId 字典id
	 * @return List<DicItem> 字典项集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午6:41:54
	 */
	@RequestMapping(value = "/selectDicItemListByDicId",method = RequestMethod.POST)
	@ResponseBody
	public List<DicItem> selectDicItemListByDicId(int dicId){
		return dicItemService.selectDicItemListByDicId(dicId);
	}
}
