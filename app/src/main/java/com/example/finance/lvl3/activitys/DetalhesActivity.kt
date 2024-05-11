package com.example.finance.lvl3.activitys

import FormularioMovimentacao
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl1.getPeriodoFromNome
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Getters.getMembroById
import com.example.finance.lvl2.Getters.getMovimentacoes
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import com.example.finance.lvl3.componentes.ItemValue
import com.example.finance.lvl3.componentes.listas.NewListaDeMovimentacao
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.telas.Detalhes
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.dropdown.DropdownIsGasto
import com.example.finance.lvl3.widgets.dropdown.Tipo
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

var detalhes: Detalhes? = null

class DetalhesActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movimentacaoHolder = intent.getSerializableExtra("movimentacaoHolder") as MovimentacaoHolder
        val isGasto :Boolean = intent.getBooleanExtra("isGasto", false)
        val periodo: Periodo = intent.getSerializableExtra("periodo") as Periodo
        detalhes = Detalhes(
            movimentacaoHolder = movimentacaoHolder,
            isGastoInicial = isGasto,
            periodoInicial = periodo
        )
        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    detalhes!!.content()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Detalhes(
    movimentacaoHolder: MovimentacaoHolder,
    isGastoInicial: Boolean,
    ) {
    val membroSelecionado = remember {
        mutableStateOf<MovimentacaoHolder>(getMembroById(movimentacaoHolder.id))
    }

    val periodoSelecionado = remember{
        mutableStateOf(
            getUltimoPeriodoUtilizado(
                getPeriodosFromMovimentacoes(
                    movimentacaoHolder.movimentacoes
                )
            )
        )
    }
    var periodosUtilizados by remember {
        mutableStateOf(getPeriodosFromMovimentacoes(membroSelecionado.value.movimentacoes))
    }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    var background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    var tipoSelecionado by remember {
        mutableStateOf(Tipo.getTipoFromIsGasto(isGastoInicial))
    }

    val movimentacoes by remember{
        mutableStateOf(
            getMovimentacoes(
                movimentacaoHolder = membroSelecionado.value,
                periodo = periodoSelecionado.value,
                isGasto = false
            )
        )
    }

    
    fun atualizar() {
        periodosUtilizados = getPeriodosFromMovimentacoes(membroSelecionado.value.movimentacoes)
        periodoSelecionado.value = getPeriodoFromNome(membroSelecionado.value.nome, periodosUtilizados) ?: getUltimoPeriodoUtilizado(periodosUtilizados)
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .padding(bottom = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Header(nome = membroSelecionado.value.nome)
        ItemValue(valor = membroSelecionado.value.getSaldo(periodoSelecionado.value))
        DropdownIsGasto(
            tipoSelecionado = tipoSelecionado,
            modifier = Modifier
                .fillMaxWidth(.75f)
                .padding(
                    start = 4.dp,
                    top = 4.dp
                )
        ) {
            tipoSelecionado = it
        }
        NewListaDeMovimentacao(
            movimentacoes = movimentacoes,
            isAlways = tipoSelecionado == Tipo.TODOS
        )

    }

    Footer(
        periodosUtilizados = periodosUtilizados,
        openBottomSheetClick = { isSheetOpen = true },
        periodoSelecionado =    periodoSelecionado.value,
        onChoicePeriodo = { periodoSelecionado.value = it }
    )

    BottomSheet(
        isSheetOpen = isSheetOpen,
        onDismiss = { isSheetOpen = false }){
        FormularioMovimentacao(
            membroPreSelecionado = membroSelecionado.value,
            onConfirm = {
                atualizar()
            },
            lockedMembro = true
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DetalhesPreview() {
    testeCadastro()
    gerarCategoriasBasicas()
    testeAdicionarMovimentacao(Login.getCasaLogada())
    val detalhes = Detalhes(
        movimentacaoHolder = Login.getCasaLogada(),
        isGastoInicial = true)
    FinanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Detalhes(movimentacaoHolder = Login.getCasaLogada(), isGastoInicial = true)
        }
    }
}

fun abrirDetalhes(context: Context, movimentacaoHolder: MovimentacaoHolder, isGasto: Boolean, periodo: Periodo){
    val intent = Intent(context, DetalhesActivity::class.java).apply {
        putExtra("movimentacaoHolder", movimentacaoHolder)
        putExtra("isGasto", isGasto)
        putExtra("periodo", periodo)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    ActivityCompat.startActivity(context, intent, null)
}