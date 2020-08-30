package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class searchinfo {
    String fname;
    String lname;
    String subject;

    public searchinfo(String fname, String lname, String subject) {
        this.fname = fname;
        this.lname = lname;
        this.subject = subject;
    }

    public searchinfo() {
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSubject() {
        return subject;
    }
}
