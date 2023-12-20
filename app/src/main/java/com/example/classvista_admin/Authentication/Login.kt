package com.example.classvista_admin.Authentication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Components.AuthField
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Models.Admin
import com.example.classvista_admin.Models.Token
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.R
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun Login(navController: NavController = rememberNavController()) {
    var loginClicked by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    LaunchedEffect(key1 = loginClicked) {
        if (loginClicked) {


            try {
                val response =
                    RetrofitInstance.userInterface.AdminLogin(
                        Admin(
                            email = email,
                            password = password
                        )
                    )

                if (response.isSuccessful) {

                    CoroutineScope(Dispatchers.IO).launch {
                        var userViewModel=UserViewModel()
                        var preferenceDataStore = UserStore(context)
                        userViewModel.userId.value= Token(response.body()!!.token)
                        preferenceDataStore.setValue(userViewModel.userId.value)
                        preferenceDataStore.getDetails().collect {

                        }

                        navController.navigate(Screen.Home.route)

                    }

                } else {

                }
            } catch (e: Exception) {

            } finally {
                loginClicked = false
            }
        }
    }
    Scaffold(bottomBar = {
        Column(modifier = Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp)) {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    loginClicked = true;
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Login")
            }
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(

                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Text(
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate("signup")
                    },
                    text = "Get Started",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff1B232D))
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.app_bg),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(Modifier.height(10.dp))
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.W600, color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    AuthField(
                        leadingIcon = Icons.Default.Email,
                        value = email,
                        valueChange = { email = it },
                        hint = "Enter Email",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(Modifier.height(20.dp))
                    AuthField(
                        leadingIcon = Icons.Default.Password,
                        value = password,
                        valueChange = { password = it },
                        hint = "Enter Password",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                    Spacer(modifier = Modifier.height(40.dp))


                }

            }
        }
    }
}


