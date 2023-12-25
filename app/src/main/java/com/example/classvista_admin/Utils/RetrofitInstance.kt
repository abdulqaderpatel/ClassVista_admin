package com.example.classvista_admin.Utils

import com.example.classvista_admin.Data.CourseInterface
import com.example.classvista_admin.Data.CourseYearInterface
import com.example.classvista_admin.Data.SubjectInterface
import com.example.classvista_admin.Data.TeacherInterface
import com.example.classvista_admin.Data.UserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val userInterface: UserInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE).addConverterFactory(GsonConverterFactory.create())
            .build().create(UserInterface::class.java)
    }

    val courseInterface: CourseInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE).addConverterFactory(GsonConverterFactory.create())
            .build().create(CourseInterface::class.java)
    }

    val courseyearInterface: CourseYearInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE).addConverterFactory(GsonConverterFactory.create())
            .build().create(CourseYearInterface::class.java)
    }

    val subjectInterface: SubjectInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE).addConverterFactory(GsonConverterFactory.create())
            .build().create(SubjectInterface::class.java)
    }

    val teacherInterface: TeacherInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE).addConverterFactory(GsonConverterFactory.create())
            .build().create(TeacherInterface::class.java)
    }


}