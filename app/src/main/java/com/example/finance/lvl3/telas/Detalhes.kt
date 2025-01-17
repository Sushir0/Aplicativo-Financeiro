package com.example.finance.lvl3.telas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.PesquisaParcelasViewModel
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO
import com.example.finance.lvl3.componentes.formularios.FormularioEditarFiltros
import com.example.finance.lvl3.componentes.listas.ListaDeParcelas
import com.example.finance.lvl3.componentes.listas.ListaDePeriodos
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.BarraPesquisa
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.Escolher.EscolhaOrdem
import com.example.finance.lvl3.widgets.Escolher.EscolhaSelecaoUsuario
import com.example.finance.lvl3.widgets.Escolher.EscolhaSelecaoUsuarioNew
import com.example.finance.lvl3.widgets.ListaDeFiltros
import com.example.finance.lvl3.widgets.buttons.ButtonAbrirFiltros
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesquisaParcelas(
    movimentacaoHolderDTO: MovimentacaoHolderDTO,
    selecaoUsuarioInicial: SelecaoUsuario,
    periodoInicial: Periodo,
    viewModel: PesquisaParcelasViewModel

) {
    val context = LocalContext.current
    var background = if (isSystemInDarkTheme()) {
        backgroundDark
    } else {
        backgroundLight
    }
    val coroutineScope = rememberCoroutineScope()
    var periodoEscolhidoPreConfirmacao: Periodo? by remember(viewModel.periodo) {
        mutableStateOf(viewModel.periodo)
    }

    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }

    LaunchedEffect(Unit) {
        viewModel.inicialiar(
            movimentacaoHolderDTO,
            selecaoUsuarioInicial,
            periodoInicial
        )
    }

    //val valorAnimado by animateFloatAsState(targetValue = valor.toFloat())


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(nome = movimentacaoHolderDTO.nome)
//        OutlinedCard(
//            modifier = Modifier.animateContentSize()
//        ) {
//            Text(text = "$textoValor: ${valorMonetario(valorAnimado.toDouble())}")
//        }
//        ButtonAbrirFiltros(
//            nomePeriodo = viewModel.periodo?.nome ?: "",
//            nomeSelecao = viewModel.textoSelecao.collectAsState().value
//        ) {
//            viewModel.abrirBottomSheetFiltros()
//        }
//        Divider(
//            modifier = Modifier.padding(
//                horizontal = 16.dp,
//                vertical = 16.dp
//            ),
//            thickness = 1.dp,
//            color = MaterialTheme.colorScheme.onBackground
//        )

        BarraPesquisa(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 2.dp),
            onChanged = {
                viewModel.onPesquisaChange(it)
            },
        )
        ListaDeFiltros(
            selecaoUsuario = viewModel.selecaoUsuario,
            periodo = viewModel.periodo,
            ordem = viewModel.ordem,
            onClickPeriodo = {
                viewModel.abrirBottomSheetPeriodo()
            },
            onClickTipo = {
                viewModel.abrirBottomSheetSelecao()
            },
            onClickOrdenarPor = {
                viewModel.abrirBottomSheetOrdem()
            }
        )

        ListaDeParcelas(
            parcelas = viewModel.parcelas.collectAsState().value,

        )

    }

    BottomSheet(
        isSheetOpen = viewModel.bottomSheetPeriodo,
        onDismiss = {
            viewModel.fecharBottomSheetPeriodo()
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Escolha um periodo")
            ListaDePeriodos(
                periodos = viewModel.periodos.collectAsState().value,
                periodoSelecionado = periodoEscolhidoPreConfirmacao,
            ) {
                periodoEscolhidoPreConfirmacao = it
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (periodoEscolhidoPreConfirmacao != null){
                        coroutineScope.launch {
                            viewModel.atualizarPeriodo(periodoEscolhidoPreConfirmacao!!)
                            viewModel.fecharBottomSheetPeriodo()
                        }
                    }else{
                        avisoDeErros(context, listOf("Nenhum periodo selecionado"))
                    }
                }
            ) {
                Text(text = "Confirmar")
            }
        }
    }
    BottomSheet(
        isSheetOpen = viewModel.bottomSheetSelecao,
        onDismiss = { viewModel.fecharBottomSheetSelecao() }
    ) {
        EscolhaSelecaoUsuarioNew(
            isCasa = movimentacaoHolderDTO.isCasa,
            macroCategorias = viewModel.macroCategorias.collectAsState().value,
            categorias = viewModel.categorias.collectAsState().value,
            needConfirmation = true,
            onDismiss = { viewModel.fecharBottomSheetSelecao() }
        ) {
            coroutineScope.launch {
                viewModel.atualizarSelecao(it)
            }
        }
    }
    BottomSheet(
        isSheetOpen = viewModel.bottomSheetOrdem,
        onDismiss = { viewModel.fecharBottomSheetOrdem() }
    ) {
        EscolhaOrdem(
            ordem = viewModel.ordem,
        ) {
            coroutineScope.launch {
                viewModel.atualizarOrdem(it)
            }
            viewModel.fecharBottomSheetOrdem()
        }
    }


//    BottomSheet(
//        isSheetOpen = viewModel.BottomSheetFiltros,
//        onDismiss = { viewModel.fecharBottomSheetFiltros() }) {
//        FormularioEditarFiltros(
//            movimentacaoHolderDTO = movimentacaoHolderDTO,
//            selecaoUsuarioInicial = viewModel.selecaoUsuario,
//            periodoInicial = viewModel.periodo,
//            onDismiss = { viewModel.fecharBottomSheetFiltros() }
//        ) { periodo, selecaoUsuario ->
//            coroutineScope.launch {
//                viewModel.atualizarSelecao(selecaoUsuario)
//                viewModel.atualizarPeriodo(periodo!!)
//                viewModel.atualizarParcelas()
//            }
//
//        }
//    }
//    BottomSheet(
//        isSheetOpen = isSheetOpenMovimentacao,
//        onDismiss = { isSheetOpenMovimentacao = false }) {
//        FormularioMovimentacao(
//            membroPreSelecionado = membroSelecionado,
//            onDismiss = {
//                isSheetOpenMovimentacao = false
//                movimentacaoSheet = null
//            },
//            onConfirm = { atualizar() },
//            movimentacao = movimentacaoSheet
//        )
//    }

}

@ExperimentalMaterial3Api
class Detalhes(
    private val movimentacaoHolder: MovimentacaoHolder,
    private val selecaoUsuarioInicial: SelecaoUsuario,
    private val periodoInicial: Periodo
) {
    /*
    private val membroSelecionado: MovimentacaoHolder by mutableStateOf( movimentacaoHolder) // getMembro(movimentacaoHolder.id) )
    private var periodosUtilizados by mutableStateOf(getPeriodosFromMovimentacoes_ComAnos(membroSelecionado.movimentacoes))
    private var periodoSelecionado by mutableStateOf(
        getPeriodoFromNome(
            periodoInicial.nome,
            periodosUtilizados
        ) ?: periodoInicial
    )
    private var selecaoUsuario by mutableStateOf<SelecaoUsuario>(
        selecaoUsuarioInicial
    )
    private lateinit var movimentacoes: List<Movimentacao> /*by mutableStateOf(
        getMovimentacoes(
            movimentacaoHolder = membroSelecionado,
            periodo = periodoSelecionado,
            selecaoUsuario = selecaoUsuario
        )
    )

   */
    private var valor: Double = 0.0/* = getValorCom_SelecaoUsuario_MovimentacaoHolder_Periodo(
        selecaoUsuario = selecaoUsuario,
        movimentacaoHolder = membroSelecionado,
        periodo = periodoSelecionado,
    )
    */
    private var textoPesquisa by mutableStateOf("")


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun content() {
        val context = LocalContext.current
        var isSheetOpenFiltro by rememberSaveable { mutableStateOf(false) }
        var isSheetOpenMovimentacao by rememberSaveable { mutableStateOf(false) }
        var movimentacaoSheet by rememberSaveable { mutableStateOf<Movimentacao?>(null) }
        var background = if (isSystemInDarkTheme()) {
            backgroundDark
        } else {
            backgroundLight
        }
        var textoValor by remember(selecaoUsuario) {
            when (selecaoUsuario) {
                is SelecaoUsuario.tipoSelecionado -> {
                    mutableStateOf((selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo.getNome_Trocando_Todos_Por_Saldo())
                }

                is SelecaoUsuario.categoriaSelecionada -> {
                    if ((selecaoUsuario as SelecaoUsuario.categoriaSelecionada).categoria.isGasto) {
                        mutableStateOf("Gasto")
                    } else {
                        mutableStateOf("Recebimento")
                    }

                }

                is SelecaoUsuario.macroCategoriaSelecionada -> {
                    mutableStateOf((selecaoUsuario as SelecaoUsuario.macroCategoriaSelecionada).macroCategoria.nome)
                }
            }
        }
        var textoSelecao by remember(selecaoUsuario) {
            when (selecaoUsuario) {
                is SelecaoUsuario.tipoSelecionado -> {
                    mutableStateOf((selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo.toString())
                }

                is SelecaoUsuario.categoriaSelecionada -> {
                    mutableStateOf((selecaoUsuario as SelecaoUsuario.categoriaSelecionada).categoria.nome)
                }

                is SelecaoUsuario.macroCategoriaSelecionada -> {
                    mutableStateOf((selecaoUsuario as SelecaoUsuario.macroCategoriaSelecionada).macroCategoria.nome)
                }
            }

        }
        var isTodos by remember(selecaoUsuario) {
            if (selecaoUsuario is SelecaoUsuario.tipoSelecionado) {
                if ((selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo == Tipo.TODOS) {
                    mutableStateOf(true)
                } else {
                    mutableStateOf(false)
                }
            } else {
                mutableStateOf(false)
            }
        }

        val valorAnimado by animateFloatAsState(targetValue = valor.toFloat())


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(nome = membroSelecionado.nome)
            OutlinedCard(
                modifier = Modifier.animateContentSize()
            ) {
                Text(text = "$textoValor: ${valorMonetario(valorAnimado.toDouble())}")
            }
            ButtonAbrirFiltros(
                nomePeriodo = periodoSelecionado.nome,
                nomeSelecao = textoSelecao
            ) {
                isSheetOpenFiltro = true
            }
            Divider(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            BarraPesquisa(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 2.dp),
                onChanged = {
                    textoPesquisa = it
                    atualizar()
                },
            )

            NewListaDeMovimentacao(
                movimentacoes = movimentacoes,
                isAlways = isTodos,
                onItemClick = {
                    isSheetOpenMovimentacao = true
                    movimentacaoSheet = it
                }

            )

        }
        BottomSheet(
            isSheetOpen = isSheetOpenFiltro,
            onDismiss = { isSheetOpenFiltro = false }) {
            FormularioEditarFiltros(
                movimentacaoHolder = membroSelecionado,
                selecaoUsuarioInicial = selecaoUsuario,
                periodoInicial = periodoSelecionado,
                onDismiss = { isSheetOpenFiltro = false }
            ) { periodo, selecaoUsuario ->
                this.periodoSelecionado = periodo!!
                this.selecaoUsuario = selecaoUsuario
                atualizar()

            }
        }
        BottomSheet(
            isSheetOpen = isSheetOpenMovimentacao,
            onDismiss = { isSheetOpenMovimentacao = false }) {
            FormularioMovimentacao(
                membroPreSelecionado = membroSelecionado,
                onDismiss = {
                    isSheetOpenMovimentacao = false
                    movimentacaoSheet = null
                },
                onConfirm = { atualizar() },
                movimentacao = movimentacaoSheet
            )
        }


    }

    fun atualizar() {/*
        periodosUtilizados = getPeriodosFromMovimentacoes_ComAnos(membroSelecionado.movimentacoes)
        periodoSelecionado = getPeriodoFromNome(periodoSelecionado.nome, periodosUtilizados)
            ?: getUltimoPeriodoUtilizado(periodosUtilizados)
        movimentacoes = if (textoPesquisa.isEmpty()) {
            getMovimentacoes(
                movimentacaoHolder = membroSelecionado,
                periodo = periodoSelecionado,
                selecaoUsuario = selecaoUsuario
            )
        } else {
            getMovimentacoes(
                movimentacaoHolder = membroSelecionado,
                periodo = periodoSelecionado,
                selecaoUsuario = selecaoUsuario
            ).filter {
                it.descricao.contains(textoPesquisa, ignoreCase = true)
            }
        }
        valor = getValorCom_SelecaoUsuario_MovimentacaoHolder_Periodo(
            selecaoUsuario = selecaoUsuario,
            movimentacaoHolder = membroSelecionado,
            periodo = periodoSelecionado,
        )
        */
    }

     */

}


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DetalhesPreview() {
    val context = LocalContext.current
    val viewModel = PesquisaParcelasViewModel(dataSourse = RoomDataSources(context))
    val periodo = Periodo(ano = 2025)
    val selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.GASTO)
    val movimentacaoHolderDTO = MovimentacaoHolderDTO(
        id = VariaveisDeAmbiente.casaId,
        nome = "Casa",
        isCasa = true
    )

    PesquisaParcelas(
        movimentacaoHolderDTO = movimentacaoHolderDTO,
        selecaoUsuarioInicial = selecaoUsuario,
        periodoInicial = periodo,
        viewModel = viewModel
    )



}