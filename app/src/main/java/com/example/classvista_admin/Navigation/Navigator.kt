package com.example.classvista_admin.Navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classvista_admin.Authentication.Login
import com.example.classvista_admin.Authentication.Signup
import com.example.classvista_admin.DataStore.UserStore
import com.example.classvista_admin.Main.Home
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Navigator() {
    val navController= rememberNavController()
var detail by remember {
    mutableStateOf("")
}
    var preferenceDataStore= UserStore(LocalContext.current)
    LaunchedEffect(Unit){
       preferenceDataStore.getDetails().collect{
          detail=it.token
           Log.d("checking", detail)
        }

    }
    NavHost(navController =navController, startDestination =if(detail!="29|3173RCYvc5VtkCvafORDDID8OH1E4LaC2VSnSHrrc560bc34")"signup" else("home"))
    {
        composable("signup")
        {
            Signup(navController)
        }
        composable("login")
        {
            Login(navController)
        }
        composable("home")
        {
           Home(navController)
        }
    }
}