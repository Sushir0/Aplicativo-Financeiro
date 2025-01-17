package com.example.finance.lvl3.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.lvl3.widgets.buttons.ButtonAlternar_C_Icone

@Composable
fun ShowAtributosMacroCategoria(
    modifier: Modifier = Modifier,
    macroCategoria: MacroCategoria? = null
    ) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        var textoIsGasto : String
        var textoAfetaPessoa : String
        var textoAfetaCasa : String

        if(macroCategoria == null){
            textoIsGasto = "Carregando"
            textoAfetaPessoa = "Carregando"
            textoAfetaCasa = "Carregando"
        }else{
            textoIsGasto = if(macroCategoria.isGasto) "Gasto" else "Recebimento"
            textoAfetaPessoa = if(macroCategoria.afetaPessoa) "Afeta Pessoa" else "Não afeta Pessoa"
            textoAfetaCasa = if(macroCategoria.afetaCasa) "Afeta Casa" else "Não afeta Casa"
        }

        ButtonAlternar_C_Icone(
            texto = textoAfetaPessoa,
            isTrue = macroCategoria?.afetaPessoa,
            modifier = Modifier.weight(1f),
            enabled = false,
        ){
            Icon(
                Icons.Outlined.Person,
                contentDescription = when(macroCategoria?.afetaPessoa){
                    true -> "Afeta pessoa"
                    false -> "Não afeta pessoa"
                    null -> "Carregando"
                }
            )
        }
        ButtonAlternar_C_Icone(
            texto = textoIsGasto,
            isTrue = when (macroCategoria?.isGasto) {
                true -> {
                    false
                }
                false -> {
                    true
                }
                null -> {
                    null
                }
            },
            modifier = Modifier.weight(1f),
            enabled = false,
        ){
            SetaMovimentacao(isGasto = macroCategoria?.isGasto)
        }
        ButtonAlternar_C_Icone(
            texto = textoAfetaCasa,
            isTrue = macroCategoria?.afetaCasa,
            modifier = Modifier.weight(1f),
            enabled = false,
        ){
            Icon(
                Icons.Outlined.Home,
                contentDescription = when(macroCategoria?.afetaCasa){
                    true -> "Afeta casa"
                    false -> "Não afeta casa"
                    null -> "Carregando"
                }
            )
        }
    }

}