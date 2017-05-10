package cn.yiyizuche.common.ou.role.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao;
import cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao;
import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;
import cn.yiyizuche.common.ou.role.service.RoleService;
import cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.ou.role.service.impl
 * @Class : RoleServiceImpl.java
 * @Description : Role Service Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午5:25:58
 * @version : V1.0.0
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog: Name        Date           Reason/Contents 
 *            jiwenfang   2017.03.17     修改deleteByRoleId方法，并添加相关注释说明
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleExtendDao roleExtendDao;
	// 注入角色与菜单的关联关系数据层
	@Autowired
	private RoleMenuExtendDao roleMenuExtendDao;

	@Autowired
	private UserRoleExtendDao userRoleExtendDao;

	/**
	 * 添加角色 (非 Javadoc)
	 * 
	 * @param role
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#addRole(cn.yiyizuche.common.ou.role.entity.Role)
	 */
	public ResultMsg addRole(Role role) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断角色数据是否为空
		if (role == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.ROLE_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断角色名是否为空
			if (role.getRoleName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_NAME_EMPTY);
				return resultMsg;
			}
			// 判断角色名是否存在
			Role tmp = this.selectByRoleName(role.getRoleName());
			if (tmp != null) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_NAME_EXIST);
				return resultMsg;
			}
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			role.setStatus(OuConstants.RoleStatus.ENABLE);
			role.setUpdateUser(role.getCreateUser());
			this.roleExtendDao.insert(role);

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
	 * 通过角色Id删除角色 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#deleteByRoleId(int)
	 */
	public ResultMsg deleteByRoleId(int id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			//根据角色id查询用户角色的关联关系
			List<UserRoleKey> userRoleKeys = this.userRoleExtendDao.selectUserRoleListByRoleId(id);
			//判断角色是否有关联用户，有关联用户时，不允许删除(需先解除关联关系)
			if (userRoleKeys != null && userRoleKeys.size() > 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_USER_RELATION);
				return resultMsg;
			}
			//根据角色id查询角色与菜单的关联关系
			List<RoleMenuKey> roleMenuKeys = this.roleMenuExtendDao.selectRelationByRoleId(id);
			//判断角色是否已关联菜单，有关联菜单时，不允许删除（需先解除关联关系）
			if (roleMenuKeys != null && roleMenuKeys.size() > 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_MENU_RELATION);
				return resultMsg;
			}
			//根据角色ID删除角色
			this.roleExtendDao.deleteByRoleId(id);
			//返回删除成功提示信息
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			//返回删除失败提示信息
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		return resultMsg;
	}

	/**
	 * 更新角色 (非 Javadoc)
	 * 
	 * @param role
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#updateRole(cn.yiyizuche.common.ou.role.entity.Role)
	 */
	public ResultMsg updateRole(Role role) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断角色数据是否为空
		if (role == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.ROLE_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断角色名是否为空
			if (role.getRoleName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_NAME_EMPTY);
				return resultMsg;
			}
			// 判断角色名是否存在
			Role tmp = this.selectByRoleName(role.getRoleName());
			if (tmp != null && tmp.getId() != role.getId()) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.ROLE_NAME_EXIST);
				return resultMsg;
			}
			Role instance = this.roleExtendDao.selectByPrimaryKey(role.getId());
			instance.setRoleName(role.getRoleName());
			instance.setSort(role.getSort());
			instance.setRemark(role.getRemark());
			instance.setUpdateUser(role.getUpdateUser());
			instance.setUpdateTime(role.getUpdateTime());
			this.roleExtendDao.updateByPrimaryKey(instance);
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
	 * 通过角色名搜索角色 (非 Javadoc)
	 * 
	 * @param roleName
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectByRoleName(java.lang.String)
	 */
	public Role selectByRoleName(String roleName) {
		return this.roleExtendDao.selectByRoleName(roleName);
	}

	/**
	 * 查找所有角色 (非 Javadoc)
	 * 
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectAll()
	 */
	public List<Role> selectAll() {
		List<Role> roles = this.roleExtendDao.selectAll();
		return roles;
	}

	/**
	 * 通过创建userId搜索所有角色 (非 Javadoc)
	 * 
	 * @param userId
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectByCreateUserId(int)
	 */
	public List<Role> selectByCreateUserId(int userId) {
		return this.roleExtendDao.selectByCreateUserId(userId);
	}

	/**
	 * 通过更新userId搜索所有角色 (非 Javadoc)
	 * 
	 * @param userId
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectByUpdateUserId(int)
	 */
	public List<Role> selectByUpdateUserId(int userId) {
		return this.roleExtendDao.selectByUpdateUserId(userId);
	}

	/**
	 * 为角色分配菜单权限 (非 Javadoc)
	 * 
	 * @param roleId
	 *            角色ID
	 * @param menuIds
	 *            菜单id数组
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#saveAuthority(int,
	 *      int[])
	 */
	@Override
	public ResultMsg saveAuthority(int roleId, int[] menuIds) {
		ResultMsg result = new ResultMsg();
		try {
			// 1、首次根据角色id删除角色与菜单的关联关系
			roleMenuExtendDao.deleteRelationByRoleId(roleId);
			// 2、创建角色与菜单的关联关系实体集合
			List<RoleMenuKey> list = new ArrayList<RoleMenuKey>();
			for (int menuId : menuIds) {
				RoleMenuKey roleMenuKey = new RoleMenuKey();
				roleMenuKey.setMenuId(menuId);// 设置菜单Id
				roleMenuKey.setRoleId(roleId);// 设置角色id
				list.add(roleMenuKey);
			}
			// 3、保存角色与菜单的关联关系
			roleMenuExtendDao.saveAuthority(list);
			// 设置分配权限成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(OuConstants.SAVE_AUTH_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			// 设置分配权限失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(OuConstants.SAVE_AUTH_FAIL);
		}
		return result;
	}

	/**
	 * 根据角色Id数组查询角色与菜单的关联关系 (非 Javadoc)
	 * 
	 * @param roleIds
	 *            角色id
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectRelationByRoleIds(int[])
	 */
	@Override
	public List<RoleMenuKey> selectRelationByRoleIds(int[] roleIds) {
		return roleMenuExtendDao.selectRelationByRoleIds(roleIds);
	}

	/**
	 * 根据角色名称分页查询角色集合 (非 Javadoc)
	 * 
	 * @param roleName 角色名称
	 * @param page 分页对象
	 * @return
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectRoleListByName(String, Page)
	 */
	@Override
	public Page<Role> selectRoleListByName(String roleName, Page<Role> page) {
		Map<String,Object> parameMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(roleName)){
			parameMap.put("roleName", roleName.trim());
		}
		parameMap.put("orderBy", "sort asc");
		return roleExtendDao.selectRolePageByName(parameMap, page);
	}
	
	/**
	 * 根据角色id查询角色实体 (非 Javadoc)
	 * 
	 * @param id 角色ID
	 * @return 角色实体对象
	 * @see cn.yiyizuche.common.ou.role.service.RoleService#selectRoleListByName(String, Page)
	 */
	@Override
	public Role findRoleById(int id) {
		return roleExtendDao.selectByPrimaryKey(id);
	}
	
	private static final Logger _log = LoggerFactory.getLogger(RoleServiceImpl.class);

}
