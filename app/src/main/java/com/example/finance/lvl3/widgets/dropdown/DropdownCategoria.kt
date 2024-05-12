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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.ui.theme.FinanceTheme



@Composable
fun DropdownCategoria(
    expandedInicial : Boolean = false,
    categorias: List<Categoria>,
    categoriaSelecionada : Categoria?,
    modifier: Modifier = Modifier,
    onChoice: (Categoria) -> Unit
) {
    var expandedMenu by remember { mutableStateOf<Boolean>(expandedInicial) }
    var textoBotao = categoriaSelecionada?.nome ?: "Selecione uma categoria."



    Column(modifier = modifier) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = { expandedMenu = true },
        ) {
            Text(
                text = textoBotao,
                style = MaterialTheme.typography.titleSmall,
                )

        }
        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = {
                expandedMenu = false
            },
            modifier = modifier

        ) {
            categorias.forEach { categoria ->
                DropdownItem(
                    categoria = categoria,
                    onChoice = {
                        onChoice(it)
                        expandedMenu = false
                    }
                )
            }
        }
    }

}

@Composable
private fun DropdownItem(
    categoria : Categoria,
    onChoice: (Categoria) -> Unit = {},
) {
    DropdownMenuItem(
        text = { Text(
            text = categoria.nome,
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            onChoice(categoria)
        },
    )
}

@Preview
@Composable
private fun DropdownCategoriaPrev() {
    FinanceTheme {
        var flag by remember {
            mutableStateOf(true)
        }
        if (flag){
            gerarCategoriasBasicas()
            flag = false
        }
        var categoriaSelecionada by remember { mutableStateOf<Categoria?>(null) }
        DropdownCategoria(
            categorias = Login.getCasaLogada().getCategorias(),
            categoriaSelecionada = categoriaSelecionada,
            onChoice = {
                categoriaSelecionada = it
            }
        )
    }
}
