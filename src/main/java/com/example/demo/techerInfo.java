package com.example.demo;

public class techerInfo {


    String email;
    String subject;
    String Experience;
    String city;



    String password;

    public techerInfo(String email, String subject, String experience, String city) {
        this.email = email;
        this.subject = subject;
        Experience = experience;
        this.city = city;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getExperience() {
        return Experience;
    }

    public String getCity() {
        return city;
    }
}
