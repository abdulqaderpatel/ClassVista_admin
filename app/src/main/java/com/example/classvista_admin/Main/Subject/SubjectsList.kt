package com.example.classvista_admin.Main.Subject

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.NavigatingFloatingActionButton
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Student.CourseCard
import com.example.classvista_admin.Components.Subject.CourseSubjectDetails
import com.example.classvista_admin.Data.CourseYearInterface
import com.example.classvista_admin.Data.SubjectCourses
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.CourseYear.Data
import com.example.classvista_admin.Models.CourseYear.SubjectCourse
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


    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        isLoading = true;
        if (!courseViewModel.coursesLoaded.value) {
            courseViewModel.coursesLoaded.value = true
            var token = userViewModel.userId.value.token
            courseViewModel.courses.addAll(
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!.data
            )

        }
        isLoading = false
    }

    if (!isLoading)
        Scaffold(topBar = { PrimaryAppBar(title = "Subject Courses") }, floatingActionButton = {
            NavigatingFloatingActionButton(
                navController = navController,
                route = Screen.AddSubject.route,
                icon = Icons.Filled.Subject,
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
                            onClick = { navController.navigate("${Screen.SubjectByCourseYears.route}/${course.id}") })
                    }
                }
            }
        }
}
