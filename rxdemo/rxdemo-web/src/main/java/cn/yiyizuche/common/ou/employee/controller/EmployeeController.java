package cn.yiyizuche.common.ou.employee.controller;

import java.util.Date;
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

import cn.yiyizuche.common.base.BaseController;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.employee.service.EmployeeService;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.sys.util.DateUtil;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.controller 
 * @Class : employeeController.java 
 * @Description : 员工控制层
 * 
//	有关员工表和用户表的关系处理说明：
//	1.员工相当于用户的扩展信息，员工与用户若存在关联，为1对1关系，员工表数据维护一个用户id。
//	2.添加员工时，员工的部门为非必填字段。员工必须要关联一个用户。登录用户时，检查用户的员工信息是否存在，不存在员工信息的用户不予登录，并提示。
//	3.员工管理中，没有删除，没有启用/禁用，员工受用户删除字段影响，列出所有用户信息为未删除状态的员工信息。删除用户后，该用户的员工信息不再展示。
//	4.删除用户时，需检查用户是否已经关联角色，检查用户是否为部门负责人或者部门主管。
//	5.用户删除字段标识为1，该用户数据为已删除数据，不会在系统内显示；用户禁用，该用户仍在系统内显示，但是该用户不能登录。
//	6.oa模块中获取审批人，应查询有员工信息的用户。
//	8.导出考勤记录时，应该以员工表为基础去查询。所有用户信息为未删除状态的员工的考勤记录。
	
	
 * @author :jiwenfang
 * @CreateDate : 2017年3月13日 上午10:21:22 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@RequestMapping("/ou/employee")
@Controller
public class EmployeeController extends BaseController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 
	 * @Method: updateEmployee  
	 * @Description: 修改员工
	 * @param employee 员工实体
	 * @return ResultMsg 提示信息 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 下午1:24:11
	 */
	@RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateEmployee(Employee employee, HttpServletRequest req){
		HttpSession session = req.getSession();
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user != null){
			employee.setUpdateUser(user.getUserId());
			employee.setUpdateTime(new Date());
		}
		return employeeService.updateEmployee(employee);
	}
	
	/**
	 * 
	 * @Method: deleteById  
	 * @Description: 根据员工id删除员工信息
	 * @param id 员工ID
	 * @return ResultMsg (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 上午10:22:56
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteById(int id){
		return employeeService.deleteById(id);
	}

	/**
	 * 
	 * @Method: addEmployee  
	 * @Description: 添加员工
	 * @param employee 员工实体
	 * @return ResultMsg 提示信息
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月13日 下午1:23:08
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
	public ResultMsg addEmployee(Employee employee, HttpServletRequest request) {
		_log.debug("addEmployee");
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user != null){
			employee.setCreateUser(user.getUserId());
			employee.setCreateTime(new Date());
		}
		return this.employeeService.addEmployee(employee);
	}
	
	/**
	 * 
	 * @Method: selectEmployeeVoByEmployeeId  
	 * @Description:通过employeeId查询EmployeeVo
	 * @param employeeId
	 * @param req
	 * @return EmployeeVo (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:03:59
	 */
	@ResponseBody
    @RequestMapping(value = "/selectEmployeeVoByEmployeeId", method = RequestMethod.POST)
    public EmployeeVo selectEmployeeVoByEmployeeId(@RequestParam(value = "employeeId", defaultValue = "0") Integer employeeId, HttpServletRequest req) {
		EmployeeVo employeeVo = this.employeeService.selectEmployeeVoByEmployeeId(employeeId);
		formatterEmployeeVoDateTime(employeeVo);
		return employeeVo;
	}

	/**
	 * 
	 * @Method: selectEmployeeVoByUserId  
	 * @Description:通过userId查询EmployeeVo
	 * @param userId
	 * @param req
	 * @return EmployeeVo (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:04:01
	 */
	@ResponseBody
    @RequestMapping(value = "/selectEmployeeVoByUserId", method = RequestMethod.POST)
    public EmployeeVo selectEmployeeVoByUserId(@RequestParam(value = "userId", defaultValue = "0") Integer userId, HttpServletRequest req) {
		EmployeeVo employeeVo = this.employeeService.selectEmployeeVoByUserId(userId);
		formatterEmployeeVoDateTime(employeeVo);
		return employeeVo;
	}
	
	/**
	 * 
	 * @Method: selectEmployeeVoListByName  
	 * @Description:分页查询EmployeeVo
	 * @param userName
	 * @param realName
	 * @param page
	 * @return Page<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:04:17
	 */
	@ResponseBody
	@RequestMapping(value = "/selectEmployeeVoListByName", method = RequestMethod.POST)
	public Page<EmployeeVo> selectEmployeeVoListByName(@RequestParam(value = "userName", defaultValue = "") String userName, String realName, int depId, Page<EmployeeVo> page) {
		_log.debug("selectEmployeeVoListByName");
		Page<EmployeeVo> pageResult = this.employeeService.selectEmployeeVoListByName(userName, realName, depId, page);
		List<EmployeeVo> employeeVos = pageResult.getResult();
		if (employeeVos != null) {
			for (EmployeeVo employeeVo : employeeVos) {
				formatterEmployeeVoDateTime(employeeVo);
			}
		}
		return pageResult;
	}

	/**
	 * 
	 * @Method: searchEmployeeVoByPage  
	 * @Description:分页查询EmployeeVo
	 * @param userName
	 * @param realName
	 * @param current
	 * @param rowCount
	 * @return Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:04:07
	 */
	@ResponseBody
	@RequestMapping(value = "/searchEmployeeVoByPage", method = RequestMethod.POST)
	public Map<String, Object> searchEmployeeVoByPage(String userName, String realName, int depId, int current, int rowCount) {
		_log.debug("searchEmployeeVoByPage");
		Page<EmployeeVo> page = new Page<>(current, rowCount);
		Page<EmployeeVo> pageResult = this.employeeService.selectEmployeeVoListByName(userName, realName, depId, page);
		List<EmployeeVo> employeeVos = pageResult.getResult();
		if (employeeVos != null) {
			for (EmployeeVo employeeVo : employeeVos) {
				formatterEmployeeVoDateTime(employeeVo);
			}
		}
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	}
	
	/**
	 * 
	 * @Method: updateEmployeeStatusByEmployeeId  
	 * @Description:更新用户状态
	 * @param id
	 * @param status
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:03:54
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEmployeeStatusByEmployeeId",method = RequestMethod.POST)
	public ResultMsg updateEmployeeStatusByEmployeeId(int id, int status) {
		return this.employeeService.updateEmployeeStatusByEmployeeId(id, status);
	}
	
	/**
	 * 
	 * @Method: showEmployeeListPage  
	 * @Description:跳转到员工
	 * @return ModelAndView (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月24日 上午10:58:01
	 */
	@ResponseBody
	@RequestMapping(value = "/showEmployeeListPage", method = RequestMethod.GET)
	public ModelAndView showEmployeeListPage(){
		ModelAndView view = new ModelAndView("/page/ou/employee/employee_list");
		return view;
	}
	
	/**
	 * 
	 * @Method: loadAllEmployeeVo  
	 * @Description: (查询所有员工信息)
	 * @return List<EmployeeVo> (返回员工集合) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午4:20:17
	 */
	@ResponseBody
	@RequestMapping(value="/loadAllEmployeeVo", method = RequestMethod.POST)
	public List<EmployeeVo> loadAllEmployeeVo(){
		List<EmployeeVo> list = employeeService.loadAllEmployeeVo();
		if (list != null) {
			for (EmployeeVo employeeVo : list) {
				formatterEmployeeVoDateTime(employeeVo);
			}
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Method: formatterEmployeeVoDateTime  
	 * @Description:
	 * @param employeeVo void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月6日 下午2:32:41
	 */
	private void formatterEmployeeVoDateTime(EmployeeVo employeeVo) {
		if (employeeVo == null) {
			return;
		}
		String formatterString = DateUtil.DEFAULT_DATE_FORMAT;
		if (employeeVo.getBirthdate() != null) {
			employeeVo.setBirthdateString(DateUtil.date2Str(employeeVo.getBirthdate(), formatterString));
		}
		if (employeeVo.getEntryTime() != null) {
			employeeVo.setEntryTimeString(DateUtil.date2Str(employeeVo.getEntryTime(), formatterString));
		}
		if (employeeVo.getWorkTime() != null) {
			employeeVo.setWorkTimeString(DateUtil.date2Str(employeeVo.getWorkTime(), formatterString));
		}
		if (employeeVo.getCreateTime() != null) {
			employeeVo.setCreateTimeString(DateUtil.date2Str(employeeVo.getCreateTime(), formatterString));
		}
		if (employeeVo.getUpdateTime() != null) {
			employeeVo.setUpdateTimeString(DateUtil.date2Str(employeeVo.getUpdateTime(), formatterString));
		}
		
	}
	
	private static final Logger _log = LoggerFactory.getLogger(EmployeeController.class);

}
