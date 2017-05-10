package cn.yiyizuche.common.ou.department.entity;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.ou.department.entity 
 * @Class : DepartmentVo.java 
 * @Description : (部门VO,用于处理修改部门时显示部门相关属性） 
 * @author : wangjing 
 * @CreateDate : 2017年3月27日 下午6:34:39 
 * @version : V1.0.0 
 * @Copyright: 2017 yizu Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public class DepartmentVo extends Department{
	
	/** 部门负责人名称 **/
	private String leaderName;
	
	/** 部门主管名称  **/
	private String supLeaderName;
	
	/** 上级部门名称 **/
	private String parentName;

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getSupLeaderName() {
		return supLeaderName;
	}

	public void setSupLeaderName(String supLeaderName) {
		this.supLeaderName = supLeaderName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
