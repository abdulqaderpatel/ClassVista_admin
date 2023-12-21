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
import com.example.classvista_admin.Main.Course.AddedCourses
import com.example.classvista_admin.Main.Home
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun Navigator(navController: NavHostController) {

    var userViewModel = UserViewModel()
    var courseViewModel = CourseViewModel()


    var isLoading by remember {
        mutableStateOf(true)
    }


    var preferenceDataStore = UserStore(LocalContext.current)
    var details by remember {
        mutableStateOf("")
    }
    userViewModel.timepass.value="hello"
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

        }
    }
}