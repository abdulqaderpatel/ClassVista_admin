package com.example.classvista_admin.Main.Teacher

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.TeacherViewModel
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun EnrollTeacherInSubjects(
    navController: NavController,
    userViewModel: UserViewModel,
    teacherViewModel: TeacherViewModel, teacherId: String
) {
    Text(text = teacherId)

    LaunchedEffect(Unit) {
        var response =
            RetrofitInstance.teacherInterface.checkIfSubjectsEnrolledByTeacher(
                "Bearer ${userViewModel.userId.value.token}",
                teacherId
            )

        Log.d("hello india", response.body()!!.data.toString())
    }

}