package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class RegisterInfo {
    String name;
    String email;
    String phone;

    public RegisterInfo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public RegisterInfo() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
