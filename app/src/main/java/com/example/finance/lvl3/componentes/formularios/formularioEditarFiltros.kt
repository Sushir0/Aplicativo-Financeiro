package com.example.finance.lvl3.componentes.formularios

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.EscolherSelecaoUsuarioViewModel
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.b_useCase.ParcelaService
import com.example.finance.b_useCase.PeriodoService
import com.example.finance.lvl3.componentes.listas.ListaDePeriodos
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.valorMonetario
import com.example.finance.lvl3.widgets.Escolher.EscolhaSelecaoUsuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEditarFiltros(
    movimentacaoHolderDTO: MovimentacaoHolderDTO,
    selecaoUsuarioInicial: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
    onDismiss: () -> Unit = { },
    periodoInicial: Periodo? = null,
    onConfirm: (Periodo?, SelecaoUsuario)-> Unit = { periodo, selecaoUsuario -> }
) {
    val context = LocalContext.current
    val parcelaService = ParcelaService(RoomDataSources(context).parcelaDataSource)
    val periodos by remember {
        mutableStateOf(PeriodoService().gerarPeriodosDoUltimoAno())
    }
    var periodoSelecionado by remember {
        if(periodoInicial == null){
            mutableStateOf(periodos.first())
        }else{
            mutableStateOf(periodoInicial)
        }
    }
    var selecaoUsuario by remember {
        mutableStateOf(selecaoUsuarioInicial)
    }
    var textoValor by remember(selecaoUsuario, periodoSelecionado) {
        when (selecaoUsuario) {
            is SelecaoUsuario.tipoSelecionado -> {
                mutableStateOf((selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo.getNome_Trocando_Todos_Por_Saldo())
            }
            is SelecaoUsuario.categoriaSelecionada -> {
                mutableStateOf((selecaoUsuario as SelecaoUsuario.categoriaSelecionada).categoria.nome)
            }
            is SelecaoUsuario.macroCategoriaSelecionada -> {
                mutableStateOf((selecaoUsuario as SelecaoUsuario.macroCategoriaSelecionada).macroCategoria.nome)
            }
        }
    }
    var valor by remember(selecaoUsuario, periodoSelecionado) {
        mutableStateOf(0.0)
    }

    LaunchedEffect(Unit){
        val resultado = parcelaService.getParcelas(
            movimentacaoHolderDTO = movimentacaoHolderDTO,
            selecaoUsuario = selecaoUsuario,
            periodo = periodoSelecionado
        )
        if(resultado is Resultado.Sucesso){
            if(!resultado.data.isNullOrEmpty()){
                valor = resultado.data.sumOf { it.valor }
            }else{
                avisoDeErros(context = context, listOf("Nenhuma parcela encontrada"))
            }
        }else if(resultado is Resultado.Falha){
            avisoDeErros(context = context, resultado.errors)
        }

    }
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Filtros",
            style = MaterialTheme.typography.headlineLarge,
        )
        OutlinedCard(modifier = Modifier.padding(8.dp)) {
            Text(text = "$textoValor: ${valorMonetario(valor)}")
        }

        if(periodoInicial != null){
            Box(modifier = Modifier.padding(12.dp)){
                ListaDePeriodos(
                    periodos = periodos,
                    periodoSelecionado = periodoSelecionado
                ) {
                    periodoSelecionado = it

                }
            }
        }
        Box(modifier = Modifier.padding(8.dp)){
            val escolhaSelecaoViewModel = EscolherSelecaoUsuarioViewModel(RoomDataSources(context))
            EscolhaSelecaoUsuario(
                movimentacaoHolder = movimentacaoHolderDTO.toMovimentacaoHolder(),
                viewModel = escolhaSelecaoViewModel,
                selecaoUsuarioInicial = selecaoUsuario,
                onSelecaoChange = {
                    selecaoUsuario = it
                    textoValor = it.toString()
                }
            )
        }
        OutlinedCard(
            onClick = {
                onConfirm(periodoSelecionado, selecaoUsuario)
                onDismiss()
                      },
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Filtrar",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun FormularioEditarFiltrosPreview() {
    /*
    LoginController().testeCadastro()
    MovimentacaoController().testeAdicionarMovimentacao(Login.getCasaLogada())
    CategoriaDebbug().gerarCategoriasBasicas()

        FormularioEditarFiltros(
        Login.getCasaLogada()
    )

     */
}