package com.example.classvista_admin.Main.Subject

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Data.CourseCreation
import com.example.classvista_admin.Data.CourseCreationWithYears
import com.example.classvista_admin.Data.SubjectInterface
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

    var subjectId by remember {
        mutableStateOf("")
    }

    var selectedYear by remember {
        mutableStateOf("FY")
    }
    var years = listOf("FY", "SY", "TY")
    var yearIndex = 1;

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


        }
        dataLoading = false
    }


    var courses = mutableStateListOf<String>()
    courseViewModel.courses.map {
        courses.add(it.name)
        Log.d("dsfdsfd", courses.toString())
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }



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
                val courseYear = RetrofitInstance.courseyearInterface.getUniqueCourseYear(
                    "Bearer ${userViewModel.userId.value.token}",
                    courseViewModel.courses[selectedIndex].id,
                    yearIndex
                )
                Log.d("Course year", courseYear.body().toString())
                if(courseYear.isSuccessful) {
                    var response = RetrofitInstance.subjectInterface.CreateSubject(
                        "Bearer ${userViewModel.userId.value.token}",
                        SubjectInterface.Subject(
                            subjectId,
                            courseYear.body()!!.data.id,
                            title
                        )
                    )
                }






                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Subject Added Successfully", Toast.LENGTH_LONG)
                        .show()
                }

                buttonLoading = false

            }

        }
    }) { it ->
        if (!dataLoading) {

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
                        value = subjectId,
                        valueChange = { subjectId = it },
                        hint = "Subject Id",
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(start = 5.dp),

                        text = "Course",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()

                            .background(Color(0xffE7E0EC))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Selected: ${courses[selectedIndex]}",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                            )

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
                            TextButton(onClick = { expanded = true }) {
                                Text(text = "Select Course")
                            }


                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(start = 5.dp),

                        text = "Year",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        years.mapIndexed { index, year ->
                            ElevatedButton(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .height(38.dp),
                                onClick = {
                                    selectedYear = year
                                    yearIndex = index + 1
                                },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedYear == year) Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {

                                Text(
                                    text = year,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedYear == year) Color.White else (Color.Black)
                                )
                            }
                        }
                    }
                }
            }
        }
        else{
            Box(modifier = Modifier.fillMaxSize())
            {
                CircularProgressIndicator()
            }
        }
    }
}


