package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Course.CourseList
import com.example.classvista_admin.Models.CourseYear.CourseYear
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface CourseInterface {

    @GET("course/data")
    suspend fun GetAllCourses(@Header(value = "Authorization") id: String): Response<CourseList>

    @POST("course/create")
    suspend fun CreateCourse(
        @Header(value = "Authorization") id: String,
        @Body courseCreation: CourseCreation
    ): Response<CourseCreationResponse>

    //Get courseyears data from courseid
    @GET("course/courseyears/{courseId}")
    suspend fun GetCourseyearsFromCourse(@Path("courseId") courseId: Int): Response<CourseyearsFromCourse>

}


data class CourseCreation(
    val name: String,
    val short_form: String
)

data class CourseCreationResponse(
    val data: Course
)

data class CourseyearsFromCourse(
    val data: List<CourseYear>
)
