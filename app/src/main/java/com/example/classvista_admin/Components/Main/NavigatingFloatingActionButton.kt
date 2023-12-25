package com.example.classvista_admin.Components.Main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.classvista_admin.Navigation.Screen

@Composable
fun NavigatingFloatingActionButton(
    navController: NavController,
    route: String,
    icon: ImageVector,
    description: String,
) {
    FloatingActionButton(onClick = { navController.navigate(route) }) {
        Icon(imageVector = icon, contentDescription = description)
    }
}