package com.example.classvista_admin.Main.Subject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsList(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Subjects",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(Screen.AddSubject.route) }) {
            Icon(imageVector = Icons.Default.Subject, contentDescription = "add course")
        }
    })
    {
        Box(modifier= Modifier.padding(it))
        {

        }
    }
}