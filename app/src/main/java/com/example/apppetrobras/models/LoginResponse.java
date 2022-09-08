package com.example.apppetrobras.models;

public class LoginResponse {

    private boolean error;
    private String message;
    private UserAPI user;

    public LoginResponse(boolean error, String message, UserAPI user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserAPI getUser() {
        return user;
    }
}
