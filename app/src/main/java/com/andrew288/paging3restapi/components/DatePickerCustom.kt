package com.andrew288.paging3restapi.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerCustom(
    fecha: MutableState<String>
) {
    val anio: Int
    val mes: Int
    val dia: Int
    val mCalendar: Calendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { DatePicker, anio: Int, mes: Int, dia: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(anio, mes, dia)
            selectedDate.set(Calendar.HOUR_OF_DAY, 0)
            selectedDate.set(Calendar.MINUTE, 0)
            selectedDate.set(Calendar.SECOND, 0)
            val formattedDate = dateFormat.format(selectedDate.time)
            fecha.value = formattedDate
        }, anio, mes, dia
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = fecha.value,
                onValueChange = { fecha.value = it },
                readOnly = true,
                label = { Text(text = "Fecha de inscripción") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                mDatePickerDialog.show() })
                }
            )
        }
    }
}