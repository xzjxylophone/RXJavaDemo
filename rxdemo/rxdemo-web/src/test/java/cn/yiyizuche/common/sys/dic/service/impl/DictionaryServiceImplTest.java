package cn.yiyizuche.common.sys.dic.service.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao;
import cn.yiyizuche.common.sys.dic.entity.DicItem;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;
import cn.yiyizuche.common.sys.dic.service.DicItemService;
import cn.yiyizuche.common.sys.dic.service.DictionaryService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service.impl 
 * @Class : DictionaryServiceImplTest.java 
 * @Description : 字典服务层测试类
 * @author :jiwenfang
 * @CreateDate : 2017年4月14日 上午11:52:36 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class DictionaryServiceImplTest extends BaseTest{
	@Autowired
	private DictionaryExtendDao dictionaryExtendDao;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DicItemService dicItemService;
	
	@Test
	public void testAll(){
		int count = 3;//添加的字典项数量
		ResultMsg msg = new ResultMsg();
		//1、添加字典及字典项
		Dictionary dic = new Dictionary();
		dic.setCreateUser(1);
		dic.setDicName("字典名称");
		dic.setDicCode("001");
		dic.setDicStatus(SysConstants.ENABLE_FLAG);//启用状态
		dic.setIsExternal(SysConstants.DicType.ISINTERIOR);//内部字典
		dic.setDisplayType(SysConstants.DicDisplayType.LIST);//树形字典
		
		dic = dictionaryExtendDao.insert(dic);//保存字典
		
		//添加字典项：添加多个字典项
		for(int i=0;i<count;i++){
			DicItem item = new DicItem();
			
			item.setCreateUser(1);
			item.setDicId(dic.getId());
			item.setItemName("职务" + i);
			item.setItemCode("00" + i);
			item.setItemSort(i);
			
			//添加字典项
			msg= dicItemService.addDicItem(item);
			//添加断言
			Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		}
		
		String dicName = "修改字典名称";
		//2、修改字典
		dic.setDicName(dicName);
		//添加字典项
		msg= dictionaryService.updateDic(dic);
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		
		//3、禁用字典
		msg = dictionaryService.updateDicStatus(dic.getId(), SysConstants.UNENABLE_FLAG);
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		
		//4、根据字典id查询字典实体
		dic = dictionaryService.selectDicByDicId(dic.getId());
		//添加断言:同时验证修改字典功能
		Assert.assertEquals(dicName, dic.getDicName());
		
		//5、删除字典
		msg = dictionaryService.deleteDicByDicId(dic.getId());
		List<DicItem> itemList = dicItemService.selectDicItemListByDicId(dic.getId());//根据字典id查询启用的字典项列表
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		Assert.assertEquals(0, itemList.size());
		
	}

}
