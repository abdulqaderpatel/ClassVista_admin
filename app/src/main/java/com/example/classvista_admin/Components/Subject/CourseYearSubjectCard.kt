package com.example.classvista_admin.Components.Subject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classvista_admin.Models.CourseYear.Data

@Preview(showBackground = true)
@Composable
fun CourseYearSubjectCard(
    course: List<Data> = listOf(
        Data(id = "SBIT450", subject_name = "Web Programming"),
        Data(id = "SBIT450", subject_name = "Web Programming"),
        Data(id = "SBIT450", subject_name = "Web Programming")
    ),
    year: String = "First Year",
    onClick: () -> Unit = {},
    cardColor: Color = Color.Blue
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 30.dp)

            .clickable { onClick() }, colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Course Name as heading
            Text(
                text = year,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displayMedium.copy()
            )

            course.map {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(modifier = Modifier.defaultMinSize(minWidth = 100.dp)) {
                        Text(
                            text = it.id,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 4.dp, end = 4.dp)
                        )
                    }

                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = it.subject_name,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }


        }
    }
}

