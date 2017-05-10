package cn.yiyizuche.common.ou.role.dao;

import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;

public interface RoleMenuDao {
    int deleteByPrimaryKey(RoleMenuKey key);

    RoleMenuKey insert(RoleMenuKey record);

    RoleMenuKey insertSelective(RoleMenuKey record);
}