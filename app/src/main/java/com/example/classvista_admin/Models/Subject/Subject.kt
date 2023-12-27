package com.example.classvista_admin.Models.Subject

data class Subject(
    val admin_id: Int=0,
    val course_id: Int,
    val created_at: String="",
    val id: String,
    val subject_name: String,
    val teacher_id: Any,
    val updated_at: String=""
)