package com.example.finance.lvl3.componentes.listas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.utils.isPortrait
import com.example.finance.lvl3.widgets.BotaoExpandirConteudo
import com.example.finance.ui.theme.backgroundGasto
import com.example.finance.ui.theme.backgroundRecebimento

@Composable
fun ListaDeMovimentacoes(movimentacoes : List<Movimentacao>) {
    var expandirConteudo by remember { mutableStateOf(true) }
    var modoVertical = isPortrait()


    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        if(modoVertical) {
            BotaoExpandirConteudo(
                expandirConteudo = expandirConteudo,
                onClick = { expandirConteudo = !expandirConteudo }
            )
        }else{
            expandirConteudo = false
        }
        LazyRow(modifier = Modifier.fillMaxWidth(),){
            items(movimentacoes){movimentacao ->
                if(expandirConteudo){
                    ItemDetalhado(movimentacao = movimentacao)
                }else{
                    ItemSimples(movimentacao = movimentacao)
                }
            }
        }




    }


}

@Composable
private fun ItemSimples(movimentacao: Movimentacao) {
    val corDoCard = if(movimentacao.isGasto){
        backgroundGasto
    }else{
        backgroundRecebimento
    }
    ElevatedCard (
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = 6.dp)
    ) {
        Box(modifier = Modifier.background(corDoCard)) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = movimentacao.assunto,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = movimentacao.valor.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun ItemDetalhado(movimentacao: Movimentacao) {
    val corDoCard = if(movimentacao.isGasto){
        backgroundGasto
    }else{
        backgroundRecebimento
    }
    ElevatedCard (
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = 6.dp)
    ) {
        Box(modifier = Modifier.background(corDoCard)) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = movimentacao.assunto,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = movimentacao.data.getDataString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = movimentacao.valor.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaDeGastosPreview() {
    testeCadastro()
    val gasto = Movimentacao("assunto", Data(15,10,2024), 3596.5)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    ListaDeMovimentacoes(
        movimentacoes = Login.getCasaLogada().gastos
    )
}

