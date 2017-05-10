package cn.yiyizuche.common.ou.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.yiyizuche.common.base.MyBatisDao;

import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.dao.UserDao;

@Repository(value="userDao")
public class UserDaoImpl extends MyBatisDao implements UserDao {
	
	protected static final String NAMESPACE="UserMapper";

	public User insert(User record) {
		this.getSqlSession().insert(NAMESPACE+".insert", record);
		return record;
	}

	public User insertSelective(User record) {
		this.getSqlSession().insert(NAMESPACE+".insertSelective", record);
		return record;
	}

	public int deleteByPrimaryKey(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}

	public User selectByPrimaryKey(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByPrimaryKey", id);
		if (!(object instanceof User)) {
			return null;
		}
		return (User) object;
	}

	public User updateByPrimaryKeySelective(User record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKey",record);
		return record;
	}

	public User updateByPrimaryKey(User record) {
		this.getSqlSession().update(NAMESPACE+".updateByPrimaryKeySelective",record);
		return record;
	}
	
	public List<User> queryByCondition(java.lang.String condition) {
		return this.getSqlSession().selectList(NAMESPACE+".queryByCondition", condition);
	}	

}
