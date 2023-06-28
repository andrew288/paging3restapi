package com.andrew288.paging3restapi.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.andrew288.paging3restapi.domain.Sensor


@Composable
fun SensorItem(
    sensor: Sensor,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = sensor.date,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = sensor.dato.toString(),
                fontStyle = FontStyle.Italic,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = sensor.comentario,
                fontStyle = FontStyle.Italic,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}