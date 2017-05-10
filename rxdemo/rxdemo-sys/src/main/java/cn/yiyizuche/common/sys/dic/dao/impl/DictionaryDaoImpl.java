package cn.yiyizuche.common.sys.dic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.sys.dic.entity.Dictionary;
import cn.yiyizuche.common.sys.dic.dao.DictionaryDao;

@Repository(value="dictionaryDao")
public class DictionaryDaoImpl extends MyBatisDao implements DictionaryDao {
	
	protected static final String NAMESPACE="DictionaryMapper";

	public Dictionary insert(Dictionary record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public Dictionary insertSelective(Dictionary record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public Dictionary selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof Dictionary)) {
			return null;
		}
		return (Dictionary) object;
	}

	public Dictionary updateByPrimaryKeySelective(Dictionary record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public Dictionary updateByPrimaryKey(Dictionary record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<Dictionary> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
