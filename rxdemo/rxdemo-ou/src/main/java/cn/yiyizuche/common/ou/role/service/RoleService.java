package cn.yiyizuche.common.ou.role.service;

import java.util.List;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.role.service 
 * @Class : RoleService.java 
 * @Description : Role Service Interface
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午5:25:11 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface RoleService {
	
	/**
	 * 
	 * @Method: addRole  
	 * @Description: 添加角色
	 * @param role
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:50:28
	 */
	ResultMsg addRole(Role role);
	
	/**
	 * 
	 * @Method: deleteByRoleId  
	 * @Description: 通过角色Id删除角色
	 * @param id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:50:37
	 */
	ResultMsg deleteByRoleId(int id);
	
	/**
	 * 
	 * @Method: updateRole  
	 * @Description: 更新角色
	 * @param role
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:50:45
	 */
	ResultMsg updateRole(Role role);
	
	/**
	 * 
	 * @Method: selectByRoleName  
	 * @Description: 通过角色名搜索角色
	 * @param roleName
	 * @return Role (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:50:53
	 */
	Role selectByRoleName(String roleName);

	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 搜索全部的角色
	 * @return List<Role> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午5:23:25
	 */
	List<Role> selectAll();

	/**
	 * 
	 * @Method: selectByCreateUserId  
	 * @Description: 通过创建userId搜索所有角色
	 * @param userId
	 * @return List<Role> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:51:05
	 */
	List<Role> selectByCreateUserId(int userId);
	
	/**
	 * 
	 * @Method: selectByUpdateUserId  
	 * @Description: 通过更新userId搜索所有角色
	 * @param userId
	 * @return List<Role> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:51:29
	 */
	List<Role> selectByUpdateUserId(int userId);
	
	/**
	 * 
	 * @Method: saveAuthority  
	 * @Description: 为角色分配权限（保存角色与权限的关联关系）
	 * @param roleId 角色id
	 * @param menuIds 菜单id数组
	 * @return ResultMsg 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午4:30:50
	 */
	ResultMsg saveAuthority(int roleId, int[] menuIds);
	
	/**
	 * 
	 * @Method: selectRelationByRoleIds  
	 * @Description: 根据角色id数组查询角色与菜单的关联关系
	 * @param roleId 角色id数组
	 * @return List<RoleMenuKey> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午4:32:08
	 */
	List<RoleMenuKey> selectRelationByRoleIds(int[] roleIds);
	
	/**
	 * 
	 * @class : RoleService
	 * @method : selectRoleListByName
	 * @param roleName 角色名称
	 * @param page 分页
	 * @return : Page<Role> 返回角色集合
	 * @throws : 
	 * @description : 根据角色名称查询角色集合
	 * @author : wangjing (wangjing@yiyizuche.com) 
	 * @createDate: 2017年3月20日 下午8:14:01
	 */
	Page<Role> selectRoleListByName(String roleName, Page<Role> page);
	
	/**
	 * 
	 * @class : RoleService
	 * @method : findRoleById
	 * @param id 角色ID
	 * @return : Role 角色对象
	 * @throws : 
	 * @description : 根据角色ID查询角色对象
	 * @author : wangjing (wangjing@yiyizuche.com) 
	 * @createDate: 2017年3月21日 下午7:56:58
	 */
	Role findRoleById(int id);
	
}
