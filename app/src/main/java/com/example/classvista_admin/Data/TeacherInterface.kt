package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Teacher.Data
import com.example.classvista_admin.Models.Teacher.TeacherSubjects
import com.example.classvista_admin.Navigation.Screen
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TeacherInterface {

    data class getTeacherIds(
        val data: List<String>
    )

    data class TeacherIds(
        var teacher_ids: List<String>
    )

    data class TeacherAccount(
        var id: String,
        var name: String,
        var password: String
    )

    //get all the teacher ids which are later used to retreive their respective subjects
    @GET("teacher/all/ids")
    suspend fun getAllTeacherIds(@Header(value = "Authorization") id: String): Response<getTeacherIds>

    //get all the teachers with the respective subjects they teach
    @POST("teacher/all/subjects")
    suspend fun getAllTeacherSubjects(
        @Header(value = "Authorization") id: String,
        @Body teacherIds: TeacherIds

    ): Response<TeacherSubjects>

    //create a teacher account
    @POST("teacher/signup")
    suspend fun SignupTeacher(
        @Header(value = "Authorization") id: String,
        @Body teacherAccount:TeacherAccount
    ):Response<Data>
}