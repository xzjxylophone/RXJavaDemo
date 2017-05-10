package cn.yiyizuche.common.ou.department.dao.impl.extend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao;
import cn.yiyizuche.common.ou.department.dao.impl.DepartmentDaoImpl;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.entity.DepartmentVo;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.dao.impl.extend 
 * @Class : DepartmentExtendDaoImpl.java 
 * @Description :  Department Extend Dao Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午6:06:45 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="departmentExtendDao")
public class DepartmentExtendDaoImpl extends DepartmentDaoImpl implements DepartmentExtendDao{

	/**
	 * 通过部门名称查找部门
	 * (非 Javadoc) 
	 *   
	 * @param depName
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectByDepName(java.lang.String)
	 */
	public Department selectByDepName(String depName) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByDepName", depName);
	}
	
	/**
	 * 通过depId删除部门
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#deleteByDepId(int)
	 */
	public int deleteByDepId(int id) {
		return this.getSqlSession().delete(NAMESPACE+".deleteByPrimaryKey", id);
	}
	
	/**
	 * 查找所有部门
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectAll()
	 */
	public List<Department> selectAll() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAll");
	}
	
	/**
	 * 通过创建userId查找所有部门
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectByCreateUserId(int)
	 */
	public List<Department> selectByCreateUserId(int id) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectByCreateUserId", id);
	}
	
	/**
	 * 通过更新userId查找所有部门
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectByUpdateUserId(int)
	 */
	public List<Department> selectByUpdateUserId(int id) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectByUpdateUserId", id);
	}
	
	/**
	 * 通过父部门Id查找所有子部门
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectByPaerntDepId(int)
	 */
	public List<Department> selectByParentDepId(int pId) {
		Map<String, Object> map = new HashMap();
		map.put("pId", pId);
		return this.getSqlSession().selectList(NAMESPACE + ".findChildListByParam", map);
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param params 
	 * @param page
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#findPageListByPid(java.util.Map, cn.yiyizuche.common.base.Page)
	 */
	@Override
	public Page<Department> findPageListByPid(Map<String, Object> params, Page<Department> page) {
		return this.selectPage(page, NAMESPACE + ".findPageListByPid", NAMESPACE + ".findPageCountByPid", params);
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectDepartmentVoById(int)
	 */
	@Override
	public DepartmentVo selectDepartmentVoById(int id){
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectDepartmentVoById", id);
		if (!(object instanceof Department)) {
			return null;
		}
		return (DepartmentVo) object;
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param departmentCode
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao#selectByCode(java.lang.String)
	 */
	@Override
	public Department selectByCode(String departmentCode) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectByCode", departmentCode );
	}
}
