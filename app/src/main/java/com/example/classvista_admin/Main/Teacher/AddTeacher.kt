package com.example.classvista_admin.Main.Teacher

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Data.TeacherInterface
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.TeacherViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddTeacher(
    navController: NavController,
    userViewModel: UserViewModel,
    teacherViewModel: TeacherViewModel
) {
    var id by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var isButtonLoading by remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = { PrimaryAppBar(title = "Add Teacher") }, bottomBar = {
        AdminButton(label = "Add Teacher", buttonLoading = isButtonLoading)
        {
            CoroutineScope(Dispatchers.IO).launch {
                isButtonLoading = true


                withContext(Dispatchers.Main)
                {
                    var response = RetrofitInstance.teacherInterface.SignupTeacher(
                        "Bearer ${userViewModel.userId.value.token}",
                        TeacherInterface.TeacherAccount(id, name, password)
                    )
                    teacherViewModel.isTeacherDataLoaded.value = false
                    teacherViewModel.teacherIds.clear()
                    teacherViewModel.teacherSubjects.clear()
                    isButtonLoading = false;
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


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                AdminTextField(
                    leadingIcon = Icons.Default.Difference,
                    value = id,
                    valueChange = { id = it },
                    hint = "Id"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    leadingIcon = Icons.Default.Difference,
                    value = name,
                    valueChange = { name = it },
                    hint = "Name"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    leadingIcon = Icons.Default.Difference,
                    value = password,
                    valueChange = { password = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    isPassword = true,
                    hint = "Password"
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }
}