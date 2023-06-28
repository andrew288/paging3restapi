package com.andrew288.paging3restapi.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andrew288.paging3restapi.components.DatePickerCustom
import com.andrew288.paging3restapi.domain.Sensor
import com.andrew288.paging3restapi.navigation.Routes
import okhttp3.Route

@Composable
fun SensorCreateScreen(navController: NavHostController, sensorViewModel: SensorViewModel) {

    val datoSensor = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }
    val comentario = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TITULO
        Text(text = "Crear registro de sensor")
        Spacer(modifier = Modifier.height(20.dp))

        // DATO SENSOR
        TextField(value = datoSensor.value, onValueChange = {
            datoSensor.value = it
        }, label = { Text(text = "Dato del sensor")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
        Spacer(modifier = Modifier.height(16.dp))

        // FECHA DE REGISTRO
        DatePickerCustom(fecha = date)
        Spacer(modifier = Modifier.height(16.dp))

        // COMENTARIO
        TextField(value = comentario.value, onValueChange = {
            comentario.value = it
        }, label = { Text(text = "Comentario")})
        Spacer(modifier = Modifier.height(16.dp))

        // Botones
        Row() {
            Button(
                onClick = { navController.navigate(Routes.ScreenHome.route) },
            ) {
                Text(text = "Cancelar")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(
                onClick = {
                    if(datoSensor.value.isEmpty() || date.value.isEmpty()){
                        Toast.makeText(context, "Datos incompletos", Toast.LENGTH_LONG).show()
                    } else {
                        try{
                            val sensorDouble = datoSensor.value.toDouble()
                            val fechaRegitro = date.value
                            val comentarioRegistro = comentario.value

                            val sensorNew = Sensor(dato = sensorDouble, date = fechaRegitro,comentario = comentarioRegistro )
                            sensorViewModel.enviarRegistroSensor(sensorNew)

                            navController.navigate(Routes.ScreenHome.route)
                        } catch (e: NumberFormatException){
                            Toast.makeText(context, "Dato de sensor invalido", Toast.LENGTH_LONG).show()
                        }
                    }
                },
            ) {
                Text(text = "Crear")
            }
        }
    }
}