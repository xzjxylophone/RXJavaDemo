package cn.yiyizuche.common.ou.role.dao.impl.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao;
import cn.yiyizuche.common.ou.role.dao.impl.RoleDaoImpl;
import cn.yiyizuche.common.ou.role.entity.Role;


@Repository(value="roleExtendDao")

public class RoleExtendDaoImpl extends RoleDaoImpl implements RoleExtendDao {

	/**
	 * 通过角色Id删除角色
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#deleteByRoleId(int)
	 */
	public int deleteByRoleId(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}
	
	/**
	 * 通过角色名搜索角色
	 * 	(非 Javadoc) 
	 *   
	 * @param roleName
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#selectByRoleName(java.lang.String)
	 */
	public Role selectByRoleName(String roleName) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByRoleName", roleName);
	}
	
	/**
	 * 搜索全部的角色(非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#selectAll()
	 */
	public List<Role> selectAll() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAll");
	}
	
	/**
	 * 通过创建userId搜索所有角色
	 * (非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#selectByCreateUserId(int)
	 */
	public List<Role> selectByCreateUserId(int userId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectByCreateUserId", userId);
	}
	
	/**
	 * 通过更新userId搜索所有角色
	 * (非 Javadoc) 
	 *   
	 * @param userId
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#selectByUpdateUserId(int)
	 */
	public List<Role> selectByUpdateUserId(int userId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectByUpdateUserId", userId);
	}
	
	/**
	 * 根据角色名称分页查询角色集合
	 * (非 Javadoc) 
	 *   
	 * @param parame
	 * @param page
	 * @return  
	 * @see cn.yiyizuche.common.ou.role.dao.extend.RoleExtendDao#selectRolePageByName(Map, Page)
	 */
	@Override
	public Page<Role> selectRolePageByName(Map<String,Object> parame, Page<Role> page) {
		this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", parame);
		return page;
	}
	
}
