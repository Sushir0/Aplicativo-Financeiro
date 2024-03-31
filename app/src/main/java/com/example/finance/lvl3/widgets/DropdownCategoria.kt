package com.example.finance.lvl3.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.categorias
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight


@Composable
fun DropdownCategoria(
    expanded : MutableState<Boolean>,
    categorias: List<Categoria>,
    categoriaSelecionada : MutableState<Categoria>,
    modifier: Modifier
) {
    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    Column(modifier = modifier.background(background)) {
        OutlinedButton(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            onClick = { expanded.value = true },
        ) {
            Text(
                text = categoriaSelecionada.value.nome,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground)

        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            }
        ) {
            categorias.forEach { categoria ->
                DropdownItem(
                    categoria = categoria,
                    expanded = expanded,
                    categoriaSelecionada = categoriaSelecionada,
                    modifier = modifier
                )
            }
        }
    }

}

@Composable
private fun DropdownItem(
    categoria : Categoria,
    expanded: MutableState<Boolean>,
    categoriaSelecionada: MutableState<Categoria>,
    modifier: Modifier
) {
    DropdownMenuItem(
        text = { Text(
            text = categoria.nome,
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            categoriaSelecionada.value = categoria
            expanded.value = false
        },
        modifier = modifier,
    )
}


@Preview
@Composable
private fun DropdownCategoriaPrev() {
    FinanceTheme {
        gerarCategoriasBasicas()
        var expandedMenu = remember { mutableStateOf<Boolean>(true) }
        var categoriaSelecionada = remember{ mutableStateOf(categorias[0]) }
        DropdownCategoria(
            expanded = expandedMenu,
            categorias = categorias,
            categoriaSelecionada = categoriaSelecionada,
            modifier = Modifier.width(200.dp)
        )
    }
}
