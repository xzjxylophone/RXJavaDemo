package cn.yiyizuche.common.ou.department.entity;

import java.util.Date;

public class Department {
    private int id;

    private int pId;

    private int leaderId;

    private int supleaderId;

    private String name;

    private String shortName;

    private String punchIn;

    private String punchOut;

    private String code;

    private int createUser;

    private Date createTime;

    private int updateUser;

    private Date updateTime;

    private int sort;

    private String remark;

    private String faild1;

    private String faild2;

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

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getSupleaderId() {
        return supleaderId;
    }

    public void setSupleaderId(int supleaderId) {
        this.supleaderId = supleaderId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName == null ? "" : shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getPunchIn() {
        return punchIn == null ? "" : punchIn;
    }

    public void setPunchIn(String punchIn) {
        this.punchIn = punchIn == null ? null : punchIn.trim();
    }

    public String getPunchOut() {
        return punchOut == null ? "" : punchOut;
    }

    public void setPunchOut(String punchOut) {
        this.punchOut = punchOut == null ? null : punchOut.trim();
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFaild1() {
        return faild1 == null ? "" : faild1;
    }

    public void setFaild1(String faild1) {
        this.faild1 = faild1 == null ? null : faild1.trim();
    }

    public String getFaild2() {
        return faild2 == null ? "" : faild2;
    }

    public void setFaild2(String faild2) {
        this.faild2 = faild2 == null ? null : faild2.trim();
    }
}