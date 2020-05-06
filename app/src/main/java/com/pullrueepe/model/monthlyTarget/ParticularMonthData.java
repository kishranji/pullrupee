package com.pullrueepe.model.monthlyTarget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParticularMonthData {

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("targetamount")
    @Expose
    private String targetamount;
    @SerializedName("displaymonth")
    @Expose
    private String displaymonth;

    public String getDisplaymonth() {
        return displaymonth;
    }

    public void setDisplaymonth(String displaymonth) {
        this.displaymonth = displaymonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTargetamount() {
        return targetamount;
    }

    public void setTargetamount(String targetamount) {
        this.targetamount = targetamount;
    }

}

