package com.pullrueepe.model.funds;

public class CreateFundRequest {
    private String  clientid;
    private int transactiontype;
    private String transactionamount;
    private String transactiondate;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(int transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getTransactionamount() {
        return transactionamount;
    }

    public void setTransactionamount(String transactionamount) {
        this.transactionamount = transactionamount;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
    }
}
