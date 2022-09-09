package com.example.apppetrobras.models;

public class LoginResponse {

    private boolean error;
    private String message;
    private String hashlogin;

    public LoginResponse(boolean error, String message, String hashlogin) {
        this.error = error;
        this.message = message;
        this.hashlogin = hashlogin;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getHashlogin() {
        return hashlogin;
    }
}
