package cn.yiyizuche.common.ou.role.service.impl;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.service.RoleService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.role.service 
 * @Class : RoleServiceTest.java 
 * @Description : 角色业务逻辑层测试类
 * @author :jiwenfang
 * @CreateDate : 2017年3月10日 下午2:57:49 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class RoleServiceTest extends BaseTest {

	@Autowired
	private RoleService roleService;
	

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

	private String roleFormatterString(Role role) {
		return "id:" + role.getId() + ", roleName:" + role.getRoleName();
	}

	@Test
	public void test_allAction() {
		int count = 10;
		// 批量添加
		for (int i = 0; i < count; i++) {
			Role role = new Role();
			role.setCreateUser(1);
			role.setRoleName("juese" + (i + 1));
			role.setRemark("remark" + (i + 1));
			ResultMsg resultMsg = this.roleService.addRole(role);
			_log.info("add result msg:" + resultMsg.toString());
		}

		// 查询全部
		List<Role> roles = this.roleService.selectAll();
		int rolesSize = roles.size();
		int min = 0;
		int max = rolesSize - 1;
		// 随机一个数
		int deleteNumber = this.random(min, max);
		_log.info("random deleteNumber:" + deleteNumber);
		Role deleteRole = roles.get(deleteNumber);
		int deleteRoleId = deleteRole.getId();
//		String deleteRowName = deleteRole.getRoleName();
		_log.info("before delete role:" + this.roleFormatterString(deleteRole));
		// 删除角色
		ResultMsg deleteResultMsg = this.roleService.deleteByRoleId(deleteRoleId);
		_log.info("delete result:" + deleteResultMsg.toString());
//		// 查询刚刚删除的角色
//		Role afterDeleteRole = this.roleService.selectByRoleName(deleteRowName);
//		_log.info("after delete role:" + this.roleFormatterString(afterDeleteRole));

		// 更新角色
		int updateNumber = this.random(min, max);
		Role updateRole = roles.get(updateNumber);
		int suffix = this.random(0, 100);
		String newRoleName = "xuzhijun" + suffix;
		_log.info("except old roleName:" + updateRole.getRoleName() + ",new roleName:" + newRoleName);
		updateRole.setRoleName(newRoleName);
//		String updateRoleName = updateRole.getRoleName();
		_log.info("before update role:" + this.roleFormatterString(updateRole));
		ResultMsg updateResultMsg = this.roleService.updateRole(updateRole);
		_log.info("update result:" + updateResultMsg.toString());

		// 查询刚刚更新角色updateRoleId
		Role afterUpdateRole = this.roleService.selectByRoleName(newRoleName);
		_log.info("after update role:" + this.roleFormatterString(afterUpdateRole));

		// 通过角色名查询角色
		String roleName = afterUpdateRole.getRoleName();
		Role selectByRoleNameRole = this.roleService.selectByRoleName(roleName);
		_log.info("selectByRoleName role:" + this.roleFormatterString(selectByRoleNameRole));

		// 查询创建人Id所有角色
		List<Role> createUserRoles = this.roleService.selectByCreateUserId(1);
		for (Role role : createUserRoles) {
			_log.info("createUserRoles:" + this.roleFormatterString(role));
		}

		// 查询更新人Id所有角色
		List<Role> updateUserRoles = this.roleService.selectByUpdateUserId(1);
		for (Role role : updateUserRoles) {
			_log.info("updateUserRoles:" + this.roleFormatterString(role));
		}

	}
	
	
	@Test
	public void test_updateRole() {
		_log.info("test_updateRole");
		List<Role> roles = this.roleService.selectAll();
		Role role = roles.get(0);
		_log.info("test_updateRole2");

		role.setSort(1);
		_log.info("test_updateRole3");

		ResultMsg updateResultMsg = this.roleService.updateRole(role);
		_log.info("updateResultMsg:" + updateResultMsg.toString());
	}

	/**
	 * 
	 * @Method: testSaveAuthority  
	 * @Description: 测试分配权限
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午2:58:26
	 */
	@Test
	public void testSaveAuthority(){
		//角色id
		int roleId = 1;
		//菜单id数组
		int[] menuIds = new int[]{1,3};
		//添加断言：期望为角色成功分配权限
		Assert.assertEquals(SysConstants.SUCCESS, roleService.saveAuthority(roleId, menuIds).getState());
	}
	
	/**
	 * 
	 * @Method: testSelectRelationByRoleId  
	 * @Description: 根据角色Id查询角色与菜单的关联关系
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午4:36:12
	 */
	@Test
	public void testSelectRelationByRoleIds(){
		//角色id
		int[] roleId = {1};
		//添加断言
		Assert.assertEquals(1, roleService.selectRelationByRoleIds(roleId).size());
	}
	private static final Log _log = LogFactory.getLog(RoleServiceTest.class);

}
