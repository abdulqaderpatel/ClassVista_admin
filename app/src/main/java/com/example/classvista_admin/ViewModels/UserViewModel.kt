package com.example.classvista_admin.ViewModels

import android.media.session.MediaSession.Token
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private var _userId = mutableStateOf(com.example.classvista_admin.Models.Token(""))
    val userId: State<com.example.classvista_admin.Models.Token> = _userId

    var timepass= mutableStateOf("")


    fun getId(id: String) {
        _userId.value.token = id
    }
}