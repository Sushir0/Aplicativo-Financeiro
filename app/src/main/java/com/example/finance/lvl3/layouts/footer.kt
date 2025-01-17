package com.example.finance.lvl3.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.getPeriodosFromMovimentacoes_ComAnos
import com.example.finance.a_Domain.model.MetaDados.getUltimoPeriodoUtilizado
import com.example.finance.lvl3.componentes.listas.ListaDePeriodos
import com.example.finance.lvl3.widgets.buttons.ButtonAdicionar

@Composable
fun Footer(
    periodosUtilizados: List<Periodo>? = null,
    openBottomSheetClick: () -> Unit = { },
    periodoSelecionado: Periodo? = null,
    onChoicePeriodo: (Periodo) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ButtonAdicionar(openBottomSheetClick)
        if (periodosUtilizados != null){
            ListaDePeriodos(
                periodos = periodosUtilizados,
                periodoSelecionado = periodoSelecionado,
                onChoice = { onChoicePeriodo(it) }
            )
        }

    }
}

@Preview
@Composable
fun FooterPrev() {
    /*
    LoginController().testeCadastro()
    val periodos = remember { getPeriodosFromMovimentacoes_ComAnos(Login.getCasaLogada().movimentacoes) }
    val periodoSelecionado = remember { mutableStateOf(getUltimoPeriodoUtilizado(periodos)) }

    MovimentacaoController().adicionarMovimentacao(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10, 5, 2020),
        movimentacaoHolder = Login.getCasaLogada(),
        categoria = null
    )
    MovimentacaoController().adicionarMovimentacao(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10, 6, 2021),
        movimentacaoHolder = Login.getCasaLogada(),
        categoria = null
    )
    Footer(
        getPeriodosFromMovimentacoes_ComAnos(Login.getCasaLogada().movimentacoes),
        periodoSelecionado = periodoSelecionado.value,
        openBottomSheetClick = {},
        onChoicePeriodo = { periodoSelecionado.value = it }
    )


     */
}

