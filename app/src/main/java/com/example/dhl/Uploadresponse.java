package com.example.dhl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uploadresponse {

    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    public Uploadresponse(boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public boolean isErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}