package com.pullrueepe.model.monthlyTarget;

public class ParticularMonthRequest {
    private String clientid;
    private int year;
    private int month;
    private String actionbutton;

    public String getActionbutton() {
        return actionbutton;
    }

    public void setActionbutton(String actionbutton) {
        this.actionbutton = actionbutton;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
