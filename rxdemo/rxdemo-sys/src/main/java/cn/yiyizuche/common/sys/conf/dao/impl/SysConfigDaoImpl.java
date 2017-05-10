package cn.yiyizuche.common.sys.conf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.sys.conf.entity.SysConfig;
import cn.yiyizuche.common.sys.conf.dao.SysConfigDao;

@Repository(value="sysConfigDao")
public class SysConfigDaoImpl extends MyBatisDao implements SysConfigDao {
	
	protected static final String NAMESPACE="SysConfigMapper";

	public SysConfig insert(SysConfig record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public SysConfig insertSelective(SysConfig record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public SysConfig selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof SysConfig)) {
			return null;
		}
		return (SysConfig) object;
	}

	public SysConfig updateByPrimaryKeySelective(SysConfig record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public SysConfig updateByPrimaryKey(SysConfig record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<SysConfig> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
