package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class userInfo{
String firstname;
    String lastname;
    String email;
    String status;
    String password;
    String subject;


    public String getProfilepic() {
        return profilepic;
    }

    String experience;
    String city;
    String logged;
    String profilepic;


    public userInfo(String status,String firstname, String lastname, String email,String subject,String Experience,String city,String password, String logged,String profilepic) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
        this.password = password;
        this.subject=subject;
        this.experience=Experience;
        this.city=city;
        this.logged= logged;
        this.profilepic=profilepic;
    }

    public String getSubject() {
        return subject;
    }
    public String getLogged() {
        return logged;
    }

    public String getExperience() {
        return experience;
    }

    public String getCity() {
        return city;
    }




    public userInfo() {
    }
    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }


}
