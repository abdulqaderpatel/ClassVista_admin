package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.CourseYear.CourseYearList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseYearInterface {

    @GET("courseyear/data/{course_id}")
    suspend fun GetCourseWithYearsAssociated(
        @Header(value = "Authorization") id: String,
        @Path("course_id") courseId: Int
    ): Response<CourseYearList>

}