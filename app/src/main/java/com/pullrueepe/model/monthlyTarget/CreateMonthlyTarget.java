package com.pullrueepe.model.monthlyTarget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateMonthlyTarget {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("pullrupeedata")
    @Expose
    private List<CreateMonthlyData> pullrupeedata = null;
    @SerializedName("responsemessage")
    @Expose
    private String responsemessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<CreateMonthlyData> getPullrupeedata() {
        return pullrupeedata;
    }

    public void setPullrupeedata(List<CreateMonthlyData> pullrupeedata) {
        this.pullrupeedata = pullrupeedata;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

}

