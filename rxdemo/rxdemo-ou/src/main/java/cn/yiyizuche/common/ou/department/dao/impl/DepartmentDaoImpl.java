package cn.yiyizuche.common.ou.department.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.dao.DepartmentDao;

@Repository(value="departmentDao")
public class DepartmentDaoImpl extends MyBatisDao implements DepartmentDao {
	
	protected static final String NAMESPACE="DepartmentMapper";

	public Department insert(Department record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public Department insertSelective(Department record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public Department selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof Department)) {
			return null;
		}
		return (Department) object;
	}

	public Department updateByPrimaryKeySelective(Department record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public Department updateByPrimaryKey(Department record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<Department> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
