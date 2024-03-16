package com.example.finance.lvl3.auxiliares

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isPortrait(): Boolean {
    val orientacao = LocalConfiguration.current.orientation
    return orientacao == Configuration.ORIENTATION_PORTRAIT
}