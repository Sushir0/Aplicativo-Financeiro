package com.example.finance.lvl3.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.Tipo

@Composable
fun ButtonEscolherTipo(tipo: Tipo, modifier: Modifier = Modifier, isSelected: Boolean = false, onClick: (Tipo) -> Unit) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    } else {
        Color.Transparent
    }
    OutlinedCard(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable { onClick(tipo) },
            contentAlignment = Alignment.Center
        ) {
            Text(text = tipo.toString(), modifier = Modifier.padding(8.dp))
        }
    }
}