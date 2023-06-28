package com.andrew288.paging3restapi.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SensorDao {

    @Upsert
    suspend fun upserAll(sensors: List<SensorEntity>)

    @Query("SELECT * FROM sensorentity")
    fun pagingSource(): PagingSource<Int, SensorEntity>

    @Query("DELETE FROM sensorentity")
    suspend fun clearAll()

}