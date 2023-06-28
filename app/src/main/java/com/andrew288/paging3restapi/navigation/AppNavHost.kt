package com.andrew288.paging3restapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import com.andrew288.paging3restapi.domain.Sensor
import com.andrew288.paging3restapi.presentation.HomeScreen
import com.andrew288.paging3restapi.presentation.SensorCreateScreen
import com.andrew288.paging3restapi.presentation.SensorListScreen
import com.andrew288.paging3restapi.presentation.SensorViewModel

@Composable
fun AppNavHost(
    sensors: LazyPagingItems<Sensor>,
    viewModel: SensorViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ScreenHome.route
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(Routes.ScreenHome.route){
            HomeScreen(navController)
        }
        composable(Routes.ScreenListSensor.route){
            SensorListScreen(sensors = sensors, navController)
        }
        composable(Routes.ScreenCreateSensor.route){
            SensorCreateScreen(navController, viewModel)
        }
    }
}