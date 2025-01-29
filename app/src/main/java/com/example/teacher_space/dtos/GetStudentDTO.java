package com.example.teacher_space.dtos;

import com.example.teacher_space.entity.Student;

import java.util.Date;

public class GetStudentDTO {

    private String id;
    private String name;
    private String category;
    private String email;
    private Date birthdate;
    private String cpf;
    private Date createdAt;
    private Date updatedAt;
    private String teacherId;
    private String phone;

    public GetStudentDTO() {}

    public GetStudentDTO(String id, String name, String category, String email, Date birthdate, String cpf, Date createdAt, Date updatedAt, String teacherId, String phone) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.email = email;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.teacherId = teacherId;
        this.phone = phone;
    }

    public Student build (GetStudentDTO studentDTO){
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setCpf(studentDTO.getCpf());
        student.setCategory(studentDTO.getCategory());
        student.setPhone(studentDTO.getPhone());
        return student;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
