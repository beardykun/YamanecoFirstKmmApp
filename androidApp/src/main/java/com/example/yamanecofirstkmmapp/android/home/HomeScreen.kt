package com.example.yamanecofirstkmmapp.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yamanecofirstkmmapp.android.core.Routes
import com.example.yamanecofirstkmmapp.home.presentation.HomeEvent
import com.example.yamanecofirstkmmapp.home.presentation.HomeState

@Composable
fun HomeScreen(
    navController: NavController,
    onEvent: (HomeEvent) -> Unit,
    state: HomeState
) {
    Scaffold {
        if (state.isLogOut) {
            LaunchedEffect(Unit) {
                navController.navigate(Routes.LOGIN)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)
        ) {
            Text(text = state.user.id)
            Text(text = state.user.name)
            Button(
                onClick = {
                    onEvent(HomeEvent.LogOut)
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "LogOut")
            }
        }
    }
}