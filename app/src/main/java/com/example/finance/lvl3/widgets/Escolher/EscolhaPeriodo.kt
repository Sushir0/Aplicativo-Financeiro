package com.example.finance.lvl3.widgets.Escolher

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.b_useCase.PeriodoService

@Composable
fun ListaDuplaVerticalPeriodo(
    periodos: List<Periodo>?,
    periodoEscolhido: Periodo? = null,
    onPeriodoClick: (Periodo) -> Unit = {}
){
    var periodo by remember { mutableStateOf(periodoEscolhido) }
    if(periodos.isNullOrEmpty()){
        Text(text = "Nenhum periodo encontrado")
    }else{
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp)
        ) {
            items(periodos.size){
                val periodoAtual = periodos[it]
                Item(
                    isSelecionado = periodo == periodoAtual,
                    periodo = periodoAtual,
                    onClick = { periodoEscolhido ->
                        onPeriodoClick(periodoEscolhido)
                    }
                )
            }
        }
    }

}

@Composable
private fun Item(
    isSelecionado: Boolean = false,
    periodo: Periodo,
    onClick: (Periodo) -> Unit = {}
) {
    val backgroundColor = if (isSelecionado) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    } else {
        Color.Transparent
    }
    OutlinedCard(
        modifier = Modifier
            .padding(2.dp)
            .clickable { onClick(periodo) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                text = periodo.nome,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun ListaDuplaVerticalPeriodoPreview() {
    val periodos by remember{ mutableStateOf( PeriodoService().gerarPeriodosDoUltimoAno() ) }
    var periodoEscolhido by remember { mutableStateOf<Periodo?>(null) }
    ListaDuplaVerticalPeriodo(
        periodos = periodos,
        periodoEscolhido = periodoEscolhido,
        onPeriodoClick = {
            periodoEscolhido = it
        }
    )


}