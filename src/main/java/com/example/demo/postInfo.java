package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class postInfo {
    String email;
    String heading;
    String content;

    public postInfo(String email, String heading, String content) {
        this.email = email;
        this.heading = heading;
        this.content = content;
    }

    public postInfo() {
    }

    public String getEmail() {
        return email;
    }

    public String getHeading() {
        return heading;
    }

    public String getContent() {
        return content;
    }
}
