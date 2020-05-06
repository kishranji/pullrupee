
package com.pullrueepe.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("pullrupeedata")
    @Expose
    private Pullrupeedata pullrupeedata;

    @SerializedName("responsemessage")
    @Expose
    private String responsemessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Pullrupeedata getPullrupeedata() {
        return pullrupeedata;
    }

    public void setPullrupeedata(Pullrupeedata pullrupeedata) {
        this.pullrupeedata = pullrupeedata;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

}
