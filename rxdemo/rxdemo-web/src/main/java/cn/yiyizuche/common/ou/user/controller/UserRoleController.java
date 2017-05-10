package cn.yiyizuche.common.ou.user.controller;


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

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.role.entity.Role;
import cn.yiyizuche.common.ou.role.service.RoleService;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.ou.user.service.UserRoleService;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.user.controller 
 * @Class : UserRoleController.java 
 * @Description : 用户角色控制层 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午8:25:16 
 * @version : V1.0.0 
 * @Copyright: 2017 htht Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Controller
@RequestMapping("ou/userRole")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 
	 * @Method: addUserRole  
	 * @Description:添加用户角色
	 * @param userRole
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:56:33
	 */
	@ResponseBody
    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
	public ResultMsg addUserRole(@RequestParam(value = "userRole", defaultValue = "0") UserRoleKey userRole) {
		return this.userRoleService.addUserRole(userRole);
	}

	/**
	 *
	 * @class : UserRoleController
	 * @method : findById
	 * @param id
	 * @return : Role
	 * @throws :
	 * @description : TODO
	 * @author : wangjing (wangjing@yiyizuche.com)
	 * @createDate: 2017年3月21日 下午7:55:02
	 */
	@ResponseBody
	@RequestMapping(value = "/findRoleById")
	public Role findRoleById(int id){
		return this.roleService.findRoleById(id);
	}
	/**
	 *
	 * @class : UserRoleController
	 * @method : addRole
	 * @param role 角色对象
	 * @return : ResultMsg 添加操作的结果
	 * @throws :
	 * @description : 添加角色
	 * @author : wangjing (wangjing@yiyizuche.com)
	 * @createDate: 2017年3月21日 下午5:31:31
	 */
	@ResponseBody
	@RequestMapping(value = "/addRole", method=RequestMethod.POST)
	public ResultMsg addRole(Role role, HttpServletRequest req){
		HttpSession session = req.getSession();
		UserVo sessionUser = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(sessionUser != null){
			int userId = sessionUser.getUserId();
			role.setCreateUser(userId);
			role.setUpdateUser(userId);
		}
		return roleService.addRole(role);
	}

	/**
	 *
	 * @class : UserRoleController
	 * @method : deleteRoleById
	 * @param id 角色ID
	 * @return : ResultMsg 删除结果
	 * @throws :
	 * @description : 根据角色ID删除角色
	 * @author : wangjing (wangjing@yiyizuche.com)
	 * @createDate: 2017年3月21日 下午9:09:08
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRoleById", method=RequestMethod.POST)
	public ResultMsg deleteRoleById(int id){
		return roleService.deleteByRoleId(id);
	}

	/**
	 *
	 * @class : UserRoleController
	 * @method : updateRole
	 * @param role 被修改的角色实体
	 * @param req HttpServletRequest
	 * @return : ResultMsg
	 * @throws :
	 * @description : 修改角色
	 * @author : wangjing (wangjing@yiyizuche.com)
	 * @createDate: 2017年3月21日 下午8:07:23
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRole", method=RequestMethod.POST)
	public ResultMsg updateRole(Role role, HttpServletRequest req){
		HttpSession session = req.getSession();
		UserVo sessionUser = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(sessionUser != null){
			int userId = sessionUser.getUserId();
			role.setUpdateUser(userId);
			role.setUpdateTime(new Date());
		}
		return roleService.updateRole(role);
	}

	/**
	 * 
	 * @Method: deleteByUserRole  
	 * @Description:删除用户权限
	 * @param userRole
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:56:53
	 */
	@ResponseBody
    @RequestMapping(value = "/deleteByUserRole", method = RequestMethod.POST)
	public ResultMsg deleteByUserRole(@RequestParam(value = "userRole", defaultValue = "0") UserRoleKey userRole) {
		return this.userRoleService.deleteByUserRole(userRole);
	}
	
	/**
	 * 
	 * @Method: selectAllRole  
	 * @Description:搜索全部的权限
	 * @return List<Role> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:57:04
	 */
	@ResponseBody
    @RequestMapping(value = "/selectAllRole", method = RequestMethod.POST)
	public List<Role> selectAllRole() {
		return this.roleService.selectAll();
	}

	/**
	 * 
	 * @Method: selectUserRoleListByRoleId  
	 * @Description:通过UserId搜索UserRole
	 * @param roleId
	 * @return List<UserRoleKey> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:57:16
	 */
	@ResponseBody
    @RequestMapping(value = "/selectUserRoleListByRoleId", method = RequestMethod.POST)
	public List<UserRoleKey> selectUserRoleListByRoleId(@RequestParam(value = "roleId", defaultValue = "0") int roleId) {
		return this.userRoleService.selectUserRoleListByRoleId(roleId);
	}

	/**
	 * 
	 * @Method: selectUserRoleListByUserId  
	 * @Description:通过RoleId搜索UserRole
	 * @param userId
	 * @return List<UserRoleKey> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:57:32
	 */
	@ResponseBody
    @RequestMapping(value = "/selectUserRoleListByUserId", method = RequestMethod.POST)
	public List<UserRoleKey> selectUserRoleListByUserId(@RequestParam(value = "userId", defaultValue = "0") int userId) {
		return this.userRoleService.selectUserRoleListByUserId(userId);
	}

	/**
	 * 
	 * @Method: selectUserRoleVoListByUserId  
	 * @Description:通过UserId搜索UserRoleVo
	 * @param userId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:57:44
	 */
	@ResponseBody
    @RequestMapping(value = "/selectUserRoleVoListByUserId", method = RequestMethod.POST)
	public List<UserRoleVo> selectUserRoleVoListByUserId(@RequestParam(value = "userId", defaultValue = "0") int userId) {
		return this.userRoleService.selectUserRoleVoListByUserId(userId);
	}
	
	/**
	 * 
	 * @Method: selectUserRoleVoListByRoleId  
	 * @Description:通过RoleId搜索UserRoleVo
	 * @param roleId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:57:50
	 */
	@ResponseBody
    @RequestMapping(value = "/selectUserRoleVoListByRoleId", method = RequestMethod.POST)
	public List<UserRoleVo> selectUserRoleVoListByRoleId(@RequestParam(value = "roleId", defaultValue = "0") int roleId) {
		return this.userRoleService.selectUserRoleVoListByRoleId(roleId);
	}
	
	/**
	 * 
	 * @Method: selectAllUserRoleVoListByRoleId  
	 * @Description:通过RoleId搜索全部的用户的UserRoleVo
	 * @param roleId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:58:08
	 */
	@ResponseBody
    @RequestMapping(value = "/selectAllUserRoleVoListByRoleId", method = RequestMethod.POST)
	public List<UserRoleVo> selectAllUserRoleVoListByRoleId(@RequestParam(value = "roleId", defaultValue = "0") int roleId) {
		List<UserRoleVo> userRoleVos = this.userRoleService.selectAllUserRoleVoListByRoleId(roleId);
		return userRoleVos;
	}
	
	/**
	 * 
	 * @Method: updateRoleWithUserIds  
	 * @Description:给角色分配用户
	 * @param roleId
	 * @param userIds
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:58:40
	 */
	@ResponseBody
    @RequestMapping(value = "/updateRoleWithUserIds", method = RequestMethod.POST)
	public ResultMsg updateRoleWithUserIds(int roleId, String userIds) {
		return this.userRoleService.updateRoleWithUserIds(roleId, userIds);
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @class : UserRoleController
	 * @method : selectRoleListByName
	 * @param roleName 角色名称
	 * @return : Page<Role>
	 * @throws : 
	 * @description : 根据角色名称查询角色集合列表
	 * @author : wangjing (wangjing@yiyizuche.com) 
	 * @createDate: 2017年3月20日 下午8:02:05
	 */
	@ResponseBody
	@RequestMapping(value = "/selectRoleListByName", method = RequestMethod.POST)
	public Page<Role> selectRoleListByName(@RequestParam(value = "roleName", defaultValue = "") String roleName, Page<Role> page) {
		Page<Role> pageResult = this.roleService.selectRoleListByName(roleName, page);
	    return pageResult;
	}

	/**
	 * @Method: searchRoleByPage
	 * @Description: 分页查询方法
	 * @param roleName 查询条件
	 * @param current 当前页
	 * @param rowCount 每页多少条
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/22 13:31
	 */
	@ResponseBody
	@RequestMapping(value = "/searchRoleByPage", method = RequestMethod.POST)
	public Map<String, Object> searchRoleByPage(String roleName, int current, int rowCount) {
		Page<Role> page = new Page<>(current, rowCount);
		Page<Role> pageResult = this.roleService.selectRoleListByName(roleName, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	}
	
	/**
	 * 
	 * @class : UserRoleController
	 * @method : showRoleListPage
	 * @return : ModelAndView
	 * @throws : 
	 * @description : 跳转到角色权限列表页面
	 * @author : wangjing (wangjing@yiyizuche.com) 
	 * @createDate: 2017年3月20日 下午7:41:25
	 */
	@RequestMapping(value = "/showRoleListPage", method = RequestMethod.GET)
	public ModelAndView showRoleListPage(){
		_log.info("showRoleListPage");
		ModelAndView view = new ModelAndView("/page/ou/role/role_list");
		return view;
	}

	private static final Logger _log = LoggerFactory.getLogger(UserRoleController.class);

    
}
