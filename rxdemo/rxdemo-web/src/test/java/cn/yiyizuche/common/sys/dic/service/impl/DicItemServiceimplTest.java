package cn.yiyizuche.common.sys.dic.service.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.sys.dic.dao.extend.DicItemExtendDao;
import cn.yiyizuche.common.sys.dic.dao.extend.DictionaryExtendDao;
import cn.yiyizuche.common.sys.dic.entity.DicItem;
import cn.yiyizuche.common.sys.dic.entity.Dictionary;
import cn.yiyizuche.common.sys.dic.service.DicItemService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.dic.service.impl 
 * @Class : DicItemServiceimplTest.java 
 * @Description : 字典项服务层测试类
 * @author :jiwenfang
 * @CreateDate : 2017年4月14日 上午10:03:38 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class DicItemServiceimplTest extends BaseTest{
	@Autowired
	private DictionaryExtendDao dictionaryExtendDao;
	@Autowired
	private DicItemExtendDao dicItemExtendDao;
	@Autowired
	private DicItemService dicItemService;
	
	
	@Test
	public void testAll(){
		int count = 3;//添加的字典项数量
		ResultMsg msg = new ResultMsg();
		//1、添加字典
		Dictionary dic = new Dictionary();
		dic.setCreateUser(1);
		dic.setDicName("职务");
		dic.setDicCode("001");
		dic.setDicStatus(SysConstants.ENABLE_FLAG);//启用状态
		dic.setIsExternal(SysConstants.DicType.ISINTERIOR);//内部字典
		dic.setDisplayType(SysConstants.DicDisplayType.LIST);//树形字典
		
		dic = dictionaryExtendDao.insert(dic);//保存字典
		
		//2、添加字典项：添加多个字典项
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
		
		//3、根据字典id查询启用的字典项列表	
		List<DicItem> itemList = dicItemService.selectDicItemListByDicId(dic.getId());
		//添加断言
		Assert.assertEquals(count, itemList.size());
		
		//4、修改字典项
		DicItem item = itemList.get(0);
		//重设字典项值
		item.setItemName("修改职务0");
		//保存修改个字典项信息
		msg= dicItemService.updateDicItem(item);
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		
		//5、禁用字典项
		msg= dicItemService.updateDicItemStatus(itemList.get(0).getId(), SysConstants.UNENABLE_FLAG);
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		
		//6、根据字典项id查询字典项
		item = dicItemService.selectByDicItemId(itemList.get(0).getId());
		//添加断言:同时验证禁用字典项及修改字典项的结果
		Assert.assertEquals("修改职务0", item.getItemName());
		Assert.assertEquals(SysConstants.UNENABLE_FLAG, item.getItemStatus());
		
		//7、删除字典项
		msg = dicItemService.deleteByDicItemId(itemList.get(0).getId());
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, msg.getState());
		
		//8、分页查询字典项
		Page<DicItem> page = new Page<DicItem>(1, 10);
		Page<DicItem> pageResult = dicItemService.searchDicItemByPage(page, dic.getId(), "", -1);
		//添加断言:同时验证删除功能的结果：刚开始添加了count条记录，删除一条后，应该剩余count-1条
		Assert.assertEquals(count-1, pageResult.getTotalItems());
	}
}
