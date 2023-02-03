package com.example.yamanecofirstkmmapp.android.start.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.extensions.navigateWithDestroy
import com.example.yamanecofirstkmmapp.start.presentation.Status

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: AndroidStartViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LaunchedEffect(Unit) {
            viewModel.viewModel.redirectToCorrectScreen()
        }
        when (state.status) {
            Status.NOT_LOGGED_IN -> {
                LaunchedEffect(Unit) {
                    navController.navigateWithDestroy(
                        Routes.LOGIN
                    )
                }
            }
            Status.LOGGED_IN -> {
                LaunchedEffect(Unit) {
                    navController.navigateWithDestroy(
                        Routes.HOME
                    )
                }
            }
            Status.NONE -> {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "app logo"
                )
            }
        }
    }
}