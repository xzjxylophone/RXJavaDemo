package cn.yiyizuche.common.ou.department.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.service.DepartmentService;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.sys.util.PropertiesUtil;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.controller 
 * @Class : DepartmentController.java 
 * @Description :部门控制层
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月21日 上午9:23:53 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Controller
@RequestMapping("/ou/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	/**
	 * 
	 * @Method: addDepartment  
	 * @Description:添加部门
	 * @param dep
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:24:38
	 */
	@ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultMsg addDepartment(Department dep, HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user != null){
			dep.setCreateUser(user.getUserId());
		}
		return this.departmentService.addDepartment(dep);
	}


	/**
	 * 
	 * @Method: deleteByDepId  
	 * @Description:通过depId删除部门
	 * @param id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:24:12
	 */
	@ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultMsg deleteByDepId(@RequestParam(value = "id", defaultValue = "0") int id, HttpServletRequest request) {
		return this.departmentService.deleteByDepId(id);
	}


	/**
	 * 
	 * @Method: updateDepartment  
	 * @Description:更新部门
	 * @param dep
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:15
	 */
	@ResponseBody
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
	public ResultMsg updateDepartment(Department dep, HttpServletRequest req) {
		_log.debug("updateDepartment updateDepartment updateDepartment");
		HttpSession session = req.getSession();
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user != null){
			dep.setUpdateUser(user.getUserId());
			dep.setUpdateTime(new Date());
		}
		return this.departmentService.updateDepartment(dep);

	}


	/**
	 * 
	 * @Method: selectByDepName  
	 * @Description:通过部门名称查找部门
	 * @param name
	 * @return Department (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:20
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByDepName", method = RequestMethod.POST)
	public Department selectByDepName(@RequestParam(value = "name", defaultValue = "0") String name, HttpServletRequest request) {
		return this.departmentService.selectByDepName(name);
	}

	/**
	 * 
	 * @Method: selectAll  
	 * @Description:查找所有部门
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:24
	 */
	@ResponseBody
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	public List<Department> selectAll(HttpServletRequest request) {
		_log.debug("selectAll");
		List<Department> departments = this.departmentService.selectAll();
		HttpSession session = request.getSession(); 
		session.setAttribute("Department_List", departments);
		return departments;
	}

	/**
	 * 
	 * @Method: selectByCreateUserId  
	 * @Description:通过创建userId查找所有部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:27
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByCreateUserId", method = RequestMethod.POST)
	public List<Department> selectByCreateUserId(@RequestParam(value = "id", defaultValue = "0") int id, HttpServletRequest request) {
		return this.departmentService.selectByCreateUserId(id);
	}

	/**
	 * 
	 * @Method: selectByUpdateUserId  
	 * @Description:通过更新userId查找所有部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:31
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByUpdateUserId", method = RequestMethod.POST)
	public List<Department> selectByUpdateUserId(@RequestParam(value = "id", defaultValue = "0") int id, HttpServletRequest request) {
		return this.departmentService.selectByUpdateUserId(id);
	}

	/**
	 * 
	 * @Method: selectByParentDepId  
	 * @Description:通过父部门Id查找所有子部门
	 * @param id
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:34
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByParentDepId", method = RequestMethod.POST)
	public List<Department> selectByParentDepId(@RequestParam(value = "id", defaultValue = "0") int id, HttpServletRequest request) {
		return this.departmentService.selectByParentDepId(id);
	}

	/**
	 * 
	 * @Method: getDepartmentTreeByPid  
	 * @Description: (根据父ID查询部门树，除掉父本身)
	 * @param pId 父ID
	 * @return List<TreeNode> (部门树) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月24日 下午8:43:03
	 */
	@ResponseBody
	@RequestMapping(value="/getDepartmentTreeByPid", method = RequestMethod.POST)
	public List<TreeNode> getDepartmentTreeByPid(int pId){
		return departmentService.selectDepartmentTreeList(pId);
	}
	
	

	/**
	 * 
	 * @Method: selectEmpByDepId  
	 * @Description:根据部门部门id查询员工列表
	 * @param depId
	 * @return List<Employee> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:41
	 */
	@ResponseBody
    @RequestMapping(value = "/selectEmpByDepId", method = RequestMethod.POST)
	public List<Employee> selectEmpByDepId(@RequestParam(value = "depId", defaultValue = "0") int depId, HttpServletRequest request) {
		return this.departmentService.selectEmpByDepId(depId);
	}
	

	

	

	/**
	 * 
	 * @Method: selectEmpNotInDep  
	 * @Description:查询未分配部门的员工列表
	 * @return List<Employee> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:45
	 */
	@ResponseBody
    @RequestMapping(value = "/selectEmpNotInDep", method = RequestMethod.POST)
	public List<Employee> selectEmpNotInDep(HttpServletRequest request) {
		return this.departmentService.selectEmpNotInDep();
	}
	
	/**
	 * 
	 * @Method: updateEmpForDep  
	 * @Description:为部门分配员工
	 * @param depId
	 * @param empIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:48
	 */
	@ResponseBody
    @RequestMapping(value = "/updateEmpForDep", method = RequestMethod.POST)
	public ResultMsg updateEmpForDep(@RequestParam(value = "id", defaultValue = "0") int depId, @RequestParam(value = "empIds", defaultValue = "0") List<Integer> empIds, HttpServletRequest request) {
		return this.departmentService.updateEmpForDep(depId, empIds);

	}
	
	/**
	 * 
	 * @Method: selectByDepId  
	 * @Description:通过部门Id搜索部门
	 * @param id
	 * @return Department (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月21日 上午9:25:52
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByDepId", method = RequestMethod.POST)
	public Department selectByDepId(@RequestParam(value = "id", defaultValue = "0") int id, HttpServletRequest request) {
		return this.departmentService.selectDepartmentVoById(id);
	}
	
	/**
	 * 
	 * @Method: showDepartmentListPage  
	 * @Description: (跳转到部门首页)
	 * @return ModelAndView 指定视图页面
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月24日 上午10:55:50
	 */
	@RequestMapping(value = "/showDepartmentListPage", method = RequestMethod.GET)
	public ModelAndView showDepartmentListPage(){
		return new ModelAndView("/page/ou/department/department_list");
	}
	
	/**
	 * 
	 * @Method: findOrgPageByPid  
	 * @Description: 根据部门ID分页查询部门列表
	 * @param pId 部门ID
	 * @param current 当前页
	 * @param rowCount 每页条数
	 * @return Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午2:55:29
	 */
	@ResponseBody
	@RequestMapping(value = "/findOrgPageByPid", method = RequestMethod.POST)
	public Map<String, Object> findOrgPageByPid(int pId, int current, int rowCount){
		Page<Department> page = new Page<>(current, rowCount);
		Page<Department> pageResult = departmentService.findPageListByPid(pId, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	}
	
	
	private static final Logger _log = LoggerFactory.getLogger(DepartmentController.class);


}
