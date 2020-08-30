package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class connectionInfo {
    String studentEmail;
    String teacherEmail;

    public connectionInfo(String studentEmail, String teacherEmail) {
        this.studentEmail = studentEmail;
        this.teacherEmail = teacherEmail;
    }

    public connectionInfo() {
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }
}
