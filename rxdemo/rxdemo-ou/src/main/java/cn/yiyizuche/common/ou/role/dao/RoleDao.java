package cn.yiyizuche.common.ou.role.dao;

import cn.yiyizuche.common.ou.role.entity.Role;

public interface RoleDao {
    int deleteByPrimaryKey(int id);

    Role insert(Role record);

    Role insertSelective(Role record);

    Role selectByPrimaryKey(int id);

    Role updateByPrimaryKeySelective(Role record);

    Role updateByPrimaryKey(Role record);
}