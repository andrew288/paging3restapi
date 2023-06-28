package com.andrew288.paging3restapi.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.andrew288.paging3restapi.data.local.SensorDatabase
import com.andrew288.paging3restapi.data.local.SensorEntity
import com.andrew288.paging3restapi.data.mappers.toSensorEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SensorRemoteMediator(
    private val sensorDb: SensorDatabase,
    private val sensorApi: SensorApi
): RemoteMediator<Int, SensorEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SensorEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null ){
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val sensors = sensorApi.getSensorData(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            sensorDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    sensorDb.dao.clearAll()
                }
                val sensorEntities = sensors.map { it.toSensorEntity() }
                sensorDb.dao.upserAll(sensorEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = sensors.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}