package cn.yiyizuche.common.ou.employee.dao.impl.extend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao;
import cn.yiyizuche.common.ou.employee.dao.impl.EmployeeDaoImpl;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.sys.util.SysConstants;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.dao.impl.extend 
 * @Class : EmployeeExtendDaoImpl.java 
 * @Description : 员工数据层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 下午5:01:43 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="employeeExtendDao")
public class EmployeeExtendDaoImpl extends EmployeeDaoImpl implements EmployeeExtendDao {
	
	/**
	 * 根据员工id删除员工信息
	 * (非 Javadoc) 
	 *   
	 * @param id  员工ID
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#deleteById(int)
	 */
	@Override
	public void deleteById(int id) {
		this.getSqlSession().delete(NAMESPACE+".deleteById", id);
	}

	/**
	 * 分页查询员工信息
	 * (非 Javadoc) 
	 *   
	 * @param page 分页信息
	 * @param paramsMap
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmployeeByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<Employee> selectEmployeeByPage(Page<Employee> page,
			Map<String, Object> paramsMap) {
		return this.selectPage(page, NAMESPACE + ".selectEmployeeByPage", NAMESPACE + ".selectByPageCount", paramsMap);
	}
	
	/**
	 * 分页查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param page
	 * @param paramsMap
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmployeeVoByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	public Page<EmployeeVo> selectEmployeeVoByPage(Page<EmployeeVo> page, Map<String, Object> paramsMap) {
		return this.selectPage(page, NAMESPACE + ".selectEmployeeVoByPage", NAMESPACE + ".selectEmployeeVoByPageCount", paramsMap);
	}
	
	/**
	 * 通过userId查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmployeeVoByUserId(int)
	 */
	public List<EmployeeVo> selectEmployeeVoByUserId(int id) {
		return this.getSqlSession().selectList(NAMESPACE+".selectEmployeeVoByUserId", id);
	}
	
	/**
	 * 通过employeeId查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmployeeVoByEmployeeId(int)
	 */
	public EmployeeVo selectEmployeeVoByEmployeeId(int id) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectEmployeeVoByEmployeeId", id);
	}
	
	/**
	 * 通过工号查询员工(非 Javadoc) 
	 *   
	 * @param jobNumber
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmployeeByJobNumber(java.lang.String)
	 */
	public Employee selectEmployeeByJobNumber(String jobNumber) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectEmployeeByJobNumber", jobNumber);
	}
	
	/**
	 * 更新用户状态(非 Javadoc) 
	 *   
	 * @param id
	 * @param status
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#updateEmployeeStatusByEmployeeId(int, int)
	 */
	public ResultMsg updateEmployeeStatusByEmployeeId(int id, int status) {
		ResultMsg resultMsg = new ResultMsg();
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("status", status);
		this.getSqlSession().update(NAMESPACE + ".updateEmployeeStatusByEmployeeId", params);
		resultMsg.setState(SysConstants.SUCCESS);
		return resultMsg;
	}

	/**
	 * 根据员工id集合删除员工信息
	 * (非 Javadoc) 
	 *   
	 * @param ids 员工id集合 
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#deleteByIds(java.util.List)
	 */
	@Override
	public void deleteByIds(List<Integer> ids) {
		this.getSqlSession().delete(NAMESPACE+".deleteByIds", ids);
	}

	/**
	 * 根据部门部门id查询员工列表
	 * (非 Javadoc) 
	 *   
	 * @param depId
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmpByDepId(int)
	 */
	@Override
	public List<Employee> selectEmpByDepId(int depId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectEmpByDepId", depId);
	}
	
	/**
	 * 搜索员工(非 Javadoc) 
	 *   
	 * @param depId
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmpVoByDepId(int)
	 */
	@Override
	public List<EmployeeVo> selectEmpVoByDepId(int depId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectEmpVoByDepId", depId);
	}

	/**查询未分配组织的员工
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmpNotInDep()
	 */
	@Override
	public List<Employee> selectEmpNotInDep() {
		return this.getSqlSession().selectList(NAMESPACE+".selectEmpNotInDep");
	}

	/**
	 * 为部门分配员工
	 * (非 Javadoc) 
	 *   
	 * @param params  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#updateEmpForDep(java.util.Map)
	 */
	@Override
	public void updateEmpForDep(Map<String, Object> params) {
		this.getSqlSession().update(NAMESPACE+".updateEmpForDep", params);
	}

	/**查询所有的员工信息
	 * (非 Javadoc)
	 *
	 * @return
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectAllEmp()
	 */
	@Override
	public List<Employee> selectAllEmp() {
		return this.getSqlSession().selectList(NAMESPACE+".selectAllEmp");
	}

	/**
	 * 通过userId查询员工
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectByUserId(int)
	 */
	@Override
	public Employee selectByUserId(int id) {
		Object object = this.getSqlSession().selectOne(NAMESPACE + ".selectByUserId", id);
		if (!(object instanceof Employee)) {
			return null;
		}
		return (Employee) object;
	}

	/**
	 * 查询所有员工信息(非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#loadAllEmployeeVo()
	 */
	@Override
	public List<EmployeeVo> loadAllEmployeeVo() {
		return this.getSqlSession().selectList(NAMESPACE+".loadAllEmployeeVo","");
	}
	
	/**
	 * 查询除本人外的本部门人员（选择审批人时使用）
	 * (非 Javadoc) 
	 *   
	 * @param params 查询条件
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao#selectEmpVoByParam(int, int)
	 */
	@Override
	public List<EmployeeVo> selectEmpVoByParam(Map<String, Integer> params) {
		return this.getSqlSession().selectList(NAMESPACE+".selectEmpVoByParam", params);
	}
}
