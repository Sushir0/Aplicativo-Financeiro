package com.example.finance.lvl3.widgets

import FiltrosMacroCategoria
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.widgets.buttons.ButtonAlternar_C_Icone

@Composable
fun FiltroMacroCategoria(
    filtros: FiltrosMacroCategoria,
    onClick: (FiltrosMacroCategoria) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        val textoIsGasto : String = when (filtros.isGasto) {
            true -> "Gasto"
            false -> "Recebimento"
            null -> "Tipo: todos"
        }

        val textoAfetaPessoa : String = "Afeta pessoa"

        val textoAfetaCasa : String = "Afeta casa"

        val textoIsAtivo : String = "Status"

        ButtonAlternar_C_Icone(
            texto = textoAfetaPessoa,
            isTrue = filtros.afetaPessoa,
            modifier = Modifier.weight(1f),
            enabled = true,
            onClick = {
                filtros.nextAfetaPessoa()
                onClick(filtros)
            }
        ){
            Icon(
                Icons.Outlined.Person,
                contentDescription = textoAfetaPessoa
            )
        }
        ButtonAlternar_C_Icone(
            texto = textoIsGasto,
            isTrue = filtros.isGasto,
            modifier = Modifier.weight(1f),
            enabled = true,
            onClick = {
                filtros.nextIsGasto()
                onClick(filtros)
            }
        ){
            SetaMovimentacao(isGasto = filtros.isGasto)
        }
        ButtonAlternar_C_Icone(
            texto = textoAfetaCasa,
            isTrue = filtros.afetaCasa,
            modifier = Modifier.weight(1f),
            enabled = true,
            onClick = {
                filtros.nextAfetaCasa()
                onClick(filtros)
            }
        ){
            Icon(
                Icons.Outlined.Home,
                contentDescription = textoAfetaCasa
            )
        }
        ButtonAlternar_C_Icone(
            texto = textoIsAtivo,
            isTrue = filtros.isAtivo,
            modifier = Modifier.weight(1f),
            enabled = true,
            onClick = {
                filtros.nextIsAtivo()
                onClick(filtros)
            }
        ){
            Icon(
                Icons.Outlined.Home,
                contentDescription = textoIsAtivo
            )
        }
    }
}

@Preview
@Composable
private fun FiltrosMacroCategoriaPrev() {
    var filtros by remember { mutableStateOf(FiltrosMacroCategoria()) }
    FiltroMacroCategoria(filtros = filtros){
        filtros = it
    }
}