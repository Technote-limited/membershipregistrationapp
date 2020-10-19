package com.example.dhl;

import com.example.dhl.model.Members;

public class MemberResponse {

    private boolean error;
    private String message;
    private Members members;

    public MemberResponse(boolean error, String message, Members members) {
        this.error = error;
        this.message = message;
        this.members = members;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Members getUser() {
        return members;
    }
}
