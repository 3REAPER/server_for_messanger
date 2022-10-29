package com.pervukhin.domain;

public class ResultEmailAndPassword {
    private String result;
    private Profile profile;

    public void setResult(String result) {
        this.result = result;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getResult() {
        return result;
    }

    public Profile getProfile() {
        return profile;
    }
}
