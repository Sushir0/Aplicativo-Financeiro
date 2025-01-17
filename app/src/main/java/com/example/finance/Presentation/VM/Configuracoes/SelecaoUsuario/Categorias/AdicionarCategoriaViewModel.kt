package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.a_Domain.Resultado
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.Categoria.CategoriaServiceInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AdicionarCategoriaViewModel(
    dataSources: InterfaceDataSources
): ViewModel() {
    private val categoriaService = CategoriaService(dataSources.categoriaDataSource)


    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    private val _nome = mutableStateOf("")
    val nome: State<String> = _nome

    fun onNomeChange(novoNome: String) {
        _nome.value = novoNome
    }

    fun personalizarPrevisao(){

    }

    suspend fun onSave(macroCategoriaId: String, onDismiss: () -> Unit) {
        val resultadoCriacao = categoriaService.criarCategoria(
            nome = nome.value,
            macroCategoriaId = macroCategoriaId
        )
        if (resultadoCriacao is Resultado.Falha) {
            acionarAviso(resultadoCriacao.errors)
            return
        }
        acionarAviso(listOf("Categoria criada com sucesso"))

        delay(10)
        onDismiss()
    }

    fun limparAviso() {
        _mensagemAviso.value = emptyList()
    }

    private fun acionarAviso(avisos: List<String>) {
        _mensagemAviso.value = avisos
    }



}