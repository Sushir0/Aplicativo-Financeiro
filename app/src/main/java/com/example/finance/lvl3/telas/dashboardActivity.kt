package com.example.finance.lvl3.telas

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getPeriodoFromNome
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.componentes.NovoResumoFinanceiro
import com.example.finance.lvl3.componentes.listas.ListaDeMovimentacoes
import com.example.finance.lvl3.componentes.listas.NovaListaDeMembros
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

class dashboardActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewDashboard()
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewDashboard() {
    val membroSelecionado = remember {
        mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada())
    }

    val periodoSelecionado = remember{
        mutableStateOf(
            getUltimoPeriodoUtilizado(
                getPeriodosFromMovimentacoes(
                    membroSelecionado.value.movimentacoes
                )
            )
        )
    }
    var periodosUtilizados by remember {
        mutableStateOf(getPeriodosFromMovimentacoes(membroSelecionado.value.movimentacoes))
    }

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }

    fun atualizar() {
        periodosUtilizados = getPeriodosFromMovimentacoes(membroSelecionado.value.movimentacoes)
        periodoSelecionado.value = getPeriodoFromNome(periodoSelecionado.value.nome, periodosUtilizados) ?: getUltimoPeriodoUtilizado(periodosUtilizados)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(membroSelecionado.value.nome)
        Column (modifier = Modifier
            .verticalScroll(rememberScrollState()),
        ){
            NovoResumoFinanceiro(
                recebimentos = membroSelecionado.value.getRecebimentosTotais(periodoSelecionado.value),
                gastos = membroSelecionado.value.getGastosTotais(periodoSelecionado.value),
                saldo = membroSelecionado.value.getSaldo(periodoSelecionado.value)
            )
            Box(modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp)
            ) {
                Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
            }
            NovaListaDeMembros(
                membros = getMembros(),
                membroSelecionado,
                onClick = { atualizar() }
            )
            ListaDeMovimentacoes(movimentacoes = membroSelecionado.value.movimentacoes)


            Spacer(modifier = Modifier.height(64.dp))
        }
    }
    Footer(
        periodosUtilizados = periodosUtilizados,
        periodoSelecionado = periodoSelecionado,
        openBottomSheetClick = { isSheetOpen = true }
        )
    BottomSheet(
        isSheetOpen = isSheetOpen,
        onDismiss = { isSheetOpen = false },
        membroSelecionado = membroSelecionado,
        onConfirmFormulario = {
            atualizar()

        }
    )

}






@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardPreview() {
    testeCadastro()
    gerarCategoriasBasicas()
    testeAdicionarMovimentacao(Login.getCasaLogada())



    FinanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewDashboard()
        }
    }

    
}

fun abrirDashboard(mContext: Context){
    val intent = Intent(mContext, dashboardActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    ActivityCompat.startActivity(mContext, intent, null)
}