package com.example.finance.lvl3.componentes.listas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.Dados.ParcelaMock
import com.example.finance.lvl3.componentes.Loading.ListaSimplesVerticalCarregando
import com.example.finance.lvl3.utils.valorMonetario
import com.example.finance.lvl3.widgets.SetaMovimentacao

@Composable
fun ListaDeParcelas(
    parcelas: List<Parcela>?,
    showSeta: Boolean = true,
    onItemClick: (Parcela) -> Unit = { },
) {
    if(parcelas.isNullOrEmpty()){
        ListaSimplesVerticalCarregando()
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            itemsIndexed(parcelas) { index, parcela ->
                Item(
                    parcela = parcela,
                    showSeta = showSeta,
                    onClick = { onItemClick(parcela) }
                )
            }
        }
    }
}

@Composable
private fun Item(
    parcela: Parcela,
    onClick: (Parcela) -> Unit = { },
    showSeta: Boolean = true
) {
    Box(modifier = Modifier.clickable { onClick(parcela) }) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (showSeta) {
                    SetaMovimentacao(
                        isGasto = parcela.isGasto,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(32.dp))
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = parcela.descricao,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                    Text(
                        text = valorMonetario(parcela.valor),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                    Text(
                        text = parcela.data.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "Ver Mais ->", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Preview
@Composable
private fun ListaParcelasPreview() {
    val parcelas = listOf(
        ParcelaMock(),
        ParcelaMock(),
        ParcelaMock(),
        ParcelaMock(),
        ParcelaMock(),
    )

    ListaDeParcelas(parcelas = parcelas)
}