package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class loginInfo {

    String email;
    String password;

    public loginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public loginInfo(){

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
