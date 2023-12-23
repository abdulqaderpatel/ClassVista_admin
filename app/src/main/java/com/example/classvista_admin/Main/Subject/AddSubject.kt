package com.example.classvista_admin.Main.Subject

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Components.Main.LargeDropdownMenu
import com.example.classvista_admin.Data.CourseCreation
import com.example.classvista_admin.Data.CourseCreationWithYears
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubject(
    navController: NavController,
    userViewModel: UserViewModel,
    courseViewModel: CourseViewModel
) {

    var title by remember {
        mutableStateOf("")
    }

    var courseId by remember {
        mutableStateOf("")
    }

    var years = listOf(1, 2, 3)

    var buttonLoading by remember {
        mutableStateOf(false)
    }
    var dataLoading by remember {
        mutableStateOf(true)
    }




    LaunchedEffect(Unit) {
        if (!courseViewModel.coursesLoaded.value) {
            courseViewModel.coursesLoaded.value = true
            var token = userViewModel.userId.value.token
            courseViewModel.courses.addAll(
                RetrofitInstance.courseInterface.GetAllCourses("Bearer $token").body()!!.data
            )

            dataLoading = false

        }
    }


    var courses = mutableStateListOf<String>()
    courseViewModel.courses.map {
        courses.add(it.name)
        Log.d("dsfdsfd", courses.toString())
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }


    if (!dataLoading)
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Subject",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        }, bottomBar = {
            val context = LocalContext.current
            AdminButton(label = "Add Subject", buttonLoading) {
                CoroutineScope(Dispatchers.IO).launch {
                    buttonLoading = true
                    var response = RetrofitInstance.courseInterface.CreateCourse(
                        "Bearer ${userViewModel.userId.value.token}",
                        CourseCreation(name = title, short_form = courseId)
                    )
                    var courseId = response.body()!!.data.id

                    courseViewModel.courses.add(response.body()!!.data)
                    Log.d("TAGGGG", response.body().toString())

                    for (element in years) {
                        RetrofitInstance.courseyearInterface.CreateCourseWithYearsAssociated(
                            "Bearer ${userViewModel.userId.value.token}",
                            CourseCreationWithYears(courseId, element)
                        )

                    }
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Course Added Successfully", Toast.LENGTH_LONG)
                            .show()
                    }

                    buttonLoading = false

                }

            }
        }) { it ->
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxHeight()
                ) {


                    AdminTextField(
                        leadingIcon = Icons.Default.Title,
                        value = title,
                        valueChange = { title = it },
                        hint = "Title"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    AdminTextField(
                        leadingIcon = Icons.Default.RadioButtonChecked,
                        value = courseId,
                        valueChange = { courseId = it },
                        hint = "Course Id",
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                    Column {
                        Text(
                            "Selected: ${courses[selectedIndex]}",
                            modifier = Modifier.padding(16.dp)
                        )

                        // Dropdown menu
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color(0xffE7E0EC))
                        ) {
                            // IconButton that toggles the dropdown menu
                            IconButton(onClick = { expanded = true }) {
                                Text("Open Dropdown")
                            }

                            // DropdownMenu displays a list of DropdownMenuItem
                            DropdownMenu(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xffE7E0EC)),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                courses.forEachIndexed { index, text ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedIndex = index
                                            expanded = false
                                        }, text = {
                                            Text(text)
                                        }
                                    )
                                }
                            }
                        }
                    }


                }
            }
        }
}


