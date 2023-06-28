package com.andrew288.paging3restapi.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.andrew288.paging3restapi.data.local.SensorDatabase
import com.andrew288.paging3restapi.data.local.SensorEntity
import com.andrew288.paging3restapi.data.remote.SensorApi
import com.andrew288.paging3restapi.data.remote.SensorRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@OptIn(ExperimentalPagingApi::class)
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSensorDatabase(@ApplicationContext context: Context): SensorDatabase {
        return Room.databaseBuilder(
            context,
            SensorDatabase::class.java,
            "sensordata.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSensorApi(): SensorApi {
        return Retrofit.Builder()
            .baseUrl(SensorApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSensorPager(sensorDb: SensorDatabase, sensorApi: SensorApi): Pager<Int, SensorEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = SensorRemoteMediator(
                sensorDb = sensorDb,
                sensorApi = sensorApi
            ),
            pagingSourceFactory = {
                sensorDb.dao.pagingSource()
            }
        )
    }
}