package cn.yiyizuche.common.base;

import java.io.Serializable;

public class ResultMsg implements Serializable {
	/**返回状态*/
	private boolean state;
	/**返回结果码*/
	private int code;
	/**返回简要信息*/
	private String message;
	
	public ResultMsg(boolean state, int code, String message) {
		this.state = state;
		this.code = code;
		this.message = message;
	}

	public ResultMsg() {
		
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String toString() {
		return "state:" + this.state + ",code:" + this.code + ",msg:" + this.message;
	}
}
