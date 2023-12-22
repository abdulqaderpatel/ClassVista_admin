package com.example.classvista_admin.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.classvista_admin.Models.Authentication.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class UserStore(context: Context) {

    var pref = context.dataStore
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("auth_token")

        var name = stringPreferencesKey("token")

    }

    suspend fun setValue(token: Token) {
        CoroutineScope(Dispatchers.IO).launch {
            pref.edit {
                it[name] = token.token
            }
        }

    }

    fun getDetails() = pref.data.map {
        Token(token = it[name] ?: "")
    }

}