package cn.yiyizuche.common.ou.menu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.menu.dao.MenuDao;

@Repository(value="menuDao")
public class MenuDaoImpl extends MyBatisDao implements MenuDao {
	
	protected static final String NAMESPACE="MenuMapper";

	public Menu insert(Menu record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public Menu insertSelective(Menu record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public Menu selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof Menu)) {
			return null;
		}
		return (Menu) object;
	}

	public Menu updateByPrimaryKeySelective(Menu record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public Menu updateByPrimaryKey(Menu record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<Menu> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
