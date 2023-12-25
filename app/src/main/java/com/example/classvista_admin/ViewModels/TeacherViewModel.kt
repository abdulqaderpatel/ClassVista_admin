package com.example.classvista_admin.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.classvista_admin.Models.Teacher.Data
import com.example.classvista_admin.Models.Teacher.TeacherSubject
import com.example.classvista_admin.Models.Teacher.TeacherSubjects

class TeacherViewModel:ViewModel() {

    var teacherIds= mutableListOf<String>()

    var teacherSubjects=mutableStateListOf<Data>()
}