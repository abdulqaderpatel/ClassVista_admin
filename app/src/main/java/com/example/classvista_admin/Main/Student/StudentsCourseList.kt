package com.example.classvista_admin.Main.Student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.NavigatingFloatingActionButton
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Student.CourseCard
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun StudentsCourseList(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel
) {
    LaunchedEffect(Unit) {
        if (!courseViewModel.coursesLoaded.value) {
            courseViewModel.coursesLoaded.value = true
            var token = userViewModel.userId.value.token
            courseViewModel.courses.addAll(
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!.data
            )

        }
    }

    Scaffold(topBar = { PrimaryAppBar(title = "Student Courses") }, floatingActionButton = {
        NavigatingFloatingActionButton(
            navController = navController,
            route = Screen.AddStudent.route,
            icon = Icons.Default.Person,
            description = "Add Student"
        )
    }) {
        Box(modifier = Modifier.padding(it))
        {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(courseViewModel.courses)
                { course ->
                    CourseCard(
                        course = Course(short_form = course.short_form, id = course.id),
                        onClick = { navController.navigate("${Screen.StudentsYearCourse.route}/${course.id}") })
                }
            }
        }
    }
}

