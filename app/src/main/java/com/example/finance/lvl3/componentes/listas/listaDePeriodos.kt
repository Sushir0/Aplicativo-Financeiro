package com.example.finance.lvl3.componentes.listas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl3.utils.retirarExclamacao


@Composable
fun ListaDePeriodos(periodos: List<Periodo>, periodoSelecionado: Periodo, onChoice: (Periodo) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start
    ) {
        periodos.forEach {
            Item(
                it,
                isSelected = it == periodoSelecionado
            ) {
                onChoice(it)
            }
            println("periodo: ")
            println(it.toString())
            println("  ${periodoSelecionado.toString()}")
        }
    }
}

@Composable
private fun Item(periodo: Periodo, isSelected: Boolean = false, onClick: (Periodo)->Unit) {

    var style = if(periodo.isAno){
        MaterialTheme.typography.titleMedium
    }else{
        MaterialTheme.typography.titleSmall
    }
    val backgroundColor = if (isSelected) Color.Blue else Color.Transparent

    ElevatedCard (
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        ),
        modifier = Modifier.clickable { onClick(periodo) }
    ){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(92.dp)
            .background(backgroundColor),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = periodo.nome,
                modifier = Modifier.padding(vertical = 8.dp,),
                style = style
            )
        }
    }
}

@Preview
@Composable
private fun ListaDePeriodosPrev() {
    val periodos = remember{ mutableListOf<Periodo>() }
    periodos.add(Periodo(ano = 10))

    val periodoSelecionado = remember {
        mutableStateOf(Periodo(ano = 5))
    }
    ListaDePeriodos(periodos = periodos,
        periodoSelecionado = periodoSelecionado.value,
        onChoice = { periodoSelecionado.value = it }
    )
}