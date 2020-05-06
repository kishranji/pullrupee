package com.pullrueepe.model.monthlyTarget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pullrupeedatum {
    @SerializedName("currentmonth")
    @Expose
    private String currentmonth;
    @SerializedName("currentmonthtarget")
    @Expose
    private String currentmonthtarget;
    @SerializedName("currentmonthsofardone")
    @Expose
    private String currentmonthsofardone;
    @SerializedName("currentmonthyettodo")
    @Expose
    private String currentmonthyettodo;
    @SerializedName("currentmonthperdayrequired")
    @Expose
    private String currentmonthperdayrequired;
    @SerializedName("currentmonthdaysinhand")
    @Expose
    private String currentmonthdaysinhand;

    public String getCurrentmonth() {
        return currentmonth;
    }

    public void setCurrentmonth(String currentmonth) {
        this.currentmonth = currentmonth;
    }

    public String getCurrentmonthtarget() {
        return currentmonthtarget;
    }

    public void setCurrentmonthtarget(String currentmonthtarget) {
        this.currentmonthtarget = currentmonthtarget;
    }

    public String getCurrentmonthsofardone() {
        return currentmonthsofardone;
    }

    public void setCurrentmonthsofardone(String currentmonthsofardone) {
        this.currentmonthsofardone = currentmonthsofardone;
    }

    public String getCurrentmonthyettodo() {
        return currentmonthyettodo;
    }

    public void setCurrentmonthyettodo(String currentmonthyettodo) {
        this.currentmonthyettodo = currentmonthyettodo;
    }

    public String getCurrentmonthperdayrequired() {
        return currentmonthperdayrequired;
    }

    public void setCurrentmonthperdayrequired(String currentmonthperdayrequired) {
        this.currentmonthperdayrequired = currentmonthperdayrequired;
    }

    public String getCurrentmonthdaysinhand() {
        return currentmonthdaysinhand;
    }

    public void setCurrentmonthdaysinhand(String currentmonthdaysinhand) {
        this.currentmonthdaysinhand = currentmonthdaysinhand;
    }
}
