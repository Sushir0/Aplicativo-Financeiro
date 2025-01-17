package com.example.finance.lvl3.componentes.Loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado

@Composable
fun ListaSimplesVerticalCarregando(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "Carregando")
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing), // Tempo da animação
            repeatMode = RepeatMode.Reverse // Anima indo e voltando
        ), label = "Carregando"
    )
    Column {
        var index = 0
        val tamanho = 6
        for(i in 0 until tamanho){
            Box(modifier.graphicsLayer { alpha = animatedAlpha }){
                ItemNullVertical()
                if(index < tamanho-1){
                    DivisorHorizontalPersonalizado()
                    index++
                }
            }
        }
    }
}

@Composable
private fun ItemNullVertical(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 22.dp),
    )
}

@Composable
fun ListaSimplesHorizontalCarregando(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "Carregando")
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.17f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing), // Tempo da animação
            repeatMode = RepeatMode.Reverse // Anima indo e voltando
        ), label = "Carregando"
    )
    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
        var index = 0
        val tamanho = 6
        for(i in 0 until tamanho){
            Box(modifier.graphicsLayer { alpha = animatedAlpha }
                .padding(6.dp)){
                ItemNullHorizontal()
            }
        }
    }
}
@Composable
fun ItemNullHorizontal() {
    Card() {
        Box(Modifier.background(MaterialTheme.colorScheme.onBackground)){
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 25.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ListaSimplesHorizontalCarragandoPrev() {
    ListaSimplesHorizontalCarregando()
}

@Preview
@Composable
private fun ListaSimplesVerticalCarregandoPrev() {
    ListaSimplesVerticalCarregando()
}

@Preview
@Composable
private fun ListasSimplesCarragandoPrev() {
    Column(){
        ListaSimplesHorizontalCarregando()
        ListaSimplesVerticalCarregando()
    }

}