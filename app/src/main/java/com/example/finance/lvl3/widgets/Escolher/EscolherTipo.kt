package com.example.finance.lvl3.widgets.Escolher

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.lvl3.widgets.buttons.ButtonEscolherTipo
import kotlinx.coroutines.launch

@Composable
fun EscolherTipo(
    tipoEscolhido: Tipo = Tipo.TODOS,
    onEscolha: (tipo: Tipo) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ButtonEscolherTipo(
                tipo = Tipo.TODOS,
                onClick = {
                    onEscolha(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                isSelected = tipoEscolhido == Tipo.TODOS
            )
            ButtonEscolherTipo(
                tipo = Tipo.GASTO,
                onClick = {
                    onEscolha(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                isSelected = tipoEscolhido == Tipo.GASTO
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ButtonEscolherTipo(
                tipo = Tipo.RECEBIMENTO,
                onClick = {
                    onEscolha(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                isSelected = tipoEscolhido == Tipo.RECEBIMENTO
            )
        }
    }

}

@Preview
@Composable
private fun EscolherTipoPreview() {
    var tipoEscolhido by remember { mutableStateOf(Tipo.TODOS) }
    EscolherTipo(
        tipoEscolhido = tipoEscolhido,
        onEscolha = {tipoEscolhido = it}
    )
}