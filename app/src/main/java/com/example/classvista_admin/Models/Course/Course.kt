package com.example.classvista_admin.Models.Course

data class Course(
    val id: Int,
    val admin_id: Int,
    val name: String,

    val short_form: String,
    val created_at: String,
    val updated_at: String
)
