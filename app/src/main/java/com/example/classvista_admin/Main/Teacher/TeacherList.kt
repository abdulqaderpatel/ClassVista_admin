package com.example.classvista_admin.Main.Teacher

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.NavigatingFloatingActionButton
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Subject.courseViewModel
import com.example.classvista_admin.Components.Teacher.TeacherEnrolledInSubjects
import com.example.classvista_admin.Data.TeacherInterface
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.TeacherViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun TeacherList(
    navController: NavController,
    userViewModel: UserViewModel,
    teacherViewModel: TeacherViewModel
) {

    LaunchedEffect(Unit) {


        teacherViewModel.teacherIds.addAll(
            RetrofitInstance.teacherInterface.getAllTeacherIds("Bearer ${userViewModel.userId.value.token}")
                .body()!!.data
        )

        teacherViewModel.teacherSubjects.addAll(
            RetrofitInstance.teacherInterface.getAllTeacherSubjects(
                "Bearer ${userViewModel.userId.value.token}",
                TeacherInterface.TeacherIds(teacherViewModel.teacherIds)
            ).body()!!.data
        )

        Log.d("teachers lis", teacherViewModel.teacherSubjects[1].id)
    }

    Scaffold(topBar = { PrimaryAppBar(title = "Teachers")},floatingActionButton = {
        NavigatingFloatingActionButton(
            navController = navController,
            route = Screen.AddTeacher.route,
            icon = Icons.Default.School,
            description = "Teacher"
        )
    }) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize())
        {
            LazyColumn(modifier=Modifier.fillMaxWidth()) {
                itemsIndexed(teacherViewModel.teacherSubjects)
                {
                    index,teacher->
                    TeacherEnrolledInSubjects(teacherSubjects = teacher)
                }

            }
        }
    }
}