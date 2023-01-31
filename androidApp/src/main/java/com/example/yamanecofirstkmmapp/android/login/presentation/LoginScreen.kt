package com.example.yamanecofirstkmmapp.android.login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
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
                        OutlinedTextField(
                            value = state.email,
                            onValueChange = { newValue ->
                                onEvent(LoginEvent.EditEmail(newValue))
                            },
                            shape = RoundedCornerShape(50.dp),
                            placeholder = { Text(text = "enter email") },
                            modifier = Modifier.fillMaxWidth().padding(16.dp).padding(end = 40.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = state.password,
                            onValueChange = { newValue ->
                                onEvent(LoginEvent.EditPassword(newValue))
                            },
                            shape = RoundedCornerShape(50.dp),
                            placeholder = { Text(text = "enter password") },
                            modifier = Modifier.fillMaxWidth().padding(16.dp).padding(end = 40.dp)
                        )
                    }
                    item {
                        Button(onClick = {
                            onEvent(LoginEvent.Login)
                        }) {
                            Text("Login")
                        }
                    }
                    item {
                        Button(onClick = {
                            onEvent(LoginEvent.Register)
                        }) {
                            Text("Register")
                        }
                    }
                }
        }
    }
}