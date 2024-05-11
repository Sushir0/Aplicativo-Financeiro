package com.example.finance.lvl3.widgets.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.finance.ui.theme.FinanceTheme


enum class Tipo {
    GASTO,
    RECEBIMENTO,
    TODOS;

    override fun toString(): String {
        return when (this) {
            GASTO -> "Gasto"
            RECEBIMENTO -> "Recebimento"
            TODOS -> "Todos"
        }
    }

    companion object {
        fun getTipos(): List<Tipo> {
            return enumValues<Tipo>().toList()
        }
        fun getTipoFromIsGasto(isGasto: Boolean): Tipo {
            return if(isGasto){ GASTO }else{ RECEBIMENTO }
        }
    }
}

@Composable
fun DropdownIsGasto(
    expandedInicial : Boolean = false,
    tipoSelecionado : Tipo,
    modifier: Modifier = Modifier,
    onChoice: (Tipo) -> Unit
) {
    var expandedMenu = remember { mutableStateOf<Boolean>(expandedInicial) }
    var textoBotao = tipoSelecionado.toString()


    Column(modifier = modifier) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = { expandedMenu.value = true },
        ) {
            Text(
                text = textoBotao,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = {
                expandedMenu.value = false
            },
            modifier = modifier

        ) {
            Tipo.getTipos().forEach { tipo ->
                DropdownItem(
                    tipo = tipo,
                    onChoice = {
                        onChoice(it)
                        expandedMenu.value = false
                    }
                )
            }
        }
    }

}

@Composable
private fun DropdownItem(
    tipo: Tipo,
    onChoice: (Tipo) -> Unit = {},
) {
    DropdownMenuItem(
        text = { Text(
            text = tipo.toString(),
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            onChoice(tipo)
        },
    )
}


@Preview
@Composable
private fun DropdownCategoriaPrev() {
    FinanceTheme {
        var tipoSelecionado by remember { mutableStateOf<Tipo>(Tipo.RECEBIMENTO) }
        DropdownIsGasto(
            tipoSelecionado = tipoSelecionado,
            onChoice = {
                tipoSelecionado = it
            }
        )
    }
}
