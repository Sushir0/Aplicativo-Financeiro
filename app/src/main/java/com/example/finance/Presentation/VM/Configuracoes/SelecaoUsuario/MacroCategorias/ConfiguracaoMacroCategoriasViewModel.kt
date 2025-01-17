package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias

import FiltrosMacroCategoria
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaServiceInterface
import com.example.finance.lvl3.activitys.Configuracoes.abrirConfig_Categorias
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfiguracaoMacroCategoriasViewModel(
    val dataSource: InterfaceDataSources
): ViewModel() {
    val macroCategoriaService = MacroCategoriaService(dataSource.macroCategoriaDataSource)

    var titulo = mutableStateOf("Carregando")

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    private val _macroCategoriasStateFlow = MutableStateFlow<List<MacroCategoria>>(emptyList())
    val macroCategoriasStateFlow: StateFlow<List<MacroCategoria>> = _macroCategoriasStateFlow

    var bottomSheetAdicionar = mutableStateOf(false)
        private set

    var filtros = FiltrosMacroCategoria()
        private set

    suspend fun setFiltro(filtro: FiltrosMacroCategoria){
        filtros = filtro
        setMacroCategorias(idCasa, filtro)
    }


    suspend fun inicializar(idCasa: String){
        setMacroCategorias(idCasa, filtros)
        titulo.value = "Macro categorias"
        this.idCasa = idCasa
    }

    lateinit var idCasa: String

    private suspend fun setMacroCategorias(idCasa: String, filtro: FiltrosMacroCategoria) {
        val resultado = macroCategoriaService.getMacroCategorias(idCasa = idCasa)
        if (resultado is Resultado.Falha) {
            acionarMensagem(resultado.errors)
            return
        }
        val macroCategorias = (resultado as Resultado.Sucesso).data.orEmpty()
        val macroCategoriasFiltradas = macroCategorias.filter {
            //it.isAtivo == filtro.isAtivo &&
            (it.afetaCasa == filtro.afetaCasa || filtro.afetaCasa == null) &&
            (it.afetaPessoa == filtro.afetaPessoa || filtro.afetaPessoa == null) &&
            (it.isGasto == filtro.isGasto || filtro.isGasto == null)
        }
        _macroCategoriasStateFlow.value = macroCategoriasFiltradas
    }


    fun abrirBottomSheetAdicionar() {
        bottomSheetAdicionar.value = true
    }

    suspend fun fecharBottomSheetAdicionar() {
        bottomSheetAdicionar.value = false
        if(idCasa.isNotEmpty()){
            setMacroCategorias(idCasa, filtros)
        }
    }

    fun selecionarMacroCategoria(context: Context, macroCategoria: MacroCategoria) {
        abrirConfig_Categorias(context, macroCategoria.id, dataSource.type)
    }

    private fun acionarMensagem(errors: List<String>) {
        _mensagemAviso.value = errors
    }
    fun limparMensagem(){
        _mensagemAviso.value = emptyList()
    }
}