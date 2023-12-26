package com.example.classvista_admin.Main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Models.Admin.AdminCard
import com.example.classvista_admin.Navigation.Screen
import com.example.classvista_admin.ViewModels.UserViewModel


@Preview(showBackground = true)
@Composable
fun Home(navController: NavController = rememberNavController()) {

    LaunchedEffect(Unit) {
        var userViewModel=UserViewModel()

        Log.d("timepasstime", userViewModel.userId.value.token)
    }

    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(),
                    text = "Welcome Back",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray)
                )
                Text(
                    modifier = Modifier.padding(),
                    text = "Jai Hind College",
                    style = MaterialTheme.typography.displaySmall.copy()
                )
            }
        }
    }) {

        var adminCard = listOf<AdminCard>(
            AdminCard(
                primary = Color(0xff8769FF),
                secondary = Color(0xfffe60f5),
                icon = Icons.Filled.Person,
                label = "Student",
                route=Screen.StudentCourseList.route
            ),
            AdminCard(
                primary = Color(0xffef4136),
                secondary = Color(0xfffbb040),
                icon = Icons.Filled.School,
                label = "Teacher",
                route = Screen.TeacherList.route
            ),
            AdminCard(
                primary = Color(0xffFF0000),
                secondary = Color(0xffFF7878),
                icon = Icons.Filled.Subject,
                label = "Subject",
                route = Screen.SubjectList.route
            ),
            AdminCard(
                primary = Color(0xfffbb040),
                secondary = Color(0xff19ed32),
                icon = Icons.Filled.Newspaper,
                label = "Course",
                route = Screen.AddedCourses.route
            ),
            AdminCard(
                primary = Color(0xffff7db8),
                secondary = Color(0xffee2a7b),
                icon = Icons.Filled.Message,
                label = "Notice",
                route = Screen.AddedCourses.route
            ),
            AdminCard(
                primary = Color(0xffEF484E),
                secondary = Color(0xff651347),
                icon = Icons.Filled.Event,
                label = "Event",
                route = Screen.AddedCourses.route
            ),

            )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 100.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(adminCard) { admin ->
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .clickable { navController.navigate(admin.route) }

                                .background(
                                    shape = RoundedCornerShape(15.dp),
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            admin.primary, admin.secondary
                                        )
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    imageVector = admin.icon,
                                    contentDescription = "icon",
                                    tint = Color.White
                                )
                                Spacer(Modifier.height(3.dp))
                                Text(
                                    text = admin.label,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.White, fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}