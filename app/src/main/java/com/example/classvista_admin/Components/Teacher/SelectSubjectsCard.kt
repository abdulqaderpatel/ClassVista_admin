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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classvista_admin.Models.Course.Course
import com.example.classvista_admin.Models.CourseYear.Data
import com.example.classvista_admin.Models.Subject.Subject

@Preview(showBackground = true)
@Composable
fun SelectSubjectsCard(
    isSelected: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    index: Int = 0,
    subject: Subject = Subject(
        id = "BSCIT302",
        subject_name = "Web Programming",
        course_id = 34,
        teacher_id = "3"
    )
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = isSelected, onCheckedChange = onCheckedChange)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {


                    Text(
                        text = "${subject.id}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "${subject.subject_name}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }


        }
    }
}