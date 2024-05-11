package com.example.finance.lvl3.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

@Composable
fun ButtonAbrirFiltros(nomePeriodo: String, nomeCategoria: String, onClick: () -> Unit) {
    val background = if (isSystemInDarkTheme()) backgroundDark else backgroundLight
    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedCard (modifier = Modifier
            .padding(start = 16.dp, end = 32.dp, top = 24.dp)
            .clickable { onClick() }) {
            Box(modifier = Modifier.background(background)){
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically){
                    Column (Modifier.padding(8.dp)){
                        Text(text = "Exibindo: $nomeCategoria")
                        Text(text = "Periodo: $nomePeriodo")
                    }
                    Text(text = "Editar Filtros ->", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ButtonAbrirFiltrosPreview() {
    FinanceTheme {
        ButtonAbrirFiltros("Periodo", "Categoria") { }
    }
}