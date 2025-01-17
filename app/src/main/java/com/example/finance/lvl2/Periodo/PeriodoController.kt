package com.example.finance.lvl2.Periodo

import com.example.finance.a_Domain.model.MetaDados.PeriodoComValor
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.getPeriodosFromMovimentacoes_ComAnos
import com.example.finance.a_Domain.model.MetaDados.getPeriodosFromMovimentacoes_SemAnos
import com.example.finance.a_Domain.model.MetaDados.getUltimoPeriodoUtilizado
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder

class PeriodoController {
    fun getPeriodos_ComAnosInclusos(movimentacoes: List<Movimentacao>): ArrayList<Periodo>{
        val periodos = ArrayList<Periodo>()
        periodos.addAll(getPeriodosFromMovimentacoes_ComAnos(movimentacoes))
        return periodos
    }

    fun getPeriodos_SemAnosInclusos(movimentacoes: List<Movimentacao>): ArrayList<Periodo>{
        val periodos = ArrayList<Periodo>()
        periodos.addAll(getPeriodosFromMovimentacoes_SemAnos(movimentacoes))
        return periodos
    }
/*
    fun getPeriodoInicial(membroSelecionado: MovimentacaoHolder): Periodo {
        return getUltimoPeriodoUtilizado(
            getPeriodosFromMovimentacoes_ComAnos(
                membroSelecionado.movimentacoes
            )
        )
    }

 */
/*
    fun getPeriodosComValor_ComAnosInclusos(movimentacaoHolder: MovimentacaoHolder, selecaoUsuario: SelecaoUsuario): List<PeriodoComValor> {
        var periodosComValor = ArrayList<PeriodoComValor>()
        val periodos = getPeriodosFromMovimentacoes_ComAnos(getMovimentacoes(movimentacaoHolder, selecaoUsuario))
        for (periodo in periodos) {
            val valor = getValorCom_SelecaoUsuario_MovimentacaoHolder_Periodo(
                selecaoUsuario = selecaoUsuario,
                movimentacaoHolder = movimentacaoHolder,
                periodo = periodo
            )
            periodosComValor.add(PeriodoComValor(periodo, valor.toFloat()))
        }
        return periodosComValor
    }

    fun getPeriodosComValor_SemAnosInclusos(movimentacaoHolder: MovimentacaoHolder, selecaoUsuario: SelecaoUsuario): List<PeriodoComValor> {
        var periodosComValor = ArrayList<PeriodoComValor>()
        val periodos = getPeriodosFromMovimentacoes_SemAnos(getMovimentacoes(movimentacaoHolder, selecaoUsuario))
        for (periodo in periodos) {
            val valor = getValorCom_SelecaoUsuario_MovimentacaoHolder_Periodo(
                selecaoUsuario = selecaoUsuario,
                movimentacaoHolder = movimentacaoHolder,
                periodo = periodo
                )
            periodosComValor.add(PeriodoComValor(periodo, valor.toFloat()))
        }
        return periodosComValor
    }

 */


}