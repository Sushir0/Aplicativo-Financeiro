package com.example.finance.lvl3.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.converterDataMillisParaData


@Composable
fun BuscaDeDatas(
    onConfirm: (Data) -> Unit,
    modifier : Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var data by remember { mutableStateOf(converterDataMillisParaData(System.currentTimeMillis())) }
    val onDismiss = { showDialog = false }
    var textButton = data.toString()

    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        onClick = { showDialog = !showDialog }
    ) {
        Text(
            text = textButton,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(8.dp)
        )
    }
    if (showDialog) {
        BuscaDeDatasDialog(
            onConfirm = { dataSelecionada ->
                data = dataSelecionada
                onConfirm(dataSelecionada)
            },
            onDismiss = { showDialog = false },
        )
    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscaDeDatasDialog(
    onConfirm: (Data) -> Unit,
    onDismiss: () -> Unit,
) {
    val dateState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {

                val selectedDateMillis = dateState.selectedDateMillis ?: System.currentTimeMillis()

                val dataSelecionada = converterDataMillisParaData(
                    selectedDateMillis,
                    datePicker = (dateState.selectedDateMillis != null ||
                            dateState.selectedDateMillis?.let { converterDataMillisParaData(dataMillis = it).dia } == 1))


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