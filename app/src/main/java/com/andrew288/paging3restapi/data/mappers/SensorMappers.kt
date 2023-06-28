package com.andrew288.paging3restapi.data.mappers

import com.andrew288.paging3restapi.data.local.SensorEntity
import com.andrew288.paging3restapi.data.remote.SensorDto
import com.andrew288.paging3restapi.domain.Sensor

fun SensorDto.toSensorEntity(): SensorEntity {
    return SensorEntity(
        id = id,
        date = date,
        dato = dato,
        comentario = comentario
    )
}

fun SensorEntity.toSensor(): Sensor {
    return Sensor(
        id = id,
        date = date,
        dato = dato,
        comentario = comentario
    )
}