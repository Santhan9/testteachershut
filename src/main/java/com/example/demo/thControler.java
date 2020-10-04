package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

@RestController
public class thControler {

    @Autowired
    private userInfo userinfo;

    @Autowired
    private loginInfo logininfo;

    @Autowired
    private searchinfo search;
    @Autowired
    private connectionInfo ci;
    @Autowired
    private postInfo pi;

    Logger logger = LoggerFactory.getLogger(thControler.class);




    @CrossOrigin(origins = "*")
    @PostMapping(path = "/postAddUser", consumes = "application/json", produces = "application/json")
    public String addMember(@RequestBody userInfo user) throws ClassNotFoundException {

        this.userinfo=user;

logger.debug(user.experience+" experience");
System.out.println(user.experience+" Experience");


        thRepository tr = new thRepository();

        if(user.status.equals("student")){
            tr.addUser(user.firstname,user.lastname,user.email,user.status,user.password);
        }else if(user.status.equals("teacher")){
            tr.addUser(user.firstname,user.lastname,user.email,user.status,user.password);
            tr.addTeacher(user.email,user.subject,user.experience,user.city);
        }

        return "user Added";

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/validatelogin", consumes = "application/json", produces = "application/json")
    public userInfo validateUser(@RequestBody loginInfo user) throws ClassNotFoundException {

        this.logininfo=user;

      boolean   validuser = false;


        System.out.println(user.email+" Email");


        thRepository tr = new thRepository();
       return tr.validateLogin(user.email,user.password);


    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws ClassNotFoundException, IOException {

        File f = new File(System.getProperty("user.dir")+"\\target\\images\\"+name+".jpg");
        f.createNewFile();
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(file.getBytes());
        fout.close();
        thRepository tr = new thRepository();
       return tr.setprofilepic(name);


       // return "File is uploaded successfully";


    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addConnection", consumes = "application/json", produces = "application/json")
    public String  addConnection(@RequestBody connectionInfo cn) throws ClassNotFoundException {

        this.ci=cn;

       // System.out.println(user.email+" Email");


        thRepository tr = new thRepository();
        tr.storeConnections(cn.studentEmail,cn.teacherEmail);
        return "Connection added";

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/createPost", consumes = "application/json", produces = "application/json")
    public postInfo  createPost(@RequestBody postInfo post) throws ClassNotFoundException {

        this.pi=post;

        // System.out.println(user.email+" Email");


        thRepository tr = new thRepository();
        tr.createPost(post.email,post.heading,post.content);
        return  this.pi;


    }

    @CrossOrigin(origins = "*")
    @GetMapping("/searchTeacher")
    public Set<userInfo> searchTeacher(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
       return tr.searchTeacher(id);



    }

    @CrossOrigin(origins = "*")
    @GetMapping(value="/profilepic",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProfilePic(@RequestParam String id) throws ClassNotFoundException, IOException {
        InputStream in = new FileInputStream(System.getProperty("user.dir")+"\\target\\images\\"+id+".jpg");
        System.out.println(in+"image input stream");
        return IOUtils.toByteArray(in);





    }

    @CrossOrigin(origins = "*")
    @GetMapping("/logout")
    public void logout(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
         tr.logout(id);



    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getTeachers")
    public List<userInfo> getTeachers(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
        return tr.getTeacher(id);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getStudents")
    public List<userInfo> getStudents(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
        return tr.getStudents(id);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getTeacherPosts")
    public List<postInfo> getTeacherPosts(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
        return tr.getTeacherPosts(id);



    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getStudentPosts")
    public List<postInfo> getStudentPosts(@RequestParam String id) throws ClassNotFoundException {

        System.out.println("Search operation begin"+id);
        thRepository tr = new thRepository();
        return tr.getStudentPosts(id);



    }


}
