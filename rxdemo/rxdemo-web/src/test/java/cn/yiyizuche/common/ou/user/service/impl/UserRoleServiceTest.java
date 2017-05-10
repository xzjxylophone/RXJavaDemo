package cn.yiyizuche.common.ou.user.service.impl;


import java.util.List;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.service.RoleService;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;
import cn.yiyizuche.common.ou.user.service.UserRoleService;
import cn.yiyizuche.common.ou.user.service.UserService;


public class UserRoleServiceTest extends BaseTest {

//	private static final Log logger = LogFactory.getLog(UserRoleServiceTest.class);

	@Autowired
    private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
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

	/**
	 * 
	 * @Method: test_allAction  
	 * @Description: void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月10日 下午4:17:40
	 */
	@Test
	public void test_allAction() {
		List<User> users = this.userService.selectAll();
		
		List<Role> roles = this.roleService.selectAll();
		
		_log.info("user size:" + users.size() + ", role size:" + roles.size());
		
		int userNumber = this.random(0, users.size() - 1);
		int roleNumber = this.random(0, roles.size() - 1);
		
		User user = users.get(userNumber);
		Role role = roles.get(roleNumber);
		int userId = user.getId();
		int roleId = role.getId();

		_log.info("userid:" + userId + ",roleid:" + roleId);
		UserRoleKey userRoleKey = new UserRoleKey();
		userRoleKey.setUserId(userId);
		userRoleKey.setRoleId(roleId);
		
		// 判断是否存在
		ResultMsg resultMsg = this.userRoleService.exist(userRoleKey);
		// 没有就添加,有就删除
		if (resultMsg.getState()) {
			// 删除
			ResultMsg deleteResultMsg = this.userRoleService.deleteByUserRole(userRoleKey);
			_log.info("delete msg:" + deleteResultMsg.toString());
		} else {
			// 添加
			ResultMsg addResultMsg = this.userRoleService.addUserRole(userRoleKey);
			_log.info("add msg:" + addResultMsg.toString());
		}
		
		// 搜索全部
		List<UserRoleKey> userRoleKeysByAll = this.userRoleService.selectAll();
		for (UserRoleKey userRoleKey2 : userRoleKeysByAll) {
			_log.info("userRoleKeysByAll userId:" + userRoleKey2.getUserId() + ",roleId:" + userRoleKey2.getRoleId());
		}
		
		// 通过userId搜索全部
		List<UserRoleKey> userRoleKeysByUserId = this.userRoleService.selectUserRoleListByUserId(userId);
		for (UserRoleKey userRoleKey2 : userRoleKeysByUserId) {
			_log.info("userRoleKeysByUserId userId:" + userRoleKey2.getUserId() + ",roleId:" + userRoleKey2.getRoleId());
		}
		
		// 通过roleId 搜索全部
		List<UserRoleKey> userRoleKeysByRoleId = this.userRoleService.selectUserRoleListByRoleId(roleId);
		for (UserRoleKey userRoleKey2 : userRoleKeysByRoleId) {
			_log.info("userRoleKeysByRoleId userId:" + userRoleKey2.getUserId() + ",roleId:" + userRoleKey2.getRoleId());
		}
	}
	
	@Test
	public void test_userRoleVo() {
		List<UserRoleVo> userRoleVos = this.userRoleService.selectUserRoleVoListByRoleId(1);
		for (UserRoleVo userRoleVo : userRoleVos) {
			_log.info("userRoleVo:" + userRoleVo.toString());
		}
		
		List<UserRoleVo> userRoleVos2 = this.userRoleService.selectAllUserRoleVoListByRoleId(1);
		for (UserRoleVo userRoleVo : userRoleVos2) {
			_log.info("userRoleVo:" + userRoleVo.toString());
		}
	}
	
	private static final Log _log = LogFactory.getLog(UserServiceTest.class);

}
