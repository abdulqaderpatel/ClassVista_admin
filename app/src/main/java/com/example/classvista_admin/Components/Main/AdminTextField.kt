package com.example.classvista_admin.Components.Main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTextField(
    leadingIcon: ImageVector,
    value: String,
    valueChange: (String) -> Unit,
    isPassword: Boolean = false,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
) {
    Text(
        modifier = Modifier.padding(start = 5.dp),

        text = hint,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
    )
    Spacer(modifier = Modifier.height(3.dp))
    TextField(
        value = value,
        onValueChange = valueChange, shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        label = {
            Text(
                text = "Enter $hint",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        },

        visualTransformation = if (!isPassword) VisualTransformation.None else (PasswordVisualTransformation()),


        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = "textfield icon")
        },
        keyboardOptions = keyboardOptions,

        colors = TextFieldDefaults.textFieldColors(

            cursorColor = Color.Blue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}