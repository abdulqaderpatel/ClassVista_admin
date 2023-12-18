package com.example.classvista_admin.Authentication

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Hail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Components.AuthField
import com.example.classvista_admin.Models.Admin
import com.example.classvista_admin.Models.Authentication.ErrorResponse
import com.example.classvista_admin.R
import com.example.classvista_admin.Utils.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun Signup(navController: NavController = rememberNavController()) {
var loginClicked by remember {
    mutableStateOf(false)
}
    var organization by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = loginClicked) {
        if (loginClicked) {


            try {
                val response=RetrofitInstance.api.AdminSignup(Admin(organization, email, password))

                if (response.isSuccessful) {
                    Log.d("TAGGG",response.body().toString())
                    // Handle successful login response (e.g., save token, navigate to next screen)
                } else {

                }
            } catch (e: Exception) {
                // Handle exception (e.g., network error)
            } finally {
                loginClicked = false // Reset the click state after API call completion
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff1B232D))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.app_bg),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(Modifier.height(10.dp))
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = "Create Account", style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.W600, color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                AuthField(
                    leadingIcon = Icons.Default.Business,
                    value = organization,
                    valueChange = { organization = it },
                    hint = "Enter Organization name"
                )
                Spacer(modifier = Modifier.height(20.dp))
                AuthField(
                    leadingIcon = Icons.Default.Email,
                    value = email,
                    valueChange = { email = it },
                    hint = "Enter Email",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                AuthField(
                    leadingIcon = Icons.Default.Password,
                    value = password,
                    valueChange = { password = it },
                    hint = "Enter Password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        loginClicked=true;
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Create Account")
                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Signup()
}