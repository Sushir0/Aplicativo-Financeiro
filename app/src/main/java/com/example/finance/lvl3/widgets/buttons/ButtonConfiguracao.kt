package com.example.finance.lvl3.widgets.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.activitys.abrirConfiguracao


@Composable
fun ButtonConfiguracao(color: Color = MaterialTheme.colorScheme.onBackground){
    val contexto = LocalContext.current
    IconButton(
        onClick = {
            abrirConfiguracao(contexto)
        },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(
            Icons.Outlined.Settings,
            contentDescription = "Configurações",
            modifier = Modifier.size(32.dp),
            tint = color
        )
    }
}