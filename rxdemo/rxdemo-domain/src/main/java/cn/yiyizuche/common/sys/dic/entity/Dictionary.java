package cn.yiyizuche.common.sys.dic.entity;

import java.util.Date;

public class Dictionary {
    private int id;

    private String dicName;

    private String dicCode;

    private int displayType;

    private int isExternal;

    private String dicTableCode;

    private String dicCodeField;

    private String dicNameField;

    private int createUser;

    private Date createTime;

    private int updateUser;

    private Date updateTime;

    private int dicStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDicName() {
        return dicName == null ? "" : dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public String getDicCode() {
        return dicCode == null ? "" : dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public int getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(int isExternal) {
        this.isExternal = isExternal;
    }

    public String getDicTableCode() {
        return dicTableCode == null ? "" : dicTableCode;
    }

    public void setDicTableCode(String dicTableCode) {
        this.dicTableCode = dicTableCode == null ? null : dicTableCode.trim();
    }

    public String getDicCodeField() {
        return dicCodeField == null ? "" : dicCodeField;
    }

    public void setDicCodeField(String dicCodeField) {
        this.dicCodeField = dicCodeField == null ? null : dicCodeField.trim();
    }

    public String getDicNameField() {
        return dicNameField == null ? "" : dicNameField;
    }

    public void setDicNameField(String dicNameField) {
        this.dicNameField = dicNameField == null ? null : dicNameField.trim();
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

    public int getDicStatus() {
        return dicStatus;
    }

    public void setDicStatus(int dicStatus) {
        this.dicStatus = dicStatus;
    }
}