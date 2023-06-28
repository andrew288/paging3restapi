package com.andrew288.paging3restapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.andrew288.paging3restapi.data.Repository
import com.andrew288.paging3restapi.data.local.SensorEntity
import com.andrew288.paging3restapi.data.mappers.toSensor
import com.andrew288.paging3restapi.data.remote.SensorApi
import com.andrew288.paging3restapi.domain.Sensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val repository: Repository,
    pager: Pager<Int, SensorEntity>,
): ViewModel(){
    val sensorPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toSensor() }
        }
        .cachedIn(viewModelScope)

    fun enviarRegistroSensor(sensor: Sensor){
        viewModelScope.launch {
            repository.createSensorData(sensor)
        }
    }
}