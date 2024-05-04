package com.example.finance.lvl3.telas

import FormularioMovimentacao
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.getMovimentacaoHolderById
import com.example.finance.lvl1.getPeriodoFromNome
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl2.Getters.getMovimentacoes
import com.example.finance.lvl3.componentes.ItemValue
import com.example.finance.lvl3.componentes.listas.NewListaDeMovimentacao
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.utils.avisoLongo
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.DropdownIsGasto
import com.example.finance.lvl3.widgets.Tipo
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

class Detalhes (
    private val movimentacaoHolder: MovimentacaoHolder,
    private val isGastoInicial: Boolean,
    private val periodoInicial: Periodo
){
    private val membroSelecionado: MovimentacaoHolder by mutableStateOf(getMovimentacaoHolderById(movimentacaoHolder.id))
    private var periodosUtilizados by mutableStateOf(getPeriodosFromMovimentacoes(membroSelecionado.movimentacoes))
    private var periodoSelecionado by mutableStateOf(getPeriodoFromNome(periodoInicial.nome, periodosUtilizados)?: periodoInicial)
    private var movimentacoes by mutableStateOf(
            getMovimentacoes(
                movimentacaoHolder = membroSelecionado,
                periodo = periodoSelecionado,
                isGasto = isGastoInicial
            )
        )
    private var tipoSelecionado by mutableStateOf(Tipo.getTipoFromIsGasto(isGastoInicial))


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun content(){
        val context = LocalContext.current
        var isSheetOpen by rememberSaveable { mutableStateOf(false) }
        var background = if(isSystemInDarkTheme()){
            backgroundDark
        }else{
            backgroundLight
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(background)
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Header(nome = membroSelecionado.nome)
            ItemValue(valor = membroSelecionado.getSaldo(periodoSelecionado))
            DropdownIsGasto(
                tipoSelecionado = tipoSelecionado,
                modifier = Modifier
                    .fillMaxWidth(.75f)
                    .padding(
                        start = 4.dp,
                        top = 4.dp
                    )
            ) {
                tipoSelecionado = it
                atualizar()
            }
            NewListaDeMovimentacao(
                movimentacoes = movimentacoes,
                isAlways = tipoSelecionado == Tipo.TODOS
            )

        }

        Footer(
            periodosUtilizados = periodosUtilizados,
            openBottomSheetClick = { isSheetOpen = true },
            periodoSelecionado =    periodoSelecionado,
            onChoicePeriodo = {
                periodoSelecionado = it
                atualizar()
            }
        )

        BottomSheet(
            isSheetOpen = isSheetOpen,
            onDismiss = { isSheetOpen = false }){
            FormularioMovimentacao(
                membroPreSelecionado = membroSelecionado,
                onConfirm = {
                    atualizar()
                },
                lockedMembro = true,
                onDismiss = { isSheetOpen = false }
            )
        }

    }

    fun atualizar() {
        periodosUtilizados = getPeriodosFromMovimentacoes(membroSelecionado.movimentacoes)
        periodoSelecionado = getPeriodoFromNome(periodoSelecionado.nome, periodosUtilizados) ?: getUltimoPeriodoUtilizado(periodosUtilizados)
        movimentacoes = when(tipoSelecionado){
            Tipo.GASTO -> membroSelecionado.getGastos(periodoSelecionado)
            Tipo.RECEBIMENTO -> membroSelecionado.getRecebimentos(periodoSelecionado)
            Tipo.TODOS -> membroSelecionado.getMovimentacoes(periodoSelecionado)
        }
    }

















}