package com.pullrueepe.model.funds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullrupeeData {
        @SerializedName("currentinvestment")
        @Expose
        private double currentinvestment;
        @SerializedName("currentvalue")
        @Expose
        private double currentvalue;
        @SerializedName("profitloss")
        @Expose
        private double profitloss;
        @SerializedName("profitlosspercentage")
        @Expose
        private double profitlosspercentage;

    public double getCurrentinvestment() {
        return currentinvestment;
    }

    public void setCurrentinvestment(double currentinvestment) {
        this.currentinvestment = currentinvestment;
    }

    public double getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(double currentvalue) {
        this.currentvalue = currentvalue;
    }

    public double getProfitloss() {
        return profitloss;
    }

    public void setProfitloss(double profitloss) {
        this.profitloss = profitloss;
    }

    public double getProfitlosspercentage() {
        return profitlosspercentage;
    }

    public void setProfitlosspercentage(double profitlosspercentage) {
        this.profitlosspercentage = profitlosspercentage;
    }
}



