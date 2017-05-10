package cn.yiyizuche.common.ou.employee.entity;

import java.util.Date;

public class EmployeeVo extends Employee {

	private String depName;
	private String userName;
	private String userRealName;
	private String email;
	private String mobile;
	
	/** 部门负责人id **/
	private int leaderId;
	/**主管领导id**/
	private int supleaderId;
	
	
	private int sex;
	private Date birthdate;
	

	private String birthdateString;
	private String workTimeString;
	private String entryTimeString;
	private String createTimeString;
	private String updateTimeString;
	
	
	public String getBirthdateString() {
        return birthdateString == null ? "" : birthdateString;
    }

    public void setBirthdateString(String birthdateString) {
        this.birthdateString = birthdateString == null ? null : birthdateString.trim();
    }
    
    public String getWorkTimeString() {
        return workTimeString == null ? "" : workTimeString;
    }

    public void setWorkTimeString(String workTimeString) {
        this.workTimeString = workTimeString == null ? null : workTimeString.trim();
    }
    
    public String getEntryTimeString() {
        return entryTimeString == null ? "" : entryTimeString;
    }

    public void setEntryTimeString(String entryTimeString) {
        this.entryTimeString = entryTimeString == null ? null : entryTimeString.trim();
    }
    

	public String getCreateTimeString() {
        return createTimeString == null ? "" : createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString == null ? null : createTimeString.trim();
    }
    

	public String getUpdateTimeString() {
        return updateTimeString == null ? "" : updateTimeString;
    }

    public void setUpdateTimeString(String updateTimeString) {
        this.updateTimeString = updateTimeString == null ? null : updateTimeString.trim();
    }
    
    
    
	
	public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
	
	public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }
    
	public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }


	public String getDepName() {
        return depName == null ? "" : depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }
    
    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    
    public String getUserRealName() {
        return userRealName == null ? "" : userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }
    
    /**
     * 
     * @Method: getLeaderId  
     * @Description:获取部门负责人id
     * @return int 部门负责人ID
     * @author :jiwenfang
     * @CreateDate : 2017年3月28日 下午2:03:27
     */
    public int getLeaderId() {
		return leaderId;
	}

    /**
     * 
     * @Method: setLeaderId  
     * @Description: 设置部门负责人id
     * @param leaderId 部门负责人id
     * @author :jiwenfang
     * @CreateDate : 2017年3月28日 下午2:03:54
     */
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}

	/**
	 * 
	 * @Method: getSupleaderId  
	 * @Description: 获取主管领导id
	 * @return int 主管领导id
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月28日 下午2:04:18
	 */
	public int getSupleaderId() {
		return supleaderId;
	}

	/**
	 * 
	 * @Method: setSupleaderId  
	 * @Description: 设置主管领导id
	 * @param supleaderId 主管领导id
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月28日 下午2:04:43
	 */
	public void setSupleaderId(int supleaderId) {
		this.supleaderId = supleaderId;
	}

	public String toString() {
    	
    		String result = "empId:" + this.getId() + ",userId:" + this.getUserId() + ",depId:" +
    			this.getDepId() + ",userName:" + this.getUserName() + ",realName:" + 
    			this.getUserRealName() + ",depName:" + this.getDepName();
    		return result;
    }
    
    
    
}
