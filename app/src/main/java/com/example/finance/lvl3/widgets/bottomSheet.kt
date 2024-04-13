package com.example.finance.lvl3.widgets

import FormularioMovimentacao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl2.Login.testeCadastro


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(isSheetOpen: Boolean, onDismiss : ()-> Unit){

    if(isSheetOpen){
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismissRequest = onDismiss
        ) {
            FormularioMovimentacao(onDismiss)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun BottomSheetPrev() {
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    gerarCategoriasBasicas()
    testeCadastro()
    BottomSheet(isSheetOpen) { isSheetOpen = false }
}

