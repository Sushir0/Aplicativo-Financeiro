package com.example.finance.lvl3.componentes.listas.Categoria

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado

@Composable
fun ListaDuplaCategoriaHorizontal(
    categorias: List<Categoria>?,
    onCategoriaClick: (Categoria) -> Unit,
    modifier: Modifier = Modifier,
    categoriaEscolhida: Categoria? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        verticalArrangement = Arrangement.Center
    ){
        if(categorias.isNullOrEmpty()){
            listaCarregando(modifier)
        }else{
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(4),
                modifier = modifier
            ) {
                items(categorias.size) {
                    Item(
                        isSelecionado = categoriaEscolhida == categorias[it],
                        categoria = categorias[it],
                        onCategoriaClick = onCategoriaClick
                    )
                }
            }
        }

    }
}
@Composable
fun ListaDuplaCategoriaVertical(
    categorias: List<Categoria>?,
    onCategoriaClick: (Categoria) -> Unit,
    modifier: Modifier = Modifier,
    categoriaEscolhida: Categoria? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        verticalArrangement = Arrangement.Center
    ){
        if(categorias.isNullOrEmpty()){
            Text("Categorias não encontradas")
        }else{
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
                modifier = modifier
            ) {
                items(categorias.size) {
                    Item(
                        isSelecionado = categoriaEscolhida == categorias[it],
                        categoria = categorias[it],
                        onCategoriaClick = onCategoriaClick
                    )
                }
            }
        }

    }
}

@Composable
private fun Item(
    isSelecionado: Boolean = false,
    categoria: Categoria,
    onCategoriaClick: (Categoria) -> Unit
) {
    val backgroundColor = if (isSelecionado) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    } else {
        Color.Transparent
    }
    OutlinedCard(
        modifier = Modifier
            .padding(2.dp)
            .clickable { onCategoriaClick(categoria) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                text = categoria.nome,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun listaCarregando(modifier: Modifier = Modifier){
    val infiniteTransition = rememberInfiniteTransition(label = "Carregando")
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing), // Tempo da animação
            repeatMode = RepeatMode.Reverse // Anima indo e voltando
        ), label = "Carregando"
    )
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        modifier = modifier
    ) {
        var index = 0
        val tamanho = 6
        items(tamanho){
            Box(modifier.graphicsLayer { alpha = animatedAlpha }){
                ItemNull()
                if(index < tamanho-1){
                    DivisorHorizontalPersonalizado()
                    index++
                }
            }
        }
    }
}

@Composable
private fun ItemNull(modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier
            .padding(8.dp)
    ){
        Box(modifier = Modifier)
    }

}