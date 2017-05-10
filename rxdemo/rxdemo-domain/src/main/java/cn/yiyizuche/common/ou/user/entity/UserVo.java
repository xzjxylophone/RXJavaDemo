package cn.yiyizuche.common.ou.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.user.entity 
 * @Class : UserVo.java 
 * @Description : 用户vo
 * @author :jiwenfang
 * @CreateDate : 2017年3月17日 下午3:51:13 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class UserVo implements Serializable{
	/** 序列化标记 */
	private static final long serialVersionUID = 1L;
	/** 用户id */
	private int userId;
	/** 用户名 */
	private String userName;
	/** 用户真实姓名 */
	private String userRealName;
	/** 密码 */
	private String userPwd;
	/**员工生日**/
	private Date birthdate;
	
	/**
	 * 
	 * @Method: getUserId  
	 * @Description: 获取用户id
	 * @return int 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:51:50
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * 
	 * @Method: setUserId  
	 * @Description: 设置用户ID
	 * @param userId void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:52:14
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @Method: getUserName  
	 * @Description: 获取用户名
	 * @return String (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:52:28
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @Method: setUserName  
	 * @Description: 设置用户名
	 * @param userName void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:52:45
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @Method: getUserRealName  
	 * @Description: 获取用户真实姓名
	 * @return String (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:52:59
	 */
	public String getUserRealName() {
		return userRealName;
	}
	/**
	 * 
	 * @Method: setUserRealName  
	 * @Description: 设置用户真实姓名
	 * @param userRealName
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:53:14
	 */
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	/**
	 * 
	 * @Method: getUserPwd  
	 * @Description: 获取用户密码
	 * @return String 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:53:30
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * 
	 * @Method: setUserPwd  
	 * @Description: 设置用户密码
	 * @param userPwd void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:53:57
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	/**
	 * 
	 * @Method: getBirthdate  
	 * @Description 获取员工生日
	 * @return Date (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月31日 下午4:33:26
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * 
	 * @Method: setBirthdate  
	 * @Description: 设置员工生日
	 * @param birthdate void (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月31日 下午4:33:49
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
}
