package com.example.finance.lvl3.componentes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BotaoExpandirConteudo(
    expandirConteudo: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .width(32.dp)
            .height(32.dp)
            .padding(8.dp)

    ) {
        if(expandirConteudo){
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = "Expandir detalhes",
                tint = MaterialTheme.colorScheme.onBackground)
        }else{
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Expandir detalhes",
                tint = MaterialTheme.colorScheme.onBackground)
        }
    }
}