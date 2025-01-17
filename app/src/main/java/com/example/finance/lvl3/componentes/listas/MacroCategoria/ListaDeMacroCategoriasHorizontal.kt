package com.example.finance.lvl3.componentes.listas.MacroCategoria

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoriaMock
import com.example.finance.lvl3.componentes.Loading.ListaSimplesVerticalCarregando
import com.example.finance.lvl3.utils.avisoLongo
import kotlin.reflect.KProperty

@Composable
fun ListaDeMacroCategoriasHorizontal(
    macroCategorias: List<MacroCategoria>?,
    macroCategoriaEscolhida: MacroCategoria?,
    onMacroCategoriaClick: (MacroCategoria?) -> Unit,
) {
    if(macroCategorias.isNullOrEmpty()){
        Text(text = "Grupos de categorias não encontradas")
    }else {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(macroCategorias) { macroCategoria ->
                Item(
                    macroCategoria = macroCategoria,
                    isSelecionado = macroCategoria.equals(macroCategoriaEscolhida),
                    onMacroCategoriaClick = onMacroCategoriaClick
                )
            }
        }
    }


}

@Composable
private fun Item(
    macroCategoria: MacroCategoria,
    isSelecionado: Boolean = true,
    onMacroCategoriaClick: (MacroCategoria) -> Unit
) {
    val context = LocalContext.current
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }
    if (isSelecionado) {
        backgroundColor = MaterialTheme.colorScheme.primary
    } else {
        backgroundColor = Color.Transparent
    }
    ElevatedCard(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .clickable {
                    onMacroCategoriaClick(macroCategoria)
                }
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = macroCategoria.nome,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ListaDeMacroCategoriasHorizontalPreview() {
    var macroCategoriaEscolhida by remember { mutableStateOf<MacroCategoria?>(null) }
    val context = LocalContext.current

    val macroCategorias by remember {

        mutableStateOf(
            listOf(
                MacroCategoriaMock(nome = "Alimentação"),
                MacroCategoriaMock(nome = "Transporte"),
                MacroCategoriaMock(nome = "Educação"),
                MacroCategoriaMock(nome = "Saúde"),
                MacroCategoriaMock(nome = "Lazer"),
                MacroCategoriaMock(nome = "Outros"),
            )
        )
    }
    ListaDeMacroCategoriasHorizontal(
        macroCategorias = macroCategorias,
        macroCategoriaEscolhida = macroCategoriaEscolhida,
        onMacroCategoriaClick = { macroCategoria ->
            macroCategoriaEscolhida = macroCategoria
            //avisoLongo(context, "Clicou em ${macroCategoriaEscolhida!!.nome}")
        }
    )

}

