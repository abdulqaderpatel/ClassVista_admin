package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.CourseYear.CourseYear
import com.example.classvista_admin.Models.CourseYear.CourseYearList
import com.example.classvista_admin.Models.CourseYear.SubjectCourse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

data class CourseCreationWithYears(
    val course_id: Int,
    val year_id: Int,
)

data class CourseYearCreationResponse(
    val data: CourseYear
)

data class SubjectCourses(
    val course_ids: List<Int>
)

data class Student(
    val id: String,
    val admin_id: Int,
    val course_id: Int,
    val name: String,
    val created_at: String,
    val updated_at: String
)

data class StudentsForCourse(
    val data: List<Student>
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
        @Header(value = "Authorization") id: String,
        @Body courseCreationWithYears: CourseCreationWithYears
    ): Response<CourseYearCreationResponse>

    @GET("courseyear/data/{course_id}/{year_id}")
    suspend fun getUniqueCourseYear(
        @Header(value = "Authorization") id: String,
        @Path("course_id") course_id: Int,
        @Path("year_id") year_id: Int
    ): Response<CourseYearCreationResponse>

    @POST("courseyear/data/subjects")
    suspend fun getCourseSubjects(
        @Body courseids: SubjectCourses
    ): Response<SubjectCourse>


    //get students details for single course
    @GET("courseyear/data/students/{courseId}")
    suspend fun studentDetailsForSingleCourse(@Path("courseId") courseId: Int): Response<StudentsForCourse>
}