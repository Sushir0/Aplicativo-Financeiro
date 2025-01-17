package com.example.finance.lvl3.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.Ordem
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo

@Composable
fun ListaDeFiltros(
    selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.RECEBIMENTO),
    periodo: Periodo? = null,
    ordem: Ordem = Ordem.MaisRecente,
    onClickTipo: () -> Unit = {},
    onClickPeriodo: () -> Unit = {},
    onClickOrdenarPor: () -> Unit = {},
    ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
    ) {
        Item(
            texto = selecaoUsuario.Nome,
            isSelected = false,
            onClick = onClickTipo
        )
        Item(
            texto = periodo?.nome ?: "Período",
            isSelected = false,
            onClick = onClickPeriodo
        )
        Item(
            texto = ordem.toString(),
            isSelected = false,
            onClick = onClickOrdenarPor
        )

    }

}

@Composable
private fun Item(
    texto: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    val background = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp),
        onClick = onClick

    ) {
        Row(
            modifier = Modifier
                .background(background),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp),
                text = texto,
                style = MaterialTheme.typography.bodySmall,
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Abrir detalhes",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(12.dp),

                )
        }
    }
}

@Preview
@Composable
private fun ListaDeFiltrosPreview() {
    ListaDeFiltros()
}