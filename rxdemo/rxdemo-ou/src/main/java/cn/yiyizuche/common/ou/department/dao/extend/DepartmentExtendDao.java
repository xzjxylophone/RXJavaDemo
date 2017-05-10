package cn.yiyizuche.common.ou.department.dao.extend;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.department.dao.DepartmentDao;
import cn.yiyizuche.common.ou.department.entity.Department;
import cn.yiyizuche.common.ou.department.entity.DepartmentVo;
import cn.yiyizuche.common.ou.menu.entity.Menu;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.dao.extend 
 * @Class : DepartmentExtendDao.java 
 * @Description : Department Extend Dao
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月9日 下午6:03:48 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface DepartmentExtendDao extends DepartmentDao{

	/**
	 * 
	 * @Method: selectByDepName  
	 * @Description: 通过部门名称查找部门
	 * @param depName
	 * @return Department (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:04:02
	 */
	Department selectByDepName(String depName);

	/**
	 * 
	 * @Method: deleteByDepId  
	 * @Description: 通过depId删除部门
	 * @param id
	 * @return int (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:04:04
	 */
	int deleteByDepId(int id);
	
	/**
	 * 
	 * @Method: selectAll  
	 * @Description: 查找所有部门
	 * @return List<Department> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月9日 下午6:04:07
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
	 * @CreateDate : 2017年3月9日 下午6:04:10
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
	 * @CreateDate : 2017年3月9日 下午6:04:13
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
	 * @CreateDate : 2017年3月9日 下午6:04:16
	 */
	List<Department> selectByParentDepId(int pId);
	
	/**
	 * 
	 * @Method: findPageListByPid  
	 * @Description: (分页查询部门列表)
	 * @param params 查询属性集合
	 * @param page 分页对象
	 * @return Page<Menu> (返回分页查询出来的部门分页对象) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 上午11:42:50
	 */
	Page<Department> findPageListByPid(Map<String, Object> params, Page<Department> page);

	/**
	 * 
	 * @Method: selectDepartmentVoById  
	 * @Description: (根据ID查询部门VO实体)
	 * @param id 部门ID
	 * @return DepartmentVo (返回DepartmentVo实体) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月27日 下午7:06:49
	 */
	DepartmentVo selectDepartmentVoById(int id);
	
	/**
	 * 
	 * @Method: selectByCode  
	 * @Description: (根据部门编码查询部门实体)
	 * @param departmentCode
	 * @return Department (返回查询出来的实体，如果没有返回null) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月30日 上午10:17:26
	 */
	Department selectByCode(String departmentCode);
}
