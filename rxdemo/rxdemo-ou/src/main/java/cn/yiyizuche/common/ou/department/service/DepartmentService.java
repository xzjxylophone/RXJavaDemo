package cn.yiyizuche.common.ou.department.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.entity.DepartmentVo;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.service 
 * @Class : DepartmentService.java 
 * @Description : Department Service
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午6:08:39 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public interface DepartmentService {

	/**
	 * 
	 * @Method: addDepartment  
	 * @Description: 添加部门
	 * @param dep
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:08:49
	 */
	ResultMsg addDepartment(Department dep);
	
	/**
	 * 
	 * @Method: deleteByDepId  
	 * @Description: 通过depId删除部门
	 * @param id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:08:52
	 */
	ResultMsg deleteByDepId(int id);
	
	/**
	 * 
	 * @Method: selectLeaderAndSuperLeaderByDepId  
	 * @Description:通过部门ID查询部门主管信息和超级主管信息
	 * @param depId
	 * @return Map<String,EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月31日 下午9:09:00
	 */
	Map<String, EmployeeVo> selectLeaderAndSuperLeaderByDepId(int depId);
	
	/**
	 * 
	 * @Method: updateDepartment  
	 * @Description: 更新部门
	 * @param dep
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:08:57
	 */
	ResultMsg updateDepartment(Department dep);
	
	/**
	 * 
	 * @Method: selectDepartmentTreeList  
	 * @Description:搜索部门树结构
	 * @param pid
	 * @return List<TreeNode> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月11日 下午2:44:56
	 */
	List<TreeNode> selectDepartmentTreeList(int pId);
	
	/**
	 * 
	 * @Method: selectByDepName  
	 * @Description: 通过部门名称查找部门
	 * @param name
	 * @return Department (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:08:59
	 */
	Department selectByDepName(String name);

	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 查找所有部门
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:09:02
	 */
	List<Department> selectAll();

	/**
	 * 
	 * @Method: selectByCreateUserId  
	 * @Description: 通过创建userId查找所有部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:09:04
	 */
	List<Department> selectByCreateUserId(int id);
	
	/**
	 * 
	 * @Method: selectByUpdateUserId  
	 * @Description: 通过更新userId查找所有部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:09:08
	 */
	List<Department> selectByUpdateUserId(int id);
	
	/**
	 * 
	 * @Method: selectByPaerntDepId  
	 * @Description: 通过父部门Id查找所有子部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:09:12
	 */
	List<Department> selectByParentDepId(int id);
	
	/**
	 * 
	 * @Method: selectEmpByDepId  
	 * @Description: 根据部门部门id查询员工列表
	 * @param depId 部门id
	 * @return List<Employee> 员工集合 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午2:28:14
	 */
	List<Employee> selectEmpByDepId(int depId);
	
	/**
	 * 
	 * @Method: selectEmpVoByDepId  
	 * @Description:搜索员工
	 * @param depId
	 * @return List<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月23日 下午8:14:58
	 */
	List<EmployeeVo> selectEmpVoByDepId(int depId);
	
	/**
	 * 
	 * @Method: selectEmpNotInDep  
	 * @Description: 查询未分配部门的员工列表
	 * @return List<Employee> 员工列表
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午2:59:04
	 */
	List<Employee> selectEmpNotInDep();
	
	/**
	 * 
	 * @Method: updateEmpForDep  
	 * @Description: 为部门分配员工
	 * @param params
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 下午3:12:34
	 */
	ResultMsg updateEmpForDep(int depId, List<Integer> empIds);
	
	/**
	 * 
	 * @Method: selectByDepId  
	 * @Description:通过部门Id搜索部门
	 * @param id
	 * @return Department (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月16日 下午2:06:32
	 */
	Department selectByDepId(int id);
	
	/**
	 * 
	 * @Method: findPageListByPid  
	 * @Description: (根据父ID分页查询部门列表)
	 * @param pid 父ID
	 * @param page 分页对象
	 * @return Page<Department> (部门分页集合) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月24日 下午8:48:42
	 */
	Page<Department> findPageListByPid(int pid, Page<Department> page);
	
	/**
	 * 
	 * @Method: selectDepartmentVoById  
	 * @Description: (根据部门ID查询部门VO实体)
	 * @param id 部门ID
	 * @return DepartmentVo (返回DepartmentVo实体) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午7:10:00
	 */
	DepartmentVo selectDepartmentVoById(int id);
	
	/**
	 * 
	 * @Method: selectByCode  
	 * @Description: (根据编码查询部门,如果已存在，返回部门，否则返回null)
	 * @param departmentCode 部门编码
	 * @return Department (如果有，返回部门，否则返回null) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月30日 上午10:13:17
	 */
	Department selectByCode(String departmentCode);

	/**
	 * 
	 * @Method: selectEmpVoByParam  
	 * @Description: 查询除本人外的本部门人员（选择审批人时使用）
	 * @param userDepId 用户所属部门id
	 * @param userId 当前用户ID
	 * @return List<EmployeeVo> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月30日 下午5:56:46
	 */
	List<EmployeeVo> selectEmpVoByParam(int userDepId, int userId);
}
