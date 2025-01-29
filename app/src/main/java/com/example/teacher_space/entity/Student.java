package com.example.teacher_space.entity;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String category;
    private String phone;
    private String email;
    private Date birthDate;
    private String cpf;

    public Student(){}

    public Student(String id, String name, String category, String phone, String email, Date birthDate, String cpf) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
