package com.example.classvista_admin.ViewModels

import android.media.session.MediaSession.Token
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel :ViewModel()
{
    var userId=mutableStateOf(com.example.classvista_admin.Models.Token(""))

    fun getId(id:String)
    {
        userId.value.token=id
    }
}