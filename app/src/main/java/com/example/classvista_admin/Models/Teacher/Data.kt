package com.example.classvista_admin.Models.Teacher

data class Data(
    val created_at: String="",
    val id: String="",
    val name: String="",
    val organization_id: Int=0,
    val subjects: List<TeacherSubject> = listOf(),
    val updated_at: String=""
)