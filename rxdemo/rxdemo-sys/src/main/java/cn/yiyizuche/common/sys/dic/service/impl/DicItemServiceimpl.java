package cn.yiyizuche.common.sys.dic.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao;
import cn.yiyizuche.common.sys.dic.entity.DicItem;
import cn.yiyizuche.common.sys.dic.service.DicItemService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service.impl 
 * @Class : DicItemServiceimpl.java 
 * @Description : 字典业务逻辑层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:36:13 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class DicItemServiceimpl implements DicItemService{
	@Autowired
	private DicItemExtendDao dicItemExtendDao;
	
	/**
	 * (非 Javadoc) 
	 * 分页查询字典项
	 *   
	 * @param page
	 * @param dicId 字典id
	 * @param itemName 字典项名称
	 * @param itemStatus 字典项id
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#searchDicItemByPage(cn.yiyizuche.common.base.Page, int, java.lang.String)
	 */
	@Override
	public Page<DicItem> searchDicItemByPage(Page<DicItem> page, int dicId, String itemName, int itemStatus) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();

    	paramsMap.put("dicId", dicId);//字典id
    	paramsMap.put("itemName", itemName);//字典项名称
    	paramsMap.put("itemStatus", itemStatus);//字典项名称
        
        //返回查询结果
        return dicItemExtendDao.searchDicItemByPage(page, paramsMap);
	}
	/**
	 * (非 Javadoc) 
	 *   修改字典项
	 * @param dicItem
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#updateDicItem(cn.yiyizuche.common.sys.dic.entity.DicItem)
	 */
	@Override
	public ResultMsg updateDicItem(DicItem dicItem) {
		ResultMsg result = new ResultMsg();
		try{
			//根据字典id查询字典实体
			DicItem oldDicItem = dicItemExtendDao.selectByPrimaryKey(dicItem.getId());
			
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("itemId", dicItem.getId());
			param.put("itemName", dicItem.getItemName());
			param.put("dicId", oldDicItem.getDicId());
			
			//查询数据库中同一字典下同名的字典项数量
			int count = dicItemExtendDao.selectSameDicItemNameCount(param);
			//如果同名数量大于0，则不允许保存字典
			if(count > 0){
				//设置重名提示信息
				result.setState(SysConstants.FAIL);
				result.setMessage(SysConstants.CONFIG_NAME_EXIST);
			}else{
				//将新值设置到旧实体中
				oldDicItem.setItemCode(dicItem.getItemCode());//字典项编码
				oldDicItem.setItemName(dicItem.getItemName());//字典项名称
				oldDicItem.setItemSort(dicItem.getItemSort());//字典项排序
				oldDicItem.setUpdateTime(new Date());//修改时间
				oldDicItem.setUpdateUser(dicItem.getUpdateUser());//修改人
				
				//保存修改后的字典信息
				dicItemExtendDao.updateByPrimaryKey(oldDicItem);
				
				//设置保存成功提示信息
				result.setState(SysConstants.SUCCESS);
				result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			}
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置保存失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		
		return result;
	}
	
	/**
	 * (非 Javadoc) 
	 *   根据字典项Id查询字典项实体
	 *   
	 * @param dicItemId 字典项id
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#selectByDicItemId(int)
	 */
	@Override
	public DicItem selectByDicItemId(int dicItemId) {
		return dicItemExtendDao.selectByPrimaryKey(dicItemId);
	}
	/**
	 * (非 Javadoc) 
	 *   根据字典项id删除字典项
	 *   
	 * @param dicItemId 字典项id
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#deleteByDicItemId(int)
	 */
	@Override
	public ResultMsg deleteByDicItemId(int dicItemId) {
		ResultMsg result = new ResultMsg();
		try{
			//删除字典项
			dicItemExtendDao.deleteByPrimaryKey(dicItemId);
			
			//设置删除成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置删除失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		
		return result;
	}
	/**
	 * (非 Javadoc) 
	 *   添加字典项
	 *   
	 * @param dicItem
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#addDicItem(cn.yiyizuche.common.sys.dic.entity.DicItem)
	 */
	@Override
	public ResultMsg addDicItem(DicItem dicItem) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("itemId", 0);
			param.put("itemName", dicItem.getItemName());
			param.put("dicId", dicItem.getDicId());
			
			//查询数据库中同一字典下同名的字典项数量
			int count = dicItemExtendDao.selectSameDicItemNameCount(param);
			//如果同名数量大于0，则不允许保存字典
			if(count > 0){
				//设置重名提示信息
				result.setState(SysConstants.FAIL);
				result.setMessage(SysConstants.CONFIG_NAME_EXIST);
			}else{
				//设置新添加字典的初始值
				dicItem.setCreateTime(new Date());//创建时间
				dicItem.setItemStatus(SysConstants.ENABLE_FLAG);//启用标识
				//保存字典
				dicItemExtendDao.insert(dicItem);
				
				//设置保存成功提示信息
				result.setState(SysConstants.SUCCESS);
				result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			}
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置保存失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		
		return result;
	}
	/**
	 * (非 Javadoc) 
	 * 启用、禁用字典项  
	 *   
	 * @param itemId 字典项id
	 * @param itemStatus 字典项状态
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#updateDicItemStatus(int, int)
	 */
	@Override
	public ResultMsg updateDicItemStatus(int itemId, int itemStatus) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("itemId", itemId);
			params.put("itemStatus", itemStatus);
			
			//启用、禁用字典项
			dicItemExtendDao.updateDicItemStatus(params);
			
			//设置启用、禁用成功提示信息
			result.setState(SysConstants.SUCCESS);
			//根据状态设置提示信息
			if(itemStatus == SysConstants.ENABLE_FLAG){
				result.setMessage(SysConstants.ENABLE_SUCCESS_MSG);//启用成功
			}else if(itemStatus == SysConstants.UNENABLE_FLAG){
				result.setMessage(SysConstants.UNENABLE_SUCCESS_MSG);//禁用成功
			}
			
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置启用、禁用失败提示信息
			result.setState(SysConstants.FAIL);
			//根据状态设置提示信息
			if(itemStatus == SysConstants.ENABLE_FLAG){
				result.setMessage(SysConstants.ENABLE_FAIL_MSG);//启用失败
			}else if(itemStatus == SysConstants.UNENABLE_FLAG){
				result.setMessage(SysConstants.UNENABLE_FAIL_MSG);//禁用失败
			}
		}
		
		return result;
	}
	
	/**
	 * (非 Javadoc) 
	 * 根据字典id查询字典项集合
	 *   
	 * @param dicId 字典id
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DicItemService#selectDicItemListByDicId(int)
	 */
	@Override
	public List<DicItem> selectDicItemListByDicId(int dicId) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("dicId", dicId);
		params.put("itemStatus", SysConstants.ENABLE_FLAG);
		
		return dicItemExtendDao.selectDicItemListByDicId(params);
	}

	
	private static final Logger _log = LoggerFactory.getLogger(DicItemServiceimpl.class);

}
