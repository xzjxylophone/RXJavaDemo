package cn.yiyizuche.common.ou.user.entity;

public class UserRoleVo extends UserRoleKey {

	private String userName;
	private String realName;
	private String roleName;
	private String depName;
	private int depId;
	private int empId;
	
	private int exist; // 0 是不存在, 1 是存在
	
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
	
	public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
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

	public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
    public String getRoleName() {
        return roleName == null ? "" : roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
    
    
    
	
    
    @Override
    public String toString() {
    		return 	"exist:" + this.getExist() +
    				",roleId:" + this.getRoleId() + 
    				",userId:" + this.getUserId() + 
    				",roleName:" + this.getRoleName() + 
    				",realName:" + this.getRealName() + 
    				",userName:" + this.getUserName();
    }
	
	
}
