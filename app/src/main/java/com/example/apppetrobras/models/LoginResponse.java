package com.example.apppetrobras.models;

import java.util.List;

public class LoginResponse {

    List<UserAPI> userAPIList;

    public LoginResponse(List<UserAPI> userAPIList) {
        this.userAPIList = userAPIList;
    }

    public List<UserAPI> getUserAPIList() {
        return userAPIList;
    }
}