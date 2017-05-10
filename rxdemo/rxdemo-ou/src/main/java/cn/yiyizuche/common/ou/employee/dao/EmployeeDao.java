package cn.yiyizuche.common.ou.employee.dao;

import cn.yiyizuche.common.ou.employee.entity.Employee;

public interface EmployeeDao {
    int deleteByPrimaryKey(int id);

    Employee insert(Employee record);

    Employee insertSelective(Employee record);

    Employee selectByPrimaryKey(int id);

    Employee updateByPrimaryKeySelective(Employee record);

    Employee updateByPrimaryKey(Employee record);
}