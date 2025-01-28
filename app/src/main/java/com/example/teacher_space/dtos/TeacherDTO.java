package com.example.teacher_space.dtos;

import com.example.teacher_space.entity.Teacher;

public class TeacherDTO {
    //private long id;
    private String name;
    private String subject;

    private String phone;
    private String email;
    private String password;

    public TeacherDTO() {}

    public TeacherDTO( String name, String subject, String phone, String email, String password) {
        this.name = name;
        this.subject = subject;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

//    public Teacher getTeacher() {
//        return new Teacher(id, name, subject, phone, email, password);
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
}
