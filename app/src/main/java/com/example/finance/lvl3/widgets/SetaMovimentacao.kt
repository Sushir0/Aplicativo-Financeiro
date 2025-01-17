package com.example.finance.lvl3.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.finance.R
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import com.example.finance.ui.theme.corSetaGasto
import com.example.finance.ui.theme.corSetaRecebimento

@Composable
fun SetaMovimentacao(
    isGasto: Boolean? = true,
    modifier: Modifier = Modifier) {
    val icon: Painter = when (isGasto) {
        true -> rememberVectorPainter(Icons.Sharp.KeyboardArrowUp) // Converte para Painter
        false -> rememberVectorPainter(Icons.Sharp.KeyboardArrowDown)
        null -> painterResource(id = R.drawable.baseline_autorenew_24)
    }
    val color = when(isGasto){
        true -> corSetaGasto
        false -> corSetaRecebimento
        null -> Color.Black
    }
    val descricao = when(isGasto){
        true -> "Gasto"
        false -> "Recebimento"
        null -> "Carregando"
    }
    val background = if(isSystemInDarkTheme()) backgroundDark else backgroundLight

    OutlinedCard(
        shape = CircleShape,
    ) {
        Box(modifier = Modifier.background(background)){
            Icon(
                modifier = modifier,
                painter = icon,
                contentDescription = descricao,
                tint = color
            )
        }
    }
}