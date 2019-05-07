package com.insthub.cat.android.module;

import java.io.Serializable;

/**
 * 提阿偶他信息
 * Created by linux on 2017/12/29.
 */

public class RaiseItemData implements Serializable {


    private String id;

    private String raiseType;

    private String raiseName;

    private String businessTas;

    private String phone;

    private String unitAddress;

    private String openBank;

    private String openNumber;

    private String name;

    private float invoickMoney;

    private String defaultRaise;

    private String userId;

    private String userName;

    private int  status;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getBusinessTas() {
        return businessTas;
    }

    public void setBusinessTas(String businessTas) {
        this.businessTas = businessTas;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(String openNumber) {
        this.openNumber = openNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getInvoickMoney() {
        return invoickMoney;
    }

    public void setInvoickMoney(float invoickMoney) {
        this.invoickMoney = invoickMoney;
    }

    public String getDefaultRaise() {
        return defaultRaise;
    }

    public void setDefaultRaise(String defaultRaise) {
        this.defaultRaise = defaultRaise;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
