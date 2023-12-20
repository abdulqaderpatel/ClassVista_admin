package com.example.classvista_admin.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Subject
import androidx.compose.material.icons.filled.TextRotationAngleup
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun Home(navController: NavController = rememberNavController()) {
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp), contentAlignment = Alignment.Center
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

        var primary =
            listOf(Color(0xff7f00ff), Color(0xffef4136), Color(0xffFF0000), Color(0xfffbb040))
        var primaryVariant =
            listOf(Color(0xffe100ff), Color(0xfffbb040), Color(0xffFF7878), Color(0xff19ed32))

        var icons = listOf(
            Icons.Filled.Person,
            Icons.Outlined.Book,
            Icons.Default.Subject,
            Icons.Default.Event
        )
        var labels = listOf(
            "Student",
            "Teacher",
            "Subject",
            "Event"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it), contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start=10.dp, end = 10.dp,top=100.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(primary)
                    { index, item ->
                        Box(
                            modifier = Modifier
                                .height(200.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            primary[index],
                                            primaryVariant[index]
                                        )
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(modifier=Modifier.size(30.dp),
                                    imageVector = icons[index],
                                    contentDescription = "icon", tint = Color.White
                                )
                                Spacer(Modifier.height(3.dp))
                                Text(
                                    text = labels[index],
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
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