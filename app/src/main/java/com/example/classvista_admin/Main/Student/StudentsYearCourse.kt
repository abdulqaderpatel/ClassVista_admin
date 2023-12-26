package com.example.classvista_admin.Main.Student

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Student.YearCard
import com.example.classvista_admin.Models.CourseYear.CourseYear
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.Dispatchers

@SuppressLint("UnrememberedMutableState")
@Composable
fun StudentsYearCourse(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel,
    courseId: Int
) {


    var colors = listOf(Color.Red, Color.Green, Color.Blue)

    LaunchedEffect(Dispatchers.IO) {
       courseViewModel.courseYearsData.clear()

            courseViewModel.courseYearsData.addAll(
                RetrofitInstance.courseInterface.GetCourseyearsFromCourse(courseId = courseId)
                    .body()!!.data
            )

    }
    Scaffold(topBar = { PrimaryAppBar(title = "Student Course Years") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center
        )
        {
            LazyColumn {
                itemsIndexed(courseViewModel.courseYearsData)
                { index, course ->

                    var year = when (course.year_id) {
                        1 -> "First Year"
                        2 -> "Second Year"
                        else -> "Third Year"
                    }

                    YearCard(
                        year = year,
                        cardColor = colors[index],
                        onClick = { navController.navigate("${Screen.StudentsInCourse.route}/${course.id}") })


                }
            }
        }
    }
}