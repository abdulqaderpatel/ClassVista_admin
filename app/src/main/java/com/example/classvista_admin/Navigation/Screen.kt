package com.example.classvista_admin.Navigation

sealed class Screen(val route: String) {
    data object Login : Screen(route = "login")
    data object Signup : Screen(route = "signup")
    data object Home : Screen("home")

    data object AddedCourses : Screen("added_courses")

    data object CourseWithYearsAssociated : Screen("course_with_years_associated")

    data object AddCourse:Screen("add_course")

    data object SubjectList:Screen("subject_list")

    data object AddSubject:Screen("add_subject")

}