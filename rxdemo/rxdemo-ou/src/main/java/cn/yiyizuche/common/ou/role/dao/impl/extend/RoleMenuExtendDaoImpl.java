package cn.yiyizuche.common.ou.role.dao.impl.extend;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao;
import cn.yiyizuche.common.ou.role.dao.impl.RoleMenuDaoImpl;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.role.dao.impl.extend 
 * @Class : RoleMenuExtendDaoImpl.java 
 * @Description : 角色与菜单关联关系扩展dao层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年3月10日 下午2:37:57 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="roleMenuExtendDao")
public class RoleMenuExtendDaoImpl extends RoleMenuDaoImpl implements RoleMenuExtendDao {
	/**
	 * 保存权限（保存角色与菜单的关联关系）
	 * (非 Javadoc) 
	 *   
	 * @param roleMenu  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao#saveAuthority(java.util.List)
	 */
	@Override
	public void saveAuthority(List<RoleMenuKey> roleMenu) {
		this.getSqlSession().insert(NAMESPACE+".saveAuthority", roleMenu);
	}

	/**
	 * 根据角色id删除角色与菜单的关联关系
	 * (非 Javadoc) 
	 *   
	 * @param roleId  角色ID
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao#deleteRelationByRoleId(int)
	 */
	@Override
	public void deleteRelationByRoleId(int roleId) {
		this.getSqlSession().insert(NAMESPACE+".deleteRelationByRoleId", roleId);
	}

	/**
	 * 根据角色Id数组查询角色与权限的关联关系
	 * (非 Javadoc) 
	 *   
	 * @param roleIds
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao#selectRelationByRoleId(int)
	 */
	@Override
	public List<RoleMenuKey> selectRelationByRoleIds(int[] roleIds) {
		return this.getSqlSession().selectList(NAMESPACE+".selectRelationByRoleIds", roleIds);
	}

	/**
	 * 根据菜单Id查询角色与权限的关联关系
	 * (非 Javadoc) 
	 *   
	 * @param menuId 菜单id
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao#selectRelationByMenuId(int)
	 */
	@Override
	public List<RoleMenuKey> selectRelationByMenuId(int menuId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectRelationByMenuId", menuId);
	}
	
	/**
	 * 根据角色id查询角色与菜单的关联关系
	 * (非 Javadoc) 
	 *   
	 * @param roleId 角色id
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao#selectRelationByRoleId(int)
	 */
	@Override
	public List<RoleMenuKey> selectRelationByRoleId(int roleId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectRelationByRoleId", roleId);
	}
	
}
