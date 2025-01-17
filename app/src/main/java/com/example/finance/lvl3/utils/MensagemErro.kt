package com.example.finance.lvl3.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MensagemErro(messages: List<String>) {
    Column {
        messages.forEach { message ->
            Text(
                text = message,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}