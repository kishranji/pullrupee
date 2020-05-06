package com.pullrueepe.model.holdings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pullrupeedatum {
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("buyqty")
    @Expose
    private String buyqty;
    @SerializedName("buyavg")
    @Expose
    private String buyavg;
    @SerializedName("minimumsellavg")
    @Expose
    private String minimumsellavg;
    @SerializedName("ltp")
    @Expose
    private String ltp;
    @SerializedName("profitloss")
    @Expose
    private String profitloss;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBuyqty() {
        return buyqty;
    }

    public void setBuyqty(String buyqty) {
        this.buyqty = buyqty;
    }

    public String getBuyavg() {
        return buyavg;
    }

    public void setBuyavg(String buyavg) {
        this.buyavg = buyavg;
    }

    public String getMinimumsellavg() {
        return minimumsellavg;
    }

    public void setMinimumsellavg(String minimumsellavg) {
        this.minimumsellavg = minimumsellavg;
    }

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public String getProfitloss() {
        return profitloss;
    }

    public void setProfitloss(String profitloss) {
        this.profitloss = profitloss;
    }
}
