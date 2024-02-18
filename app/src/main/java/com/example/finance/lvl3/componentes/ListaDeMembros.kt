package com.example.finance.lvl3.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLista
import com.example.finance.ui.theme.contentBackground

@Composable
fun listaDeMembros(casa: Casa) {
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
                listaDetalhada(casa = casa)
            }else{
                listaSimples(casa = casa)
            }
        }
    }

}

@Composable
private fun listaSimples(casa: Casa){
    LazyRow(modifier = Modifier
    ){
        items(casa.moradores){morador->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(3.dp)) {
                Text(text = morador.nome,
                    modifier = Modifier
                        .padding(2.dp))
            }
        }
    }
}

@Composable
private fun listaDetalhada(casa: Casa){
    LazyRow(modifier = Modifier
    ){
        items(casa.moradores){morador->
            Card(shape = RoundedCornerShape(3.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp)) {
                Column (modifier = Modifier.padding(4.dp)){
                    Text(text = morador.nome,
                        modifier = Modifier
                            .padding(2.dp),
                        style = MaterialTheme.typography.titleLarge)
                    Text(text = "Valor recebido: "+morador.recebimentos)
                    Text(text = "Gasto total: "+morador.recebimentos)
                    Text(text = "Valor de sobra: "+morador.recebimentos)
                }
            }
        }
    }
}

@Preview
@Composable
fun listaDeMembrosPreview() {
    testeCadastro()
    listaDeMembros(Login.getCasaLogada())

}