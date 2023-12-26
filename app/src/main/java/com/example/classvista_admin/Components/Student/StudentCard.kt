package com.example.classvista_admin.Components.Student

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.classvista_admin.Data.Student

@Preview(showBackground = true)
@Composable
fun StudentCard(
    student: Student = Student(
        id = "SBIT4023",
        name = "Abdul Qader Patel",
        admin_id = 2,
        course_id = 2,
        created_at = "dfd",
        updated_at = "dfds"
    ),
    onClick: () -> Unit = {},
    cardColor: Color = Color(0xff009100)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 30.dp)
            .fillMaxWidth()
            .clickable { onClick() }, colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "UID: ${student.id}",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            // Course Name as heading
            Text(
                text = "Name: ${student.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displayMedium.copy(color = Color.White)
            )


        }
    }
}
