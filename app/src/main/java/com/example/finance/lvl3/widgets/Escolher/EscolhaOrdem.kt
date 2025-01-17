package com.example.finance.lvl3.widgets.Escolher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.Ordem

@Composable
fun EscolhaOrdem(
    ordem: Ordem = Ordem.MaisRecente,
    onEscolha: (Ordem) -> Unit = {}
) {
    Column {
        Item(texto = Ordem.MaisRecente.toString(), isSelecionado = ordem == Ordem.MaisRecente) {
            onEscolha(Ordem.MaisRecente)
        }
        Item(texto = Ordem.MaisAntigo.toString(), isSelecionado = ordem == Ordem.MaisAntigo) {
            onEscolha(Ordem.MaisAntigo)
        }
        Item(texto = Ordem.MaiorValor.toString(), isSelecionado = ordem == Ordem.MaiorValor) {
            onEscolha(Ordem.MaiorValor)
        }
        Item(texto = Ordem.MenorValor.toString(), isSelecionado = ordem == Ordem.MenorValor) {
            onEscolha(Ordem.MenorValor)
        }
    }

}

@Composable
private fun Item(
    texto: String,
    isSelecionado: Boolean,
    onClick: () -> Unit
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,

    ){
        RadioButton(
            selected = isSelecionado,
            onClick = { onClick() },
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(text = texto)
    }
}

@Preview
@Composable
private fun EscolhaOrdemPreview() {
    var ordem by rememberSaveable {
        mutableStateOf(Ordem.MaisRecente)
    }
    EscolhaOrdem(
        ordem = ordem,
        onEscolha = { ordem = it }
    )
}