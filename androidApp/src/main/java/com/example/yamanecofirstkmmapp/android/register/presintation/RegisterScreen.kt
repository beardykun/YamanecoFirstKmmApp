package com.example.yamanecofirstkmmapp.android.register.presintation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.composables.EditField
import com.example.yamanecofirstkmmapp.android.core.composables.RoundedButton
import com.example.yamanecofirstkmmapp.register.presentation.RegisterEvent
import com.example.yamanecofirstkmmapp.register.presentation.RegisterState
import com.example.yamanecofirstkmmapp.shared.localization.L
import com.example.yamanecofirstkmmapp.shared.localization.email
import com.example.yamanecofirstkmmapp.shared.localization.name
import com.example.yamanecofirstkmmapp.shared.localization.password
import com.example.yamanecofirstkmmapp.shared.localization.register

@Composable
fun RegisterScreen(
    navController: NavController,
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        val context = LocalContext.current
        Box(contentAlignment = Alignment.Center) {
            if (state.isRegistered) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.HOME)
                }
            }
            if (state.error != null) {
                Toast.makeText(context, state.error?.message, Toast.LENGTH_SHORT).show()
                onEvent(RegisterEvent.OnErrorSeen)
            } else
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        EditField(
                            text = state.name,
                            hint = L.registration.editText.name(),
                            onTextChanged = {
                                onEvent(RegisterEvent.EditName(it))
                            }
                        )
                    }
                    item {
                        EditField(
                            text = state.email,
                            hint = L.registration.editText.email(),
                            onTextChanged = {
                                onEvent(RegisterEvent.EditEmail(it))
                            }
                        )
                    }

                    item {
                        EditField(
                            text = state.password,
                            hint = L.registration.editText.password(),
                            onTextChanged = {
                                onEvent(RegisterEvent.EditPassword(it))
                            }
                        )
                    }
                    item {
                        RoundedButton(
                            label = L.registration.button.register(),
                            onClick = { onEvent(RegisterEvent.Register) }
                        )
                    }
                }
        }
    }
}