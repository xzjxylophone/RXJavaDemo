package cn.yiyizuche.common.ou.employee.dao.extend;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.employee.dao.EmployeeDao;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.employee.dao.extend 
 * @Class : EmployeeExtendDao.java 
 * @Description :员工数据层接口
 * @author :jiwenfang
 * @CreateDate : 2017年3月11日 下午5:02:13 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface EmployeeExtendDao extends EmployeeDao{
	/**
	 * 
	 * @Method: deleteById  
	 * @Description: 根据员工id删除员工
	 * @param id 员工ID
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:19:49
	 */
	void deleteById(int id);
	
	/**
	 * 
	 * @Method: deleteByIds  
	 * @Description: 根据员工Id集合删除员工
	 * @param ids 员工id集合
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:20:23
	 */
	void deleteByIds(List<Integer> ids);
	
	/**
	 * 
	 * @Method: selectEmployeeByPage  
	 * @Description: 分页查询员工
	 * @param page 分页对象
	 * @param params
	 * @return Page<Menu> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:21:12
	 */
	Page<Employee> selectEmployeeByPage(Page<Employee> page, Map<String, Object> params);
	
	/**
	 * 
	 * @Method: selectEmployeeVoByPage  
	 * @Description: 分页查询EmployeeVo
	 * @param page
	 * @param paramsMap
	 * @return Page<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:00:22
	 */
	Page<EmployeeVo> selectEmployeeVoByPage(Page<EmployeeVo> page, Map<String, Object> paramsMap);

	/**
	 * 
	 * @Method: selectEmployeeVoByUserId  
	 * @Description: 通过userId查询EmployeeVo
	 * @param id
	 * @return List<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:00:39
	 */
	List<EmployeeVo> selectEmployeeVoByUserId(int id);
	
	/**
	 * 
	 * @Method: selectEmployeeVoByEmployeeId  
	 * @Description:通过employeeId查询EmployeeVo
	 * @param id
	 * @return EmployeeVo (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:00:59
	 */
	EmployeeVo selectEmployeeVoByEmployeeId(int id);
	
	/**
	 * 
	 * @Method: selectEmployeeByJobNumber  
	 * @Description:通过工号查询员工
	 * @param jobNumber
	 * @return Employee (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月11日 上午11:58:06
	 */
	Employee selectEmployeeByJobNumber(String jobNumber);
	
	/**
	 * 
	 * @Method: updateEmployeeStatusByEmployeeId  
	 * @Description:更新用户状态
	 * @param id
	 * @param status
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:01:19
	 */
	ResultMsg updateEmployeeStatusByEmployeeId(int id, int status);

	/**
	 * 
	 * @Method: selectEmpByDepId  
	 * @Description: 根据部门id查询员工列表
	 * @param depId 部门ID
	 * @return List<Employee> 员工集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:21:39
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
	 * @CreateDate : 2017年3月23日 下午8:13:43
	 */
	List<EmployeeVo> selectEmpVoByDepId(int depId);
	
	/**
	 * 
	 * @Method: selectEmpNotInDep  
	 * @Description: 查询未分配部门的员工
	 * @return List<Employee> 员工集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:11:29
	 */
	List<Employee> selectEmpNotInDep();
	
	/**
	 * 
	 * @Method: updateEmpForDep  
	 * @Description: 为部门分配员工
	 * @param params void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月11日 上午11:18:38
	 */
	void updateEmpForDep(Map<String,Object> params);

	/**
	 *
	 * @Method: selectAllEmp
	 * @Description: 查询所有的员工信息
	 * @return List<Employee> 员工集合
	 * @author :lipeng
	 * @CreateDate : 2017年3月11日 上午11:11:29
	 */
	List<Employee> selectAllEmp();
	
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
	 * @return List<EmployeeVo> (返回员工集合) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午4:25:56
	 */
	List<EmployeeVo> loadAllEmployeeVo();
	
	/**
	 * 
	 * @Method: selectEmpVoByParam  
	 * @Description: 查询除本人外的本部门人员（选择审批人时使用）
	 * @param params 查询条件
	 * @return List<EmployeeVo> (返回类型描述) 
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月30日 下午5:59:14
	 */
	List<EmployeeVo> selectEmpVoByParam(Map<String, Integer> params);
}
