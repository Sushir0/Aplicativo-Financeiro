package com.example.finance.lvl3.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.ui.theme.backgroundLista

@Composable
fun ListaDeMembros(casa: Casa) {

    var expandirConteudo by remember { mutableStateOf(true) }


    Column (
        modifier = Modifier,
        horizontalAlignment = Alignment.End
    ) {
        IconButton(
            onClick = { expandirConteudo = !expandirConteudo },
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .padding(8.dp)

        ) {
            if(expandirConteudo){
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = "Expandir detalhes",
                    tint = MaterialTheme.colorScheme.onBackground)
            }else{
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Expandir detalhes",
                    tint = MaterialTheme.colorScheme.onBackground)
            }
        }
        Box (
            modifier = Modifier
                .background(backgroundLista)
                .fillMaxWidth()){

            if(expandirConteudo){
                ListaDetalhada(casa = casa)
            }else{
                ListaSimples(casa = casa)
            }
        }
    }

}

@Composable
private fun ListaSimples(casa: Casa){
    LazyRow(modifier = Modifier
    ){
        items(casa.moradores){morador->
            ElevatedCard(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                ) {
                Text(
                    text = morador.nome,
                    modifier = Modifier
                        .padding(6.dp),
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun ListaDetalhada(casa: Casa){
    LazyRow(modifier = Modifier
    ){
        items(casa.moradores){morador->
            ElevatedCard(
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .padding(horizontal = 6.dp)) {
                Column (modifier = Modifier.padding(6.dp)){
                    Text(text = morador.nome,
                        modifier = Modifier
                            .padding(2.dp),
                        style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "Valor recebido: "+morador.recebimentos,
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Gasto total: "+morador.recebimentos,
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Valor de sobra: "+morador.recebimentos,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview
@Composable
fun ListaDeMembrosPreview() {
    testeCadastro()
    ListaDeMembros(Login.getCasaLogada())

}