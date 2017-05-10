package cn.yiyizuche.common.sys.dic.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao;
import cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;
import cn.yiyizuche.common.sys.dic.service.DictionaryService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service.impl 
 * @Class : DictionaryServiceImpl.java 
 * @Description : 字典项业务逻辑层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:36:33 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{
	@Autowired
    private DictionaryExtendDao dictionaryExtendDao;
	@Autowired
	private DicItemExtendDao dicItemExtendDao;

	/**
	 * 
	 * (非 Javadoc) 
	 * 添加字典
	 *   
	 * @param dic 字典实体
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DictionaryService#addDic(cn.yiyizuche.common.sys.dic.entity.Dictionary)
	 */
	@Override
	public ResultMsg addDic(Dictionary dic) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("dicId", 0);
			param.put("dicName", dic.getDicName());
			
			//查询数据库中同名字典的数量
			int count = dictionaryExtendDao.selectSameDicNameCount(param);
			//如果同名数量大于0，则不允许保存字典
			if(count > 0){
				//设置重名提示信息
				result.setState(SysConstants.FAIL);
				result.setMessage(SysConstants.CONFIG_NAME_EXIST);
			}else{
				//设置新添加字典的初始值
				dic.setCreateTime(new Date());//创建时间
				dic.setDicStatus(SysConstants.ENABLE_FLAG);//启用标识
				//保存字典
				dictionaryExtendDao.insert(dic);
				
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
	 * 修改字典
	 *   
	 * @param dic 字典实体
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DictionaryService#updateDic(cn.yiyizuche.common.sys.dic.entity.Dictionary)
	 */
	@Override
	public ResultMsg updateDic(Dictionary dic) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("dicId", dic.getId());
			param.put("dicName", dic.getDicName());
			
			//查询数据库中同名字典的数量
			int count = dictionaryExtendDao.selectSameDicNameCount(param);
			//如果同名数量大于0，则不允许保存字典
			if(count > 0){
				//设置重名提示信息
				result.setState(SysConstants.FAIL);
				result.setMessage(SysConstants.CONFIG_NAME_EXIST);
			}else{
				//根据字典id查询字典实体
				Dictionary oldDic = dictionaryExtendDao.selectByPrimaryKey(dic.getId());
				//将新值设置到旧实体中
				oldDic.setDicCode(dic.getDicCode());//字典编码
				oldDic.setDicCodeField(dic.getDicCodeField());//外部字典编码
				oldDic.setDicName(dic.getDicName());//字典名称
				oldDic.setDicNameField(dic.getDicNameField());//外部字典名称
				oldDic.setDicTableCode(dic.getDicTableCode());//外部字典表编码
				oldDic.setDisplayType(dic.getDisplayType());//字典类型
				oldDic.setIsExternal(dic.getIsExternal());//是否是外部字典
				oldDic.setUpdateTime(new Date());//修改时间
				oldDic.setUpdateUser(dic.getUpdateUser());//修改人
				
				//保存修改后的字典信息
				dictionaryExtendDao.updateByPrimaryKey(oldDic);
				
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
	 * 根据字典id删除字典
	 *   
	 * @param dicId 字典id
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DictionaryService#deleteDicByDicId(int)
	 */
	@Override
	public ResultMsg deleteDicByDicId(int dicId) {
		ResultMsg result = new ResultMsg();
		try{
			//删除字典项
			dicItemExtendDao.deleteByDicId(dicId);
			//删除字典
			dictionaryExtendDao.deleteByPrimaryKey(dicId);
			
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
	 * 启用、禁用字典
	 *   
	 * @param dicId 字典id
	 * @param dicStatus 字典状态
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DictionaryService#updateDicStatus(int, int)
	 */
	@Override
	public ResultMsg updateDicStatus(int dicId, int dicStatus) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("dicId", dicId);
			params.put("dicStatus", dicStatus);
			
			//启用、禁用字典
			dictionaryExtendDao.updateDicStatus(params);
			
			//设置启用、禁用成功提示信息
			result.setState(SysConstants.SUCCESS);
			//根据状态设置提示信息
			if(dicStatus == SysConstants.ENABLE_FLAG){
				result.setMessage(SysConstants.ENABLE_SUCCESS_MSG);//启用成功
			}else if(dicStatus == SysConstants.UNENABLE_FLAG){
				result.setMessage(SysConstants.UNENABLE_SUCCESS_MSG);//禁用成功
			}
			
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置启用、禁用失败提示信息
			result.setState(SysConstants.FAIL);
			//根据状态设置提示信息
			if(dicStatus == SysConstants.ENABLE_FLAG){
				result.setMessage(SysConstants.ENABLE_FAIL_MSG);//启用失败
			}else if(dicStatus == SysConstants.UNENABLE_FLAG){
				result.setMessage(SysConstants.UNENABLE_FAIL_MSG);//禁用失败
			}
		}
		
		return result;
	}
	/**
	 * (非 Javadoc) 
	 * 根据字典id查询字典实体
	 *   
	 * @param dicId
	 * @return  
	 * @see cn.yiyizuche.common.sys.dic.service.DictionaryService#selectDicByDicId(int)
	 */
	@Override
	public Dictionary selectDicByDicId(int dicId) {
		return dictionaryExtendDao.selectByPrimaryKey(dicId);
	}

	/**
	 */
	@Override
	public Page<Dictionary> selectDicByPage(Page<Dictionary> page, String dicName, int dicStatus) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();

    	paramsMap.put("dicName", dicName);//字典名称
    	paramsMap.put("dicStatus", dicStatus);//字典状态
        
        //返回查询结果
        return dictionaryExtendDao.selectDicByPage(page, paramsMap);
	}
	
	
	private static final Logger _log = LoggerFactory.getLogger(DictionaryServiceImpl.class);

}
