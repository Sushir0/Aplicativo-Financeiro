package com.example.finance.lvl3.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.adicionarMovimentacao
import com.example.finance.lvl3.componentes.listas.ListaDePeriodos
import com.example.finance.lvl3.widgets.buttons.ButtonAdicionar

@Composable
fun Footer(
    periodosUtilizados : List<Periodo>,
    openBottomSheetClick:  () -> Unit = {  },
    periodoSelecionado: Periodo,
    onChoicePeriodo: (Periodo) -> Unit = {  }
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ButtonAdicionar(openBottomSheetClick)
            ListaDePeriodos(
                periodos = periodosUtilizados,
                periodoSelecionado = periodoSelecionado,
                onChoice = { onChoicePeriodo(it) }
            )
    }
}

@Preview
@Composable
fun FooterPrev() {
    testeCadastro()
    val periodos = remember{ getPeriodosFromMovimentacoes(Login.getCasaLogada().movimentacoes) }
    val periodoSelecionado = remember{ mutableStateOf( getUltimoPeriodoUtilizado(periodos) )}

    adicionarMovimentacao(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10,5,2020),
        movimentacaoHolder = Login.getCasaLogada(),
        categoria = null
    )
    adicionarMovimentacao(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10,6,2021),
        movimentacaoHolder = Login.getCasaLogada(),
        categoria = null
    )
    Footer(
        getPeriodosFromMovimentacoes(Login.getCasaLogada().movimentacoes),
        periodoSelecionado = periodoSelecionado.value,
        openBottomSheetClick = {},
        onChoicePeriodo = { periodoSelecionado.value = it }
    )

}

