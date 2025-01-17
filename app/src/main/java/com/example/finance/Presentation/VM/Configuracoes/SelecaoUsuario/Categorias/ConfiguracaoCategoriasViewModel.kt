package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.Categoria.CategoriaServiceInterface
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaServiceInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfiguracaoCategoriasViewModel(
    val dataSource: InterfaceDataSources,
) : ViewModel() {
    val macroCategoriaService = MacroCategoriaService(dataSource.macroCategoriaDataSource)
    val categoriaService = CategoriaService(dataSource.categoriaDataSource)

    private val _errorMessages = MutableStateFlow<List<String>>(emptyList())
    val errorMessages: StateFlow<List<String>> = _errorMessages

    var macroCategoria = mutableStateOf<MacroCategoria?>(null)
        private set

    private val _categoriasStateFlow = MutableStateFlow<List<Categoria>>(emptyList())
    val categoriasStateFlow: StateFlow<List<Categoria>> = _categoriasStateFlow

    var bottomSheetAdicionar = mutableStateOf(false)
        private set

    var bottomSheetDetalhes = mutableStateOf(false)
        private set

    var categoriaSelecionada = mutableStateOf<Categoria?>(null)
        private set

    suspend fun setMacroCategoria(idMacroCategoria: String) {
        val resultadoMacroCategoria = macroCategoriaService.getMacroCategoria(idMacroCategoria)
        if (resultadoMacroCategoria is Resultado.Falha) {
            acionarErrors(resultadoMacroCategoria.errors)
            return
        }
        val macroCategoriaResult = (resultadoMacroCategoria as Resultado.Sucesso).data
        if (macroCategoriaResult == null) {
            acionarErrors(listOf("Macro categoria não encontrada"))
            return
        }
        macroCategoria.value = macroCategoriaResult
        setCategorias(idMacroCategoria)
    }

    private suspend fun setCategorias(idMacroCategoria: String) {
        val resultadoCategorias = categoriaService.getCategoriasFromMacro(idMacroCategoria)
        if (resultadoCategorias is Resultado.Falha) {
            acionarErrors(resultadoCategorias.errors)
            return
        }

        val categoriasList = (resultadoCategorias as Resultado.Sucesso).data.orEmpty()
        if (categoriasList.isEmpty()){
            acionarErrors(listOf("Não foram encontradas categorias para esta macro categoria"))
            return
        }

        _categoriasStateFlow.value = categoriasList
    }

    fun selecionarCategoria(categoria: Categoria) {
        categoriaSelecionada.value = categoria
        abrirBottomSheetDetalhes()
    }

    fun abrirBottomSheetAdicionar() {
        bottomSheetAdicionar.value = true
    }

    fun abrirBottomSheetDetalhes() {
        bottomSheetDetalhes.value = true
    }

    suspend fun fecharBottomSheetDetalhes() {
        bottomSheetDetalhes.value = false
        setCategorias(macroCategoria.value!!.id)
    }

    suspend fun fecharBottomSheetAdicionar() {
        bottomSheetAdicionar.value = false
        setCategorias(macroCategoria.value!!.id)
    }

    private fun acionarErrors(errors: List<String>) {
        _errorMessages.value = errors
    }

    fun limparErros(){
        _errorMessages.value = emptyList()
    }
}