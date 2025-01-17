package com.example.finance.lvl3.telas

import FormularioMovimentacao
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Data.DataSource.RoomDB.RoomMock
import com.example.finance.Presentation.VM.DashboardViewModel
import com.example.finance.Presentation.VM.Movimentacao.AdicionarMovimentacaoViewModel
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.lvl3.componentes.NovoResumoFinanceiro
import com.example.finance.lvl3.componentes.listas.NovaListaDeMembros
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import kotlinx.coroutines.launch

@Composable
fun Dashboard(
    idCasa: String = VariaveisDeAmbiente.casaId,
    viewModel: DashboardViewModel
    ) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var background: Color = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }

    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }

    val valorGasto by viewModel.valorGasto.collectAsState()
    val valorRecebimento by viewModel.valorRecebido.collectAsState()
    val valorSaldo by viewModel.saldo.collectAsState()

    var titulo = viewModel.titulo
    val recebimentoAnimado by animateFloatAsState(targetValue = valorRecebimento)
    val gastosAnimado by animateFloatAsState(targetValue = valorGasto)
    val saldoAnimado by animateFloatAsState(targetValue = valorSaldo)

    val membros = viewModel.membrosStateFlow.collectAsState()
    val periodos = viewModel.periodosStateFlow.collectAsState()

    val membroSelecionado by viewModel.membroSelecionado
    var periodoSelecionado by viewModel.periodoSelecionado

    val bottomSheetAdicinoar by viewModel.bottomSheetAdicinoar

    LaunchedEffect(key1 = idCasa){
        viewModel.inicializar(idCasa)
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(titulo)
        Column (modifier = Modifier
            .verticalScroll(rememberScrollState()),
        ){
            NovoResumoFinanceiro(
                recebimentos = recebimentoAnimado.toDouble(),
                gastos = gastosAnimado.toDouble(),
                saldo = saldoAnimado.toDouble(),
                onClickRecebimentos = {

                    viewModel.abrirDetalhesActivity(
                        context = context,
                        selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.RECEBIMENTO)
                    )
                },

                onClickGastos = {
                    viewModel.abrirDetalhesActivity(
                        context = context,
                        selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.GASTO)
                    )
                }

            )
            Divider(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 16.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            NovaListaDeMembros(
                membros = membros.value,
                membroSelecionado,
                onClick = {
                    coroutineScope.launch {
                        viewModel.selecinoarMembro(it) }

                    }
            )
            /*




            if (VariaveisDeAmbiente.debugMode){
                ListaDeMovimentacoes(movimentacoes = getMovimentacoes(membroSelecionado, periodo = periodoSelecionado))
            }

             */


            Spacer(modifier = Modifier.height(64.dp))
        }
    }
    Footer(
        periodosUtilizados = periodos.value,
        periodoSelecionado = periodoSelecionado,
        openBottomSheetClick = { viewModel.abrirBottomSheet() },
        onChoicePeriodo = {
            coroutineScope.launch {
                viewModel.selecionarPeriodo(it)
            }
        }
    )



    BottomSheet(
        isSheetOpen = bottomSheetAdicinoar,
        onDismiss = { viewModel.fecharBottomSheet() }
    ){
        val viewModelAdicionar = AdicionarMovimentacaoViewModel(dataSources = viewModel.dataSource)
        FormularioMovimentacao(
            membroPreSelecionado = membroSelecionado,
            viewModel = viewModelAdicionar,
            onDismiss = { viewModel.fecharBottomSheet() }
        )
    }


}






@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardPreview() {
    val context = LocalContext.current
    val casaId = VariaveisDeAmbiente.casaId
    val viewModel = DashboardViewModel(
        dataSource = RoomDataSources(context)
    )
    LaunchedEffect(Unit) {
        //RoomMock(context)
//        try {
//            val casa = AppDatabase.getDatabase(context).casaDao().getCasa(casaId)
//            if (casa == null) {
//
//            }
//        } catch (e: Exception) {
//            RoomMock(context)
//
//        }
    }




    FinanceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Dashboard(
                idCasa = casaId,
                viewModel = viewModel
            )
        }
    }



}
