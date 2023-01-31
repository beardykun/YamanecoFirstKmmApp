package com.example.yamanecofirstkmmapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.home.AndroidHomeViewModel
import com.example.yamanecofirstkmmapp.android.home.HomeScreen
import com.example.yamanecofirstkmmapp.android.login.presentation.AndroidLoginViewModel
import com.example.yamanecofirstkmmapp.android.login.presentation.LoginScreen
import com.example.yamanecofirstkmmapp.android.register.presintation.AndroidRegisterViewModel
import com.example.yamanecofirstkmmapp.android.register.presintation.RegisterScreen
import com.example.yamanecofirstkmmapp.android.start.presentation.StartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                RegisterRoot()
            }
        }
    }
}

@Composable
fun RegisterRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.START
    ) {
        composable(
            route = Routes.START
        ) {
            StartScreen(navController = navController)
        }
        composable(
            route = Routes.REGISTER
        ) {
            val viewModel = hiltViewModel<AndroidRegisterViewModel>()
            val state by viewModel.state.collectAsState()
            RegisterScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(
            route = Routes.LOGIN,
        ) {
            val viewModel = hiltViewModel<AndroidLoginViewModel>()
            val state by viewModel.state.collectAsState()
            LoginScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(
            route = Routes.HOME,
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val viewModel = hiltViewModel<AndroidHomeViewModel>()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                navController = navController,
                onEvent = viewModel::onEvent,
                state = state
            )
        }
    }
}