package com.example.classvista_admin.Navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Authentication.Login
import com.example.classvista_admin.Authentication.Signup
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Main.Course.AddCourse
import com.example.classvista_admin.Main.Course.AddedCourses
import com.example.classvista_admin.Main.Course.CourseListWithYearsAssociated
import com.example.classvista_admin.Main.Home
import com.example.classvista_admin.Main.Notice.AddNotice
import com.example.classvista_admin.Main.Notice.NoticeList
import com.example.classvista_admin.Main.Student.AddStudent

import com.example.classvista_admin.Main.Student.StudentsCourseList
import com.example.classvista_admin.Main.Student.StudentsInCourse
import com.example.classvista_admin.Main.Subject.AddSubject
import com.example.classvista_admin.Main.Subject.SubjectsList
import com.example.classvista_admin.Main.Teacher.AddTeacher
import com.example.classvista_admin.Main.Teacher.TeacherList
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.TeacherViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun Navigator(navController: NavHostController) {

    var userViewModel = UserViewModel()
    var courseViewModel = CourseViewModel()
    var teacherViewModel = TeacherViewModel()


    var isLoading by remember {
        mutableStateOf(true)
    }


    var preferenceDataStore = UserStore(LocalContext.current)
    var details by remember {
        mutableStateOf("")
    }
    userViewModel.timepass.value = "hello"
    LaunchedEffect(Unit) {

        preferenceDataStore.getDetails().collect {

            details = it.token

            Log.d("abvcdbvvb", userViewModel.userId.value.token)
            isLoading = false


        }
    }
    userViewModel.userId.value.token = details

    if (!isLoading) {
        NavHost(
            navController = navController,
            startDestination = if (details.isEmpty()) Screen.Signup.route else (Screen.Home.route)
        )
        {
            composable(Screen.Signup.route)
            {
                Signup(navController)
            }
            composable(Screen.Login.route)
            {
                Login(navController)
            }
            composable(Screen.Home.route)
            {
                Home(navController)
            }
            composable(Screen.AddedCourses.route)
            {
                AddedCourses(navController = navController, userViewModel, courseViewModel)
            }

            composable("${Screen.CourseWithYearsAssociated.route}/{course_id}")
            { navBackStackEntry ->
                var course_id =
                    navBackStackEntry.arguments?.getString("course_id")?.toIntOrNull() ?: 0

                if (course_id != null) {
                    CourseListWithYearsAssociated(
                        navController = navController,
                        userViewModel = userViewModel, courseViewModel = courseViewModel,
                        course_id = course_id

                    )
                }
            }
            composable(Screen.AddCourse.route)
            {
                AddCourse(
                    navController = navController,
                    userViewModel = userViewModel,
                    courseViewModel = courseViewModel
                )
            }
            composable(Screen.SubjectList.route)
            {
                SubjectsList(
                    navController = navController,
                    userViewModel = userViewModel,
                    courseViewModel = courseViewModel
                )
            }
            composable(Screen.AddSubject.route)
            {
                AddSubject(
                    navController = navController,
                    userViewModel = userViewModel,
                    courseViewModel = courseViewModel
                )
            }
            composable(Screen.TeacherList.route)
            {
                TeacherList(
                    navController = navController,
                    userViewModel = userViewModel,
                    teacherViewModel = teacherViewModel
                )
            }
            composable(Screen.AddTeacher.route)
            {
                AddTeacher(
                    navController = navController,
                    userViewModel = userViewModel,
                    teacherViewModel = teacherViewModel
                )
            }
            composable(Screen.StudentCourseList.route)
            {
                StudentsCourseList(
                    navController = navController,
                    userViewModel = userViewModel,
                    courseViewModel
                )
            }
            composable(Screen.StudentsInCourse.route)
            {
                StudentsInCourse(navController = navController, userViewModel = userViewModel)
            }
            composable(Screen.StudentsYearCourse.route)
            {

            }
            composable(Screen.AddStudent.route)
            {
                AddStudent(navController = navController, userViewModel = userViewModel)
            }
            composable(Screen.NoticeList.route)
            {
                NoticeList(navController = navController, userViewModel = userViewModel)
            }
            composable(Screen.AddNotice.route)
            {
                AddNotice(navController = navController, userViewModel = userViewModel)
            }
        }
    }
}