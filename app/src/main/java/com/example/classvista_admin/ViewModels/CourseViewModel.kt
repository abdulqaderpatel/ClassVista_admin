package com.example.classvista_admin.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classvista_admin.Models.Course.CourseList
import com.example.classvista_admin.Utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    var courses = mutableStateOf(CourseList(emptyList()))

    var coursesLoading = false;


    fun fetchCourses(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            coursesLoading = true
            var response =
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token")


                courses.value=response.body()!!


            Log.d("TAGGG", courses.toString())

            coursesLoading = false
        }

    }

    fun getCourses(): CourseList
    {
        return courses.value
    }
}