package com.example.classvista_admin.Main.Student

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Data.SubjectInterface
import com.example.classvista_admin.Models.Student.StudentSignup
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddStudent(
    navController: NavController,
    userViewModel: UserViewModel, courseViewModel: CourseViewModel

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

    var selectedIndex by remember{
        mutableIntStateOf(0)
    }

    var expanded by remember{
        mutableStateOf(false)
    }

    var selectedYear by remember {
        mutableStateOf("FY")
    }
    var years = listOf("FY", "SY", "TY")
    var yearIndex = 1;


    var buttonLoading by remember {
        mutableStateOf(false)
    }
    var context= LocalContext.current
    Scaffold(topBar = { PrimaryAppBar(title = "Student") }, bottomBar = {
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
                    var response = RetrofitInstance.studentInterface.createStudentAccount(
                        "Bearer ${userViewModel.userId.value.token}",
                        StudentSignup(id,name,password,courseYear.body()!!.data.id)

                    )
                }






                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Student Added Successfully", Toast.LENGTH_LONG)
                        .show()
                }

                buttonLoading = false

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
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(start = 5.dp),

                    text = "Course",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                )
                Spacer(modifier = Modifier.height(3.dp))
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
                            "Selected: ${courseViewModel.courses[selectedIndex].name}",
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
                            courseViewModel.courses.forEachIndexed { index, text ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedIndex = index
                                        expanded = false
                                    }, text = {
                                        Text(text.name)
                                    }
                                )
                            }
                        }
                        TextButton(onClick = { expanded = true }) {
                            Text(text = "Select Course")
                        }
                    }
                }
                Spacer(modifier=Modifier.height(10.dp))
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
}