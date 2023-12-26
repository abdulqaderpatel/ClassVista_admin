package com.example.classvista_admin.Data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeInterface {

    @GET("notice/image/{imageName}")
    suspend fun retrieveSinglePdf(@Path("imageName") imageName: String = "1703600744_app_bg.jpeg"): ResponseBody
}

