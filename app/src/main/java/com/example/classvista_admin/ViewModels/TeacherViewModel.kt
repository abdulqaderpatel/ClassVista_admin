package com.example.classvista_admin.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.classvista_admin.Models.Subject.Subject
import com.example.classvista_admin.Models.Teacher.Data
import com.example.classvista_admin.Models.Teacher.TeacherSubject
import com.example.classvista_admin.Models.Teacher.TeacherSubjects

class TeacherViewModel:ViewModel() {

var isTeacherDataLoaded=mutableStateOf(false);
    var teacherIds= mutableListOf<String>()
    var teacherSubjects=mutableStateListOf<Data>()

    var subjects = mutableListOf<Subject>()

    var isSubjectSelected = mutableListOf<MutableState<Boolean>>()

    var selectedSubjectIds = mutableListOf<String>()
}