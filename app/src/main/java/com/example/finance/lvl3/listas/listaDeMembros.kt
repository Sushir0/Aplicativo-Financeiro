package com.example.finance.lvl3.listas
import androidx.compose.foundation.clickable
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
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.componentes.BotaoExpandirConteudo
import com.example.finance.lvl3.componentes.isPortrait

@Composable
fun ListaDeMembros(casa: Casa) {
    var expandirConteudo by remember { mutableStateOf(true) }
    var modoVertical = isPortrait()


    Column (
        modifier = Modifier,
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
            items(casa.moradores){residente ->
                if(expandirConteudo){
                    ItemDetalhado(residente = residente)
                }else{
                    ItemSimples(residente = residente)
                }
            }
        }
    }

}

@Composable
private fun ItemSimples(residente: Pessoa) {
    ElevatedCard(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable(onClick = {

            }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Text(
            text = residente.nome,
            modifier = Modifier
                .padding(6.dp),
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun ItemDetalhado(residente: Pessoa) {
    ElevatedCard(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = 6.dp)) {
        Column (modifier = Modifier.padding(6.dp)){
            Text(text = residente.nome,
                modifier = Modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Valor recebido: "+residente.recebimentos,
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Gasto total: "+residente.recebimentos,
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Valor de sobra: "+residente.recebimentos,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview
@Composable
fun ListaDeMembrosPreview() {
    testeCadastro()
    ListaDeMembros(Login.getCasaLogada())

}