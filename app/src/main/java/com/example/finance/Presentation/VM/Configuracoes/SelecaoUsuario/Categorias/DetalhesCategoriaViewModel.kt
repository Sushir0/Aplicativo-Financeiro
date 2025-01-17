package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.Categoria.CategoriaServiceInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetalhesCategoriaViewModel(
    val dataSource: InterfaceDataSources,
): ViewModel() {
    val categoriaService = CategoriaService(dataSource.categoriaDataSource)

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    var categoria by mutableStateOf<Categoria?>(null)
        private set

    var quantidadeMovimentacoes by mutableStateOf(0)

    var alertDelete by mutableStateOf(false)
        private set

    var alertDesativacao by mutableStateOf(false)
        private set

    var alertAtivacao by mutableStateOf(false)
        private set

    var textoButtonAtivacao by mutableStateOf("")
        private set


    suspend fun inicializar(categoriaId: String){
        val resultado = categoriaService.getCategoria(categoriaId)
        if(resultado is Resultado.Sucesso){
            categoria = resultado.data
            textoButtonAtivacao = if(categoria!!.isActivate) "Desativar" else "Ativar"
        }else if(resultado is Resultado.Falha){
            acionarAviso(resultado.errors)
        }
    }

    suspend fun deletarCategoria(onDismiss: () -> Unit){
        val resultado = categoriaService.deletarCategoria(categoria!!.id)
        if(resultado is Resultado.Sucesso){
            acionarAviso(listOf("Categoria deletada com sucesso"))
            delay(10) // delay para dar tempo do aviso ser exibido antes da janela ser fechada
            onDismiss()
        }else if(resultado is Resultado.Falha){
            acionarAviso(resultado.errors)
        }
    }

    suspend fun toggleAtivacaoCategoria(onDismiss: () -> Unit){
        val resultado = categoriaService.togleAtivo(categoria!!.id)
        if(resultado is Resultado.Sucesso){
            if(categoria!!.isActivate){
                acionarAviso(listOf("Categoria desativada com sucesso"))
            }else{
                acionarAviso(listOf("Categoria ativada com sucesso"))
            }
            fecharAlertDesativacao()
            fecharAlertAtivacao()
            inicializar(categoria!!.id)
        }else if(resultado is Resultado.Falha){
            acionarAviso(resultado.errors)
        }
    }


    fun abrirAlertDelete(){
        alertDelete = true
    }
    fun fecharAlertDelete(){
        alertDelete = false
    }

    private fun abrirAlertDesativacao(){
        alertDesativacao = true
    }
    fun fecharAlertDesativacao() {
        alertDesativacao = false
    }

    private fun abrirAlertAtivacao(){
        alertAtivacao = true
    }
    fun fecharAlertAtivacao() {
        alertAtivacao = false
    }

    fun apertarBotaoAtivacao(){
        if(categoria!!.isActivate){
            abrirAlertDesativacao()
        }else{
            abrirAlertAtivacao()
        }
    }









    private fun acionarAviso(avisos: List<String>) {
        _mensagemAviso.value = avisos
    }
    fun limparAviso(){
        _mensagemAviso.value = emptyList()
    }
}