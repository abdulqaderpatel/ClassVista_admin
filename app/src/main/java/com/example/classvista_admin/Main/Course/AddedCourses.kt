package com.example.classvista_admin.Main.Course

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel


@Composable
fun AddedCourses(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel
) {

    var isLoading = true
    LaunchedEffect(Unit) {

        var token = userViewModel.userId.value.token
      courseViewModel.courses.value= RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!


        Log.d("check course", courseViewModel.getCourses().toString())
        isLoading=false

    }


        LazyColumn {
            items(courseViewModel.courses.value.data)
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




