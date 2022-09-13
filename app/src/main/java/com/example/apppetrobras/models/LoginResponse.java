package com.example.apppetrobras.models;

import java.util.List;

public class LoginResponse {

    private boolean error;
    private String message;
    private List<UserAPI> userAPIList;
    private UserAPI userAPI;


    public LoginResponse(boolean error, String message, List<UserAPI> userAPIList, UserAPI userAPI) {
        this.error = error;
        this.message = message;
        this.userAPIList = userAPIList;
        this.userAPI = userAPI;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<UserAPI> getUserAPIList() {
        return userAPIList;
    }


        public UserAPI getUserAPI() {
        return userAPI;
    }
}