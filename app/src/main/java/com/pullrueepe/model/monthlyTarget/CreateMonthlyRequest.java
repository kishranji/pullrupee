package com.pullrueepe.model.monthlyTarget;

public class CreateMonthlyRequest {
    private String clientid;
    private int tradeyear;
    private int trademonth;
    private String targetamount;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getTradeyear() {
        return tradeyear;
    }

    public void setTradeyear(int tradeyear) {
        this.tradeyear = tradeyear;
    }

    public int getTrademonth() {
        return trademonth;
    }

    public void setTrademonth(int trademonth) {
        this.trademonth = trademonth;
    }

    public String getTargetamount() {
        return targetamount;
    }

    public void setTargetamount(String targetamount) {
        this.targetamount = targetamount;
    }
}
