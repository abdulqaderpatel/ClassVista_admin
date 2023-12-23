package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.CourseYear.CourseYear
import com.example.classvista_admin.Models.CourseYear.CourseYearList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

data class CourseCreationWithYears(
    val course_id:Int,
    val year_id:Int,
)

data class CourseYearCreationResponse(
    val data:CourseYear
)
interface CourseYearInterface {

    //get a course with all its years
    @GET("courseyear/data/{course_id}")
    suspend fun GetCourseWithYearsAssociated(
        @Header(value = "Authorization") id: String,
        @Path("course_id") courseId: Int
    ): Response<CourseYearList>


    //create a course with year association
    @POST("courseyear/create")
    suspend fun CreateCourseWithYearsAssociated(
        @Header(value="Authorization") id:String,
        @Body courseCreationWithYears: CourseCreationWithYears
    ):Response<CourseYearCreationResponse>

}