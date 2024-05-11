package com.example.finance.lvl3.widgets.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonAdicionar(openBottomSheetClick : () -> Unit) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(
            onClick = openBottomSheetClick,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                Icons.Outlined.AddCircle,
                contentDescription = "Adicionar movimentações",
                modifier = Modifier
                    .size(48.dp)
            )

        }
    }
}

@Preview
@Composable
fun ButtonAdicionarPrev() {
    ButtonAdicionar({  })

}