package com.example.classvista_admin.Main.Student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun Screen.StudentsYearCourse(navController: NavController, userViewModel: UserViewModel) {
    Scaffold(topBar = { PrimaryAppBar(title = "Student Course Years") }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it))
        {

        }
    }
}