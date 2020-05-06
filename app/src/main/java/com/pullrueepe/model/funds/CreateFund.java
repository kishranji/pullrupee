package com.pullrueepe.model.funds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateFund {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("clientid")
    @Expose
    private Integer clientid;
    @SerializedName("transactiontype")
    @Expose
    private Boolean transactiontype;
    @SerializedName("transactionamount")
    @Expose
    private Integer transactionamount;
    @SerializedName("transactiondate")
    @Expose
    private String transactiondate;
    @SerializedName("createddate")
    @Expose
    private String createddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Boolean getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(Boolean transactiontype) {
        this.transactiontype = transactiontype;
    }

    public Integer getTransactionamount() {
        return transactionamount;
    }

    public void setTransactionamount(Integer transactionamount) {
        this.transactionamount = transactionamount;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }


}
