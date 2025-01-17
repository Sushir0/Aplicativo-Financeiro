package com.example.finance.Presentation.VM.Movimentacao

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.converterDataMillisParaData
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.b_useCase.CasaService
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import com.example.finance.b_useCase.MovimentacaoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AdicionarMovimentacaoViewModel(
    val dataSources: InterfaceDataSources
): ViewModel() {
    val movimentacaoService = MovimentacaoService(dataSources.movimentacaoDataSource)
    val casaService = CasaService(dataSources.casaDataSource)
    val macroCategoriaService = MacroCategoriaService(dataSources.macroCategoriaDataSource)
    val categoriaService = CategoriaService(dataSources.categoriaDataSource)

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    val categorias = mutableStateOf<List<Categoria>?>(emptyList())
    val macroCategorias = mutableStateOf<List<MacroCategoria>?>(emptyList())

    var descricao = mutableStateOf("")
    var valor = mutableStateOf("")
    val data = mutableStateOf(converterDataMillisParaData(System.currentTimeMillis()))
    var categoriaEscolhida = mutableStateOf<Categoria?>(null)

    val membros = mutableStateOf<List<MovimentacaoHolder>?>(emptyList())
    val membroSelecionado = mutableStateOf<MovimentacaoHolder?>(null)

    val textoBotaoAdicionar = mutableStateOf("Adicionar")
    val textoBotaoCategoria = mutableStateOf("Selecionar categoria")

    var showDialogDeCategoria by mutableStateOf(false)

    suspend fun inicializar(membroSelecionado: MovimentacaoHolder?){
        setMembros()
        setMembro(membroSelecionado)
        setMacroCategorias()
        setCategorias()

    }

    suspend fun setCategorias(){
        val resultadoCategorias = categoriaService.getCategoriasFiltradas(
            idCasa = VariaveisDeAmbiente.casaId
        )

        if(resultadoCategorias is Resultado.Sucesso){
            categorias.value = resultadoCategorias.data
        }else if(resultadoCategorias is Resultado.Falha){
            acionarAviso(resultadoCategorias.errors)
        }
    }

    suspend fun setMacroCategorias() {
        val resultadoMacroCategorias = macroCategoriaService.getMacroCategorias(
            idCasa = VariaveisDeAmbiente.casaId
        )
        if (resultadoMacroCategorias is Resultado.Sucesso) {
            macroCategorias.value = resultadoMacroCategorias.data
        } else if (resultadoMacroCategorias is Resultado.Falha) {
            acionarAviso(resultadoMacroCategorias.errors)
        }
    }

    fun abrirDialogoDeCategoria(){
        showDialogDeCategoria = true
    }

    fun fecharDialogoDeCategoria(){
        showDialogDeCategoria = false
    }



    fun setDescricao(descricao: String){
        this.descricao.value = descricao
    }

    fun setValor(valor: String){
        this.valor.value = valor
    }

    fun setData(data: Data){
        this.data.value = data
    }

    suspend fun setMembros(){
        val resultadoMembros = casaService.getMembros(VariaveisDeAmbiente.casaId)
        if(resultadoMembros is Resultado.Sucesso){
            membros.value = resultadoMembros.data
        }else if(resultadoMembros is Resultado.Falha){
            acionarAviso(resultadoMembros.errors)
        }
    }

    fun setMembro(membroSelecionado: MovimentacaoHolder?, categoriaEscolhida: Categoria? = null): Categoria?{
        this.membroSelecionado.value = membroSelecionado
        categoriaEscolhida?.let { categoria ->
            if (membroSelecionado != null &&
                ((categoria.afetaCasa && membroSelecionado.isCasa) ||
                        (categoria.afetaPessoa && !membroSelecionado.isCasa))) {
                acionarAviso(listOf("Categoria não afeta este membro!"))
                return null

            }
        }
        return categoriaEscolhida
    }

    fun setCategoria(categoria: Categoria){
        categoriaEscolhida.value = categoria
        setTextoCategoria()
    }

    fun setTextoCategoria(){
        textoBotaoCategoria.value = categoriaEscolhida.value?.nome ?: "Selecionar categoria"
    }

    suspend fun onSave(){
        try {
            val resultadoMovimentacao = movimentacaoService.criarMovimentacao(
                descricao = descricao.value,
                valor = valor.value.toDouble(),
                data = data.value,
                movimentacaoHolder = membroSelecionado.value!!.toDTO(),
                categoriaId = categoriaEscolhida.value!!.id,
                formaPagamento = FormaPagamento.debito()
            )
            if(resultadoMovimentacao is Resultado.Sucesso){
                acionarAviso(listOf("Movimentação criada com sucesso!"))

            }else if(resultadoMovimentacao is Resultado.Falha){
                acionarAviso(resultadoMovimentacao.errors)
            }
        }
        catch (
            e: Exception
        ){
            acionarAviso(listOf("Erro ao criar movimentação: ${e.message}"))
        }
    }

    private fun acionarAviso(avisos: List<String>) {
        _mensagemAviso.value = avisos
    }
    fun limparAviso() {
        _mensagemAviso.value = emptyList()
    }
}