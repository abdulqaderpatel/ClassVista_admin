package com.example.classvista_admin.Main.Course

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.UserViewModel

@Composable
fun AddedCourses(navController: NavController, userViewModel: UserViewModel) {

    LaunchedEffect(Unit) {



        var response =
            RetrofitInstance.courseInterface.GetAllCourses("Bearer 48|9cLkQKr9qvIzSTeXq5W85zOZNizUUFwWq5dWakMG29fe0b00")


        Log.d("fjdlskj", response.body().toString())

        Log.d("fjdlskj", response.message())    }
}