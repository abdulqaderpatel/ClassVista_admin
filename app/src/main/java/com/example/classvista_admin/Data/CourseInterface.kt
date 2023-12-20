package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Authentication.LoginResponse
import com.example.classvista_admin.Models.Course
import com.example.classvista_admin.Models.CourseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.POST

interface CourseInterface {

    @GET("course/data")
    suspend fun GetAllCourses(@Header(value = "Authorization") id: String): Response<CourseList>

}