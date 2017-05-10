package cn.yiyizuche.common.sys.dic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.sys.dic.entity.DicItem;
import cn.yiyizuche.common.sys.dic.dao.DicItemDao;

@Repository(value="dicItemDao")
public class DicItemDaoImpl extends MyBatisDao implements DicItemDao {
	
	protected static final String NAMESPACE="DicItemMapper";

	public DicItem insert(DicItem record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public DicItem insertSelective(DicItem record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public DicItem selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof DicItem)) {
			return null;
		}
		return (DicItem) object;
	}

	public DicItem updateByPrimaryKeySelective(DicItem record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public DicItem updateByPrimaryKey(DicItem record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<DicItem> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
