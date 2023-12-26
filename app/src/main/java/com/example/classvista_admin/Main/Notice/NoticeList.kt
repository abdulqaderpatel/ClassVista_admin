package com.example.classvista_admin.Main.Notice

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.navigation.NavController
import com.example.classvista_admin.Components.Main.PrimaryAppBar
import com.example.classvista_admin.Utils.RetrofitInstance
import com.example.classvista_admin.ViewModels.UserViewModel
import retrofit2.Retrofit
import java.io.ByteArrayInputStream

@Composable
fun NoticeList(navController: NavController, userViewModel: UserViewModel) {

    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(Unit) {
        Log.d("images", "timepass")
        val response = RetrofitInstance.noticeInterface.retrieveSinglePdf()

        Log.d("TAG", response.byteStream().toString())
        imageBitmap= response.bytes()?.let {
            val inputStream = ByteArrayInputStream(it)
            val bufferedImage = BitmapFactory.decodeStream(inputStream)
            bufferedImage?.asImageBitmap()
        }


    }

    Scaffold(topBar = { PrimaryAppBar(title = "Notices")}) { paddingValues ->
        Box(modifier=Modifier.padding(paddingValues))
        {
            imageBitmap?.let {
                Image(bitmap = it, contentDescription = "Generated Image")
            }
        }
    }

    fun createBitmapFromByteArray(imageData: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    }
}