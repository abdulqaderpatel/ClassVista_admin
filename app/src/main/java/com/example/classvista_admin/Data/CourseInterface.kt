package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Course.CourseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseInterface {

    @GET("course/data")
    suspend fun GetAllCourses(@Header(value = "Authorization") id: String): Response<CourseList>

}