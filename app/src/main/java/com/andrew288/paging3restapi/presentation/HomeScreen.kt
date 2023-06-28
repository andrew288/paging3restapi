package com.andrew288.paging3restapi.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andrew288.paging3restapi.navigation.Routes

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate(Routes.ScreenListSensor.route) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Listar registros de sensor")
        }

        Button(
            onClick = { navController.navigate(Routes.ScreenCreateSensor.route) },
        ) {
            Text(text = "Crear registro de sensor")
        }
    }

}