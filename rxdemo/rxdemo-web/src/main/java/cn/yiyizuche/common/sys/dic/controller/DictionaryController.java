package cn.yiyizuche.common.sys.dic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;
import cn.yiyizuche.common.sys.dic.service.DictionaryService;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dicItem.controller 
 * @Class : DictionaryController.java 
 * @Description : 字典控制层
 * @author :jiwenfang
 * @CreateDate : 2017年4月11日 下午3:59:51 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents Rappli
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@RequestMapping("/sys/dic")
@Controller
public class DictionaryController {
	//注入字典服务层
	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 
	 * @Method: showVacateList  
	 * @Description: 打开字典列表页
	 * @return ModelAndView (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月22日 下午1:54:17
	 */
	@RequestMapping(value = "/showDictionaryList", method = RequestMethod.GET)
	public ModelAndView showDictionaryList(){
		ModelAndView view = new ModelAndView("/page/sys/dic/dic_list");
		return view;
	}
	
	/**
	 * 
	 * @Method: searchDicByPage  
	 * @Description: 分页查询字典数据
	 * @param current
	 * @param rowCount
	 * @param dicName 字典名称
	 * @param dicStatus 字典状态
	 * @return Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午2:15:58
	 */
	@RequestMapping(value = "/searchDicByPage",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> searchDicByPage(int current, int rowCount,String dicName, int dicStatus){
		Page<Dictionary> page = new Page<Dictionary>(current, rowCount);
		Page<Dictionary> pageResult = this.dictionaryService.selectDicByPage(page, dicName, dicStatus);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	 }
	
	/**
	 * 
	 * @Method: addDic  
	 * @Description: 添加字典
	 * @param dic 字典实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月11日 下午7:02:42
	 */
	@RequestMapping(value = "/addDic",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addDic(Dictionary dic){
		return dictionaryService.addDic(dic);
	}
	
	/**
	 * 
	 * @Method: updateDic  
	 * @Description: 修改字典
	 * @param dic 字典实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午3:47:19
	 */
	@RequestMapping(value = "/updateDic",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateDic(Dictionary dic){
		return dictionaryService.updateDic(dic);
	}
	
	/**
	 * 
	 * @Method: selectDicByDicId  
	 * @Description: 根据字典id查询字典实体
	 * @param dicId 字典id
	 * @return Dictionary 字典实体
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 上午11:27:48
	 */
	@RequestMapping(value = "/selectDicByDicId",method = RequestMethod.POST)
	@ResponseBody
	public Dictionary selectDicByDicId(int dicId){
		return dictionaryService.selectDicByDicId(dicId);
	}
	
	/**
	 * 
	 * @Method: deleteByDicId  
	 * @Description: 根据字典id删除字典
	 * @param dicId 字典id
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月12日 下午4:45:16
	 */
	@RequestMapping(value = "/deleteByDicId",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteByDicId(int dicId){
		return dictionaryService.deleteDicByDicId(dicId);
	}
	
	/**
	 * 
	 * @Method: updateDicStatus  
	 * @Description: 启用、禁用字典
	 * @param dicId 字典id
	 * @param dicStatus 字典状态
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年4月13日 下午1:57:11
	 */
	@RequestMapping(value = "/updateDicStatus",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateDicStatus(int dicId,int dicStatus){
		return dictionaryService.updateDicStatus(dicId, dicStatus);
	}
}
