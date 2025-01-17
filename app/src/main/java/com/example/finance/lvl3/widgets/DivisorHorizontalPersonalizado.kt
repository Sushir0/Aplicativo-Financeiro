package com.example.finance.lvl3.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DivisorHorizontalPersonalizado(modifier: Modifier = Modifier) {
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.padding(horizontal = 4.dp)
    )
}

@Preview
@Composable
private fun DivisorHorizontalPersonalizadoPreview() {
    DivisorHorizontalPersonalizado()

}