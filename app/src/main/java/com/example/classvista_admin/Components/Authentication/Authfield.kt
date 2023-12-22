package com.example.classvista_admin.Components.Authentication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AuthField(
    leadingIcon: ImageVector,
    value: String,
    valueChange: (String) -> Unit,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
) {

    TextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                leadingIcon, contentDescription = null,
            )
        },

        value = value,
        onValueChange = valueChange,
        label = { Text(text = hint, color = Color.Gray) }, keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.White, focusedTextColor = Color.White,
            focusedContainerColor = Color(0xff242F3B),
            unfocusedContainerColor = Color(0xff242F3B),
        ),
    )
}



