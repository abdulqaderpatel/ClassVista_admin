package com.example.classvista_admin.Main.Course

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.AdminTextField
import com.example.classvista_admin.Navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourse(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Add Course",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    })
    {
        var title by remember {
            mutableStateOf("")
        }

        var shortForm by remember {
            mutableStateOf("")
        }

        Box(modifier = Modifier.padding(it))
        {
            Column(modifier=Modifier.padding(10.dp)) {


                AdminTextField(leadingIcon = Icons.Default.Title, value =title , valueChange ={title=it} , hint ="Title")
                AdminTextField(leadingIcon = Icons.Default.Abc, value =shortForm , valueChange ={shortForm=it} , hint ="Short Form")
                ElevatedButton(onClick = { }) {
                    Text(text = "dsfds")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}