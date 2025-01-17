package com.example.finance.lvl3.activitys

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.PesquisaParcelasViewModel
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.lvl3.telas.PesquisaParcelas
import com.example.finance.ui.theme.FinanceTheme


@ExperimentalMaterial3Api
class DetalhesParecelasActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movimentacaoHolder = intent.getSerializableExtra("movimentacaoHolder") as MovimentacaoHolder
        val selecaoUsuario : SelecaoUsuario = intent.getSerializableExtra("selecaoUsuario") as SelecaoUsuario
        val periodo: Periodo = intent.getSerializableExtra("periodo") as Periodo
        val viewModel = PesquisaParcelasViewModel(dataSourse = RoomDataSources(this))

        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PesquisaParcelas(
                        movimentacaoHolderDTO = movimentacaoHolder.toDTO(),
                        selecaoUsuarioInicial = selecaoUsuario,
                        periodoInicial = periodo,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DetalhesPreview() {
    /*
    LoginController().testeCadastro()
    CategoriaDebbug().gerarCategoriasBasicas()
    MovimentacaoController().testeAdicionarMovimentacao(Login.getCasaLogada())
    val detalhes = Detalhes(
        movimentacaoHolder = Login.getCasaLogada(),
        selecaoUsuarioInicial = SelecaoUsuario.tipoSelecionado(Tipo.GASTO),
        periodoInicial = getUltimoPeriodoUtilizado(getPeriodosFromMovimentacoes_ComAnos(Login.getCasaLogada().getMovimentacoes()))
    )

    FinanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            detalhes.content()
        }
    }

     */
}

@ExperimentalMaterial3Api
fun abrirDetalhes(context: Context, movimentacaoHolder: MovimentacaoHolder, selecaoUsuario: SelecaoUsuario, periodo: Periodo){
    val intent = Intent(context, DetalhesParecelasActivity::class.java).apply {
        putExtra("movimentacaoHolder", movimentacaoHolder)
        putExtra("selecaoUsuario", selecaoUsuario)
        putExtra("periodo", periodo)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    ActivityCompat.startActivity(context, intent, null)
}
