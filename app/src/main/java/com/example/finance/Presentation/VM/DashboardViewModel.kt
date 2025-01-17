package com.example.finance.Presentation.VM

import android.content.Context
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.b_useCase.CasaService
import com.example.finance.b_useCase.ParcelaService
import com.example.finance.b_useCase.PeriodoService
import com.example.finance.lvl3.activitys.abrirDetalhes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel(
    val dataSource: InterfaceDataSources
): ViewModel() {
    val parcelaService = ParcelaService(dataSource.parcelaDataSource)
    val casaService = CasaService(dataSource.casaDataSource)
    val periodoService = PeriodoService()

    private val _mensagemAviso = MutableStateFlow<List<String>>(emptyList())
    val mensagemAviso: StateFlow<List<String>> = _mensagemAviso

    var titulo = "Carregando"
        private set

    private val _valorGasto = MutableStateFlow(0.0f)
    val valorGasto: StateFlow<Float> = _valorGasto

    private val _valorRecebido = MutableStateFlow(0.0f)
    val valorRecebido: StateFlow<Float> = _valorRecebido

    private val _saldo = MutableStateFlow(0.0f)
    val saldo: StateFlow<Float> = _saldo


    private val _membrosStateFlow = MutableStateFlow<List<MovimentacaoHolder>>(emptyList())
    val membrosStateFlow: StateFlow<List<MovimentacaoHolder>> = _membrosStateFlow

    var membroSelecionado: MutableState<MovimentacaoHolder?> = mutableStateOf(null)
        private set

    private val _periodosStateFlow = MutableStateFlow<List<Periodo>>(emptyList())
    val periodosStateFlow: StateFlow<List<Periodo>> = _periodosStateFlow

    var periodoSelecionado: MutableState<Periodo?> = mutableStateOf(null)
        private set

    var bottomSheetAdicinoar = mutableStateOf(false)
        private set

    fun abrirBottomSheet(){
        bottomSheetAdicinoar.value = true
    }

    fun fecharBottomSheet(){
        bottomSheetAdicinoar.value = false
    }


    suspend fun inicializar(idCasa: String) {
        setMembros(idCasa)
        selecinoarMembro(membrosStateFlow.value.first())
        setPeriodos()
        setTitulo()
        setValores()
        selecionarPeriodo(periodosStateFlow.value.first())
    }

    private suspend fun setMembros(idCasa: String) {
        val resultado = casaService.getMembros(idCasa)
        if(resultado is Resultado.Sucesso){
            _membrosStateFlow.value = resultado.data!!
        }else if(resultado is Resultado.Falha){
            acionarAviso(resultado.errors)
        }
    }

    private suspend fun setPeriodos() {
        _periodosStateFlow.value = periodoService.gerarPeriodosDoUltimoAno()
    }


    suspend fun selecionarPeriodo(periodo: Periodo){
        periodoSelecionado.value = periodo
        setValores()
    }

    suspend fun selecinoarMembro(membro: MovimentacaoHolder){
        membroSelecionado.value = membro
        setTitulo()
        setValores()
    }

    fun setTitulo(){
        if (membroSelecionado.value != null){
            titulo = membroSelecionado.value!!.nome
        }else{
            titulo = "Carregando"
        }
    }


    private suspend fun setValores(){
        setRecebimento()
        setGasto()
        setSaldo()
    }

    private suspend fun setRecebimento(){
        if(membroSelecionado.value == null){
            _valorRecebido.value = 0.0f
        }else{
            val resultadoRecebimento = parcelaService.getValor(
                movimentacaoHolderDTO = membroSelecionado.value!!.toDTO(),
                selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.RECEBIMENTO),
                periodo = periodoSelecionado.value
            )
            if(resultadoRecebimento is Resultado.Sucesso){
                Log.d("DashboardViewModel", "setRecebimento: ${resultadoRecebimento.data}")
                _valorRecebido.value = resultadoRecebimento.data!!.toFloat()
            }else{
                _valorRecebido.value = 0.0f
            }
        }

    }

    private suspend fun setGasto(){
        if(membroSelecionado.value == null){
            _valorGasto.value = 0.0f
        }else {
            val resultadoGasto = parcelaService.getValor(
                movimentacaoHolderDTO = membroSelecionado.value!!.toDTO(),
                selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.GASTO),
                periodo = periodoSelecionado.value
            )
            if (resultadoGasto is Resultado.Sucesso) {
                Log.d("DashboardViewModel", "setGasto: ${resultadoGasto.data}")
                _valorGasto.value = resultadoGasto.data!!.toFloat()
            } else {
                _valorGasto.value = 0.0f
            }
        }
    }

    private suspend fun setSaldo(){
        _saldo.value = valorRecebido.value - valorGasto.value
    }

    private fun acionarAviso(erros: List<String>){
        _mensagemAviso.value = erros
    }

    fun limparAviso(){
        _mensagemAviso.value = emptyList()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun abrirDetalhesActivity(
        context: Context,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
        ){
        abrirDetalhes(
            context = context,
            movimentacaoHolder = membroSelecionado.value!!,
            selecaoUsuario = selecaoUsuario,
            periodo = periodoSelecionado.value!!
        )
    }
}
