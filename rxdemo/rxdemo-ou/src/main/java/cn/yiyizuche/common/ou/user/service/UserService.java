package cn.yiyizuche.common.ou.user.service;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserEmployeeVo;



/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.service 
 * @Class : UserService.java 
 * @Description : 用户实现类 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午7:50:52 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @Method: addUser  
	 * @Description: 添加用户
	 * @param user
	 * @return User 用户
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:50:56
	 */
	ResultMsg addUser(User user);
	
	/**
	 * 
	 * @Method: addUser  
	 * @Description: 添加用户并添加其角色
	 * @param user
	 * @param roleIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月27日 下午3:45:19
	 */
	ResultMsg addUser(User user, String roleIds);
	
	/**
	 * 
	 * @Method: deleteByUserId  
	 * @Description: 通过userId删除用户
	 * @param id
	 * @return int 当是0删除失败，否则返回Id
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:00
	 */
	ResultMsg deleteByUserId(int id);
	
	/**
	 * 
	 * @Method: updateUser  
	 * @Description:更新用户
	 * @param user
	 * @return User 用户
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:03
	 */
	ResultMsg updateUser(User user);
	
	/**
	 * 
	 * @Method: updateUser  
	 * @Description:
	 * @param user
	 * @param roleIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月11日 下午12:05:24
	 */
	ResultMsg updateUser(User user, String roleIds);

	/**
	 * 
	 * @Method: selectByUserId  
	 * @Description: 通过userId获取用户
	 * @param userId
	 * @return User 用户 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:18
	 */
	User selectByUserId(int userId);
	
	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 通过用户名查找用户
	 * @return List<User> 用户列表
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:21
	 */
	User selectByUserName(String userName);

	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 查询所有用户
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午5:30:51
	 */
	List<User> selectAll();
	
	/**
	 * 
	 * @Method: selectAllUserEmployeeVo  
	 * @Description:搜索全部的用户员工信息
	 * @return List<UserEmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:45:45
	 */
	List<UserEmployeeVo> selectAllUserEmployeeVo();
	
	/**
	 * 
	 * @Method: selectUserListNotInEmployee  
	 * @Description:查询用户不在员工表中
	 * @param id 除了employeeId的员工,
	 * 			 编辑员工的时候用到这个employeeId
	 * 			 添加员工的时候employeeId是0
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 上午11:58:57
	 */
	List<User> selectUserListNotInEmployee(int id);
	
	/**
	 * 
	 * @Method: selectAllDelete  
	 * @Description: 查找所有删除的用户
	 * @return List<User> 用户列表
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:24
	 */
	List<User> selectAllDelete();
	
	/**
	 * 
	 * @Method: selectAllNotDelete  
	 * @Description: 查找所有未删除的用户
	 * @return List<User> 用户列表 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:51:27
	 */
	List<User> selectAllNotDelete();

	/**
	 *
	 * @Method: selectUserByPage
	 * @Description: 分页查询用户
	 * @param page 分页对象
	 * @param params 查询条件
	 * @return Page<User> 分页查询对象（包含结果集）
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017年3月8日 下午10:51:28
	 */
	Page<User> selectUserByPage(Page<User> page, Map<String, Object> params);
	
	/**
	 * 
	 * @Method: selectUserListByName  
	 * @Description:分页查询用户
	 * @param userName
	 * @param realName
	 * @param page
	 * @return Page<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月22日 下午5:33:25
	 */
	Page<User> selectUserListByName(String userName, String realName, Page<User> page);

	/**
	 * 
	 * @Method: selectByUserNameAndPwd  
	 * @Description:通过用户名和密码查询用户,类似于登录
	 * @param userName
	 * @param pwd
	 * @return User (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月17日 下午5:46:57
	 */
	User selectByUserNameAndPwd(String userName, String pwd);
	
	/**
	 * 
	 * @Method: updateChangePwdByUserId  
	 * @Description:更新用户密码
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月17日 下午5:47:02
	 */
	ResultMsg updateChangePwdByUserId(int id, String oldPwd, String newPwd);
	
	/**
	 * 
	 * @Method: updateResetPwdByUserId  
	 * @Description:重置密码
	 * @param id
	 * @param newPwd void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月17日 下午5:55:31
	 */
	ResultMsg updateResetPwdByUserId(int id, String newPwd);
	
	/**
	 * 
	 * @Method: updateUserStatusByUserId  
	 * @Description:更新用户的状态
	 * @param id
	 * @param status void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月23日 上午10:40:30
	 */
	ResultMsg updateUserStatusByUserId(int id, int status);
}
