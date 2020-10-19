package com.example.dhl;

import com.example.dhl.model.Agent;

public class LoginResponse {

    private boolean error;
    private String message;
    private Agent agent;

    public LoginResponse(boolean error, String message, Agent agent) {
        this.error = error;
        this.message = message;
        this.agent = agent;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Agent getAgent() {
        return agent;
    }
}
