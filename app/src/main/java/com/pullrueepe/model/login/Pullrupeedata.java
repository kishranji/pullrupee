package com.pullrueepe.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pullrupeedata {
    @SerializedName("clientid")
    @Expose
    private String clientid;
    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;
    @SerializedName("emailid")
    @Expose
    private String emailid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sessionid")
    @Expose
    private String sessionid;

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmailid() {
        return emailid;
    }
}
