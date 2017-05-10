package cn.yiyizuche.common.ou.user.service.impl;


import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.service.LoginService;
import cn.yiyizuche.common.ou.user.service.UserService;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.*;

import javax.servlet.http.HttpServlet;




public class UserServiceTest extends BaseTest {

	@Autowired
    private UserService userService;
	@Autowired
    private LoginService loginService;
	
	

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


	
	private String userFormatterString(User user) {
		return "id:" + user.getId() + ", realName:" + user.getRealName() + ",userName:" + user.getUserName() + ",delFlag:" + user.getDelFlag();
	}
	
	/**
	 * 
	 * @Method: test_user_allAction  
	 * @Description: 测试所有的函数 void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午7:34:14
	 */
	@Test
	public void test_allAction() {
		
		int count = 10;
		
		// 批量添加
		for (int i = 0; i < count; i++) {
			User user = new User();
			user.setUserName("xuzhijun" + i);
			user.setRealName("徐志军" + i);
			user.setPwd("12345" + i);
			user.setSex(SysConstants.Sex.MAN);
			user.setMobile("1590103195" + i);
			user.setEmail("xuzhijun" + i + "@yiyizuche.com");
			user.setStatus(OuConstants.UserStatus.NORMAL);
			user.setCreateUser(1);
			user.setCreateTime(new Date());
			user.setUpdateUser(1);
			ResultMsg resultMsg = this.userService.addUser(user);
			_log.info("add user result:" + resultMsg.toString());
		}
		
		// 查询全部
		List<User> users = this.userService.selectAll();
		int usersSize = users.size();
		int min = 0;
		int max = usersSize - 1;
		// 随机一个数
		int deleteNumber = this.random(min, max);
		_log.info("random deleteNumber:" + deleteNumber);
		User deleteUser = users.get(deleteNumber);
		int deleteUserId = deleteUser.getId();
		_log.info("before delete user:" + this.userFormatterString(deleteUser));
		// 删除用户
		ResultMsg deleteResultMsg = this.userService.deleteByUserId(deleteUserId);
		_log.info("delete result:" + deleteResultMsg.toString());
		// 查询刚刚删除的用户
		User afterDeleteUser = this.userService.selectByUserId(deleteUserId);
		_log.info("after delete user:" + this.userFormatterString(afterDeleteUser));
		
		// 更新用户
		int updateNumber = this.random(min, max);
		User updateUser = users.get(updateNumber);
		int suffix = this.random(0, 100);
		String newRealName = "徐志军" + suffix;
		_log.info("except old realName:" + updateUser.getRealName() + ",new realName:" + newRealName);
		updateUser.setRealName(newRealName);
		int updateUserId = updateUser.getId();
		_log.info("before update user:" + this.userFormatterString(updateUser));
		ResultMsg updateResultMsg = this.userService.updateUser(updateUser);
		_log.info("update result:" + updateResultMsg.toString());
		
		// 查询刚刚更新的用户updateUserId
		User afterUpdateUser = this.userService.selectByUserId(updateUserId);
		_log.info("after update user:" + this.userFormatterString(afterUpdateUser));
		
		// 通过用户名查询用户
		String userName = afterUpdateUser.getUserName();
		User selectByUserNameUser = this.userService.selectByUserName(userName);
		_log.info("selectByUserName user:" + this.userFormatterString(selectByUserNameUser));

		// 查询所有删除的用户
		List<User> allDeleteUsers = this.userService.selectAllDelete();
		for (User user : allDeleteUsers) {
			_log.info("deleteUser:" + this.userFormatterString(user));
		}
		
		// 查询所有未删除的用户
		List<User> allNotDeleteUsers = this.userService.selectAllNotDelete();
		for (User user : allNotDeleteUsers) {
			_log.info("notDeleteUser:" + this.userFormatterString(user));
		}
	}
	
	
	@Test
	public void test_login_changePwd_resetPwd() {
		List<User> users = this.userService.selectAll();
		if (users == null || users.size() == 0) {
			_log.info("There is no user in database");
			return;
		}
		int random = this.random(0, users.size() - 1);
		User user = users.get(random);
		
		int userId = user.getId();
		String userName = user.getUserName();
		String pwd = user.getPwd();
		
		// login
		User loginUser = this.userService.selectByUserNameAndPwd(userName, pwd);
		if (loginUser.getId() == user.getId()) {
			_log.info("verify ok");
		} else {
			_log.info("verify failed");
		}
		
		// changePwd to 12345(random)
		String newPwd = "12345" + this.random(0, 9);
		_log.info("oldPwd:" + user.getPwd() + ",newPwd:" + newPwd);
		ResultMsg resultMsg = this.userService.updateChangePwdByUserId(userId, pwd, newPwd);
		if (!resultMsg.getState()) {
			_log.info("changed failed:" + resultMsg.toString());
			return;
		}
		_log.info("begin change:" + user.getUserName() + user.getPwd() + ",new pwd:" + newPwd + ",userId:" + user.getId());
		User afterChangePwdUser = this.userService.selectByUserId(user.getId());
		_log.info("after change:" + afterChangePwdUser.getUserName() + afterChangePwdUser.getPwd());
		if (afterChangePwdUser.getPwd().equals(newPwd)) {
			_log.info("change ok");
		} else {
			_log.info("change failed");
		}
		
		// resetPwd to 12345(random)
		String resetPwd = "12345" + this.random(0, 9);
		this.userService.updateResetPwdByUserId(user.getId(), resetPwd);
		
		User afterResetPwdUser = this.userService.selectByUserId(user.getId());
		if (afterResetPwdUser.getPwd().equals(resetPwd)) {
			_log.info("reset ok");
		} else {
			_log.info("reset failed");
		}
		
	}
	
	@Test
	public void test_loginService() {
		_log.info("11111");
		String userName = "zhoulin";
		String pwd = "123456";
		
		Map<String, Object> result = this.loginService.selectByUserNameAndPwd(userName, pwd);
		ResultMsg resultMsg = (ResultMsg) result.get("msg");
		
		_log.info("resultMsg:" + resultMsg.toString());
		
		
		
	}

	/**
	 *
	 * @Method: testSelectUserByPage
	 * @Description: 测试分页查询用户
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017年3月8日 上午11:47:53
	 */
	@Test
	public void testSelectUserByPage() {
		Page<User> page = new Page<>(1, 10);
		Map<String, Object> params = new HashMap<>();
		params.put("realName", "aaa");
		params.put("startTime", "2017-03-07 00:00:00");
		params.put("endTime", "2017-03-09 23:59:59");
		params.put("orderBy", "user_id");
		params.put("order", "desc");
		Page<User> resultPage = userService.selectUserByPage(page, params);
		_log.info("getResult:"+resultPage.getResult());
//		assertEquals(2,resultPage.getResult().size());
		_log.info("size:"+resultPage.getResult().size());
		_log.info("getTotalItems:"+resultPage.getTotalItems());
		_log.info("getTotalPages:"+resultPage.getTotalPages());
	}
	
	
	private static final Log _log = LogFactory.getLog(UserServiceTest.class);


}
