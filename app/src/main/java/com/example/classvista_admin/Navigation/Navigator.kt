package com.example.classvista_admin.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Authentication.Login
import com.example.classvista_admin.Authentication.Signup

@Composable
fun Navigator() {
    val navController= rememberNavController()
    NavHost(navController =navController, startDestination ="signup")
    {
        composable("signup")
        {
            Signup(navController)
        }
        composable("login")
        {
            Login(navController)
        }
    }
}