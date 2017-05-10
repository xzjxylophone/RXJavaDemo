package cn.yiyizuche.common.ou.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.dao.UserRoleDao;

@Repository(value="userRoleDao")
public class UserRoleDaoImpl extends MyBatisDao implements UserRoleDao {
	
	protected static final String NAMESPACE="UserRoleMapper";

	public UserRoleKey insert(UserRoleKey record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public UserRoleKey insertSelective(UserRoleKey record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(UserRoleKey id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

}