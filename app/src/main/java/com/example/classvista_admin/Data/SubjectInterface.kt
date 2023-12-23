package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Subject.SubjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.security.auth.Subject

interface SubjectInterface {

    data class Subject(
        val id: String,
        val course_id: Int,
        val subject_name: String
    )

    @POST("subject/create")
    suspend fun CreateSubject(
        @Header(value = "Authorization") id: String,
        @Body subject: Subject
    ):Response<SubjectResponse>



}