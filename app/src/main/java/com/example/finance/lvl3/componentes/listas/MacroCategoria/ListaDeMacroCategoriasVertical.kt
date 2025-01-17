package com.example.finance.lvl3.componentes.listas.MacroCategoria

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.lvl3.componentes.Loading.ListaSimplesVerticalCarregando
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado


@Composable
fun ListaDeMacroCategoriasVertical(
    modifier: Modifier = Modifier,
    macroCategorias: List<MacroCategoria>?,
    onClick: (MacroCategoria)->Unit
    ) {
    if(macroCategorias.isNullOrEmpty()){
        ListaSimplesVerticalCarregando()
    }else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
        ){
            itemsIndexed(macroCategorias) { index, macroCategoria ->
                Item(
                    macroCategoria = macroCategoria,
                    onClick = onClick
                )
                if (index < macroCategorias.size - 1) {
                    DivisorHorizontalPersonalizado()
                }
            }
        }
    }
}

@Composable
private fun Item(macroCategoria: MacroCategoria, onClick: (MacroCategoria)->Unit) {
    val textoIsGasto = if(macroCategoria.isGasto) "Gasto" else "Recebimento"
    val textoAfetaPessoa = if(macroCategoria.afetaPessoa) "Afeta Pessoa" else "Não afeta Pessoa"
    val textoAfetaCasa = if(macroCategoria.afetaCasa) "Afeta Casa" else "Não afeta Casa"
    Box(
        modifier = Modifier
            .clickable { onClick(macroCategoria) },
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = macroCategoria.nome,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                    Text(
                        text = textoIsGasto,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                    Text(
                        text = textoAfetaCasa,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                    Text(
                        text = textoAfetaPessoa,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "Ver Mais ->", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }

}

@Preview
@Composable
private fun ListaDeMacroCategoriasPreview() {
    /*
    LoginController().testeCadastro()
    CategoriaDebbug().gerarCategoriasBasicas()
    ListaDeMacroCategorias(
        macroCategorias = MacroCategoriaController().getMacroCategorias(),
        onClick = {  }
    )

     */
}