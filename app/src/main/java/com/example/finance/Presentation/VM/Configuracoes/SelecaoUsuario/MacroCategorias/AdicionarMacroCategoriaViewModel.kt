package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaServiceInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AdicionarMacroCategoriaViewModel(
    val dataSource: InterfaceDataSources,
): ViewModel() {
    val macroCategoriaService = MacroCategoriaService(dataSource.macroCategoriaDataSource)

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    private val _nome = mutableStateOf("")
    val nome: State<String> = _nome

    val isGasto = mutableStateOf(false)
    val afetaPessoa = mutableStateOf(false)
    val afetaCasa = mutableStateOf(false)

    suspend fun onSave(idCasa: String, onDismiss: () -> Unit) {
        val resultadoCriacao = macroCategoriaService.criarMacroCategoria(
            idCasa = idCasa,
            nome = nome.value,
            isGasto = isGasto.value,
            afetaPessoa = afetaPessoa.value,
            afetaCasa = afetaCasa.value
        )
        if (resultadoCriacao is Resultado.Falha) {
            acionarAviso(resultadoCriacao.errors)
            return
        }
        acionarAviso(listOf("Macro categoria criada com sucesso"))
        delay(10)
        onDismiss()
    }

    fun toggleIsGasto() {
        isGasto.value = !isGasto.value
    }

    fun toggleAfetaPessoa() {
        afetaPessoa.value = !afetaPessoa.value
    }

    fun toggleAfetaCasa() {
        afetaCasa.value = !afetaCasa.value
    }

    fun onNomeChange(novoNome: String) {
        _nome.value = novoNome
    }

    private fun acionarAviso(avisos: List<String>) {
        _mensagemAviso.value = avisos
    }
    fun limparAviso() {
        _mensagemAviso.value = emptyList()
    }
}
