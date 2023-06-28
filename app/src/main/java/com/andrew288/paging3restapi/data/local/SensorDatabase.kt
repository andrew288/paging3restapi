package com.andrew288.paging3restapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SensorEntity::class],
    version = 1
)
abstract class SensorDatabase: RoomDatabase() {
    abstract val dao: SensorDao

}