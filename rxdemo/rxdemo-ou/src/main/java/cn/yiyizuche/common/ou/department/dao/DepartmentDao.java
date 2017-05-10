package cn.yiyizuche.common.ou.department.dao;

import cn.yiyizuche.common.ou.department.entity.Department;

public interface DepartmentDao {
    int deleteByPrimaryKey(int id);

    Department insert(Department record);

    Department insertSelective(Department record);

    Department selectByPrimaryKey(int id);

    Department updateByPrimaryKeySelective(Department record);

    Department updateByPrimaryKey(Department record);
}