package cn.yiyizuche.common.ou.department.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.service.DepartmentService;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.service 
 * @Class : DepartmentServiceTest.java 
 * @Description : 员工业务逻辑层测试类
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 下午5:03:01 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class DepartmentServiceTest extends BaseTest {
	@Autowired
	private DepartmentService departmentService;

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

	private String departmentFormatterString(Department department) {
		if (department == null) {
			return "department is null";
		}
		return "id:" + department.getId() + ", depName:" + department.getName();
	}
	
	@Test
	public void test_allAction() {
		int count = 10;
		// 批量添加
		for (int i = 0; i < count; i++) {
			Department dep = new Department();
			dep.setCreateUser(1);
			dep.setName("juese" + (i + 1));
			dep.setRemark("remark" + (i + 1));
			ResultMsg resultMsg = this.departmentService.addDepartment(dep);
			_log.info("add result msg:" + resultMsg.toString());
		}

		// 查询全部
		List<Department> deps = this.departmentService.selectAll();
		int depsSize = deps.size();
		int min = 0;
		int max = depsSize - 1;
		// 随机一个数
		int deleteNumber = this.random(min, max);
		_log.info("random deleteNumber:" + deleteNumber);
		Department deleteDep = deps.get(deleteNumber);
		int deleteDepId = deleteDep.getId();
//		String deleteDepName = deleteDep.getName();
		_log.info("before delete dep:" + this.departmentFormatterString(deleteDep));
		// 删除部门
		ResultMsg deleteResultMsg = this.departmentService.deleteByDepId(deleteDepId);
		_log.info("delete result:" + deleteResultMsg.toString());
		
		
//		// 查询刚刚删除的部门
//		Department afterDeleteDep = this.departmentService.selectByDepName(deleteDepName);
//		_log.info("after delete dep:" + this.departmentFormatterString(afterDeleteDep));

		// 更新部门
		int updateNumber = this.random(min, max - 1);
		Department updateDep = deps.get(updateNumber);
		int suffix = this.random(0, 100);
		String newDepName = "xuzhijun" + suffix;
		_log.info("except old depName:" + updateDep.getName() + ",new depName:" + newDepName);
		updateDep.setName(newDepName);
		String updateDepName = updateDep.getName();
		_log.info("before update dep:" + this.departmentFormatterString(updateDep));
		ResultMsg updateResultMsg = this.departmentService.updateDepartment(updateDep);
		_log.info("update result:" + updateResultMsg.toString());

		// 查询刚刚更新部门
		Department afterUpdateDep = this.departmentService.selectByDepName(updateDepName);
		_log.info("after update dep:" + this.departmentFormatterString(afterUpdateDep));

		// 通过部门名查询部门
		String depName = afterUpdateDep.getName();
		Department selectByDepNameDep = this.departmentService.selectByDepName(depName);
		_log.info("selectByDepName dep:" + this.departmentFormatterString(selectByDepNameDep));

		// 查询创建人Id所有部门
		List<Department> createUserDeps = this.departmentService.selectByCreateUserId(1);
		for (Department dep : createUserDeps) {
			_log.info("createUserDeps:" + this.departmentFormatterString(dep));
		}

		// 查询更新人Id所有部门
		List<Department> updateUserDeps = this.departmentService.selectByUpdateUserId(1);
		for (Department dep : updateUserDeps) {
			_log.info("updateUserDeps:" + this.departmentFormatterString(dep));
		}

	}
	
	@Test
	public void test_tree() {
		List<TreeNode> treeNodes = this.departmentService.selectDepartmentTreeList(0);
		for (TreeNode treeNode : treeNodes) {
			_log.info("treeNode:" + treeNode.getName());
		}
	}
	
	/**
	 * 
	 * @Method: testSelectEmpByDepId  
	 * @Description: 根据部门Id查询员工信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午2:39:25
	 */
	@Test
	public void testSelectEmpByDepId(){
		int depId = 10;
		Assert.assertEquals(2, departmentService.selectEmpByDepId(depId).size());
	}
	
	@Test
	public void testSelectEmpVoByDepId(){
		
		int depId = 13;
		List<EmployeeVo> employeeVos = this.departmentService.selectEmpVoByDepId(depId);
		for (EmployeeVo employeeVo : employeeVos) {
			_log.info("VO:" + employeeVo.toString());
		}
	}
	/**
	 * 
	 * @Method: testSelectEmpNotInDep  
	 * @Description: 查询未分配部门的员工列表 
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午3:01:15
	 */
	@Test
	public void testSelectEmpNotInDep(){
		Assert.assertEquals(0, departmentService.selectEmpNotInDep().size());
	}
	/**
	 * 
	 * @Method: testAllotEmpForDep  
	 * @Description: 为部门分配员工
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午3:37:41
	 */
	@Test
	public void testUpdateEmpForDep(){
		int depId = 1;
		List<Integer> empIds = new ArrayList<Integer>();
		empIds.add(1);
		empIds.add(3);
		empIds.add(4);
		Assert.assertEquals(SysConstants.SUCCESS, departmentService.updateEmpForDep(depId,empIds).getState());
	}
	private static final Log _log = LogFactory.getLog(DepartmentServiceTest.class);

}
