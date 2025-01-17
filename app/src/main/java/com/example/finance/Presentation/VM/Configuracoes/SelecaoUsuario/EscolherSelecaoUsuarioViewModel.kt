package com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EscolherSelecaoUsuarioViewModel (
    private val dataSources: InterfaceDataSources
): ViewModel() {
    val categoriaService = CategoriaService(dataSources.categoriaDataSource)
    val macroCategoriaService = MacroCategoriaService(dataSources.macroCategoriaDataSource)

    val categorias = mutableStateOf<List<Categoria>?>(emptyList())
    var macroCategorias = mutableStateOf<List<MacroCategoria>?>(emptyList())

    val categoriaEscolhida = mutableStateOf<Categoria?>(null)
    val macroCategoriaEscolhida = mutableStateOf<MacroCategoria?>(null)
    val tipoEscolhido = mutableStateOf(Tipo.TODOS)
    val membroSelecionado = mutableStateOf<MovimentacaoHolder?>(null)

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    var selecaoUsuarioEscolhido = mutableStateOf<SelecaoUsuario>(SelecaoUsuario.tipoSelecionado(Tipo.TODOS))

    suspend fun inicializar(
        movimentacaoHolder: MovimentacaoHolder,
        selecaoUsuarioInicial: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
    ){
        membroSelecionado.value = movimentacaoHolder
        when(selecaoUsuarioInicial){
            is SelecaoUsuario.tipoSelecionado -> {
                setTipoEscolhido(selecaoUsuarioInicial.tipo)
            }
            is SelecaoUsuario.categoriaSelecionada -> {
                setCategoriaEscolhida(selecaoUsuarioInicial.categoria)
                setMacroCategoriaEscolhida(selecaoUsuarioInicial.categoria.macroCategoria)
                setTipoEscolhido(if(selecaoUsuarioInicial.categoria.isGasto) Tipo.GASTO else Tipo.RECEBIMENTO)
            }
            is SelecaoUsuario.macroCategoriaSelecionada -> {
                setMacroCategoriaEscolhida(selecaoUsuarioInicial.macroCategoria)
                setTipoEscolhido(if(selecaoUsuarioInicial.macroCategoria.isGasto) Tipo.GASTO else Tipo.RECEBIMENTO)
            }
        }

    }
    suspend fun setCategorias(){

        val resultadoCategorias = if(macroCategoriaEscolhida.value != null){
            categoriaService.getCategoriasFromMacro(macroCategoriaEscolhida.value!!.id)
        }else{
            val afetaPessoa = if (!membroSelecionado.value!!.isCasa) { true }else{ null }
            val afetaCasa = if(membroSelecionado.value!!.isCasa){ true }else{ null }
            categoriaService.getCategoriasFiltradas(
                idCasa = VariaveisDeAmbiente.casaId,
                afetaCasa = afetaCasa,
                afetaPessoa = afetaPessoa,
                isGasto = tipoEscolhido.value.getIsGasto()
            )
        }
        if(resultadoCategorias is Resultado.Sucesso){
            categorias.value = resultadoCategorias.data
        }else if(resultadoCategorias is Resultado.Falha){
            acionarAviso(resultadoCategorias.errors)
        }
    }

    suspend fun setMacroCategorias() {
        val afetaPessoa = if (!membroSelecionado.value!!.isCasa) { true }else{ null }
        val afetaCasa = if(membroSelecionado.value!!.isCasa){ true }else{ null }
        val resultadoMacroCategorias = macroCategoriaService.getMacroCategorias(
            idCasa = VariaveisDeAmbiente.casaId,
            afetaCasa = afetaCasa,
            afetaPessoa = afetaPessoa,
            isGasto = tipoEscolhido.value.getIsGasto()
        )
        if (resultadoMacroCategorias is Resultado.Sucesso) {
            macroCategorias.value = resultadoMacroCategorias.data
        } else if (resultadoMacroCategorias is Resultado.Falha) {
            acionarAviso(resultadoMacroCategorias.errors)
        }
    }

    suspend fun setMacroCategoriaEscolhida(macroCategoria: MacroCategoria?){
        if(macroCategoriaEscolhida.value == macroCategoria){
            macroCategoriaEscolhida.value = null
        }else{
            macroCategoriaEscolhida.value = macroCategoria
        }
        setCategorias()
    }

    fun setCategoriaEscolhida(categoria: Categoria?){
        if(categoriaEscolhida.value == categoria){
            categoriaEscolhida.value = null
        }else{
            categoriaEscolhida.value = categoria
        }
    }

    suspend fun setTipoEscolhido(tipo: Tipo){
        if(tipo == tipoEscolhido.value){
            tipoEscolhido.value = Tipo.TODOS
        }else{
            tipoEscolhido.value = tipo
        }
        verificarSeMacroCategoriaCorrespondeAoTipo()
        setMacroCategorias()
        setCategorias()
    }

    private suspend fun verificarSeMacroCategoriaCorrespondeAoTipo(){
        if(macroCategoriaEscolhida.value != null){
            if(!macroCategoriaEscolhida.value!!.verificarTipo(tipoEscolhido.value)){
                setMacroCategoriaEscolhida(null)
            }
        }
    }

    private fun acionarAviso(avisos: List<String>) {
        _mensagemAviso.value = avisos
    }
    fun limparAviso() {
        _mensagemAviso.value = emptyList()
    }

}
