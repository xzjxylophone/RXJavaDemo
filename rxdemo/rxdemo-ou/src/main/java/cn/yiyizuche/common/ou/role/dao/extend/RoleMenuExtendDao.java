package cn.yiyizuche.common.ou.role.dao.extend;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.ou.role.dao.RoleMenuDao;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.role.dao.extend 
 * @Class : RoleMenuExtendDao.java 
 * @Description : 角色菜单关联关系扩展数据层接口
 * @author :jiwenfang
 * @CreateDate : 2017年3月10日 下午3:53:46 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface RoleMenuExtendDao extends RoleMenuDao {
	/**
	 * 
	 * @Method: saveAuthority  
	 * @Description: 保存权限（保存角色与菜单的关联关系）
	 * @param roleMenu 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月10日 上午11:11:33
	 */
	public void saveAuthority(List<RoleMenuKey> roleMenu);
	
	/**
	 * 
	 * @Method: deleteRelationByRoleId  
	 * @Description: 根据角色Id删除角色和菜单的关联关系
	 * @param roleId 角色id
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月10日 上午11:12:38
	 */
	public void deleteRelationByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: selectRelationByRoleIds  
	 * @Description: 根据角色id数组查询角色与菜单的关联关系
	 * @param roleIds 角色ID
	 * @return List<RoleMenuKey> (返回类型描述) 
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月10日 上午11:13:53
	 */
	public List<RoleMenuKey> selectRelationByRoleIds(int[] roleIds);
	
	/**
	 * 
	 * @Method: selectRelationByMenuId  
	 * @Description: 根据菜单id查询角色与菜单的关联关系
	 * @param menuId 菜单id
	 * @return List<RoleMenuKey> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午4:53:40
	 */
	public List<RoleMenuKey> selectRelationByMenuId(int menuId);
	
	/**
	 * 
	 * @Method: selectRelationByRoleId  
	 * @Description: 根据角色id查询角色与菜单的关联关系
	 * @param roleId 角色ID
	 * @return List<RoleMenuKey> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 上午10:13:46
	 */
	List<RoleMenuKey> selectRelationByRoleId(int roleId);
}
