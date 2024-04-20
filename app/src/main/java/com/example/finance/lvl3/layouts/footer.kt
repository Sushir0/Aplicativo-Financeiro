package com.example.finance.lvl3.layouts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.getPeriodoFromDatasUtilizadas
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.adicionarMovimentacaoCasa
import com.example.finance.lvl3.componentes.listas.ListaDePeriodos
import com.example.finance.lvl3.utils.retirarExclamacao
import com.example.finance.lvl3.widgets.ButtonAdicionar

@Composable
fun Footer(
    periodosUtilizados : List<Periodo>,
    openBottomSheetClick:  () -> Unit,
    periodoSelecionado: MutableState<Periodo>
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ButtonAdicionar(openBottomSheetClick)
            ListaDePeriodos(
                periodos = periodosUtilizados,
                periodoSelecionado = periodoSelecionado
            )
    }
}

@Preview
@Composable
fun FooterPrev() {
    testeCadastro()
    val periodos = remember{ getPeriodoFromDatasUtilizadas(Login.getCasaLogada().movimentacoes) }
    val periodoSelecionado = remember{ mutableStateOf( getUltimoPeriodoUtilizado(periodos) )}

    adicionarMovimentacaoCasa(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10,5,2020),
        casa = Login.getCasaLogada(),
        categoria = null
    )
    adicionarMovimentacaoCasa(
        assunto = "teste",
        valorStr = "10.00",
        data = Data(10,6,2021),
        casa = Login.getCasaLogada(),
        categoria = null
    )
    Footer(
        getPeriodoFromDatasUtilizadas(Login.getCasaLogada().movimentacoes),
        periodoSelecionado = periodoSelecionado,
        openBottomSheetClick = {}
    )

}

