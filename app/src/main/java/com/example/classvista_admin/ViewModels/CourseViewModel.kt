package com.example.classvista_admin.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Course.CourseList
import com.example.classvista_admin.Models.CourseYear.Data
import com.example.classvista_admin.Models.CourseYear.SubjectCourse
import com.example.classvista_admin.Utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {

    //stores the course details
    var courses = mutableStateListOf<Course>()
    var coursesLoaded=mutableStateOf(false)
    var coursesLoading = false;

    //stores the subject details in relation to a course
    var subjectCourses = mutableStateListOf<List<Data>>()


    fun fetchCourses(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            coursesLoading = true
            var response =
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token")


                courses.addAll(response.body()!!.data)


            Log.d("TAGGG", courses.toString())

            coursesLoading = false
        }

    }


}