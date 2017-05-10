package cn.yiyizuche.common.ou.employee.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

import cn.yiyizuche.common.base.BaseTest;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.department.service.impl.DepartmentServiceTest;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.employee.service.EmployeeService;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.service.impl 
 * @Class : EmployeeServiceImplTest.java 
 * @Description : 员工测试类
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 下午12:03:32 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class EmployeeServiceImplTest extends BaseTest{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 
	 * @Method: testAddEmployeed  
	 * @Description: 测试添加员工
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 下午1:26:03
	 */
	@Test
	public void testAddEmployeed() {
		//设置员工信息
		Employee employee = new Employee();
		employee.setAddress("员工地址");
		employee.setEntryTime(new Date());
		employee.setDepId(10);
		employee.setUserId(101);
		
		//添加断言：期望添加成功
		Assert.assertEquals(SysConstants.SUCCESS, employeeService.addEmployee(employee).getState());
	}
	
	/**
	 * 
	 * @Method: testUpdateEmployee  
	 * @Description: 测试修改员工
	 * @param employee 员工实体
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 下午1:36:08
	 */
	@Test
	public void testUpdateEmployee(){
		Employee employee = employeeService.selectByPrimaryKey(101);
		employee.setAddress("测试员工修改");
		
		//添加断言：期望修改成功
		Assert.assertEquals(SysConstants.SUCCESS, employeeService.updateEmployee(employee).getState());
		
	}
	/**
	 * 
	 * @Method: testDeleteById  
	 * @Description: 根据员工id删除员工信息
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午12:03:50
	 */
	@Test
	public void testDeleteById() {
		int empId = 11;
		Assert.assertEquals(SysConstants.SUCCESS, employeeService.deleteById(empId).getState());
	}

	
	@Test
	public void test_page() {
		Page<EmployeeVo> page = new Page<>(1, 10);

		this.employeeService.selectEmployeeVoListByName("", "", 0, page);
		
		List<EmployeeVo> employeeVos = page.getResult();
		for (EmployeeVo employeeVo : employeeVos) {
			_log.info("VO:" + employeeVo.toString());
		}
		
		
	}
	
	@Test
	public void test_select() {
		int userId = 101;
		int employeeId = 142;
		EmployeeVo employeeVo_UserId = this.employeeService.selectEmployeeVoByUserId(userId);
		
		EmployeeVo employeeVo_EmpId = this.employeeService.selectEmployeeVoByEmployeeId(employeeId);
		_log.info("userid emp:" + employeeVo_UserId.toString());
		_log.info("empid emp:" + employeeVo_EmpId.toString());
	}
	
	
	
	/**
	 * 
	 * @Method: testSelectEmployeeByPage  
	 * @Description: 测试分页查询
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午2:07:42
	 */
	@Test
	public void testSelectEmployeeByPage(){
		Page<Employee> page = new Page<Employee>();
		page.setPageNo(1);
		page.setPageSize(10);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("depId", 10);
		params.put("jobNum", "");
		//添加断言
		assertEquals(3,employeeService.selectEmployeeByPage(page, params).getResult().size());
	}
	 
	
//	@Test
//	public void testDeleteByIds() {
//	}

	//注入service层
	@Autowired
	private EmployeeService employeeService;
	
	private static final Log _log = LogFactory.getLog(EmployeeServiceImplTest.class);

}
