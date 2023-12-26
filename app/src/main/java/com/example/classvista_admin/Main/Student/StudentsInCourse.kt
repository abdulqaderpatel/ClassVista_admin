package com.example.classvista_admin.Main.Student

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Student.StudentCard
import com.example.classvista_admin.Data.Student
import com.example.classvista_admin.Data.StudentsForCourse
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import retrofit2.Retrofit

@Composable
fun StudentsInCourse(
    navController: NavController,
    userViewModel: UserViewModel,
    courseId: Int,
    courseViewModel: CourseViewModel
) {


    LaunchedEffect(Unit) {
        courseViewModel.studentsData.clear()
        courseViewModel.studentsData.addAll(
            RetrofitInstance.courseyearInterface.studentDetailsForSingleCourse(
                courseId
            ).body()!!.data
        )


    }


    Scaffold(topBar = { PrimaryAppBar(title = "Students") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(courseViewModel.studentsData)
                { student ->
                    StudentCard(student)
                }

            }
        }


    }
}