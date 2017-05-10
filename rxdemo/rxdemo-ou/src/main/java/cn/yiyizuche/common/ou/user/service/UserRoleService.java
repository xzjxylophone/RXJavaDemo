package cn.yiyizuche.common.ou.user.service;

import java.util.List;

import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.service 
 * @Class : UserRoleService.java 
 * @Description : 用户角色接口 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午7:45:05 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface UserRoleService {

	/**
	 * 
	 * @Method: addUserRole  
	 * @Description: 添加用户角色
	 * @param userRole
	 * @return UserRole 用户角色 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:45:19
	 */
	ResultMsg addUserRole(UserRoleKey userRole);
	
	/**
	 * 
	 * @Method: exist  
	 * @Description: 用户角色是否存在
	 * @param userRole
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月10日 下午3:46:25
	 */
	ResultMsg exist(UserRoleKey userRole);
	
	/**
	 * 
	 * @Method: deleteByUserRoleId  
	 * @Description: 通过UserId删除用户角色
	 * @param userRole
	 * @return int 当是0删除失败，否则返回Id
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:45:33
	 */
	ResultMsg deleteByUserRole(UserRoleKey userRole);
	
	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 搜索全部的用户角色
	 * @return List<UserRole> 用户角色列表
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:46:52
	 */
	List<UserRoleKey> selectAll();
	
	/**
	 * 
	 * @Method: selectUserRoleListByUserId  
	 * @Description: 通过userId返回用户角色
	 * @param userId
	 * @return List<UserRole> 用户角色列表
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:47:14
	 */
	List<UserRoleKey> selectUserRoleListByUserId(int userId);
	
	/**
	 * 
	 * @Method: selectUserRoleListByRoleId  
	 * @Description: 通过角色Id返回用户
	 * @param roleId
	 * @return List<UserRole> 用户角色列表 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午7:47:18
	 */
	List<UserRoleKey> selectUserRoleListByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: selectUserRoleVoListByRoleId  
	 * @Description:通过RoleId搜索UserRoleVo
	 * @param roleId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:46:55
	 */
	List<UserRoleVo> selectUserRoleVoListByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: selectUserRoleVoListByUserId  
	 * @Description:通过UserId搜索UserRoleVo
	 * @param userId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:48:03
	 */
	List<UserRoleVo> selectUserRoleVoListByUserId(int userId);
	
	/**
	 * 
	 * @Method: selectAllUserRoleVoListByRoleId  
	 * @Description:通过roleId搜索所有的UserRoleVo
	 * @param roleId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:48:26
	 */
	List<UserRoleVo> selectAllUserRoleVoListByRoleId(int roleId);

	/**
	 * 
	 * @Method: updateRoleWithUserIds  
	 * @Description:通过roleId更新相关的UserIds
	 * @param roleId
	 * @param userIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:49:10
	 */
	ResultMsg updateRoleWithUserIds(int roleId, String userIds);
	
	/**
	 * 
	 * @Method: updateRoleWithRoleIds  
	 * @Description:通过userId更新相关的roleIds
	 * @param userId
	 * @param roleIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:49:57
	 */
	ResultMsg updateRoleWithRoleIds(int userId, String roleIds);
}
