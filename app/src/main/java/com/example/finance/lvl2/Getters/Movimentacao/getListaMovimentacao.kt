package com.example.finance.lvl2.Getters.Movimentacao

import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario.tipoSelecionado
import com.example.finance.a_Domain.model.MetaDados.Tipo
/*
fun getMovimentacoes(movimentacaoHolder: MovimentacaoHolder, selecaoUsuario: SelecaoUsuario? = null, periodo: Periodo? = null):List<Movimentacao>{
    if(selecaoUsuario == null){
        return movimentacaoHolder.getMovimentacoes(periodo = periodo)
    }
    return when (selecaoUsuario) {
        is SelecaoUsuario.categoriaSelecionada -> {
            movimentacaoHolder.getMovimentacoes(
                categoria = selecaoUsuario.categoria,
                periodo = periodo)
        }
        is tipoSelecionado -> {
            when (selecaoUsuario.tipo) {
                Tipo.GASTO -> movimentacaoHolder.getGastos(periodo)
                Tipo.RECEBIMENTO -> movimentacaoHolder.getRecebimentos(periodo)
                Tipo.TODOS -> movimentacaoHolder.getMovimentacoes(periodo)
            }

        }

        is SelecaoUsuario.macroCategoriaSelecionada -> {
            movimentacaoHolder.getMovimentacoesFromMacroCategoria(
                macroCategoria = selecaoUsuario.macroCategoria,
                periodo = periodo)
        }
    }

}

 */