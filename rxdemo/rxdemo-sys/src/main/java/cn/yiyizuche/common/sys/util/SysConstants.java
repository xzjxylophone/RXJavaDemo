package cn.yiyizuche.common.sys.util;


/**
 * 
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.util
 * @Class : Constants.java
 * @Description : 系统常量类 非常重要：：：！！！只有当是枚举值的时候，才定义一个内部类，否则不需要定义一个内部类
 * @author : jiwenfang
 * @CreateDate : 2017年3月7日 下午8:49:08
 * @version : V1.0.0
 * @Copyright: 2017 htht Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog: Name Date Reason/Contents ---------------------------------------
 *            *** **** ****
 *
 */
public class SysConstants {

	public static final int DELETE_FLAG_NORMAL = 0; // 状态标识位 - 正常
	public static final int DELETE_FLAG_DELETE = 1; // 状态标识位 - 删除

	public static final String COMMON_PROPERTIES = "/config/common.properties"; // common.properties

	public static final boolean SUCCESS = true; // 状态标识位 - 正常
	public static final boolean FAIL = false; // 状态标识位 - 失败
	
	public static final int FLAG_YES = 0; // 请求结果 - 成功
	public static final int FLAG_NO = 1; // 请求结果 - 失败
	
	public static final int ENABLE_FLAG = 0; // 状态标识位 - 启用
	public static final int UNENABLE_FLAG = 1; // 状态标识位 - 禁用

	public static final String USER_INFO = "USER_INFO"; // SESSION中用户信息标识
	public static final String USER_ID_INPAGE = "_userId";// 记录在页面上的用户ID
	public static final String MENU_LIST = "MENU_LIST";// SESSION中用户菜单列表
	public static final int ADMINID = 1;// 管理员id
	public static final String REQUESTTYPE = "1";// 当前请求类型：菜单
	
	
	public static final int MENU_PID = 0;//菜单根节点ID

	public static final String SUCCESS_KEY = "success"; // 状态标识位key
	public static final String MESSAGE_KEY = "msg"; // 信息标识key
	public static final String ERROR_CODE_KEY = "code"; // 信息标识key

	//公共操作成功失败信息
	public static final String SAVE_SUCCESS_MSG = "保存成功";
	public static final String SAVE_FAIL_MSG = "保存失败";
	public static final String DELETE_SUCCESS_MSG = "删除成功";
	public static final String DELETE_FAIL_MSG = "删除失败";
	public static final String ENABLE_SUCCESS_MSG = "启用成功";
	public static final String ENABLE_FAIL_MSG = "启用失败";
	public static final String UNENABLE_SUCCESS_MSG = "禁用成功";
	public static final String UNENABLE_FAIL_MSG = "禁用失败";
	
	
	// 系统配置相关信息
	public static final String CONFIG_NAME_EMPTY	= "名称为空";
	public static final String CONFIG_NAME_EXIST = "名称已经存在";
	

	public static final String CONFIG_KEY_EMPTY	= "参数键为空";
	public static final String CONFIG_KEY_EXIST = "参数键已经存在";
	
	// 员工的请假信息
	public static final String REMAIN_EMPLOYEE_EXIST = "该员工的请假信息已经存在";
	public static final String REMAIN_EMPLOYEE_NOT_EXIST = "该员工的请假信息不存在";
	public static final String REMAIN_CAN_DECREASE = "可以请假";

	public static final String LEAVE_REMAIN_TYPE_PARAMS_ERROR = "更新倒休操作类型参数出错";
	public static final String LEAVE_REMAIN_NOT_ENOUGH = "倒休时间不够";

	public static final String ANNUAL_VACATE_REMAIN_NOT_ENOUGH = "年假时间不够";
	public static final String ANNUAL_VACATE_REMAIN_GREATER = "剩余年休假应小于等于应休年假";
	

	// 性别
	public static final class Sex {
		public static final int MAN = 0; // 男
		public static final int WOMAN = 1; // 女
		public static final int UNKNOW = 100; // 未知
	}
	
	
	
	
	// 日志操作变化值类型
	public static final class LogChangedValueType {
		public static final int INCREASE = 0; // 增加
		public static final int DECREASE = 1; // 减少
		public static final int NEED_TO_CALCULATE = 2; // 需要计算
		public static final int INVALID = 3; // 非法
	}
	
	
	// 倒休日志表的操作类型
	public static enum LeaveLogOperType {
		
		DAOXIU_APPLY_SUBMIT("倒休提交申请", LogChangedValueType.DECREASE, 1),  // 负数
		DAOXIU_APPLY_FAILED("倒休申请驳回", LogChangedValueType.INCREASE, 2),  // 正数
		MANAGER_SET("管理调整", LogChangedValueType.NEED_TO_CALCULATE, 3),
		MANAGER_SET_ZERO("管理归零", LogChangedValueType.INCREASE, 4),
		JIABAN_APPLY_SUCCESS("加班申请成功", LogChangedValueType.INCREASE, 5), // 正数
		OTHER("其他未定义", LogChangedValueType.INVALID, 9);
		
		
		private String name;
		private int changeType;

		private int index;

		// 构造方法
		private LeaveLogOperType(String name, int changeType, int index) {
			this.name = name;
			this.changeType = changeType;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (LeaveLogOperType c : LeaveLogOperType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		public static int getChangeType(int index) {
			for (LeaveLogOperType c : LeaveLogOperType.values()) {
				if (c.getIndex() == index) {
					return c.changeType;
				}
			}
			return LogChangedValueType.INVALID;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getChangeType() {
			return changeType;
		}

		public void setChangeType(int changeType) {
			this.changeType = changeType;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
	}
	// 剩余年级日志表的操作类型
	public static enum AnnualVacateLogOperType {
		

		ANNUALVACATE_APPLY_SUBMIT("年假提交申请", LogChangedValueType.DECREASE, 1), // 负数
		ANNUALVACATE_APPLY_FAILED("年假申请驳回", LogChangedValueType.INCREASE, 2), // 正数
		MANAGER_SET("管理调整", LogChangedValueType.NEED_TO_CALCULATE, 3),
		MANAGER_REVERT("管理恢复", LogChangedValueType.INCREASE, 4),
		OTHER("其他未定义", LogChangedValueType.INVALID, 9);
		
		private String name;
		private int changeType;

		private int index;

		// 构造方法
		private AnnualVacateLogOperType(String name, int changeType, int index) {
			this.name = name;
			this.changeType = changeType;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (AnnualVacateLogOperType c : AnnualVacateLogOperType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		public static int getChangeType(int index) {
			for (AnnualVacateLogOperType c : AnnualVacateLogOperType.values()) {
				if (c.getIndex() == index) {
					return c.changeType;
				}
			}
			return LogChangedValueType.INVALID;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getChangeType() {
			return changeType;
		}

		public void setChangeType(int changeType) {
			this.changeType = changeType;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	
	
	// 系统配置的参数类型
	public static final class ConfigParamType {
		public static final int READONLY	= 0; // 只读
		public static final int CAN_MAINTAIN = 1; // 可维护
		public static final int CAN_MODIFY_BUT_CAN_NOT_DELETE = 2; // 可修改但是不可删除
	}
	
	// 字典显示类型
	public static final class DicDisplayType {
		public static final int LIST = 1; // 列表
		public static final int TREE = 2; // 树形
	}
	
	//字典类型
	public static final class DicType{
		public static final int ISINTERIOR = 0;//内部字典
		public static final int ISEXTERNAL = 1;//外部字典
	}
}
