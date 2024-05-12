package com.example.finance.lvl3.widgets

import FormularioMovimentacao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl2.Login.testeCadastro


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    isSheetOpen: Boolean,
    onDismiss : ()-> Unit,
    content: @Composable ()-> Unit
){

    if(isSheetOpen){
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            onDismissRequest = onDismiss
        ) {
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ){
                content()
            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun BottomSheetPrev() {
    var isSheetOpen by rememberSaveable {
        mutableStateOf(true)
    }
    gerarCategoriasBasicas()
    testeCadastro()
    val membroSelecionado by remember {
        mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada())
    }
    BottomSheet(
        isSheetOpen,
        onDismiss = { isSheetOpen = false }
    ){
        FormularioMovimentacao(membroPreSelecionado = membroSelecionado,
            onDismiss = {isSheetOpen = false})
    }
}

