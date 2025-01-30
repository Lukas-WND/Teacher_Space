package com.example.teacher_space.dtos;

import java.util.Date;

public class SendStudentDTO {

    private String name;
    private String category;
    private String phone;
    private String email;
    private String birthdate;
    private String cpf;

    private String teacherId;

    public SendStudentDTO() {
    }

    public SendStudentDTO(String name, String category, String phone, String email, String birthdate, String cpf, String teacherId) {
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public String getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
