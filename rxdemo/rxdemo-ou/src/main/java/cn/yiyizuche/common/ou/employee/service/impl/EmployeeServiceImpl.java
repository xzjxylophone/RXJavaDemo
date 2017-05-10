package cn.yiyizuche.common.ou.employee.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.employee.service.EmployeeService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.service.impl 
 * @Class : EmployeeServiceImpl.java 
 * @Description :  员工服务层实现类
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 上午11:51:00 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeExtendDao employeeExtendDao;
	/**
	 * 根据员工id删除员工信息
	 * (非 Javadoc) 
	 *   
	 * @param id  员工id
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#deleteById(int)
	 */
	@Override
	public ResultMsg deleteById(int empId) {
		ResultMsg result = new ResultMsg();
		try{
			employeeExtendDao.deleteById(empId);
			//设置删除成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置删除失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		return result;
	}
	
	/**根据员工Id集合删除员工信息
	 * (非 Javadoc) 
	 *   
	 * @param ids  员工id集合
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#deleteByIds(java.util.List)
	 */
	@Override
	public ResultMsg deleteByIds(List<Integer> ids) {
		return null;//TODO
	}
	
	/**分页查询员工信息
	 * (非 Javadoc) 
	 *   
	 * @param page 分页对象
	 * @param depId 部门id
	 * @param jobNum 工号
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectEmployeeByPage(cn.yiyizuche.common.base.Page, int, java.lang.String)
	 */
	@Override
	public Page<Employee> selectEmployeeByPage(Page<Employee> page,Map<String,Object> params) {
		return employeeExtendDao.selectEmployeeByPage(page, params);
	}
	
	/**
	 * 分页查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param userName
	 * @param realName
	 * @param page
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectEmployeeVoListByName(java.lang.String, java.lang.String, cn.yiyizuche.common.base.Page)
	 */
	public Page<EmployeeVo> selectEmployeeVoListByName(String userName, String realName, int depId, Page<EmployeeVo> page) {
		Map<String,Object> parameMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(userName)){
			parameMap.put("userName", userName.trim());
		}
		if(StringUtils.isNotEmpty(realName)){
			parameMap.put("realName", realName.trim());
		}
		if(depId > 0){
			parameMap.put("depId", depId);
		}
		parameMap.put("deleteFlag", SysConstants.DELETE_FLAG_NORMAL);
		return this.employeeExtendDao.selectEmployeeVoByPage(page, parameMap);
	}
	
	/**
	 * 通过userId查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectEmployeeVoByUserId(int)
	 */
	public EmployeeVo selectEmployeeVoByUserId(int id) {
		List<EmployeeVo> employeeVos = this.employeeExtendDao.selectEmployeeVoByUserId(id);
		if (employeeVos == null) {
			return null;
		}
		if (employeeVos.size() == 0) {
			return null;
		}
		return employeeVos.get(0);
	}
	
	/**
	 * 通过employeeId查询EmployeeVo(非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectEmployeeVoByEmployeeId(int)
	 */
	public EmployeeVo selectEmployeeVoByEmployeeId(int id) {
		return this.employeeExtendDao.selectEmployeeVoByEmployeeId(id);
	}
	
	/**
	 * 更新用户状态(非 Javadoc) 
	 *   
	 * @param id
	 * @param status
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#updateEmployeeStatusByEmployeeId(int, int)
	 */
	public ResultMsg updateEmployeeStatusByEmployeeId(int id, int status) {
		return this.employeeExtendDao.updateEmployeeStatusByEmployeeId(id, status);
	}
	
	/**添加员工
	 * (非 Javadoc) 
	 *   
	 * @param employee 员工实体
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#addEmployee(cn.yiyizuche.common.ou.employee.entity.Employee)
	 */
	@Override
	public ResultMsg addEmployee(Employee employee) {
		ResultMsg result = new ResultMsg();
		try{
			if (!StringUtils.isNotEmpty(employee.getJobNumber())) {
				result.setState(SysConstants.FAIL);
				result.setMessage("工号不能为空");
				return result;
			}
			Employee tmpEmployee = this.employeeExtendDao.selectEmployeeByJobNumber(employee.getJobNumber());
			if (tmpEmployee != null && tmpEmployee.getId() > 0) {
				result.setState(SysConstants.FAIL);
				result.setMessage("工号已经存在无法添加");
				return result;
			}
			
			employeeExtendDao.insert(employee);
			//设置保存成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置保存失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 修改员工
	 * (非 Javadoc) 
	 *   
	 * @param employee 员工实体
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#updateEmployee(cn.yiyizuche.common.ou.employee.entity.Employee)
	 */
	@Override
	public ResultMsg updateEmployee(Employee employee) {
		ResultMsg result = new ResultMsg();
		try{
			Employee instance = this.employeeExtendDao.selectByPrimaryKey(employee.getId());
			instance.setDepId(employee.getDepId());
			instance.setUserId(employee.getUserId());
			instance.setJobNumber(employee.getJobNumber());
			instance.setWorkTime(employee.getWorkTime());
			instance.setEntryTime(employee.getEntryTime());
			instance.setAddress(employee.getAddress());
			instance.setRegister(employee.getRegister());
			instance.setIdNumber(employee.getIdNumber());
			instance.setUpdateUser(employee.getUpdateUser());
			instance.setUpdateTime(employee.getUpdateTime());
			employeeExtendDao.updateByPrimaryKey(instance);
			//设置保存成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置保存失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 根据员工Id查询员工实体
	 * (非 Javadoc) 
	 *   
	 * @param empId 员工id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectByPrimaryKey(int)
	 */
	@Override
	public Employee selectByPrimaryKey(int empId) {
		return employeeExtendDao.selectByPrimaryKey(empId);
	}
	
	/**
	 * 通过userId查询员工
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#selectByUserId(int)
	 */
	@Override
	public Employee selectByUserId(int id) {
		return this.employeeExtendDao.selectByUserId(id);
	}
	
	/**
	 * 查询所有员工信息(非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.employee.service.EmployeeService#loadAllEmployeeVo()
	 */
	@Override
	public List<EmployeeVo> loadAllEmployeeVo() {
		return employeeExtendDao.loadAllEmployeeVo();
	}
	
	private static final Logger _log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
}
