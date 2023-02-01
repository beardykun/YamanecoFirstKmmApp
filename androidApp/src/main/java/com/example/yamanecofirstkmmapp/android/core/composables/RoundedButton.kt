package com.example.yamanecofirstkmmapp.android.core.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(50),
        modifier = modifier.padding(10.dp)
    ) {
        Text(text = label)
    }
}