package com.pullrueepe.model.monthlyTarget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllMonthlyTargets {
    @SerializedName("clientid")
    @Expose
    private Integer clientid;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("month")
    @Expose
    private Integer month;
    @SerializedName("targetamount")
    @Expose
    private Integer targetamount;
    @SerializedName("achievedamount")
    @Expose
    private Integer achievedamount;
    @SerializedName("displaymonth")
    @Expose
    private String displaymonth;

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTargetamount() {
        return targetamount;
    }

    public void setTargetamount(Integer targetamount) {
        this.targetamount = targetamount;
    }

    public Integer getAchievedamount() {
        return achievedamount;
    }

    public void setAchievedamount(Integer achievedamount) {
        this.achievedamount = achievedamount;
    }

    public String getDisplaymonth() {
        return displaymonth;
    }

    public void setDisplaymonth(String displaymonth) {
        this.displaymonth = displaymonth;
    }
}
