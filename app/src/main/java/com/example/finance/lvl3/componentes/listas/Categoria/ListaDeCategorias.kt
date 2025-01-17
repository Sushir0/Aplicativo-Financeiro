package com.example.finance.lvl3.componentes.listas.Categoria

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaMock
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado
import com.example.finance.ui.theme.corSetaRecebimento

@Composable
fun ListaDeCategorias(
    modifier: Modifier = Modifier,
    categorias: List<Categoria>?,
    onClick: (Categoria)->Unit
) {
    if(categorias.isNullOrEmpty()){
        listaCarregando(modifier)
    }else{
        LazyColumn {
            itemsIndexed(categorias) { index, Categoria ->
                Item(
                    categoria = Categoria,
                    onClick = onClick
                )
                if (index < categorias.size - 1) {
                    DivisorHorizontalPersonalizado()
                }
            }
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
    LazyColumn {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Item(categoria: Categoria, onClick: (Categoria)->Unit) {

    Box(
        modifier = Modifier
            .clickable { onClick(categoria) }
    )

    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                if (categoria.isActivate) {
                    Icon(
                        Icons.Outlined.Check,
                        contentDescription = "Categoria ativada",
                        tint = corSetaRecebimento
                    )
                }else{
                    Icon(
                        Icons.Outlined.Check,
                        contentDescription = "Categoria desativada",
                        tint = Color.Gray
                    )
                }
                Text(
                    text = categoria.nome,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                )

            }
            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "Ver Detalhes ->", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Composable
private fun ItemNull(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 22.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun ListaCategoriaPreview() {
    val categorias = listOf(
        CategoriaMock(),
    )

    ListaDeCategorias(categorias = categorias, onClick = {})


}