package cn.yiyizuche.common.ou.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;

import cn.yiyizuche.common.sys.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao;
import cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserEmployeeVo;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.service.UserRoleService;
import cn.yiyizuche.common.ou.user.service.UserService;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.ou.user.service.impl
 * @Class : UserServiceImpl.java
 * @Description : 用户服务实现类
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午8:01:06
 * @version : V1.0.0
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog: Name Date Reason/Contents ---------------------------------------
 *            *** **** ****
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserExtendDao userExtendDao;

	@Resource
	private UserRoleExtendDao userRoleExtendDao;
	
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 
	 * @Method: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return User
	 * @throws @author
	 *             : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:11:28
	 */
	public ResultMsg addUser(User user) {

		ResultMsg resultMsg = new ResultMsg();
		// 判断用户数据是否为空
		if (user == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断用户名是否为空
			if (user.getUserName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_NAME_EMPTY);
				return resultMsg;
			}
			// 判断用户名是否存在
			User tmpUser = this.selectByUserName(user.getUserName());
			if (tmpUser != null) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_NAME_EXIST);
				return resultMsg;
			}
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setDelFlag(SysConstants.DELETE_FLAG_NORMAL);
			this.userExtendDao.insert(user);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			return resultMsg;
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return resultMsg;
	}
	
	/**
	 * 添加用户并添加其角色(非 Javadoc) 
	 *   
	 * @param user
	 * @param roleIds
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#addUser(cn.yiyizuche.common.ou.user.entity.User, java.lang.String)
	 */
	public ResultMsg addUser(User user, String roleIds) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断用户数据是否为空
				if (user == null) {
					resultMsg.setState(SysConstants.FAIL);
					resultMsg.setMessage(OuConstants.USER_INFO_ERROR);
					return resultMsg;
				}
				try {
					// 判断用户名是否为空
					if (user.getUserName().length() == 0) {
						resultMsg.setState(SysConstants.FAIL);
						resultMsg.setMessage(OuConstants.USER_NAME_EMPTY);
						return resultMsg;
					}
					// 判断用户名是否存在
					User tmpUser = this.selectByUserName(user.getUserName());
					if (tmpUser != null) {
						resultMsg.setState(SysConstants.FAIL);
						resultMsg.setMessage(OuConstants.USER_NAME_EXIST);
						return resultMsg;
					}
					user.setCreateTime(new Date());
					user.setUpdateTime(new Date());
					user.setDelFlag(SysConstants.DELETE_FLAG_NORMAL);
					User newUser = this.userExtendDao.insert(user);
					if (!StringUtils.isBlank(roleIds)) {
						String[] roleIdStrings = roleIds.split(",");
						for (String string : roleIdStrings) {
							UserRoleKey record = new UserRoleKey();
							record.setUserId(newUser.getId());
							record.setRoleId(Integer.parseInt(string));
							this.userRoleExtendDao.insert(record);
						}
					}
					resultMsg.setState(SysConstants.SUCCESS);
					resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
					return resultMsg;
				} catch (Exception e) {
					e.printStackTrace();
					_log.error(e.getMessage());
					resultMsg.setState(SysConstants.FAIL);
					resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
				}
				return resultMsg;
	}

	/**
	 * 通过userId删除用户 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#deleteByUserId(int)
	 */
	public ResultMsg deleteByUserId(int id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			// 先删除用户权限
			List<UserRoleKey> userRoleKeys = this.userRoleExtendDao.selectUserRoleListByUserId(id);
			if (userRoleKeys != null && userRoleKeys.size() > 0) {
				for (UserRoleKey userRoleKey : userRoleKeys) {
					this.userRoleExtendDao.deleteByUserRole(userRoleKey);
				}
			}
			this.userExtendDao.deleteByUserId(id);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		return resultMsg;
	}

	/**
	 * 更新用户 (非 Javadoc)
	 * 
	 * @param user
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#updateUser(cn.yiyizuche.common.ou.user.entity.User)
	 */
	public ResultMsg updateUser(User user) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断用户数据是否为空
		if (user == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断用户名是否为空
			if (user.getUserName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_NAME_EMPTY);
				return resultMsg;
			}
			// 判断用户名是否存在
			User tmpUser = this.selectByUserName(user.getUserName());
			if (tmpUser != null && tmpUser.getId() != user.getId()) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_NAME_EXIST);
				return resultMsg;
			}
			// 查询user
			User instance = userExtendDao.selectByPrimaryKey(user.getId());
			instance.setUserName(user.getUserName());
			instance.setRealName(user.getRealName());
			instance.setSex(user.getSex());
			instance.setBirthdate(user.getBirthdate());
			instance.setMobile(user.getMobile());
			instance.setEmail(user.getEmail());
			instance.setStatus(user.getStatus());
			instance.setUpdateUser(user.getUpdateUser());
			instance.setUpdateTime(user.getUpdateTime());
			this.userExtendDao.updateByPrimaryKey(instance);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return resultMsg;
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param user
	 * @param roleIds
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#updateUser(cn.yiyizuche.common.ou.user.entity.User, java.lang.String)
	 */
	public ResultMsg updateUser(User user, String roleIds) {
		ResultMsg resultMsg = this.updateUser(user);
		if (!resultMsg.getState()) {
			return resultMsg;
		} else {
			resultMsg = this.userRoleService.updateRoleWithRoleIds(user.getId(), roleIds);
			if (resultMsg.getState()) {
				resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			}
			return resultMsg;
		}
	}

	/**
	 * 通过userId获取用户 (非 Javadoc)
	 * 
	 * @param userId
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectByUserId(int)
	 */
	public User selectByUserId(int userId) {
		return this.userExtendDao.selectByPrimaryKey(userId);
	}

	/**
	 * 通过用户名查找用户 (非 Javadoc)
	 * 
	 * @param userName
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectByUserName(java.lang.String)
	 */
	public User selectByUserName(String userName) {
		return this.userExtendDao.selectByUserName(userName);
	}

	/**
	 * 查找所有的用户 (非 Javadoc)
	 * 
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectAll()
	 */
	public List<User> selectAll() {
		List<User> users = this.userExtendDao.selectAll();
		return users;
	}
	
	/**
	 * 搜索全部的用户员工信息(非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectAllUserEmployeeVo()
	 */
	public List<UserEmployeeVo> selectAllUserEmployeeVo() {
		return this.userExtendDao.selectAllUserEmployeeVo();
	}
	
	/**
	 * 查询用户不在员工表中(非 Javadoc) 
	 *   
	 * @param id 除了employeeId的员工,
	 * 			 编辑员工的时候用到这个employeeId
	 * 			 添加员工的时候employeeId是0
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectUserListNotInEmployee(int)
	 */
	public List<User> selectUserListNotInEmployee(int id) {
		return this.userExtendDao.selectUserListNotInEmployee(id);
	}

	/**
	 * 查找所有删除的用户 (非 Javadoc)
	 * 
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectAllDelete()
	 */
	public List<User> selectAllDelete() {
		List<User> users = this.userExtendDao.selectAllDelete();
		return users;
	}

	/**
	 * 查找所有未删除的用户 (非 Javadoc)
	 * 
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectAllNotDelete()
	 */
	public List<User> selectAllNotDelete() {
		List<User> users = this.userExtendDao.selectAllNotDelete();
		return users;
	}

	/**
	 * 分页查询用户 (非 Javadoc)
	 *
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectUserByPage(Page,
	 *      Map)
	 */
	public Page<User> selectUserByPage(Page<User> page, Map<String, Object> params) {
		return userExtendDao.selectUserByPage(page, params);
	}
	
	/**
	 * 通过用户名和密码查询用户,类似于登录
	 * (非 Javadoc) 
	 *   
	 * @param userName
	 * @param pwd
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectByUserNameAndPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public User selectByUserNameAndPwd(String userName, String pwd) {
		List<User> users = this.userExtendDao.selectByUserNameAndPwd(userName, pwd);
		if (users == null || users.size() != 1) {
			return null;
		} else {
			return users.get(0);
		}
	}

	/**
	 * 更新用户密码
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#updateChangePwdByUserId(int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMsg updateChangePwdByUserId(int id, String oldPwd, String newPwd) {
		ResultMsg resultMsg = new ResultMsg();
		User user = this.userExtendDao.selectByPrimaryKey(id);
		if (user == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_NOT_FIND);
			return resultMsg;
		}
		// 传入旧密码密码与数据库密码不匹配
		if (!user.getPwd().equals(MD5Util.getMD5Digest(oldPwd))) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_OLDPWD_ERROR);
			return resultMsg;
		}
		// 传入旧密码密码与新密码相同
		if (oldPwd.equals(newPwd)) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_EQUALS_PWD_ERROR);
			return resultMsg;
		}
		try {
			this.userExtendDao.updatePwdByUserId(id, MD5Util.getMD5Digest(newPwd));
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(OuConstants.USER_CHANGE_PWD_SUCCESS);
		} catch (Exception exception) {
			exception.printStackTrace();
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_RESETPWD_ERROR);
		}
		return resultMsg;
	}
	
	/**
	 * 重置密码
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @param newPwd  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#updateResetPwdByUserId(int, java.lang.String)
	 */
	@Override
	public ResultMsg updateResetPwdByUserId(int id, String newPwd) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			this.userExtendDao.updatePwdByUserId(id, newPwd);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(OuConstants.USER_CHANGE_PWD_SUCCESS);
		}catch (Exception e){
			e.printStackTrace();
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_RESETPWD_ERROR);
		}
		return resultMsg;
	}
	
	/**
	 * 分页查询用户(非 Javadoc) 
	 *   
	 * @param userName
	 * @param realName
	 * @param page
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#selectUserListByName(java.lang.String, java.lang.String, cn.yiyizuche.common.base.Page)
	 */
	@Override
	public Page<User> selectUserListByName(String userName, String realName, Page<User> page) {
		Map<String,Object> parameMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(userName)){
			parameMap.put("userName", userName.trim());
		}
		if(StringUtils.isNotEmpty(realName)){
			parameMap.put("realName", realName.trim());
		}
		parameMap.put("deleteFlag", SysConstants.DELETE_FLAG_NORMAL);
		return userExtendDao.selectUserByPage(page, parameMap);
	}
	
	/**
	 * 更新用户的状态(非 Javadoc) 
	 *   
	 * @param id
	 * @param status  
	 * @see cn.yiyizuche.common.ou.user.service.UserService#updateUserStatusByUserId(int, int)
	 */
	@Override
	public ResultMsg updateUserStatusByUserId(int id, int status) {
		return this.userExtendDao.updateUserStatusByUserId(id, status);
	}

	private static final Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);
}
