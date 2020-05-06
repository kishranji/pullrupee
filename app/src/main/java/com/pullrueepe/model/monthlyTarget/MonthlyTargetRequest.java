package com.pullrueepe.model.monthlyTarget;

public class MonthlyTargetRequest {
    private String clientid;
    private String tradeyear;
    private String trademonth;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getTradeyear() {
        return tradeyear;
    }

    public void setTradeyear(String tradeyear) {
        this.tradeyear = tradeyear;
    }

    public String getTrademonth() {
        return trademonth;
    }

    public void setTrademonth(String trademonth) {
        this.trademonth = trademonth;
    }
}
