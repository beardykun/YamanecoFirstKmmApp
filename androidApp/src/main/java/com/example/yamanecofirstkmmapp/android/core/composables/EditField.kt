package com.example.yamanecofirstkmmapp.android.core.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditField(
    text: String,
    hint: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            onTextChanged.invoke(newValue)
        },
        shape = RoundedCornerShape(50.dp),
        placeholder = { Text(text = hint) },
        modifier = modifier.fillMaxWidth().padding(16.dp).padding(end = 40.dp)
    )
}