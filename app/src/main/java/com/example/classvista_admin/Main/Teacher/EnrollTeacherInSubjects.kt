package com.example.classvista_admin.Main.Teacher

import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Components.Teacher.SelectSubjectsCard
import com.example.classvista_admin.Data.TeacherInterface
import com.example.classvista_admin.Models.Subject.Subject
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.TeacherViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun EnrollTeacherInSubjects(
    navController: NavController,
    userViewModel: UserViewModel,
    teacherViewModel: TeacherViewModel, teacherId: String
) {

    var buttonLoading by remember {
        mutableStateOf(false)
    }



    var context = LocalContext.current
    LaunchedEffect(Dispatchers.IO) {

        teacherViewModel.subjects.clear()

        teacherViewModel.subjects.addAll(
            RetrofitInstance.subjectInterface.getSubjectsUnassignedToTeachers("Bearer ${userViewModel.userId.value.token}")
                .body()!!.data
        )
        teacherViewModel.subjects.map {
            teacherViewModel.isSubjectSelected.add(mutableStateOf(false))
        }
    }

    Scaffold(
        topBar = { PrimaryAppBar(title = "Assign Subjects") },
        bottomBar = {
            AdminButton(label = "Assign Subjects", buttonLoading = buttonLoading) {
                CoroutineScope(Dispatchers.Main).launch {
                    buttonLoading = true;
                    RetrofitInstance.teacherInterface.assignMultipleSubjectsToTeacher(
                        "Bearer ${userViewModel.userId.value.token}",
                        TeacherInterface.SubjectsTeacher(
                            subjectIds = teacherViewModel.selectedSubjectIds,
                            teacherId = teacherId
                        )
                    )
                    navController.popBackStack()
                    teacherViewModel.isTeacherDataLoaded.value = false
                    buttonLoading = false;
                    android.os.Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Subjects Assigned To Teacher", Toast.LENGTH_LONG)
                            .show()
                    }


                }
            }
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {
            LazyColumn {
                itemsIndexed(teacherViewModel.subjects)
                { index, subject ->
                    SelectSubjectsCard(
                        isSelected = teacherViewModel.isSubjectSelected[index].value,
                        onCheckedChange = {
                            if (!teacherViewModel.isSubjectSelected[index].value) {
                                teacherViewModel.selectedSubjectIds.add(subject.id)
                                teacherViewModel.isSubjectSelected[index].value = true;
                                Log.d("Subjects", teacherViewModel.selectedSubjectIds.toString())
                            } else {
                                teacherViewModel.selectedSubjectIds.remove(subject.id)
                                teacherViewModel.isSubjectSelected[index].value = false;
                                Log.d("Subjects", teacherViewModel.selectedSubjectIds.toString())
                            }
                        },
                        subject = subject
                    )
                }
            }
        }

    }

}