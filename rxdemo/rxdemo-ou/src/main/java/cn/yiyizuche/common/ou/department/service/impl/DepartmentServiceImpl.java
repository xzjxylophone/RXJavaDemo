package cn.yiyizuche.common.ou.department.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.department.dao.extend.DepartmentExtendDao;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.entity.DepartmentVo;
import cn.yiyizuche.common.ou.department.service.DepartmentService;
import cn.yiyizuche.common.ou.employee.dao.extend.EmployeeExtendDao;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.role.service.impl.RoleServiceImpl;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.ou.department.service.impl
 * @Class : DepartmentServiceImpl.java
 * @Description : Department Service Impl
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午6:10:34
 * @version : V1.0.0
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog: Name Date Reason/Contents ---------------------------------------
 *            *** **** ****
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentExtendDao departmentExtendDao;
	@Autowired
	private EmployeeExtendDao employeeExtendDao;
	
	/**
	 * 添加部门 (非 Javadoc)
	 * 
	 * @param dep
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#addDepartment(cn.yiyizuche.common.ou.department.entity.Department)
	 */
	public ResultMsg addDepartment(Department dep) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断部门数据是否为空
		if (dep == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.DEPARTMENT_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断部门名是否为空
			if (dep.getName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_NAME_EMPTY);
				return resultMsg;
			}
			// 判断部门名是否存在
			Department tmp = this.selectByDepName(dep.getName());
			if (tmp != null) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_NAME_EXIST);
				return resultMsg;
			}
			tmp = selectByCode(dep.getCode());
			if(tmp != null){
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_CODE_EXIST);
				return resultMsg;
			}
			dep.setCreateTime(new Date());
			dep.setUpdateTime(new Date());
			dep.setUpdateUser(dep.getCreateUser());
			this.departmentExtendDao.insert(dep);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			return resultMsg;
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return resultMsg;
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param departmentCode
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByCode(java.lang.String)
	 */
	@Override
	public Department selectByCode(String departmentCode){
		return departmentExtendDao.selectByCode(departmentCode);
	}
	
	/**
	 * 通过部门ID查询部门主管信息和超级主管信息(非 Javadoc) 
	 *   
	 * @param depId
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectLeaderAndSuperLeaderByDepId(int)
	 */
	public Map<String, EmployeeVo> selectLeaderAndSuperLeaderByDepId(int depId) {
		Map<String, EmployeeVo> resutl = new HashMap<>();
		Department department = this.selectByDepId(depId);
		if (department != null) {
			int leaderId = department.getLeaderId();
			int superLeader = department.getSupleaderId();
			List<EmployeeVo> leaders = this.employeeExtendDao.selectEmployeeVoByUserId(leaderId);
			if (leaders != null && leaders.size() > 0) {
				EmployeeVo leaderEmployeeVo = leaders.get(0);
				resutl.put("leader", leaderEmployeeVo);
			}
			List<EmployeeVo> superLeaders = this.employeeExtendDao.selectEmployeeVoByUserId(superLeader);
			if (superLeaders != null && superLeaders.size() > 0) {
				EmployeeVo superLeaderEmployeeVo = superLeaders.get(0);
				resutl.put("superLeader", superLeaderEmployeeVo);
			}
		}
		return resutl;
	}

	/**
	 * 通过depId删除部门 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#deleteByDepId(int)
	 */
	public ResultMsg deleteByDepId(int id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			List<Employee> employees = this.employeeExtendDao.selectEmpByDepId(id);
			if (employees != null && employees.size() > 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_EMPOLYEE_EXIST);
				return resultMsg;
			}
			this.departmentExtendDao.deleteByDepId(id);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.DELETE_SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		return resultMsg;
	}

	/**
	 * 更新部门 (非 Javadoc)
	 * 
	 * @param dep
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#updateDepartment(cn.yiyizuche.common.ou.department.entity.Department)
	 */
	public ResultMsg updateDepartment(Department dep) {
		ResultMsg resultMsg = new ResultMsg();
		// 判断部门数据是否为空
		if (dep == null) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.DEPARTMENT_INFO_ERROR);
			return resultMsg;
		}
		try {
			// 判断部门名是否为空
			if (dep.getName().length() == 0) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_NAME_EMPTY);
				return resultMsg;
			}
			// 判断部门名是否存在
			Department tmp = this.selectByDepName(dep.getName());
			if (tmp != null && tmp.getId() != dep.getId()) {
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_NAME_EXIST);
				return resultMsg;
			}
			tmp = selectByCode(dep.getCode());
			if(tmp != null && tmp.getId() != dep.getId()){
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.DEPARTMENT_CODE_EXIST);
				return resultMsg;
			}
			Department instance = this.departmentExtendDao.selectByPrimaryKey(dep.getId());
			instance.setpId(dep.getpId());
			instance.setName(dep.getName());
			instance.setShortName(dep.getShortName());
			instance.setLeaderId(dep.getLeaderId());
			instance.setSupleaderId(dep.getSupleaderId());
			instance.setPunchIn(dep.getPunchIn());
			instance.setPunchOut(dep.getPunchOut());
			instance.setCode(dep.getCode());
			instance.setSort(dep.getSort());
			instance.setRemark(dep.getRemark());
			instance.setUpdateUser(dep.getUpdateUser());
			instance.setUpdateTime(dep.getUpdateTime());
			this.departmentExtendDao.updateByPrimaryKey(instance);
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(SysConstants.SAVE_SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			_log.error(e.getMessage());
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return resultMsg;
	}

	/**
	 * 通过部门名称查找部门 (非 Javadoc)
	 * 
	 * @param name
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByDepName(java.lang.String)
	 */
	public Department selectByDepName(String name) {
		return this.departmentExtendDao.selectByDepName(name);
	}

	/**
	 * 查找所有部门 (非 Javadoc)
	 * 
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectAll()
	 */
	public List<Department> selectAll() {
		return this.departmentExtendDao.selectAll();
	}

	/**
	 * 通过创建userId查找所有部门 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByCreateUserId(int)
	 */
	public List<Department> selectByCreateUserId(int id) {
		return this.departmentExtendDao.selectByCreateUserId(id);
	}

	/**
	 * 通过更新userId查找所有部门 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByUpdateUserId(int)
	 */
	public List<Department> selectByUpdateUserId(int id) {
		return this.departmentExtendDao.selectByUpdateUserId(id);
	}

	/**
	 * 通过父部门Id查找所有子部门 (非 Javadoc)
	 * 
	 * @param id
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByParentDepId(int)
	 */
	public List<Department> selectByParentDepId(int id) {
		return this.departmentExtendDao.selectByParentDepId(id);
	}

	/**
	 * 搜索部门树结构 (非 Javadoc)
	 * 
	 * @param pId
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectDepartmentTreeList(int)
	 */
	public List<TreeNode> selectDepartmentTreeList(int pId) {
		// 根据菜单父id查询子菜单列表
		List<Department> depList = this.departmentExtendDao.selectByParentDepId(pId);
		// 存放组装后的树节点
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		// 循环菜单列表，组装树节点
		for (Department department : depList) {
			TreeNode node = new TreeNode(department.getId(), department.getpId(), department.getName());
			node.setOpen(true);
			treeNodeList.add(node);
		}
		// 返回菜单树
		return treeNodeList;
	}
	
	/**
	 * 根据部门部门id查询员工列表
	 * (非 Javadoc) 
	 *   
	 * @param depId 部门id
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectEmpByDepId(int)
	 */
	public List<Employee> selectEmpByDepId(int depId) {
		return employeeExtendDao.selectEmpByDepId(depId);
	}
	
	/**
	 * 搜索员工(非 Javadoc) 
	 *   
	 * @param depId
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectEmpVoByDepId(int)
	 */
	public List<EmployeeVo> selectEmpVoByDepId(int depId) {
		return employeeExtendDao.selectEmpVoByDepId(depId);
	}
	
	/**
	 *  查询未分配部门的员工列表
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectEmpNotInDep()
	 */
	@Override
	public List<Employee> selectEmpNotInDep() {
		return employeeExtendDao.selectEmpNotInDep();
	}
	
	/**
	 * 为部门分配员工
	 * (非 Javadoc) 
	 *   
	 * @param depId
	 * @param empIds
	 * @return
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#updateEmpForDep(int, List)
	 */
	@Override
	public ResultMsg updateEmpForDep(int depId, List<Integer> empIds) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("depId", depId);
			params.put("empIds", empIds);
			//调用分配员工的方法
			employeeExtendDao.updateEmpForDep(params);
			//设置分配员工成功提示信息
			resultMsg.setState(SysConstants.SUCCESS);
			resultMsg.setMessage(OuConstants.ALLOT_EMP_SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置分配员工失败提示信息
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.ALLOT_EMP_FAIL);
		}
		return resultMsg;
	}
	
	/**
	 * 通过部门Id搜索部门
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectByDepId(int)
	 */
	@Override
	public Department selectByDepId(int id) {
		return this.departmentExtendDao.selectByPrimaryKey(id);
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectDepartmentVoById(int)
	 */
	@Override
	public DepartmentVo selectDepartmentVoById(int id){
		return this.departmentExtendDao.selectDepartmentVoById(id);
	}
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param pid
	 * @param page
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#findPageListByPid(int, cn.yiyizuche.common.base.Page)
	 */
	@Override
	public Page<Department> findPageListByPid(int pid, Page<Department> page) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		//根据菜单父id查询子菜单列表
		Page<Department> result = departmentExtendDao.findPageListByPid(params, page);
		return result;
	}

	/**
	 * 查询除本人外的本部门人员（选择审批人时使用）
	 * (非 Javadoc) 
	 *   
	 * @param depId 用户所属部门id
	 * @param userId 当前用户Id
	 * @return  
	 * @see cn.yiyizuche.common.ou.department.service.DepartmentService#selectEmpVoByParam(int, int)
	 */
	@Override
	public List<EmployeeVo> selectEmpVoByParam(int depId, int userId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("depId", depId);
		params.put("userId", userId);
		return employeeExtendDao.selectEmpVoByParam(params);
	}
	
	private static final Logger _log = LoggerFactory.getLogger(RoleServiceImpl.class);
}
