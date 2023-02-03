package com.example.yamanecofirstkmmapp.android.resetPassword.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.composables.EditField
import com.example.yamanecofirstkmmapp.android.core.composables.RoundedButton
import com.example.yamanecofirstkmmapp.android.core.extensions.navigateWithDestroy
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
    Scaffold { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(paddingValues).fillMaxSize()
        ) {
            val context = LocalContext.current
            if (state.error != null) {
                Toast.makeText(context, state.error?.message.toString(), Toast.LENGTH_SHORT).show()
                onEvent(ResetPasswordEvent.OnErrorSeen)
            }
            if (state.navigateToHome) {
                LaunchedEffect(Unit) {
                    navController.navigateWithDestroy(Routes.LOGIN)
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
                label = StringRes.reset,
                onClick = {
                    onEvent(ResetPasswordEvent.OnResetPasswordClick)
                }
            )
        }
    }
}