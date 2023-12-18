package com.example.classvista_admin.Models.Authentication

data class Errors(
    val email: List<String>,
    val organization: List<String>,
    val password: List<String>
)