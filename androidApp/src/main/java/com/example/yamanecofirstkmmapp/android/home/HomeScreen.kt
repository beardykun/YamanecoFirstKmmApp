package com.example.yamanecofirstkmmapp.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.android.core.composables.RoundedButton
import com.example.yamanecofirstkmmapp.android.core.extensions.navigateWithDestroy
import com.example.yamanecofirstkmmapp.core.StringRes
import com.example.yamanecofirstkmmapp.home.presentation.HomeEvent
import com.example.yamanecofirstkmmapp.home.presentation.HomeState

@Composable
fun HomeScreen(
    navController: NavController,
    onEvent: (HomeEvent) -> Unit,
    state: HomeState
) {
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)
        ) {
            Text(text = state.user.id)
            Text(text = state.user.name)
            RoundedButton(
                label = StringRes.logout,
                color = Color.Black,
                onClick = {
                    onEvent(HomeEvent.LogOut)
                    navController.navigateWithDestroy(Routes.LOGIN)
                }
            )
        }
    }
}