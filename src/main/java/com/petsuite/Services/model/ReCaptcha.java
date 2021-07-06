package com.petsuite.Services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReCaptcha {
    private boolean success;
    private String hostname;
    private String challenge_ts;

    @JsonProperty("error-code")
    private String[] errorCodes;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setChallenge_ts(String challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getHostname() {
        return hostname;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }
}
