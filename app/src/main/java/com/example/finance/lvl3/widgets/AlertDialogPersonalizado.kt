package com.example.finance.lvl3.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertPersonalizado(
    title: String,
    message: String,
    confirmButtonText: String,
    cancelButtonText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    tem3Botoes: Boolean = false,
    terceiroBotaoTexto: String = "",
    terceiroBotaoFuncao: () -> Unit = {}

) {
    AlertDialog(
        title = { Text(title) },
        text = { Text(message) },
        onDismissRequest = { onCancel() },
        confirmButton = {
            Row(){
                if(tem3Botoes){
                    TextButton(
                        onClick = { terceiroBotaoFuncao() }) {
                        Text(terceiroBotaoTexto)
                    }
                }
                TextButton(
                    onClick = { onConfirm() }) {
                    Text(confirmButtonText)
                }
            }

        },
        dismissButton = {
            TextButton(
                onClick = { onCancel() }) {
                Text(cancelButtonText)
            }
        },


        )

}