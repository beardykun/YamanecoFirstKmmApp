package com.example.yamanecofirstkmmapp.android.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yamanecofirstkmmapp.core.Colors

@Composable
fun RoundedButton(
    label: String,
    onClick: () -> Unit,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(Colors.LightBlue)
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = label,
            color = color
        )
    }
}