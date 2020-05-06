package com.pullrueepe.model.symbol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SymbolData {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("symbolcode")
    @Expose
    private String symbolcode;
    @SerializedName("symbolcompanyname")
    @Expose
    private String symbolcompanyname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbolcode() {
        return symbolcode;
    }

    public void setSymbolcode(String symbolcode) {
        this.symbolcode = symbolcode;
    }

    public String getSymbolcompanyname() {
        return symbolcompanyname;
    }

    public void setSymbolcompanyname(String symbolcompanyname) {
        this.symbolcompanyname = symbolcompanyname;
    }

    /**
     * The Column interface represents the DB column name for Symbol table.
     */
    public interface Column {
        String SYMBOL_ID = "id";
        String SYMBOL_CODE = "symbolcode";
        String SYMBOL_COMPANY_NAME = "symbolcompanyname";
    }
}
