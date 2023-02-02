package com.example.yamanecofirstkmmapp.android.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.composables.EditField
import com.example.yamanecofirstkmmapp.android.core.composables.RoundedButton
import com.example.yamanecofirstkmmapp.core.StringRes
import com.example.yamanecofirstkmmapp.login.presinattion.LoginEvent
import com.example.yamanecofirstkmmapp.login.presinattion.LoginState

@Composable
fun LoginScreen(
    navController: NavController,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.userId != null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.HOME)
                }
            }
            if (state.navigateToResetPassword) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.RESET_PASSWORD)
                }
            }
            if (state.newRegistration) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.REGISTER)
                }
            }
            if (state.error != null) {
                Toast.makeText(context, state.error?.message.toString(), Toast.LENGTH_SHORT).show()
                onEvent(LoginEvent.OnErrorSeen)
            } else
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        EditField(
                            text = state.email,
                            hint = StringRes.editEmail,
                            onTextChanged = { newValue ->
                                onEvent(LoginEvent.EditEmail(newValue))
                            }
                        )
                    }

                    item {
                        EditField(
                            text = state.password,
                            hint = StringRes.editPassword,
                            onTextChanged = { newValue ->
                                onEvent(LoginEvent.EditPassword(newValue))
                            }
                        )
                    }
                    item {
                        Text(
                            "reset password",
                            modifier = Modifier.clickable {
                                navController.navigate(Routes.LOGIN)
                                onEvent(LoginEvent.ForgotPassword)
                            })
                    }
                    item {
                        RoundedButton(
                            label = StringRes.login,
                            onClick = { onEvent(LoginEvent.Login) }
                        )
                    }
                    item {
                        RoundedButton(
                            label = StringRes.register,
                            onClick = { onEvent(LoginEvent.Register) }
                        )
                    }
                }
        }
    }
}