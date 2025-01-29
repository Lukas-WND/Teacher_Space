package com.example.teacher_space.interfaces;

import com.example.teacher_space.dtos.LoginDTO;
import com.example.teacher_space.dtos.LoginResponseDTO;
import com.example.teacher_space.dtos.TeacherDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TeacherAPI {
    @GET("/teachers")
    Call<List<TeacherDTO>> getTeachers();

    @GET("teachers/{id}")
    Call<TeacherDTO> getTeacherById(@Path("id") Long id);

    @POST("/teachers")
    Call<TeacherDTO> createTeacher(@Body TeacherDTO teacherDTO);

    @POST("/teachers/signin")
    Call<LoginResponseDTO> login(@Body LoginDTO dto);

    @PUT("/teachers/{id}")
    Call<TeacherDTO> updateTeacher(@Path("id") Long id,
                                   @Body TeacherDTO teacherDTO);

    @DELETE("/teachers/{id}")
    Call<TeacherDTO> deleteTeacher(@Path("id") Long id);
}
