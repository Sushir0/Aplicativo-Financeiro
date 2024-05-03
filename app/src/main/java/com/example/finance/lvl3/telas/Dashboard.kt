package com.example.finance.lvl3.telas

import FormularioMovimentacao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl1.getPeriodoFromNome
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.Getters.getPeriodoInicial
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import com.example.finance.lvl3.activitys.abrirDetalhes
import com.example.finance.lvl3.componentes.NovoResumoFinanceiro
import com.example.finance.lvl3.componentes.listas.ListaDeMovimentacoes
import com.example.finance.lvl3.componentes.listas.NovaListaDeMembros
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

class Dashboard{
    private var membroSelecionado by mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada())
    private var periodoSelecionado by mutableStateOf(getPeriodoInicial(membroSelecionado))
    private var periodosUtilizados by mutableStateOf(getPeriodosFromMovimentacoes(membroSelecionado.movimentacoes))


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun content() {
        val context = LocalContext.current
        var isSheetOpen by rememberSaveable { mutableStateOf(false) }
        var background: Color = if(isSystemInDarkTheme()){
            backgroundDark
        }else{
            backgroundLight
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(membroSelecionado.nome)
            Column (modifier = Modifier
                .verticalScroll(rememberScrollState()),
            ){
                NovoResumoFinanceiro(
                    recebimentos = membroSelecionado.getRecebimentosTotais(periodoSelecionado),
                    gastos = membroSelecionado.getGastosTotais(periodoSelecionado),
                    saldo = membroSelecionado.getSaldo(periodoSelecionado),
                    onClickRecebimentos = {
                        abrirDetalhes(
                            context = context,
                            movimentacaoHolder = membroSelecionado,
                            isGasto = false,
                            periodo = periodoSelecionado
                        ) },
                    onClickGastos = {
                        abrirDetalhes(
                            context = context,
                            movimentacaoHolder = membroSelecionado,
                            isGasto = true,
                            periodo = periodoSelecionado
                        ) }

                )
                HorizontalDivider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                NovaListaDeMembros(
                    membros = getMembros(),
                    membroSelecionado,
                    onClick = {
                        membroSelecionado = it
                        atualizar()
                    }
                )


                ListaDeMovimentacoes(movimentacoes = membroSelecionado.movimentacoes)


                Spacer(modifier = Modifier.height(64.dp))
            }
        }
        Footer(
            periodosUtilizados = periodosUtilizados,
            periodoSelecionado = periodoSelecionado,
            openBottomSheetClick = { isSheetOpen = true },
            onChoicePeriodo = {periodoSelecionado = it}
        )

         
        BottomSheet(
            isSheetOpen = isSheetOpen,
            onDismiss = { isSheetOpen = false }){
            FormularioMovimentacao(
                membroPreSelecionado = membroSelecionado,
                onConfirm = { atualizar() },
                onDismiss = { isSheetOpen = false })
        }
    }

    fun atualizar() {
        periodosUtilizados = getPeriodosFromMovimentacoes(membroSelecionado.movimentacoes)
        periodoSelecionado = getPeriodoFromNome(periodoSelecionado.nome, periodosUtilizados) ?: getUltimoPeriodoUtilizado(periodosUtilizados)
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardPreview() {
    var dashboardPreview:Dashboard? by remember{ mutableStateOf(null) }
    var flag by remember {
        mutableStateOf(true)
    }
    if (flag){
        testeCadastro()
        gerarCategoriasBasicas()
        testeAdicionarMovimentacao(Login.getCasaLogada())
        dashboardPreview = Dashboard()
        flag = false
    }


    FinanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            dashboardPreview!!.content()
        }
    }


}