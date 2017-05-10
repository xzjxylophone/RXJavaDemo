package cn.yiyizuche.common.ou.menu.dao;

import cn.yiyizuche.common.ou.menu.entity.Menu;

public interface MenuDao {
    int deleteByPrimaryKey(int id);

    Menu insert(Menu record);

    Menu insertSelective(Menu record);

    Menu selectByPrimaryKey(int id);

    Menu updateByPrimaryKeySelective(Menu record);

    Menu updateByPrimaryKey(Menu record);
}