package cn.yiyizuche.common.sys.dic.dao;

import cn.yiyizuche.common.sys.dic.entity.DicItem;

public interface DicItemDao {
    int deleteByPrimaryKey(int id);

    DicItem insert(DicItem record);

    DicItem insertSelective(DicItem record);

    DicItem selectByPrimaryKey(int id);

    DicItem updateByPrimaryKeySelective(DicItem record);

    DicItem updateByPrimaryKey(DicItem record);
}