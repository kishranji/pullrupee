package com.pullrueepe.model.monthlyTarget;

public class GetAllMonthlyRequest {
    private String clientid;
    private int year;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getTradeyear() {
        return year;
    }

    public void setTradeyear(int tradeyear) {
        this.year = tradeyear;
    }
}
