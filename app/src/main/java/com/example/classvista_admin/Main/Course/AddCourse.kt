package com.example.classvista_admin.Main.Course


import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminButton
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Data.CourseCreation
import com.example.classvista_admin.Data.CourseCreationWithYears
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.CourseViewModel
import com.example.classvista_admin.ViewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourse(
    navController: NavController, userViewModel: UserViewModel, courseViewModel: CourseViewModel
) {
    var title by remember {
        mutableStateOf("")
    }

    var shortForm by remember {
        mutableStateOf("")
    }

    var years = listOf(1, 2, 3)

    var buttonLoading by remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Add Course",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }, bottomBar = {
        val context = LocalContext.current
        AdminButton(label = "Add Course", buttonLoading) {
            CoroutineScope(Dispatchers.IO).launch {
                buttonLoading = true
                var response = RetrofitInstance.courseInterface.CreateCourse(
                    "Bearer ${userViewModel.userId.value.token}",
                    CourseCreation(name = title, short_form = shortForm)
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
                android.os.Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Course Added Successfully", Toast.LENGTH_LONG).show()
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
                    leadingIcon = Icons.Default.Abc,
                    value = shortForm,
                    valueChange = { shortForm = it },
                    hint = "Short Form",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )


            }
        }
    }
}