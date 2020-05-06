package com.pullrueepe.model.funds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Funds {
        @SerializedName("result")
        @Expose
        private String result;
        @SerializedName("pullrupeedata")
        @Expose
        private ArrayList<PullrupeeData> pullrupeedata = null;
        @SerializedName("responsemessage")
        @Expose
        private String responsemessage;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public ArrayList<PullrupeeData> getPullrupeedata() {
            return pullrupeedata;
        }

        public void setPullrupeedata(ArrayList<PullrupeeData> pullrupeedata) {
            this.pullrupeedata = pullrupeedata;
        }

        public String getResponsemessage() {
            return responsemessage;
        }

        public void setResponsemessage(String responsemessage) {
            this.responsemessage = responsemessage;
        }

    }

