package com.example.classvista_admin.Main.Subject

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classvista_admin.Data.CourseYearInterface
import com.example.classvista_admin.Data.SubjectCourses
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsList(
    navController: NavController, userViewModel: UserViewModel, courseViewModel: CourseViewModel
) {

    var courses = mutableListOf<Int>()

    LaunchedEffect(Unit) {
        if (!courseViewModel.coursesLoaded.value) {
            courseViewModel.coursesLoaded.value = true
            var token = userViewModel.userId.value.token
            courseViewModel.courses.addAll(
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!.data
            )


        }
        courseViewModel.courses.map {
            if (it.id != 2) {
                courses.add(it.id)
            }

        }
        Log.d("timepass", courses.toString())


        Log.d("subjects", RetrofitInstance.courseyearInterface.getCourseSubjects(
           SubjectCourses(courses)
        ).body().toString()
        )
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Subjects",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(Screen.AddSubject.route) }) {
            Icon(imageVector = Icons.Default.Subject, contentDescription = "add course")
        }
    }) {
        Box(modifier = Modifier.padding(it)) {

        }
    }
}



@Preview(showBackground = true)
@Composable
fun CourseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Course Name as heading
            Text(
                text = "softwaring time",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subjects listed underneath each course

                Text(
                    text = "Subject ID: dsflksdj - ${"Web programming"}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

        }
    }
}