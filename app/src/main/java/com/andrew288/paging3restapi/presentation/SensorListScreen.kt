package com.andrew288.paging3restapi.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.andrew288.paging3restapi.domain.Sensor

@Composable
fun SensorListScreen(
    sensors: LazyPagingItems<Sensor>,
    navController: NavHostController
){
    val context = LocalContext.current
    LaunchedEffect(key1 = sensors.loadState){
        if(sensors.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (sensors.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(sensors.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "LISTA REGISTROS")
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(sensors) { sensor ->
                        if(sensor != null) {
                            SensorItem(
                                sensor = sensor,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    item {
                        if(sensors.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}