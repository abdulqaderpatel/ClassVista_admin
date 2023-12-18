package com.example.classvista_admin.Utils

import com.example.classvista_admin.Data.UserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: UserInterface by lazy {
        Retrofit.Builder().baseUrl(Util.Base).addConverterFactory(GsonConverterFactory.create())
            .build().create(UserInterface::class.java)
    }




}