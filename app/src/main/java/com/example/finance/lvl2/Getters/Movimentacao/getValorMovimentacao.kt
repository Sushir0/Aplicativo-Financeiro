package com.example.finance.lvl2.Getters.Movimentacao

import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
/*
fun getValorCom_SelecaoUsuario_MovimentacaoHolder_Periodo(selecaoUsuario: SelecaoUsuario, movimentacaoHolder: MovimentacaoHolder, periodo: Periodo? = null):Double{
    val valor = when (selecaoUsuario) {
        is SelecaoUsuario.tipoSelecionado -> {
            when ((selecaoUsuario).tipo) {
                Tipo.GASTO -> {
                    movimentacaoHolder.getGastosTotais(periodo)
                }
                Tipo.RECEBIMENTO -> {
                    movimentacaoHolder.getRecebimentosTotais(periodo)
                }
                Tipo.TODOS -> {
                    movimentacaoHolder.getSaldo(periodo)
                }
            }
        }
        is SelecaoUsuario.categoriaSelecionada -> {
            movimentacaoHolder.getValorTotalFromCategoria(
                categoria = (selecaoUsuario).categoria,
                periodo = periodo
            )
        }

        is SelecaoUsuario.macroCategoriaSelecionada -> {
            movimentacaoHolder.getValorTotalFromMacroCategoria(
                macroCategoria = (selecaoUsuario).macroCategoria,
                periodo = periodo
            )
        }
    }
    return valor


}
*/