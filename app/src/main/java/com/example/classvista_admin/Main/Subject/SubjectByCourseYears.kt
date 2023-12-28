package com.example.classvista_admin.Main.Subject

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Subject.CourseYearSubjectCard
import com.example.classvista_admin.Data.SubjectCourses
import com.example.classvista_admin.Models.CourseYear.Data
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun SubjectByCourseYears(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel,
    courseId: Int
) {

    var years=listOf("First Year","Second Year","Third Year")
    var colors = listOf(Color.Red, Color.Blue, Color.Green)

    LaunchedEffect(Unit) {
        courseViewModel.courseyearsSubjectData.clear()

        val courseIds = RetrofitInstance.courseInterface.GetCourseYearIdsForCourse(
            "Bearer ${userViewModel.userId.value.token}",
            courseId
        ).body()!!.data


        courseViewModel.courseyearsSubjectData.addAll(
            RetrofitInstance.courseyearInterface.getCourseSubjects(SubjectCourses(courseIds))
                .body()!!.data
        )


    }

    Scaffold(topBar = { PrimaryAppBar(title = "Subjects") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(courseViewModel.courseyearsSubjectData)
                { index, course ->
                    CourseYearSubjectCard(
                        course = course,
                        year = years[index],
                        cardColor = colors[index]
                    )
                }
            }
        }
    }
}