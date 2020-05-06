package com.pullrueepe.model.symbol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pullrueepe.model.login.Pullrupeedata;

import java.util.List;

public class GetSymbolResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("pullrupeedata")
    @Expose
    private List<SymbolData> pullrupeedata = null;
    @SerializedName("responsemessage")
    @Expose
    private String responsemessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<SymbolData> getPullrupeedata() {
        return pullrupeedata;
    }

    public void setPullrupeedata(List<SymbolData> pullrupeedata) {
        this.pullrupeedata = pullrupeedata;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }
}
