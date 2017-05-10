package cn.yiyizuche.common.base;

import java.io.Serializable;

public class TreeNode implements Serializable{
	/** 序列化标记 */
	private static final long serialVersionUID = -8135430950274281066L;
	/**节点id*/
	private int id;
	/**父节点id*/
	private int pId;
	/**节点简称*/
	private String name;
	/**节点是否选中*/
	private boolean checked = false;
	/**节点是否展开*/
	private boolean open = false;
	/**节点是否为父节点*/
	private boolean isParent = false;
	
	
	
	public TreeNode(int id, int pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}