package com.example.finance.lvl3.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isPortrait(): Boolean {
    val orientacao = LocalConfiguration.current.orientation
    return orientacao == Configuration.ORIENTATION_PORTRAIT
}

fun valorMonetario(valor: Double): String {
    return "R$ "+String.format("%.2f", valor)
}

fun contemExclamacao(texto: String): Boolean {
    val textoSemExclamacao = texto.replace("!", "")
    return texto != textoSemExclamacao
}

fun retirarExclamacao(texto: String): String {
    return texto.replace("!", "")
}