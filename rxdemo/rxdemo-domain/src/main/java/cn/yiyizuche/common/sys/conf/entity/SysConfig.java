package cn.yiyizuche.common.sys.conf.entity;

import java.util.Date;

public class SysConfig {
    private int id;

    private String confName;

    private int confType;

    private String confKey;

    private String confValue;

    private String confDesc;

    private int createUser;

    private Date createTime;

    private int updateUser;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfName() {
        return confName == null ? "" : confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
    }

    public int getConfType() {
        return confType;
    }

    public void setConfType(int confType) {
        this.confType = confType;
    }

    public String getConfKey() {
        return confKey == null ? "" : confKey;
    }

    public void setConfKey(String confKey) {
        this.confKey = confKey == null ? null : confKey.trim();
    }

    public String getConfValue() {
        return confValue == null ? "" : confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue == null ? null : confValue.trim();
    }

    public String getConfDesc() {
        return confDesc == null ? "" : confDesc;
    }

    public void setConfDesc(String confDesc) {
        this.confDesc = confDesc == null ? null : confDesc.trim();
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
}