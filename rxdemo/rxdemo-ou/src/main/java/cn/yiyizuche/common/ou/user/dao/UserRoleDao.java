package cn.yiyizuche.common.ou.user.dao;

import cn.yiyizuche.common.ou.user.entity.UserRoleKey;

public interface UserRoleDao {
    int deleteByPrimaryKey(UserRoleKey key);

    UserRoleKey insert(UserRoleKey record);

    UserRoleKey insertSelective(UserRoleKey record);
}