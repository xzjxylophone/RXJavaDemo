package cn.yiyizuche.common.ou.menu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.menu.service.MenuService;
import cn.yiyizuche.common.sys.util.SysConstants;
import junit.framework.Assert;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.menu.service 
 * @Class : MenuServiceTest.java 
 * @Description : 菜单测试类
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午4:48:41 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */

public class MenuServiceTest extends BaseTest{
	
	private static final Log _log = LogFactory.getLog(MenuServiceTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_menuTreeList() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", 0);
		List<TreeNode> treeNodes = this.menuService.findMenuTreeList(params);
		
		List<TreeNode> treeNodes2 = this.menuService.findMenuTreeList(0, 1);

		_log.info("treeNodes size:" + treeNodes.size());
		_log.info("treeNodes2 size:" + treeNodes2.size());

		for (TreeNode treeNode : treeNodes2) {
			_log.info("name:" + treeNode.getName() + ",check:" + treeNode.getChecked());
		}

	}
	
	/**
	 * 
	 * @Method: testAddMenu  
	 * @Description: 测试菜单添加  1、测试添加不重名菜单，期望添加成功   2、测试添加重名菜单，期望添加失败 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月8日 上午11:24:01
	 */
	@Test
	public void testAddMenu() {
		//1、测试添加不重名菜单
		Menu menu = new Menu();
		menu.setName("测试菜单1");
		menu.setCode("001");
		menu.setCreateUser(1);
		menu.setCreateTime(new Date());
		menu.setRemark("测试菜单1");
		menu.setSort(0);
		Assert.assertEquals(SysConstants.SUCCESS, menuService.addMenu(menu).getState());//添加断言
		
		//2、测试添加重名菜单
		Assert.assertEquals(SysConstants.FAIL, menuService.addMenu(menu).getState());//添加断言
	}

//	@Test
//	public void testDelMenubyIds(){
//		List<Integer> ids = new ArrayList<Integer>();
//		ids.add(3);
//		menuService.deleteMenubyIdsMenubyIds(ids);
//	}
	
	/**
	 * 
	 * @Method: testFindMenuChildListByParam  
	 * @Description: 根据父菜单id查询子菜单列表
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 上午11:56:08
	 */
	@Test
	public void testFindMenuChildListByParam(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", 0);
		Assert.assertEquals(7, menuService.findMenuTreeList(params).size());//添加断言
	}
	
	/**
	 * 
	 * @Method: testUpdateMenu  
	 * @Description: 测试修改菜单  1、修改的菜单名不存在,期望修改成功   2、修改的菜单名已存在，期望修改失败
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月8日 上午11:31:39
	 */
	@Test
	public void testUpdateMenu() {
		//1、查询数据库中的菜单记录
		int menuId = 1;
		Menu menu = menuService.selectMenuById(menuId);
		//2、重新命名菜单，期望修改成功
		menu.setName("测试修改1");
		//调用修改方法
		ResultMsg result_success = menuService.updateMenu(menu);
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, result_success.getState());
		
		//3、测试菜单名称重复，期望修改失败
		menu.setName("测试菜单1");
		//调用修改方法
		ResultMsg result_fail = menuService.updateMenu(menu);
		//添加断言
		Assert.assertEquals(SysConstants.FAIL, result_fail.getState());
		
	}

	/**
	 * 
	 * @Method: testSelectMenuById  
	 * @Description: 测试根据id查询菜单实体
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月8日 上午11:46:43
	 */
	@Test
	public void testSelectMenuById() {
		int menuId = 1;
		//添加断言：查询的实体不为空
		Assert.assertNotNull(menuService.selectMenuById(menuId));
	}

	/**
	 * 
	 * @Method: testDeleteMenubyId  
	 * @Description: 测试删除菜单 1、测试未分配给角色菜单，期望删除成功   2、测试已分配给角色的菜单，期望删除失败 3、测试删除父菜单时，同时删除子菜单，期望删除成功
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月8日 上午11:47:53
	 */
	@Test
	public void testDeleteMenubyId() {
		//1、测试未分配给角色菜单，期望删除成功
		int menuId = 2;
		//添加断言：期望删除成功
		Assert.assertEquals(SysConstants.SUCCESS, menuService.deleteMenubyId(menuId).getState());
		//2、测试已分配给角色的菜单，期望删除失败
		int deleteMenuId = 1; 
		Assert.assertEquals(SysConstants.FAIL, menuService.deleteMenubyId(deleteMenuId).getState());
		// 3、测试删除父菜单时，同时删除子菜单，期望删除成功
		int deleteMenuTreeId = 4; 
		Assert.assertEquals(SysConstants.SUCCESS, menuService.deleteMenubyId(deleteMenuTreeId).getState());
		
	}
	/**
	 * 
	 * @Method: testSelectMenuByPage  
	 * @Description: 测试分页查询方法
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午2:30:51
	 */
	@Test
	public void testSelectMenuByPage(){
		Page page = new Page();
		page.setPageSize(2);
		page.setPageNo(1);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", "");
		params.put("status", 1);
		params.put("pId", 0);
		Page<Menu> menuPage = menuService.selectMenuByPage(page, params);
	}
	/**
	 * 
	 * @Method: testUpdateMenuStatus  
	 * @Description: 测试修改菜单状态（启用、禁用）
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午5:10:34
	 */
	@Test
	public void testUpdateMenuStatus(){
		int menuId = 1;//菜单id
		int status = 1;//菜单状态
		//添加断言
		Assert.assertEquals(SysConstants.SUCCESS, menuService.updateMenuStatus(menuId, status).getState());
	}
	
	//注入service层
	@Autowired
	private MenuService menuService;

}
