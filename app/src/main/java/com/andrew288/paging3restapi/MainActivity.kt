package com.andrew288.paging3restapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.andrew288.paging3restapi.navigation.AppNavHost
import com.andrew288.paging3restapi.presentation.SensorListScreen
import com.andrew288.paging3restapi.presentation.SensorViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ViewModelComponentBuilder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val viewModel = hiltViewModel<SensorViewModel>()
                val sensors = viewModel.sensorPagingFlow.collectAsLazyPagingItems()
                AppNavHost(sensors = sensors, viewModel)
            }
        }
    }
}