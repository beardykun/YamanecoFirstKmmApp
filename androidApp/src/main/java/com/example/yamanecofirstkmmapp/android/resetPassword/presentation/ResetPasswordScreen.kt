package com.example.yamanecofirstkmmapp.android.resetPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.composables.EditField
import com.example.yamanecofirstkmmapp.android.core.composables.RoundedButton
import com.example.yamanecofirstkmmapp.core.StringRes
import com.example.yamanecofirstkmmapp.resetPassword.presentation.ResetPasswordEvent
import com.example.yamanecofirstkmmapp.resetPassword.presentation.ResetPasswordState

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    onEvent: (ResetPasswordEvent) -> Unit,
    state: ResetPasswordState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (state.onResetClicked) {
            LaunchedEffect(Unit) {
                navController.navigate(Routes.LOGIN)
                onEvent(ResetPasswordEvent.NavigateToHome)
            }
        }
        EditField(
            text = state.email,
            hint = StringRes.editEmail,
            onTextChanged = { value ->
                onEvent(ResetPasswordEvent.EditEmail(value))
            }
        )
        RoundedButton(
            label = "Reset",
            onClick = { onEvent(ResetPasswordEvent.OnResetPasswordClick) }
        )
    }
}