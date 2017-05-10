package cn.yiyizuche.common.sys.dic.entity;

import java.util.Date;

public class DicItem {
    private int id;

    private String itemCode;

    private int dicId;

    private String itemName;

    private int itemStatus;

    private int itemSort;

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

    public String getItemCode() {
        return itemCode == null ? "" : itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public int getDicId() {
        return dicId;
    }

    public void setDicId(int dicId) {
        this.dicId = dicId;
    }

    public String getItemName() {
        return itemName == null ? "" : itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public int getItemSort() {
        return itemSort;
    }

    public void setItemSort(int itemSort) {
        this.itemSort = itemSort;
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