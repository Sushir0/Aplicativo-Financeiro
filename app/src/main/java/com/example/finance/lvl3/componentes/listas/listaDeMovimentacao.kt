package com.example.finance.lvl3.componentes.listas

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.lvl3.utils.isPortrait
import com.example.finance.lvl3.utils.valorMonetario
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado
import com.example.finance.lvl3.widgets.SetaMovimentacao
import com.example.finance.lvl3.widgets.buttons.BotaoExpandirConteudo
import com.example.finance.ui.theme.FinanceTheme
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
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = VariaveisDeAmbiente.dampingRatioBouncy,
                    stiffness = VariaveisDeAmbiente.stiffness
                )
            )
        ){
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
                    text = movimentacao.descricao,
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
                    text = movimentacao.descricao,
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
    isAlways: Boolean = false,
    onItemClick: (Movimentacao) -> Unit = { }
) {
    LazyColumn (
        modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        itemsIndexed(movimentacoes){ index, movimentacao->
            ItemLista(
                movimentacao = movimentacao,
                isAlways = isAlways,
                onClick = { onItemClick(movimentacao) }
            )
            if(index < movimentacoes.size-1){
                Box(modifier = Modifier.padding(horizontal = 4.dp)){
                    DivisorHorizontalPersonalizado()
                }
            }

        }
    }
}

@Composable
private fun ItemLista(
    movimentacao: Movimentacao,
    onClick: (Movimentacao) -> Unit = { },
    isAlways: Boolean = true
) {
    Box(modifier = Modifier.clickable { onClick(movimentacao) }) {
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
                if (isAlways) {
                    SetaMovimentacao(isGasto = movimentacao.isGasto,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(32.dp))
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = movimentacao.descricao,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                    Text(
                        text = valorMonetario(movimentacao.valor),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                    Text(
                        text = movimentacao.data.toString(),
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
fun ListaDeGastosPreview() {
    /*
    LoginController().testeCadastro()
    CategoriaDebbug().gerarCategoriasBasicas()
    MovimentacaoController().testeAdicionarMovimentacao(Login.getCasaLogada())
    MovimentacaoController().testeAdicionarMovimentacao(Login.getCasaLogada())
    MovimentacaoController().testeAdicionarMovimentacao(Login.getCasaLogada())
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


     */
}

