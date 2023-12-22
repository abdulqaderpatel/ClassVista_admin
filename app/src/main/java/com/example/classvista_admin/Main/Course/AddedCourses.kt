package com.example.classvista_admin.Main.Course

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddedCourses(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel
) {

    var isLoading = true
    LaunchedEffect(Unit) {

        var token = userViewModel.userId.value.token
        courseViewModel.courses.value =
            RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!
        isLoading = false
    }


    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Courses",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(Screen.AddCourse.route) }) {
            Icon(imageVector = Icons.Default.Book, contentDescription = "add course")
        }
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            var i = 1
            items(courseViewModel.courses.value.data)
            { course ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("${Screen.CourseWithYearsAssociated.route}/${course.id}") },

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = course.short_form,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = course.name,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
            }
        }
    }


}




