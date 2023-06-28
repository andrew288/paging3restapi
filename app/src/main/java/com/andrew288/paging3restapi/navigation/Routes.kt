package com.andrew288.paging3restapi.navigation

sealed class Routes(val route: String) {
    object ScreenHome: Routes("HomeScreen")
    object ScreenListSensor: Routes("ListSensorScreen")
    object ScreenCreateSensor: Routes("CreateSensorScreen")
}
