package com.example.finance.lvl3.telas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.PeriodoComValor
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl2.Periodo.PeriodoController
import com.example.finance.lvl2.Utils.utils
import com.example.finance.lvl3.componentes.formularios.FormularioEditarFiltros
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.buttons.ButtonAbrirFiltros
import com.example.finance.lvl3.widgets.graficos.PeriodoValueFormatter
import com.example.finance.lvl3.widgets.graficos.SimpleChart
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.VerticalPosition
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.CorneredShape

class Graficos(
    movimentacaoHolder: MovimentacaoHolder,
    selecaoUsuarioInicial: SelecaoUsuario,
) {
    /*
    private val movimentacaoHolder: MovimentacaoHolder by mutableStateOf(
        getMovimentacaoHolderById(movimentacaoHolder.id)
    )
    private var periodosComValores by mutableStateOf(
        PeriodoController().getPeriodosComValor_SemAnosInclusos(movimentacaoHolder, selecaoUsuarioInicial)
    )
    private var selecaoUsuario by mutableStateOf(selecaoUsuarioInicial)

     */

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun content() {
        var isSheetOpenFiltro by rememberSaveable { mutableStateOf(false) }
        var background = if (isSystemInDarkTheme()) backgroundDark else backgroundLight
/*
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(nome = movimentacaoHolder.nome)
            ButtonAbrirFiltros(
                nomePeriodo = null,
                nomeSelecao = textoSelecao
            ) {
                isSheetOpenFiltro = true
            }
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SimpleChart(periodosComValor = periodosComValores)
            }
        }

        BottomSheet(
            isSheetOpen = isSheetOpenFiltro,
            onDismiss = {
                isSheetOpenFiltro = false
                atualizar()
            }
        ) {
            FormularioEditarFiltros(
                movimentacaoHolder = movimentacaoHolder,
                selecaoUsuarioInicial = selecaoUsuario,
                periodoInicial = null,
                onDismiss = { isSheetOpenFiltro = false },
                onConfirm = { _, novaSelecaoUsuario ->
                    selecaoUsuario = novaSelecaoUsuario
                    atualizar()
                }
            )
        }

 */
    }

    fun atualizar() {
        /*
        periodosComValores = PeriodoController().getPeriodosComValor_SemAnosInclusos(movimentacaoHolder, selecaoUsuario)

         */
    }
}


@Composable
fun GraficoDeColuna(
    movimentacaoHolderID: Int,
    selecaoUsuarioInicial: SelecaoUsuario
) {
    /*
    val movimentacaoHolder = getMovimentacaoHolderById(movimentacaoHolderID)
    var selecaoUsuario by remember { mutableStateOf(selecaoUsuarioInicial) }
    var periodosComValores by remember { mutableStateOf(emptyList<PeriodoComValor>()) }

    LaunchedEffect(selecaoUsuario) {
        periodosComValores = PeriodoController()
            .getPeriodosComValor_SemAnosInclusos(movimentacaoHolder, selecaoUsuario)
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

    var isSheetOpenFiltro by rememberSaveable { mutableStateOf(false) }
    val background = if (isSystemInDarkTheme()) backgroundDark else backgroundLight

    val margem = 0.25
    val maiorValor = utils().getMaiorValorFromPeriodosComValor(periodosComValores)
    val valorMaximoY = utils().arredondarValor(((maiorValor * (1 + margem)).toInt()))
    val menorValor = utils().getMenorValorFromPeriodosComValor(periodosComValores)
    val valorMinimoY = utils().arredondarValor(((menorValor * (1 - margem)).toInt()))

    var tipo: Tipo = when (selecaoUsuario) {
        is SelecaoUsuario.tipoSelecionado -> {
            (selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo
        }

        is SelecaoUsuario.categoriaSelecionada -> {
            if ((selecaoUsuario as SelecaoUsuario.categoriaSelecionada).categoria.isGasto) {
                Tipo.GASTO
            } else {
                Tipo.RECEBIMENTO
            }
        }

        is SelecaoUsuario.macroCategoriaSelecionada -> {
            if ((selecaoUsuario as SelecaoUsuario.macroCategoriaSelecionada).macroCategoria.isGasto) {
                Tipo.GASTO
            } else {
                Tipo.RECEBIMENTO
            }
        }
    }
    val modelProducer = remember { CartesianChartModelProducer() }
    val periodos = periodosComValores.map { it.periodo }
    val formatter = PeriodoValueFormatter(periodos)


    LaunchedEffect(selecaoUsuario) {
        modelProducer.runTransaction {
            columnSeries {
                series(x = periodosComValores.mapIndexed { index, _ -> index.toFloat() }, y = periodosComValores.map { it.valor.toFloat() } )
                println()
            }
        }
    }

    val corColunas = when (tipo) {
        Tipo.GASTO -> Color.Red
        Tipo.RECEBIMENTO -> Color.Green
        Tipo.TODOS -> Color.Blue
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(nome = movimentacaoHolder.nome)
        ButtonAbrirFiltros(
            nomePeriodo = null,
            nomeSelecao = textoSelecao
        ) {
            isSheetOpenFiltro = true
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (periodosComValores.isNotEmpty()) {
                CartesianChartHost(
                    chart = rememberCartesianChart(
                        rememberColumnCartesianLayer(
                            ColumnCartesianLayer.ColumnProvider.series(
                                rememberLineComponent(
                                    color = corColunas,
                                    thickness = 16.dp,
                                    shape = CorneredShape.rounded(40)
                                )
                            ),
                            dataLabel = TextComponent(),
                            dataLabelVerticalPosition = VerticalPosition.Top,
                            rangeProvider = CartesianLayerRangeProvider.fixed(minY = valorMinimoY.toDouble(), maxY = valorMaximoY.toDouble()) // Define o intervalo fixo de Y
                        ),
                        startAxis = VerticalAxis.rememberStart(
                            itemPlacer = VerticalAxis.ItemPlacer.step(shiftTopLines = true),
                            valueFormatter = remember { CartesianValueFormatter.decimal() }
                        ),
                        bottomAxis = HorizontalAxis.rememberBottom(
                            valueFormatter = formatter
                        )
                    ),
                    modelProducer = modelProducer
                )
            } else {
                Text(
                    text = "Nenhum dado disponível para exibição.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(
            onClick = {
                /*
                MovimentacaoController().adicionarMovimentacao(
                assunto = "Movimentacao 1",
                valorStr = "10.0",
                data = Data(15, 2, 2024),
                categoria = CategoriaDebbug().getCategoriaGastoFixo(),
                movimentacaoHolder = getCasa()
            )

                 */
             }) {  }

        BottomSheet(
            isSheetOpen = isSheetOpenFiltro,
            onDismiss = {
                isSheetOpenFiltro = false
            }
        ) {
            FormularioEditarFiltros(
                movimentacaoHolder = movimentacaoHolder,
                selecaoUsuarioInicial = selecaoUsuario,
                periodoInicial = null,
                onDismiss = { isSheetOpenFiltro = false },
                onConfirm = { _, novaSelecaoUsuario ->
                    selecaoUsuario = novaSelecaoUsuario
                }
            )
        }
    }

     */
}

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun GraficoPreview() {
    /*
    LoginController().testeCadastro()
    CategoriaDebbug().gerarCategoriasBasicas()
    val movimentacaoController = MovimentacaoController()
    movimentacaoController.adicionarMovimentacao(
        assunto = "Movimentacao 1",
        valorStr = "10.0",
        data = Data(15, 1, 2024),
        categoria = CategoriaDebbug().getCategoriaGastoFixo(),
        movimentacaoHolder = getCasa()
    )
    movimentacaoController.adicionarMovimentacao(
        assunto = "Movimentacao 2",
        valorStr = "20.0",
        data = Data(15, 1, 2024),
        categoria = CategoriaDebbug().getCategoriaGastoFixo(),
        movimentacaoHolder = getCasa()
    )
    movimentacaoController.adicionarMovimentacao(
        assunto = "Movimentacao 3",
        valorStr = "15.0",
        data = Data(15, 1, 2024),
        categoria = CategoriaDebbug().getCategoriaRecebimentoFixo(),
        movimentacaoHolder = getCasa()
    )




    FinanceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GraficoDeColuna(
                movimentacaoHolderID = getCasa().id,
                selecaoUsuarioInicial = SelecaoUsuario.tipoSelecionado(Tipo.TODOS)
            )
        }
    }

     */
}