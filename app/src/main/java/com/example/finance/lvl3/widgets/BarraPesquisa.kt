package com.example.finance.lvl3.widgets

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente

@ExperimentalMaterial3Api
@Composable
fun BarraPesquisa(onChanged: (String) -> Unit = {  }, modifier: Modifier = Modifier){
    var texto by remember { mutableStateOf("") }
    val offSetButton by animateDpAsState(
        targetValue = if (texto.isNotEmpty()) 0.dp else -10.dp,
        animationSpec = tween(300)
    )
    val alphaButton by animateFloatAsState(
        targetValue = if (texto.isNotEmpty()) 1f else 0f,
        animationSpec = tween(300)
    )

    val offSetTextField by animateDpAsState(
        targetValue = if (texto.isNotEmpty()) 0.dp else 10.dp,
        animationSpec = tween(300)
    )
    val keyboardController = LocalSoftwareKeyboardController.current


    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = texto,
            onValueChange = {
                texto = it
                onChanged(it) },
            label = { Text("Pesquisar") },
            singleLine = true,
            modifier = Modifier
                .offset(x = offSetTextField)
        )
        IconButton(
            onClick = {

                texto = ""
                onChanged(texto)
                if(VariaveisDeAmbiente.betaTeste.esconderKeyboardNaLimpeza) keyboardController?.hide()
                      },
            modifier = Modifier
                .offset(x = offSetButton)
                .alpha(alphaButton)
            ,
        ) {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Limpar Pesquisa"
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun BarraPesquisaPreview() {
    BarraPesquisa (
        onChanged = { /*TODO*/ },
        modifier = Modifier
    )
}