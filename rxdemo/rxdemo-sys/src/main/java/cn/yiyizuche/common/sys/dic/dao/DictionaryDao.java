package cn.yiyizuche.common.sys.dic.dao;

import cn.yiyizuche.common.sys.dic.entity.Dictionary;

public interface DictionaryDao {
    int deleteByPrimaryKey(int id);

    Dictionary insert(Dictionary record);

    Dictionary insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(int id);

    Dictionary updateByPrimaryKeySelective(Dictionary record);

    Dictionary updateByPrimaryKey(Dictionary record);
}