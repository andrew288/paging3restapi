package com.andrew288.paging3restapi.data.remote

import com.andrew288.paging3restapi.domain.Sensor
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SensorApi {

    companion object {
        const val BASE_URL = "https://lab05danp-production.up.railway.app/api/"
    }

    @GET("sensor")
    suspend fun getSensorData(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<SensorDto>

    @POST("sensor")
    suspend fun createSensorData(@Body sensorData: Sensor): ResponseBody

}