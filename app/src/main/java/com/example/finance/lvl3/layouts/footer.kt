package com.example.finance.lvl3.layouts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.utils.contemExclamacao
import com.example.finance.lvl3.utils.retirarExclamacao
import com.example.finance.lvl3.widgets.ButtonAdicionar

@Composable
fun Footer(datasUtilizadas : List<String>, openBottomSheetClick:  () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ButtonAdicionar(openBottomSheetClick)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start
        ) {
            datasUtilizadas.forEach({ Item(it) })
        }
    }
}

@Composable
private fun Item(texto : String) {
    /* Exclamação serve como uma flag para saber se é um ano
    ou um mês, para poder aplicar ao tipografia correta*/

    var style = if(texto.equals(retirarExclamacao(texto))){
        MaterialTheme.typography.titleSmall
    }else{
        MaterialTheme.typography.titleMedium
    }

    ElevatedCard (
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        )
    ){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(92.dp),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = retirarExclamacao(texto),
                modifier = Modifier.padding(vertical = 8.dp,),
                style = style
            )
        }
    }
}

@Preview
@Composable
fun FooterPrev() {
    var lista = mutableListOf<String>()
    lista.add("Ano atual")
    Footer(lista, {  })

}
