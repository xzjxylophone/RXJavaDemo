package cn.yiyizuche.common.ou.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao;
import cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao;
import cn.yiyizuche.common.ou.user.entity.UserEmployeeVo;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;
import cn.yiyizuche.common.ou.user.service.UserRoleService;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.service.impl 
 * @Class : UserRoleServiceImpl.java 
 * @Description : 用户角色实现类 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午7:48:00 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	private UserRoleExtendDao userRoleExtendDao;
	
	@Resource
	private UserExtendDao userExtendDao;

	/**
	 * 添加用户角色
	 * (非 Javadoc) 
	 *   
	 * @param userRole
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#addUserRole(cn.yiyizuche.common.ou.user.entity.UserRoleKey)
	 */
	public ResultMsg addUserRole(UserRoleKey userRole) {
		ResultMsg resultMsg = new ResultMsg();
		if (userRole == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_ROLE_INFO_ERROR);
			return resultMsg;
		}
		try {
			this.userRoleExtendDao.insert(userRole);
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
	 * 通过UserId删除用户角色
	 * (非 Javadoc) 
	 *   
	 * @param userRole
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#deleteByUserRole(cn.yiyizuche.common.ou.user.entity.UserRoleKey)
	 */
	public ResultMsg deleteByUserRole(UserRoleKey userRole) {
		ResultMsg resultMsg = new ResultMsg();
		if (userRole == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_ROLE_INFO_ERROR);
			return resultMsg;
		}
		try {
			this.userRoleExtendDao.deleteByUserRole(userRole);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
			
		} catch (Exception e) {e.printStackTrace();
		_log.error(e.getMessage());
		resultMsg.setState(SysConstants.FAIL);
		resultMsg.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		return resultMsg;
	}
	
	/**
	 * 用户角色是否存在
	 * (非 Javadoc) 
	 *   
	 * @param userRole
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#exist(cn.yiyizuche.common.ou.user.entity.UserRoleKey)
	 */
	public ResultMsg exist(UserRoleKey userRole) {
		ResultMsg resultMsg = new ResultMsg();
		if (userRole == null) {
			resultMsg.setState(SysConstants.SUCCESS);
			return resultMsg;
		}
		try {
			UserRoleKey resultUserRoleKey = this.userRoleExtendDao.selectUserRole(userRole);
			if (resultUserRoleKey != null) {
				resultMsg.setState(SysConstants.SUCCESS);
				resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
			} else {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_ROLE_EXIST);
			}
		} catch (Exception e) {e.printStackTrace();
		_log.error(e.getMessage());
		resultMsg.setState(SysConstants.FAIL);
		resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return resultMsg;
	}

	/**
	 * 搜索全部的用户角色
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectUserRoleList()
	 */
	public List<UserRoleKey> selectAll() {
		List<UserRoleKey> userRoles = this.userRoleExtendDao.selectAll();
		return userRoles;
	}
	
	/**
	 * 通过userId返回用户角色
	 * (非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectUserRoleListByUserId(int)
	 */
	public List<UserRoleKey> selectUserRoleListByUserId(int userId) {
		List<UserRoleKey> userRoles = this.userRoleExtendDao.selectUserRoleListByUserId(userId);
		return userRoles;
	}
	
	/**
	 * 通过角色Id返回用户
	 * (非 Javadoc) 
	 *   
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectUserRoleListByRoleId(int)
	 */
	public List<UserRoleKey> selectUserRoleListByRoleId(int roleId) {
		List<UserRoleKey> userRoles = this.userRoleExtendDao.selectUserRoleListByRoleId(roleId);
		return userRoles;
	}
	
	/**
	 * 通过RoleId搜索UserRoleVo(非 Javadoc) 
	 *   
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectUserRoleVoListByRoleId(int)
	 */
	public List<UserRoleVo> selectUserRoleVoListByRoleId(int roleId) {
		List<UserRoleVo> userRoleVos = this.userRoleExtendDao.selectUserRoleVoListByRoleId(roleId);
		return userRoleVos;
	}

	/**
	 * 通过UserId搜索UserRoleVo(非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectUserRoleVoListByUserId(int)
	 */
	public List<UserRoleVo> selectUserRoleVoListByUserId(int userId) {
		List<UserRoleVo> userRoleVos = this.userRoleExtendDao.selectUserRoleVoListByUserId(userId);
		return userRoleVos;
	}
	
	/**
	 * 通过roleId搜索所有的UserRoleVo(非 Javadoc) 
	 *   
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#selectAllUserRoleVoListByRoleId(int)
	 */
	public List<UserRoleVo> selectAllUserRoleVoListByRoleId(int roleId) {
		List<UserRoleVo> existUserRoleVos = this.userRoleExtendDao.selectUserRoleVoListByRoleId(roleId);
		List<UserEmployeeVo> allUserEmployeeVos = this.userExtendDao.selectAllUserEmployeeVo();
		List<UserRoleVo> resultUserRoleVos = new ArrayList<UserRoleVo>();
		for (UserEmployeeVo userEmployeeVo : allUserEmployeeVos) {
			int findRoleId = 0;
			for (UserRoleVo existUserRoleVo : existUserRoleVos) {
				if (userEmployeeVo.getId() == existUserRoleVo.getUserId()) {
					findRoleId = existUserRoleVo.getRoleId();
					break;
				}
			}
			UserRoleVo resultUserRoleVo = new UserRoleVo();
			resultUserRoleVo.setUserId(userEmployeeVo.getId());
			resultUserRoleVo.setUserName(userEmployeeVo.getUserName());
			resultUserRoleVo.setRealName(userEmployeeVo.getRealName());
			resultUserRoleVo.setDepId(userEmployeeVo.getDepId());
			resultUserRoleVo.setDepName(userEmployeeVo.getDepName());
			resultUserRoleVo.setEmpId(userEmployeeVo.getEmpId());
			resultUserRoleVo.setRoleId(findRoleId);
			resultUserRoleVo.setExist(findRoleId > 0 ? 1 : 0);
			resultUserRoleVos.add(resultUserRoleVo);
		}
		return resultUserRoleVos;
	}

	/**
	 * 通过roleId更新相关的UserIds(非 Javadoc) 
	 *   
	 * @param roleId
	 * @param userIds
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#updateRoleWithUserIds(int, java.lang.String)
	 */
	public ResultMsg updateRoleWithUserIds(int roleId, String userIds) {
		ResultMsg resultMsg = new ResultMsg();
		List<UserRoleKey> userRoleKeys = this.selectUserRoleListByRoleId(roleId);
		String[] userIdArray = {};
		if (StringUtils.isNotEmpty(userIds)) {
			userIdArray = userIds.split(",");
		}
		List<UserRoleKey> needToDeleteList = new ArrayList<UserRoleKey>();
		for (UserRoleKey userRoleKey : userRoleKeys) {
			boolean find = false;
			for (int i = 0; i < userIdArray.length; i++) {
				int userId = Integer.parseInt(userIdArray[i]);
				if (userRoleKey.getUserId() == userId) {
					find = true;
					break;
				}
			}
			if (!find) {
				needToDeleteList.add(userRoleKey);
			}
		}
		List<UserRoleKey> needToAddList = new ArrayList<UserRoleKey>();
		for (int i = 0; i < userIdArray.length; i++) {
			boolean find = false;
			int userId = Integer.parseInt(userIdArray[i]);
			for (UserRoleKey userRoleKey : userRoleKeys) {
				if (userRoleKey.getUserId() == userId) {
					find = true;
					break;
				}
			}
			if (!find) {
				UserRoleKey tmpUserRoleKey = new UserRoleKey();
				tmpUserRoleKey.setRoleId(roleId);
				tmpUserRoleKey.setUserId(userId);
				needToAddList.add(tmpUserRoleKey);
			}
		}
		for (UserRoleKey userRoleKey : needToDeleteList) {
			this.deleteByUserRole(userRoleKey);
		}

		for (UserRoleKey userRoleKey : needToAddList) {
			ResultMsg deleteMsg = this.addUserRole(userRoleKey);
			_log.info("deleteMsg:" + deleteMsg.toString());
		}
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage("");
		return resultMsg;
	}
	
	/**
	 * 通过userId更新相关的roleIds(非 Javadoc) 
	 *   
	 * @param userId
	 * @param roleIds
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.UserRoleService#updateRoleWithRoleIds(int, java.lang.String)
	 */
	public ResultMsg updateRoleWithRoleIds(int userId, String roleIds) {
		ResultMsg resultMsg = new ResultMsg();
		List<UserRoleKey> userRoleKeys = this.selectUserRoleListByUserId(userId);
		String[] roleIdArray = {};
		if (StringUtils.isNotEmpty(roleIds)) {
			roleIdArray = roleIds.split(",");
		}
		List<UserRoleKey> needToDeleteList = new ArrayList<UserRoleKey>();
		for (UserRoleKey userRoleKey : userRoleKeys) {
			boolean find = false;
			for (int i = 0; i < roleIdArray.length; i++) {
				int roleId = Integer.parseInt(roleIdArray[i]);
				if (userRoleKey.getRoleId() == roleId) {
					find = true;
					break;
				}
			}
			if (!find) {
				needToDeleteList.add(userRoleKey);
			}
		}
		List<UserRoleKey> needToAddList = new ArrayList<UserRoleKey>();
		for (int i = 0; i < roleIdArray.length; i++) {
			boolean find = false;
			int roleId = Integer.parseInt(roleIdArray[i]);
			for (UserRoleKey userRoleKey : userRoleKeys) {
				if (userRoleKey.getRoleId() == roleId) {
					find = true;
					break;
				}
			}
			if (!find) {
				UserRoleKey tmpUserRoleKey = new UserRoleKey();
				tmpUserRoleKey.setRoleId(roleId);
				tmpUserRoleKey.setUserId(userId);
				needToAddList.add(tmpUserRoleKey);
			}
		}
		for (UserRoleKey userRoleKey : needToDeleteList) {
			this.deleteByUserRole(userRoleKey);
		}
		for (UserRoleKey userRoleKey : needToAddList) {
			ResultMsg deleteMsg = this.addUserRole(userRoleKey);
			_log.info("deleteMsg:" + deleteMsg.toString());
		}
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		return resultMsg;
	}

	private static final Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);

}
