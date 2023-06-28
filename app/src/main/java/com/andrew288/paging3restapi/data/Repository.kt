package com.andrew288.paging3restapi.data

import com.andrew288.paging3restapi.data.remote.SensorApi
import com.andrew288.paging3restapi.domain.Sensor
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: SensorApi
) {
    suspend fun createSensorData(sensor: Sensor) = api.createSensorData(sensor)
}