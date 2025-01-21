package com.example.teacher_space.interfaces;

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
    @GET("/teacher")
    Call<List<TeacherDTO>> getTeachers();

    @GET("teacher/{id}")
    Call<TeacherDTO> getTeacherById(@Path("id") Long id);

    @POST("/teacher")
    Call<TeacherDTO> createTeacher(@Body TeacherDTO teacherDTO);

    @PUT("/teacher/{id}")
    Call<TeacherDTO> updateTeacher(@Path("id") Long id,
                                   @Body TeacherDTO teacherDTO);

    @DELETE("/teacher/{id}")
    Call<TeacherDTO> deleteTeacher(@Path("id") Long id);
}
