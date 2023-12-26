package com.example.classvista_admin.Models.CourseYear

import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Year.Year


data class CourseYear(
    val admin_id: Int=0,
    val course_id: Int=0,
    val created_at: String="",
    val id: Int=0,
    val updated_at: String="",
    val year_id: Int=0,
    val course:Course= Course(),
    val year:com.example.classvista_admin.Models.Year.Year= Year()
)