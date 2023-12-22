package com.example.classvista_admin.Main.Course

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Models.CourseYear.CourseYearList
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListWithYearsAssociated(
    navController: NavController,
    userViewModel: UserViewModel,
    course_id: Int
) {

    var courseYears by remember { mutableStateOf(CourseYearList(emptyList())) }
    LaunchedEffect(Unit) {


        var token = userViewModel.userId.value.token


           courseYears=RetrofitInstance.courseyearInterface.GetCourseWithYearsAssociated(
                "Bearer $token",
                course_id
            ).body()!!



        Log.d("check course", courseYears.toString())


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
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(courseYears.data)
            { course ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { },

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = course.course.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = course.course.name,
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