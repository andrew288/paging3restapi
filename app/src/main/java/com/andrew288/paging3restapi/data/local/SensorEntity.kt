package com.andrew288.paging3restapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SensorEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val dato: Double,
    val comentario: String
)