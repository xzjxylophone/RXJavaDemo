package cn.yiyizuche.common.sys.dic.service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;

/** 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service 
 * @Class : DictionaryService.java 
 * @Description : 字典业务逻辑层接口
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:37:16 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface DictionaryService {
	/**
	 * 
	 * @Method: addDic  
	 * @Description: 添加字典
	 * @param dic 字典实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午3:41:38
	 */
	ResultMsg addDic(Dictionary dic);
	
	/**
	 * 
	 * @Method: updateDic  
	 * @Description: 修改字典
	 * @param dic 字典实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午3:42:39
	 */
	ResultMsg updateDic(Dictionary dic);
	
	/**
	 * 
	 * @Method: deleteDicByDicId  
	 * @Description: 根据字典id删除字典
	 * @param dicId 字典id
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午3:43:31
	 */
	ResultMsg deleteDicByDicId(int dicId);
	
	/**
	 * 
	 * @Method: selectDicByDicId  
	 * @Description: 根据字典id查询字典
	 * @param dicId 字典id
	 * @return Dictionary 字典实体
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午3:45:05
	 */
	Dictionary selectDicByDicId(int dicId);
	
	/**
	 * 
	 * @Method: selectDicByPage  
	 * @Description: 根据条件分页查询字典列表
	 * @param page 分页对象
	 * @param dicName 字典名称
	 * @param dicStatus 字典状态
	 * @return Page<Dictionary> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:14:42
	 */
	Page<Dictionary> selectDicByPage(Page<Dictionary> page, String dicName, int dicStatus);

	/**
	 * 
	 * @Method: updateDicStatus  
	 * @Description:  启用、禁用字典
	 * @param dicId 字典id
	 * @param dicStatus 字典状态
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午1:58:10
	 */
	ResultMsg updateDicStatus(int dicId, int dicStatus);
}
