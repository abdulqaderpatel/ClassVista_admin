package com.example.classvista_admin.Navigation


import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Authentication.Login
import com.example.classvista_admin.Authentication.Signup
import com.example.classvista_admin.Main.Course.AddedCourses
import com.example.classvista_admin.Main.Home
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun Navigator(startDestination: String) {
    val navController = rememberNavController()

    var userViewModel=UserViewModel()


    NavHost(
        navController = navController,
        startDestination = startDestination
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
            AddedCourses(navController = navController,userViewModel)
        }

    }
}