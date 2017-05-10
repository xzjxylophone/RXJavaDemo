package cn.yiyizuche.common.ou.role.dao.extend;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.role.dao.RoleDao;
import cn.yiyizuche.common.ou.role.entity.Role;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.role.dao.extend 
 * @Class : RoleExtendDao.java 
 * @Description : Role Extend Dao 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午5:24:21 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface RoleExtendDao extends RoleDao {

	/**
	 * 
	 * @Method: deleteByRoleId  
	 * @Description: 通过角色Id删除角色
	 * @param roleId
	 * @return int (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:54:56
	 */
	int deleteByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: selectByRoleName  
	 * @Description: 通过角色名搜索角色
	 * @param roleName
	 * @return Role (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 上午11:54:59
	 */
	Role selectByRoleName(String roleName);

	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 搜索全部的角色(这里用一句话描述这个方法的作用)
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:04
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
	 * @CreateDate : 2017年3月9日 上午11:55:02
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
	 * @CreateDate : 2017年3月9日 上午11:55:06
	 */
	List<Role> selectByUpdateUserId(int userId);
	
	/**
	 * 
	 * @class : RoleExtendDao
	 * @method : selectRolePageByName
	 * @param parame 查询属性Map
	 * @param page 分页对象
	 * @return : List<Role>
	 * @throws : 
	 * @description : 根据角色名称分页查询角色(目前只适用于根据角色名称进行查询)
	 * @author : wangjing (wangjing@yiyizuche.com) 
	 * @createDate: 2017年3月20日 下午8:21:38
	 */
	Page<Role> selectRolePageByName(Map<String, Object> parame, Page<Role> page);
	
}
