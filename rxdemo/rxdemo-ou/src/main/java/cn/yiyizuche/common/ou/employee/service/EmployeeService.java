package cn.yiyizuche.common.ou.employee.service;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.service 
 * @Class : EmployeeService.java 
 * @Description : 员工服务层接口
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 上午11:50:13 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface EmployeeService {
	
	/**
	 * 
	 * @Method: deleteById  
	 * @Description: 根据员工id删除员工信息
	 * @param id 员工id
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:48:01
	 */
	ResultMsg deleteById(int id);
	
	/**
	 * 
	 * @Method: deleteByIds  
	 * @Description: 根据员工Id集合删除员工信息
	 * @param ids 员工Id集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:48:06
	 */
	ResultMsg deleteByIds(List<Integer> ids);
	
	/**
	 * 
	 * @Method: selectEmployeeByPage  
	 * @Description: 分页查询员工信息
	 * @param page 分页信息
	 * @param params
	 * @return Page<Employee> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午2:02:20
	 */
	Page<Employee> selectEmployeeByPage(Page<Employee> page , Map<String,Object> params);
	
	/**
	 * 
	 * @Method: selectEmployeeVoListByName  
	 * @Description:分页查询EmployeeVo
	 * @param userName
	 * @param realName
	 * @param depId
	 * @param page
	 * @return Page<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:01:57
	 */
	Page<EmployeeVo> selectEmployeeVoListByName(String userName, String realName, int depId, Page<EmployeeVo> page);

	/**
	 * 
	 * @Method: selectEmployeeVoByUserId  
	 * @Description:通过userId查询EmployeeVo
	 * @param id
	 * @return EmployeeVo (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:02:01
	 */
	EmployeeVo selectEmployeeVoByUserId(int id);
	
	/**
	 * 
	 * @Method: selectEmployeeVoByEmployeeId  
	 * @Description:通过employeeId查询EmployeeVo
	 * @param id
	 * @return EmployeeVo (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:02:06
	 */
	EmployeeVo selectEmployeeVoByEmployeeId(int id);

	/**
	 * 
	 * @Method: updateEmployeeStatusByEmployeeId  
	 * 更新用户状态
	 * @param id
	 * @param status
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:02:09
	 */
	ResultMsg updateEmployeeStatusByEmployeeId(int id, int status);
	
	/**
	 * 
	 * @Method: addEmployee  
	 * @Description: 添加员工
	 * @param employee 员工实体
	 * @return ResultMsg 提示信息 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 上午10:46:21
	 */
	ResultMsg addEmployee(Employee employee);
	
	/**
	 * 
	 * @Method: updateEmployee  
	 * @Description: 修改员工
	 * @param employee 员工实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 上午10:46:26
	 */
	ResultMsg updateEmployee(Employee employee);
	/**
	 * 
	 * @Method: selectByPrimaryKey  
	 * @Description: 根据id查询员工实体
	 * @param empId 员工id
	 * @return Employee 返回员工实体
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 下午1:41:15
	 */
	Employee selectByPrimaryKey(int empId);
	
	/**
	 * 
	 * @Method: selectByUserId  
	 * @Description:通过userId查询员工
	 * @param id
	 * @return Employee (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月16日 上午11:48:18
	 */
	Employee selectByUserId(int id);
	
	/**
	 * 
	 * @Method: loadAllEmployeeVo  
	 * @Description: (查询所有员工信息)
	 * @return List<EmployeeVo> (返回所有员工信息) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午4:24:05
	 */
	List<EmployeeVo> loadAllEmployeeVo();
}
