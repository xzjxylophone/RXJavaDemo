package cn.yiyizuche.common.ou.role.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.dao.RoleDao;

@Repository(value="roleDao")
public class RoleDaoImpl extends MyBatisDao implements RoleDao {
	
	protected static final String NAMESPACE="RoleMapper";

	public Role insert(Role record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public Role insertSelective(Role record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public Role selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof Role)) {
			return null;
		}
		return (Role) object;
	}

	public Role updateByPrimaryKeySelective(Role record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public Role updateByPrimaryKey(Role record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<Role> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
