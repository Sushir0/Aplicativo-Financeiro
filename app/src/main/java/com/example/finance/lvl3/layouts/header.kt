package com.example.finance.lvl3.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.widgets.buttons.ButtonConfiguracao

@Composable
fun Header(nome : String) {
    var background = if(isSystemInDarkTheme()){
        Color.Transparent
    }else{ MaterialTheme.colorScheme.primary }
    var colorOnBackground = if(isSystemInDarkTheme()){
        MaterialTheme.colorScheme.onBackground
    }else{
        MaterialTheme.colorScheme.onPrimary}


    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            text = nome,
            modifier = Modifier
                .padding(4.dp),
            color = colorOnBackground,
            style = MaterialTheme.typography.displaySmall
        )
        ButtonConfiguracao(color = colorOnBackground)
    }
}