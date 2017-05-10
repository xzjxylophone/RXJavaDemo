package cn.yiyizuche.common.ou.user.dao.impl.extend;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao;
import cn.yiyizuche.common.ou.user.dao.impl.UserRoleDaoImpl;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;


@Repository(value="userRoleExtendDao")

public class UserRoleExtendDaoImpl extends UserRoleDaoImpl implements UserRoleExtendDao {

	/**
	 * 通过userRole搜索UserRole
	 * (非 Javadoc) 
	 *   
	 * @param userRole
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRole(cn.yiyizuche.common.ou.user.entity.UserRoleKey)
	 */
	public UserRoleKey selectUserRole(UserRoleKey userRole) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectUserRole", userRole);
	}
	
	/**
	 * 
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRoleList()
	 */
	public List<UserRoleKey> selectAll() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAll");
	}
	/**
	 * 
	 * (非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRoleListByUserId(int)
	 */
	public List<UserRoleKey> selectUserRoleListByUserId(int userId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectUserRoleListByUserId", userId);
	}
	
	/**
	 * 
	 * (非 Javadoc) 
	 *   
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRoleListByRoleId(int)
	 */
	public List<UserRoleKey> selectUserRoleListByRoleId(int roleId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectUserRoleListByRoleId", roleId);
	}
	
	/**
	 * 通过RoleId搜索UserRoleVo(非 Javadoc) 
	 *   
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRoleVoListByRoleId(int)
	 */
	public List<UserRoleVo> selectUserRoleVoListByRoleId(int roleId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectUserRoleVoListByRoleId", roleId);
	}
	
	/**
	 * 通过UserId搜索UserRoleVo(非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#selectUserRoleVoListByUserId(int)
	 */
	public List<UserRoleVo> selectUserRoleVoListByUserId(int userId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectUserRoleVoListByUserId", userId);
	}
	
	/**
	 * 
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao#deleteByUserRoleId(int)
	 */
	public int deleteByUserRole(UserRoleKey userRole) {
		return this.getSqlSession().delete(NAMESPACE + ".deleteByPrimaryKey", userRole);
	}
}
