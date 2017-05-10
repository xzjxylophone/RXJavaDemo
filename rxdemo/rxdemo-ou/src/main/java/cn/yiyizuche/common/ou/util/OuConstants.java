package cn.yiyizuche.common.ou.util;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.util 
 * @Class : OuConstants.java 
 * @Description : Ou模块常量类 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月8日 下午7:07:17 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class OuConstants {

	// 用户信息描述
	public static final String USER_INFO_ERROR = "用户信息有问题";
	public static final String USER_NAME_EMPTY = "用户名为空";
	public static final String USER_NAME_EXIST = "该用户名已经存在";
	public static final String USER_ROLE_RELATION = "该用户有关联的角色";
	public static final String USER_NOT_FIND = "用户没有找到";
	public static final String USER_EQUALS_PWD_ERROR = "修改前后的密码不允许相同";
	public static final String USER_OLDPWD_ERROR = "旧密码输入不正确";
	public static final String USER_CHANGE_PWD_SUCCESS = "用户成功更改密码";
	public static final String USER_USERNAME_PWD_ERROR = "用户名或密码不正确";
	public static final String USER_STATUS_DISABLE = "该用户被禁用，请联系管理管";
	public static final String USER_RESETPWD_ERROR = "更改密码失败";
	public static final String USER_EMPLOYEE_ERROR = "请联系管理员补全员工信息";

	
	// 角色状态
	public static final class RoleStatus {
		public static final int ENABLE = 0; // 启用
		public static final int DISABLE = 1; // 禁用
	}

	// 角色信息描述
	public static final String ROLE_INFO_ERROR = "角色信息有问题";
	public static final String ROLE_NAME_EMPTY = "角色名为空";
	public static final String ROLE_NAME_EXIST = "该角色名已经存在";
	public static final String ROLE_USER_RELATION = "该角色有关联的用户";
	public static final String ROLE_MENU_RELATION = "该角色有关联的菜单";

	// 部门信息描述
	public static final String DEPARTMENT_INFO_ERROR = "部门信息有问题";
	public static final String DEPARTMENT_NAME_EMPTY = "部门名为空";
	public static final String DEPARTMENT_NAME_EXIST = "该部门已经存在";
	public static final String DEPARTMENT_EMPOLYEE_EXIST = "该部门存在员工";
	public static final String DEPARTMENT_CODE_EXIST = "部门编号重复";
	
	//权限信息描述
	public static final String SAVE_AUTH_SUCCESS = "权限分配成功";
	public static final String SAVE_AUTH_FAIL = "权限分配失败";
	
	// 用户角色信息描述
	public static final String USER_ROLE_INFO_ERROR = "用户角色信息有问题";
	public static final String USER_ROLE_EXIST = "用户角色信息已经存在";
			
	
	// 菜单信息描述
	public static final String MENU_REPEAT_MSG = "菜单名称重复";
	public static final String MENU_REPEAT_CODE_MSG = "菜单Code重复";
	public static final String MENU_LEVEL_LIMIT_MSG = "只允许添加二级菜单";
	public static final String MENU_FORBID_DELETE_MSG = "菜单已被分配角色，不可删除";
	
	public static final int OPERATION_ADD = 0;//当前操作：添加
	public static final int OPERATION_UPDATE = 1;//当前操作：修改
	
	// 部门信息描述
	public static final String ALLOT_EMP_SUCCESS = "分配员工成功";
	public static final String ALLOT_EMP_FAIL = "分配员工失败";
	
	// 用户状态
	public static final class UserStatus {
		public static final int NORMAL = 0; // 正常
		public static final int EXCEPTION = 10000000; // 异常
	}
	

	
	
}
