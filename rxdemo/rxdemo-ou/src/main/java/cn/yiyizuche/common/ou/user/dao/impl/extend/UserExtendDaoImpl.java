package cn.yiyizuche.common.ou.user.dao.impl.extend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao;
import cn.yiyizuche.common.ou.user.dao.impl.UserDaoImpl;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserEmployeeVo;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.dao.impl.extend 
 * @Class : UserExtendDaoImpl.java 
 * @Description : User Extend Dao Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午5:27:52 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="userExtendDao")
public class UserExtendDaoImpl extends UserDaoImpl implements UserExtendDao {

	/**
	 * 通过用户名查找用户
	 * (非 Javadoc) 
	 *   
	 * @param userName
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectByUserName(java.lang.String)
	 */
	public User selectByUserName(String userName) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByUserName", userName);
	}
	
	/**
	 * 通过userId删除用户
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#deleteByUserId(int)
	 */
	public int deleteByUserId(int id) {
		return this.getSqlSession().update(NAMESPACE + ".deleteByUserId", id);
	}
	
	/**
	 * 查找所有用户
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectAll()
	 */
	public List<User> selectAll() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAll");
	}
	
	/**
	 * 搜索全部的用户员工信息(非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectAllUserEmployeeVo()
	 */
	public List<UserEmployeeVo> selectAllUserEmployeeVo() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllUserEmployeeVo");
	}
	
	/**
	 * 查询用户不在员工表中(非 Javadoc) 
	 *   
	 * @param id 除了employeeId的员工,
	 * 			 编辑员工的时候用到这个employeeId
	 * 			 添加员工的时候employeeId是0
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectUserListNotInEmployee(int)
	 */
	public List<User> selectUserListNotInEmployee(int id) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectUserListNotInEmployee", id);
	}
	
	/**
	 * 查找所有未删除用户
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectAllNotDelete()
	 */
	public List<User> selectAllNotDelete() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllWithDelFlag", SysConstants.DELETE_FLAG_NORMAL);
	}
	
	/**
	 * selectAllDelete
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectAllDelete()
	 */
	public List<User> selectAllDelete() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllWithDelFlag", SysConstants.DELETE_FLAG_DELETE);
	}

	/**
	 * 分页查询用户
	 * (非 Javadoc)
	 *
	 * @return
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectUserByPage(Page, Map)
	 */
	public Page<User> selectUserByPage(Page<User> page, Map<String, Object> paramsMap) {
		this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", paramsMap);
		return page;
	}
	
	/**
	 * 通过用户名和密码搜索用户
	 * (非 Javadoc) 
	 *   
	 * @param userName
	 * @param pwd
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#selectByUserNameAndPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> selectByUserNameAndPwd(String userName, String pwd) {
		Map<String, String> params = new HashMap<>();
		params.put("userName", userName);
		params.put("pwd", pwd);
		return this.getSqlSession().selectList(NAMESPACE + ".selectByUserNameAndPwd", params);
	}
	
	/**
	 * 更改用户的密码
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @param newPwd
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#changePwdByUserId(int, java.lang.String)
	 */
	@Override
	public void updatePwdByUserId(int id, String newPwd) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", id);
		params.put("pwd", newPwd);
		this.getSqlSession().update(NAMESPACE + ".updatePwdByUserId", params);
	}
	
	/**
	 * 更新用户的状态(非 Javadoc) 
	 *   
	 * @param id
	 * @param status
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao#updateUserStatusByUserId(int, int)
	 */
	@Override
	public ResultMsg updateUserStatusByUserId(int id, int status) {
		ResultMsg resultMsg = new ResultMsg();
		Map<String, Object> params = new HashMap<>();
		params.put("userId", id);
		params.put("status", status);
		this.getSqlSession().update(NAMESPACE + ".updateUserStatusByUserId", params);
		resultMsg.setState(SysConstants.SUCCESS);
		return resultMsg;
	}
}
