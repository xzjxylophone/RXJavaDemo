package cn.yiyizuche.common.ou.role.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;
import cn.yiyizuche.common.ou.role.dao.RoleMenuDao;

@Repository(value="roleMenuDao")
public class RoleMenuDaoImpl extends MyBatisDao implements RoleMenuDao {
	
	protected static final String NAMESPACE="RoleMenuMapper";

	public RoleMenuKey insert(RoleMenuKey record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public RoleMenuKey insertSelective(RoleMenuKey record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(RoleMenuKey id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

}