package com.example.finance.lvl3.telas.Configuracoes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
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
import com.example.finance.Data.DataSource.TipoBancoDeDados
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.lvl3.activitys.Configuracoes.abrirConfig_MacroCategorias
import com.example.finance.lvl3.utils.avisoLongo
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

class Configuracao {
    @Composable
    fun content() {
        var background: Color = if (isSystemInDarkTheme()) {
            backgroundDark
        } else {
            backgroundLight
        }
        var debugMode by remember() { mutableStateOf(VariaveisDeAmbiente.debugMode) }
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {
            Text(
                text = "Configurações",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(16.dp)
            )
            Item(texto = "Configurações de categorias") {
                abrirConfig_MacroCategorias(
                    context = context,
                    casaId = VariaveisDeAmbiente.casaId,
                    tipoBancoDeDados = VariaveisDeAmbiente.tipoBancoDeDados
                    )
            }
            Item(texto = "Configurações do aplicativo") {

            }
            Item(texto = "Sobre o aplicativo") {

            }
            Item(texto = "Login") {

            }
            Item(
                texto = "Tipo de Banco de dados: ${VariaveisDeAmbiente.tipoBancoDeDados.name}"
            ){
                if(VariaveisDeAmbiente.tipoBancoDeDados == TipoBancoDeDados.Room){
                    VariaveisDeAmbiente.tipoBancoDeDados = TipoBancoDeDados.Cache
                }else{
                    VariaveisDeAmbiente.tipoBancoDeDados = TipoBancoDeDados.Room
                }
            }
            Item(texto = "Modo debug: $debugMode", isLastItem = true) {
                debugMode = !debugMode
                VariaveisDeAmbiente.debugMode = !VariaveisDeAmbiente.debugMode
                avisoLongo(context = context, "Modo debug alterado para $debugMode")
            }


        }
    }

    @Composable
    private fun Item(texto: String, isLastItem: Boolean = false, onClick: () -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(16.dp),

            ) {
            Text(
                text = texto,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowRight,
                    contentDescription = "Ver mais"
                )
            }
        }
        if (!isLastItem) {
            DivisorHorizontalPersonalizado()
        }
    }

    fun atualizar() {

    }
}

@Preview
@Composable
private fun ConfiguracaoPreview() {


    val config = Configuracao()
    config.content()


}