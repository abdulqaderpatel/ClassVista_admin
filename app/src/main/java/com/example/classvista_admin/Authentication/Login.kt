package com.example.classvista_admin.Authentication

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController) {
    Text(
        modifier = Modifier.clickable { navController.navigate("signup") },
        text = "this is the login page"
    )
}