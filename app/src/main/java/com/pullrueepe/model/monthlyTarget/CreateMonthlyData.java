package com.pullrueepe.model.monthlyTarget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMonthlyData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("clientid")
    @Expose
    private Integer clientid;
    @SerializedName("tradeyear")
    @Expose
    private Integer tradeyear;
    @SerializedName("trademonth")
    @Expose
    private Integer trademonth;
    @SerializedName("targetamount")
    @Expose
    private Integer targetamount;
    @SerializedName("createddate")
    @Expose
    private String createddate;
    @SerializedName("updateddate")
    @Expose
    private Object updateddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getTradeyear() {
        return tradeyear;
    }

    public void setTradeyear(Integer tradeyear) {
        this.tradeyear = tradeyear;
    }

    public Integer getTrademonth() {
        return trademonth;
    }

    public void setTrademonth(Integer trademonth) {
        this.trademonth = trademonth;
    }

    public Integer getTargetamount() {
        return targetamount;
    }

    public void setTargetamount(Integer targetamount) {
        this.targetamount = targetamount;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public Object getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Object updateddate) {
        this.updateddate = updateddate;
    }

}

