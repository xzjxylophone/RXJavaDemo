package cn.yiyizuche.common.ou.employee.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.dao.EmployeeDao;

@Repository(value="employeeDao")
public class EmployeeDaoImpl extends MyBatisDao implements EmployeeDao {
	
	protected static final String NAMESPACE="EmployeeMapper";

	public Employee insert(Employee record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public Employee insertSelective(Employee record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public Employee selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof Employee)) {
			return null;
		}
		return (Employee) object;
	}

	public Employee updateByPrimaryKeySelective(Employee record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public Employee updateByPrimaryKey(Employee record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<Employee> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
