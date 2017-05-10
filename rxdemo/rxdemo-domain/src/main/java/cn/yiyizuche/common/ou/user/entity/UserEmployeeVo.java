package cn.yiyizuche.common.ou.user.entity;

public class UserEmployeeVo extends User {

	private int depId;
	private String depName;
	private int empId;

	public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
	
	
	public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }
	

	public String getDepName() {
        return depName == null ? "" : depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }
	
	
}
