package com.example.classvista_admin.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classvista_admin.Data.Student
import com.example.classvista_admin.Data.StudentsForCourse
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Course.CourseList
import com.example.classvista_admin.Models.CourseYear.CourseYear
import com.example.classvista_admin.Models.CourseYear.Data
import com.example.classvista_admin.Models.CourseYear.SubjectCourse
import com.example.classvista_admin.Utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {

    //stores the course details
    var courses = mutableStateListOf<Course>()
    var coursesLoaded=mutableStateOf(false)


    var courseYearsDataLoaded=mutableStateOf(false)
    var courseYearsData=
    mutableStateListOf<CourseYear>()

    var studentsData=mutableStateListOf<Student>()

    //stores the subject details in relation to a course
    var subjectCourses = mutableStateListOf<List<Data>>()





}