package com.example.finance.lvl3.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.finance.ui.theme.backgroundGasto
import com.example.finance.ui.theme.backgroundRecebimento
import com.example.finance.ui.theme.contentBackground

@Composable
fun ButtonAlternar_C_Icone(
    texto: String,
    modifier: Modifier = Modifier,
    isTrue: Boolean?,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    icone: @Composable () -> Unit,
){
    val backgroundColor = when(isTrue){
        true -> backgroundRecebimento
        false -> backgroundGasto
        null -> contentBackground
    }

            OutlinedCard(
            modifier = modifier
                .then(if (enabled) Modifier.clickable { onClick() } else Modifier.alpha(0.7f))
            ) {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = texto,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(4.dp)
            )
            icone()
        }
    }
}
