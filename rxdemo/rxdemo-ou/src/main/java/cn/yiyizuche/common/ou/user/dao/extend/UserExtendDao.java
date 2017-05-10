package cn.yiyizuche.common.ou.user.dao.extend;


import java.util.List;
import java.util.Map;



import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.dao.UserDao;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserEmployeeVo;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.dao.extend 
 * @Class : UserExtendDao.java 
 * @Description : User Extend Dao
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午5:27:04 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface UserExtendDao extends UserDao {

	/**
	 * 
	 * @Method: selectByUserName  
	 * @Description: 通过用户名查找用户
	 * @param userName
	 * @return User (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:09
	 */
	User selectByUserName(String userName);
	
	/**
	 * 
	 * @Method: selectByUserNameAndPwd  
	 * @Description:通过用户名和密码搜索用户
	 * @param userName
	 * @param pwd
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月17日 下午5:38:49
	 */
	List<User> selectByUserNameAndPwd(String userName, String pwd);
	
	/**
	 * 
	 * @Method: updatePwdByUserId  
	 * @Description: 更改用户的密码
	 * @param id
	 * @param newPwd
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月17日 下午5:38:59
	 */
	void updatePwdByUserId(int id, String newPwd);
	
	/**
	 * 
	 * @Method: deleteByUserId  
	 * @Description: 通过userId删除用户
	 * @param id
	 * @return int (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:06
	 */
	int deleteByUserId(int id);
	
	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 查找所有用户
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:04
	 */
	List<User> selectAll();

	/**
	 * 
	 * @Method: selectAllUserEmployeeVo  
	 * @Description:搜索全部的用户员工信息
	 * @return List<UserEmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:44:35
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
	 * @CreateDate : 2017年3月25日 上午11:56:45
	 */
	List<User> selectUserListNotInEmployee(int id);
	
	/**
	 * 
	 * @Method: selectAllNotDelete  
	 * @Description: 查找所有未删除用户
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:03
	 */
	List<User> selectAllNotDelete();
	
	/**
	 * 
	 * @Method: selectAllDelete  
	 * @Description: 查找所有删除用户
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:00
	 */
	List<User> selectAllDelete();

	/**
	 *
	 * @Method: selectUserByPage
	 * @Description: 分页查询用户
	 * @param page 分页对象
	 * @param paramsMap 查询条件 (可选)
	 * 			userName	like条件
	 * 			realName	like条件
	 * 			startTime	创建开始时间
	 * 			endTime		创建结束时间
	 * 			deleteFlag	是否删除标记
	 * @return Page<User> 分页查询对象（包含结果集）
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017年3月8日 下午10:51:28
	 */
	Page<User> selectUserByPage(Page<User> page, Map<String, Object> paramsMap);
	
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
