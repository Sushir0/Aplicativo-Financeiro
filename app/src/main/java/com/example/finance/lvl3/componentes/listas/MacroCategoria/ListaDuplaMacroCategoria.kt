import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.lvl3.componentes.listas.Categoria.ListaDuplaCategoriaHorizontal
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado

@Composable
fun ListaDuplaMacroCategoria(
    macroCategorias: List<MacroCategoria>?,
    onMacroCategoriaClick: (MacroCategoria?) -> Unit,
    modifier: Modifier = Modifier,
    macroCategoriaEscolhida: MacroCategoria? = null,
    floatRange: Float = 0.3f
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if(macroCategorias.isNullOrEmpty()){
            listaCarregando(modifier)
        }else{
            LazyHorizontalStaggeredGrid(
                rows = StaggeredGridCells.Fixed(2),
                modifier = modifier
            ) {
                item {
                    Item(
                        isSelecionado = macroCategoriaEscolhida == null,
                        macroCategoria = null,
                        onMacroCategoriaClick = onMacroCategoriaClick
                    )
                }
                items(macroCategorias.size) {
                    Item(
                        isSelecionado = macroCategoriaEscolhida == macroCategorias[it],
                        macroCategoria = macroCategorias[it],
                        onMacroCategoriaClick = onMacroCategoriaClick,

                    )
                }
            }
        }
    }
}

@Composable
private fun Item(
    macroCategoria: MacroCategoria?,
    isSelecionado: Boolean = false,
    onMacroCategoriaClick: (MacroCategoria?) -> Unit
) {
    val backgroundColor = if (isSelecionado) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    } else {
        Color.Transparent
    }
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onMacroCategoriaClick(macroCategoria) }

    ) {
        Box(modifier = Modifier.background(backgroundColor)){
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = macroCategoria?.nome ?: "Todos",
                    style = MaterialTheme.typography.bodyLarge,
                )
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


@Preview
@Composable
private fun ListaDuplaMacroCategoriaPreview() {
    
    val macroCategorias : List<MacroCategoria>? = null
    val categorias : List<Categoria>? = null

    Column(
        modifier = Modifier.padding(30.dp)
    ){
        OutlinedCard(
        ){
            ListaDuplaMacroCategoria(
                macroCategorias = macroCategorias,
                onMacroCategoriaClick = {}
            )
            Card(
                modifier = Modifier.padding(10.dp)
            ) {
                ListaDuplaCategoriaHorizontal(
                    categorias = categorias,
                    onCategoriaClick = {}
                )
            }
        }
    }

}