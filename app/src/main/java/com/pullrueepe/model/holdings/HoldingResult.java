package com.pullrueepe.model.holdings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class HoldingResult {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("pullrupeedata")
    @Expose
    private List<Pullrupeedatum> pullrupeedata = null;
    @SerializedName("responsemessage")
    @Expose
    private String responsemessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Pullrupeedatum> getPullrupeedata() {
        return pullrupeedata;
    }

    public void setPullrupeedata(List<Pullrupeedatum> pullrupeedata) {
        this.pullrupeedata = pullrupeedata;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

}
