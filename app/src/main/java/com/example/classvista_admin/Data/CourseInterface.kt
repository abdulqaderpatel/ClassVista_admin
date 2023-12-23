package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Course.CourseList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST



interface CourseInterface {

    @GET("course/data")
    suspend fun GetAllCourses(@Header(value = "Authorization") id: String): Response<CourseList>

    @POST("course/create")
    suspend fun CreateCourse(
        @Header(value = "Authorization") id: String,
        @Body courseCreation: CourseCreation
    ): Response<CourseCreationResponse>

}


data class CourseCreation(
    val name: String,
    val short_form: String
)

data class CourseCreationResponse(
    val data: Course
)
