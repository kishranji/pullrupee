package com.pullrueepe.model.login;

public class LoginRequest {
    private String emailid;
    private String password;
    private String devicemodel;
    private String osversion;
    private String imeinumber;
    private String mobilebrand;

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getImeinumber() {
        return imeinumber;
    }

    public void setImeinumber(String imeinumber) {
        this.imeinumber = imeinumber;
    }

    public String getMobilebrand() {
        return mobilebrand;
    }

    public void setMobilebrand(String mobilebrand) {
        this.mobilebrand = mobilebrand;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
