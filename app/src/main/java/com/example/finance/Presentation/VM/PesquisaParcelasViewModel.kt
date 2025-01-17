package com.example.finance.Presentation.VM

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.Ordem
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import com.example.finance.b_useCase.ParcelaService
import com.example.finance.b_useCase.PeriodoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class PesquisaParcelasViewModel(
    val dataSourse: InterfaceDataSources,
): ViewModel() {
    val parcelaService = ParcelaService(dataSourse.parcelaDataSource)
    val periodoService = PeriodoService()
    val macroCategoriaService = MacroCategoriaService(dataSourse.macroCategoriaDataSource)
    val categoriaService = CategoriaService(dataSourse.categoriaDataSource)

    var movimentacaoHolderDTO: MovimentacaoHolderDTO? = null
        private set
    var selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS)
        private set
    var periodo: Periodo? = null
        private set
    var ordem: Ordem = Ordem.MaisRecente
        private set

    var _textoSelecao = MutableStateFlow("Carregando")
    var textoSelecao: StateFlow<String> = _textoSelecao

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    private val _parcelas = MutableStateFlow<List<Parcela>>(emptyList())
    private val _periodos = MutableStateFlow<List<Periodo>>(periodoService.gerarPeriodosDoUltimoAno())
    private val _pesquisa = MutableStateFlow("")

    private val _parcelasFiltradas = _parcelas.combine(_pesquisa) { parcelas, pesquisa ->
        if (pesquisa.isBlank()) {
            parcelas
        } else {
            parcelas.filter { parcela ->
                parcela.descricao.contains(pesquisa, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val parcelas: StateFlow<List<Parcela>> = _parcelasFiltradas
    val periodos: StateFlow<List<Periodo>> = _periodos
    val _macroCategorias = MutableStateFlow<List<MacroCategoria>>(emptyList())
    val macroCategorias: StateFlow<List<MacroCategoria>> = _macroCategorias

    val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias

    var BottomSheetFiltros: Boolean by mutableStateOf(false)
        private set

    var bottomSheetSelecao: Boolean by mutableStateOf(false)
        private set

    var bottomSheetPeriodo: Boolean by mutableStateOf(false)
        private set

    var bottomSheetOrdem: Boolean by mutableStateOf(false)
        private set

    fun onPesquisaChange(novaPesquisa: String) {
        _pesquisa.value = novaPesquisa
    }

    private suspend fun atualizarParcelas() {
        try {
            val resultado = parcelaService.getParcelas(
                movimentacaoHolderDTO = movimentacaoHolderDTO!!,
                selecaoUsuario = selecaoUsuario,
                periodo = periodo
            )
            if (resultado is Resultado.Sucesso){
                if (!resultado.data.isNullOrEmpty()){
                    _parcelas.value = when(ordem){
                        Ordem.MaisRecente -> resultado.data.sortedByDescending { it.data.toTimeStamp() }
                        Ordem.MaisAntigo -> resultado.data.sortedBy { it.data.toTimeStamp() }
                        Ordem.MenorValor -> resultado.data.sortedBy { it.valor }
                        Ordem.MaiorValor -> resultado.data.sortedByDescending { it.valor }
                    }
                     resultado.data
                }else{
                    acionarAviso(listOf("Nenhuma parcela encontrada"))
                    _parcelas.value = emptyList()
                }
            }else{
                acionarAviso(listOf("Erro ao buscar parcelas"))
            }
        }catch (e: Exception){
            acionarAviso(e.message?.let { listOf(it) } ?: emptyList())
        }
    }

    suspend fun inicialiar(
        movimentacaoHolderDTOInicial: MovimentacaoHolderDTO,
        selecaoUsuarioInicial: SelecaoUsuario,
        periodoInicial: Periodo
        ){
        movimentacaoHolderDTO = movimentacaoHolderDTOInicial
        periodo = periodoInicial
        atualizarSelecao(selecaoUsuarioInicial)
        atualizarParcelas()
        val resultadoMacroCategoria = macroCategoriaService.getMacroCategorias(
            idCasa = VariaveisDeAmbiente.casaId,
            afetaCasa = null,
            afetaPessoa = null,
            isGasto = null
        )
        if (resultadoMacroCategoria is Resultado.Sucesso){
            if (!resultadoMacroCategoria.data.isNullOrEmpty()){
                _macroCategorias.value = resultadoMacroCategoria.data
            }else{
                acionarAviso(listOf("Nenhuma macro categoria encontrada"))
                _macroCategorias.value = emptyList()
            }
        }else{
            acionarAviso(listOf("Erro ao buscar macro categorias"))
            }

        val resultadoCategoria = categoriaService.getCategoriasFiltradas(
            idCasa = VariaveisDeAmbiente.casaId,
        )
        if (resultadoCategoria is Resultado.Sucesso){
            if (!resultadoCategoria.data.isNullOrEmpty()){
                _categorias.value = resultadoCategoria.data
            }else{
                acionarAviso(listOf("Nenhuma categoria encontrada"))
                _categorias.value = emptyList()
            }
        }else{
            acionarAviso(listOf("Erro ao buscar categorias"))
        }


    }

    suspend fun atualizarSelecao(novaSelecao: SelecaoUsuario){
        selecaoUsuario = novaSelecao
        atualizarTextoSelecao()
        atualizarParcelas()
    }
    suspend fun atualizarPeriodo(novoPeriodo: Periodo){
        periodo = novoPeriodo
        atualizarParcelas()
    }

    private suspend fun atualizarTextoSelecao(){
        _textoSelecao.value = when (selecaoUsuario) {
            is SelecaoUsuario.tipoSelecionado -> {
                (selecaoUsuario as SelecaoUsuario.tipoSelecionado).tipo.toString()
            }
            is SelecaoUsuario.categoriaSelecionada -> {
                (selecaoUsuario as SelecaoUsuario.categoriaSelecionada).categoria.nome
            }
            is SelecaoUsuario.macroCategoriaSelecionada -> {
                (selecaoUsuario as SelecaoUsuario.macroCategoriaSelecionada).macroCategoria.nome
            }
        }
    }

    suspend fun atualizarOrdem(novaOrdem: Ordem){
        ordem = novaOrdem
        atualizarParcelas()
    }

    fun abrirBottomSheetFiltros(){
        BottomSheetFiltros = true
    }

    fun fecharBottomSheetFiltros(){
        BottomSheetFiltros = false
    }

    fun abrirBottomSheetPeriodo(){
        bottomSheetPeriodo = true
    }

    fun fecharBottomSheetPeriodo(){
        bottomSheetPeriodo = false
    }

    fun abrirBottomSheetSelecao(){
        bottomSheetSelecao = true
    }

    fun fecharBottomSheetSelecao(){
        bottomSheetSelecao = false
    }

    fun abrirBottomSheetOrdem(){
        bottomSheetOrdem = true
    }

    fun fecharBottomSheetOrdem(){
        bottomSheetOrdem = false
    }

    private fun acionarAviso(erros: List<String>){
        _mensagemAviso.value = erros
    }

    fun limparAviso(){
        _mensagemAviso.value = emptyList()
    }
}
