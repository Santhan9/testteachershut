package com.example.demo;

import java.util.List;
import java.util.Set;

public class thRepository {
    public thRepository() {
    }

    public void addUser(String firstname , String lastname ,String email, String status ,String password) throws ClassNotFoundException {

        thDatastorage db= new thDatastorage();

        db.storeUserDetails(firstname,lastname,email,status,password);



    }

    public void addTeacher(String email ,String subject ,String Experience,String city) throws ClassNotFoundException {

        thDatastorage db= new thDatastorage();



        db.storeTeacherDetails(email,subject,Experience,city);



    }

    public userInfo validateLogin(String email,String password) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();

       return  db.validateUserDetails(email,password);


    }

    public Set<userInfo> searchTeacher(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
       return db.searchTeacher(text);


    }

    public void logout(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
         db.logout(text);


    }


    public List<userInfo> getTeacher(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        return db.getTeacher(text);


    }

    public List<userInfo> getStudents(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        return db.getStudents(text);


    }

    public List<postInfo> getTeacherPosts(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        return db.getTeacherPosts(text);


    }

    public List<postInfo> getStudentPosts(String text) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        return db.getStudentPosts(text);


    }

    public void storeConnections(String semail, String temail) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        db.storeConnection(semail,temail);

    }

    public void createPost(String pemail, String pheading,String pcontent) throws ClassNotFoundException {
        thDatastorage db= new thDatastorage();
        db.storePosts(pemail,pheading,pcontent);

    }
}
