package com.example.classvista_admin.Components.Teacher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.Teacher.Data
import com.example.classvista_admin.Models.Teacher.TeacherSubject


@Preview(showBackground = true)
@Composable
fun TeacherEnrolledInSubjects(

    index: Int = 0,
    teacherSubjects: Data =Data(id = "SBIT393", name = "Shruti Shah", subjects = listOf(
        TeacherSubject(id = "SBIT304", subject_name = "Computer organization"),
        TeacherSubject(id = "SBIT305", subject_name = "Mobile Application Development")
    )),

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Course Name as heading
            Text(
                text = teacherSubjects.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subjects listed underneath each course
            teacherSubjects.subjects.map {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(modifier=Modifier.defaultMinSize(minWidth = 100.dp)) {
                        Text(
                            text = it.id,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 4.dp, end = 4.dp)
                        )
                    }

                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = it.subject_name,
                        fontSize = 16.sp,
                        color = Color.Blue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }


        }
    }
}