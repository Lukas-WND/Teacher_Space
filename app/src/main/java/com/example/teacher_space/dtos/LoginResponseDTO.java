package com.example.teacher_space.dtos;

import com.example.teacher_space.entity.Teacher;
import com.google.gson.annotations.SerializedName;

public class LoginResponseDTO {
    private String message;

    @SerializedName("teacher") // Mapeia corretamente o campo "teacher" do JSON
    private Teacher teacher;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String message, Teacher teacher) {
        this.message = message;
        this.teacher = teacher;
    }

    public String getMessage() {
        return message;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
