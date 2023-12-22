package com.example.classvista_admin.Data

import com.example.classvista_admin.Models.Admin.Admin
import com.example.classvista_admin.Models.Authentication.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInterface{
    @POST("user/signup")
    suspend fun AdminSignup(@Body admin: Admin): Response<LoginResponse>


    @POST("user/login")
    suspend fun AdminLogin(@Body admin: Admin):Response<LoginResponse>
}