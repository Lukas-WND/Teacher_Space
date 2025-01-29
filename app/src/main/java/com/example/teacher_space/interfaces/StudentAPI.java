package com.example.teacher_space.interfaces;

import com.example.teacher_space.dtos.GetStudentDTO;
import com.example.teacher_space.dtos.SendStudentDTO;
import com.example.teacher_space.entity.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentAPI {

    @GET("/students/{id}")
    Call<GetStudentDTO> getStudentById(@Path("id") String id);

    @GET("/teachers/{id}/students")
    Call<List<GetStudentDTO>> getStudentByTeacherId(@Path("id") String id);

    @POST("/students")
    Call<GetStudentDTO> createStudent(@Body SendStudentDTO teacherDTO);


    @DELETE("/students/{id}")
    Call<GetStudentDTO> deleteStudent(@Path("id") String id);
}
