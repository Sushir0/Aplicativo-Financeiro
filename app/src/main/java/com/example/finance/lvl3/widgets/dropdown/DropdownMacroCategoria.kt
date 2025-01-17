package com.example.finance.lvl3.widgets.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.ui.theme.FinanceTheme

@Composable
fun DropdownMacroCategoria(
    expandedInicial : Boolean = false,
    macroCategorias : List<MacroCategoria>,
    macroCategoriaSelecionada : MacroCategoria?,
    modifier: Modifier = Modifier,
    enabled : Boolean = true,
    onChoice: (MacroCategoria) -> Unit,

    ) {
    var expandedMenu by remember { mutableStateOf<Boolean>(expandedInicial) }
    var textoBotao = macroCategoriaSelecionada?.nome ?: "Selecione uma macro categoria."
    val cor = if(!enabled) MaterialTheme.colorScheme.onSurface.copy(0.8f) else Color.Unspecified



    Column(modifier = modifier) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = { expandedMenu = true },
            enabled = enabled
        ) {
            Text(
                text = textoBotao,
                style = MaterialTheme.typography.titleSmall,
                color = cor
            )

        }
        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = {
                expandedMenu = false
            },
            modifier = modifier

        ) {
            macroCategorias.forEach { macroCategoria ->
                DropdownItem(
                    macroCategoria = macroCategoria,
                    onChoice = {
                        onChoice(macroCategoria)
                        expandedMenu = false
                    }
                )
            }
        }
    }

}

@Composable
private fun DropdownItem(
    macroCategoria : MacroCategoria,
    onChoice: (MacroCategoria) -> Unit = {},
) {
    DropdownMenuItem(
        text = { Text(
            text = macroCategoria.nome,
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            onChoice(macroCategoria)
        },
    )
}

@Preview
@Composable
private fun DropdownCategoriaPrev() {
    /*
    FinanceTheme {
        var flag by remember {
            mutableStateOf(true)
        }
        if (flag){
            CategoriaDebbug().gerarCategoriasBasicas()
            flag = false
        }
        var macroCategoriaSelecionada by remember { mutableStateOf<MacroCategoria?>(null) }
        DropdownMacroCategoria(
             macroCategorias = getCasa().getMacroCategorias(),
            macroCategoriaSelecionada = macroCategoriaSelecionada,
            onChoice = {
                macroCategoriaSelecionada = it
            }
        )


    }

     */
}
