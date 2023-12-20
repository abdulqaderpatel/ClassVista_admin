package com.example.classvista_admin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Navigation.Navigator
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.ViewModels.UserViewModel
import com.example.classvista_admin.ui.theme.ClassVista_adminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel = UserViewModel()


        setContent {
            var isLoggedIn by remember {
                mutableStateOf(false)
            }

            var isLoading by remember {
                mutableStateOf(true)
            }

            var preferenceDataStore = UserStore(LocalContext.current)
            LaunchedEffect(Unit) {
                preferenceDataStore.getDetails().collect {
                    userViewModel.getId(it.token)
                    Log.d("abvcdbvvb", userViewModel.userId.value.token)
                    isLoggedIn = userViewModel.userId.value.token.isNotEmpty()
                    isLoading = false

                }


            }
            if (!isLoading) {
                if (!isLoggedIn) {
                    Log.d("dsfsdfds", (userViewModel.userId.value.token == "").toString())
                    Navigator(startDestination = Screen.Signup.route)
                } else {
                    Navigator(startDestination = Screen.Home.route)
                }

            }
        }

    }
}

