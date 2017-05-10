package cn.yiyizuche.common.ou.user.dao;

import cn.yiyizuche.common.ou.user.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(int id);

    User insert(User record);

    User insertSelective(User record);

    User selectByPrimaryKey(int id);

    User updateByPrimaryKeySelective(User record);

    User updateByPrimaryKey(User record);
}