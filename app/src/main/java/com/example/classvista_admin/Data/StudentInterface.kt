package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Student.StudentSignup
import com.example.classvista_admin.Utils.Util
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface StudentInterface {

    data class StudentSignupResponse(
        val data: Student
    )

    @POST("student/signup")
    suspend fun createStudentAccount(
        @Header(value = Util.TOKEN) id: String,
        @Body studentSignup: StudentSignup
    ): Response<StudentSignupResponse>
}