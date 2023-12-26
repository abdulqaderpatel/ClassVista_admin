package com.example.classvista_admin.Navigation

sealed class Screen(val route: String) {
    data object Login : Screen(route = "login")
    data object Signup : Screen(route = "signup")
    data object Home : Screen("home")

    data object AddedCourses : Screen("added_courses")

    data object CourseWithYearsAssociated : Screen("course_with_years_associated")

    data object AddCourse : Screen("add_course")

    data object SubjectList : Screen("subject_list")

    data object AddSubject : Screen("add_subject")

    data object TeacherList : Screen("teacher_list")

    data object AddTeacher : Screen("add_teacher")

    data object StudentCourseList : Screen("student_course_list")

    data object StudentsInCourse : Screen("students_in_course")

    data object StudentsYearCourse:Screen("students_year_course")

    data object AddStudent : Screen("add_student")

    data object NoticeList : Screen("add_notice")

    data object AddNotice : Screen("add_notice")

}