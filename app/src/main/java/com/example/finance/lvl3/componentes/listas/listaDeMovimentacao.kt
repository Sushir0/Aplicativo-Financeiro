package com.example.finance.lvl3.componentes.listas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
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
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao

import com.example.finance.lvl3.utils.isPortrait
import com.example.finance.lvl3.utils.valorMonetario
import com.example.finance.lvl3.widgets.BotaoExpandirConteudo
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundGasto
import com.example.finance.ui.theme.backgroundLight
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
    val corDoCard = if(movimentacao.isGasto()){
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
    val corDoCard = if(movimentacao.isGasto()){
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
                    text = movimentacao.data.toString(),
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

@Composable
fun NewListaDeMovimentacao(
    movimentacoes: List<Movimentacao>,
    isAlways: Boolean = false
) {
    LazyColumn (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        itemsIndexed(movimentacoes){ index, movimentacao->
            ItemLista(
                movimentacao = movimentacao,
                isAlways = isAlways
            )
            if(index < movimentacoes.size-1){
                Box(modifier = Modifier.padding(horizontal = 4.dp)){
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
    }
}

@Composable
private fun ItemLista(
    movimentacao: Movimentacao,
    onClick: ()->Unit = {  },
    isAlways: Boolean = false) {
    Box(modifier = Modifier.clickable { onClick() }){
        Row (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,

            ){
            Row (verticalAlignment = Alignment.CenterVertically){
                if(isAlways) {
                    SetaMovimentacao(isGasto = movimentacao.isGasto())
                }
                Column(modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 4.dp)) {
                    Text(
                        text = movimentacao.assunto,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = valorMonetario(movimentacao.valor),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                    Text(text = movimentacao.data.toString(),
                        style = MaterialTheme.typography.labelSmall)


                }
            }
            Text(text = "Ver Mais ->", color = MaterialTheme.colorScheme.onBackground)
        }
    }

}

@Composable
private fun SetaMovimentacao(isGasto: Boolean) {
    val icon = if(isGasto){ Icons.Sharp.KeyboardArrowUp }else{ Icons.Sharp.KeyboardArrowDown }
    val color = if(isGasto){ backgroundGasto }else{ backgroundRecebimento }
    val descricao = if (isGasto){ "Gasto"}else{ "Recebimento" }

    OutlinedCard(shape = CircleShape) {
        Icon(
            modifier = Modifier
                .padding(3.dp)
                .size(32.dp),
            imageVector = icon,
            contentDescription = descricao,
            tint = color
        )
        

    }
    
}



@Preview
@Composable
fun ListaDeGastosPreview() {
    testeCadastro()
    gerarCategoriasBasicas()
    testeAdicionarMovimentacao(Login.getCasaLogada())
    testeAdicionarMovimentacao(Login.getCasaLogada())
    testeAdicionarMovimentacao(Login.getCasaLogada())
    FinanceTheme (darkTheme = true){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewListaDeMovimentacao(
                movimentacoes = Login.getCasaLogada().getGastos(Periodo( ano = 2024))
            )
        }
    }

}

