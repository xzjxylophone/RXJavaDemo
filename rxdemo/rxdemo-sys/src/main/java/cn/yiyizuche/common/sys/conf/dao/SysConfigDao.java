package cn.yiyizuche.common.sys.conf.dao;

import cn.yiyizuche.common.sys.conf.entity.SysConfig;

public interface SysConfigDao {
    int deleteByPrimaryKey(int id);

    SysConfig insert(SysConfig record);

    SysConfig insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(int id);

    SysConfig updateByPrimaryKeySelective(SysConfig record);

    SysConfig updateByPrimaryKey(SysConfig record);
}