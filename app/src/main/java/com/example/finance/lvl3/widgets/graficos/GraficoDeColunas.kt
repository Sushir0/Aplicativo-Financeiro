package com.example.finance.lvl3.widgets.graficos

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.model.MetaDados.PeriodoComValor
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.lvl2.Utils.utils
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.CartesianMeasuringContext
import com.patrykandpatrick.vico.core.cartesian.axis.Axis
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

class GraficoColuna(
    modifier: Modifier = Modifier,
    selecaoUsuarioInicial: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS)
    ){
    var selecaoUsuario: SelecaoUsuario = selecaoUsuarioInicial
    var periodosComValor: List<PeriodoComValor> = emptyList()

    @Composable
    fun create(
        periodosComValor: List<PeriodoComValor>
    ){
        this.periodosComValor = periodosComValor

    }

    fun atualizarValores(
        periodosComValor: List<PeriodoComValor>
    ){

        
    }


}

@Composable
fun SimpleChart(
    periodosComValor: List<PeriodoComValor>,
    tipo: Tipo = Tipo.TODOS,
    modifier: Modifier = Modifier,
) {
    val margem = 0.25f

    val maiorValor = utils().getMaiorValorFromPeriodosComValor(periodosComValor)
    val valorMaximoY = utils().arredondarValor(((maiorValor * (1 + margem)).toInt()))

    val menorValor = utils().getMenorValorFromPeriodosComValor(periodosComValor)
    val valorMinimoY = utils().arredondarValor(((menorValor * (1 - margem)).toInt()))

    val corColunas = when (tipo) {
        Tipo.GASTO -> Color.Red
        Tipo.RECEBIMENTO -> Color.Green
        Tipo.TODOS -> Color.Blue
    }


    // Configurando o modelo de dados do gráfico
    val modelProducer = remember { CartesianChartModelProducer() }

    val periodos = periodosComValor.map { it.periodo }
    val formatter = PeriodoValueFormatter(periodos)


    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            columnSeries {
                series(x = periodosComValor.mapIndexed { index, _ -> index.toFloat() }, y = periodosComValor.map { it.valor.toFloat() } )
                println()
            }
        }
    }

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
        modelProducer = modelProducer,
        modifier = modifier
    )
}

class PeriodoValueFormatter(
    private val periodos: List<Periodo>
) : CartesianValueFormatter {

    override fun format(
        context: CartesianMeasuringContext,
        value: Double,
        verticalAxisPosition: Axis.Position.Vertical?
    ): CharSequence {
        val index = value.toInt()
        return if (index in periodos.indices) {
            periodos[index].nome // Acessa o nome do período com base no índice
        } else {
            "Índice não encontrado" // Valor vazio caso o índice esteja fora do intervalo
        }
    }
}

@Composable
fun ExampleUsage() {
    val periodosComValor = listOf(
        PeriodoComValor(Periodo(1, 2024), 4000f),
        PeriodoComValor(Periodo(2, 2024), -1500f),
        PeriodoComValor(Periodo(3, 2024), 2389f),
        PeriodoComValor(Periodo(4, 2024), 2057f),
        PeriodoComValor(Periodo(5, 2024), 4000f),
        PeriodoComValor(Periodo(6, 2024), 2366f),
        PeriodoComValor(Periodo(7, 2024), 2389f),
        PeriodoComValor(Periodo(8, 2024), 2057f),
        PeriodoComValor(Periodo(9, 2024), 4000f),
        PeriodoComValor(Periodo(10, 2024), 2366f),
        PeriodoComValor(Periodo(11, 2024), 2389f),
        PeriodoComValor(Periodo(12, 2024), 2057f)
    )
    SimpleChart(periodosComValor = periodosComValor, tipo = Tipo.TODOS)
}


//preview no modo Light
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun GraficoDeColunasPrevLight() {
    ExampleUsage()
}

//preview no modo noturno
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, backgroundColor = 0xFFFFFFFF)
@Composable
private fun GraficoDeColunasPrevDark() {
    ExampleUsage()
}