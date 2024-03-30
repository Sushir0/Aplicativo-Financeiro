package com.example.finance.lvl3.componentes.formularios

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.converterDataMillisParaData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioData(
    onConfirm: (Data) -> Unit,
    onDismiss: () -> Unit
) {
    val dateState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {

                val selectedDateMillis = dateState.selectedDateMillis ?: System.currentTimeMillis()
                val dataSelecionada = converterDataMillisParaData(selectedDateMillis, datePicker = dateState.selectedDateMillis != null)

                onConfirm(dataSelecionada)

                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = dateState)
    }
}