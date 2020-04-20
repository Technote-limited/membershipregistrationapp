package com.example.dhl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uploadresponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("value")
    @Expose
    private Boolean value;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

}