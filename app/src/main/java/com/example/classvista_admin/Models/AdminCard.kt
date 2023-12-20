package com.example.classvista_admin.Models

import androidx.compose.ui.graphics.vector.ImageVector

data class AdminCard(
    val primary: androidx.compose.ui.graphics.Color,
    val secondary:androidx.compose.ui.graphics.Color,
    val icon: ImageVector,
    val label:String
)