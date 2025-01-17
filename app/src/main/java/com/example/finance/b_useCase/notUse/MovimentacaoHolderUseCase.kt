package com.example.finance.b_useCase.notUse

import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MetaDados.Periodo

/*
class MovimentacaoHolderUseCase(
    val movimentacaoHolder: MovimentacaoHolder
) {
    fun getValor(
        periodo: Periodo? = null,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS)
    ): Double {
        var valor: Double = 0.0
        for (movimentacao in movimentacaoHolder.movimentacoes) {
            if (movimentacao.isOnPeriodo(periodo)) {
                if(movimentacao.isOnSelecaoUsuario(selecaoUsuario)){
                    valor += movimentacao.valor
                }
            }
        }
        return valor
    }

    fun getMovimentacoes(periodo: Periodo? = null, selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(
        Tipo.TODOS)):ArrayList<Movimentacao>{
        val movimentacoesList = ArrayList<Movimentacao>()
        for(movimentacao in movimentacaoHolder.movimentacoes){
            if(movimentacao.isOnPeriodo(periodo)){
                if(movimentacao.isOnSelecaoUsuario(selecaoUsuario)){
                    movimentacoesList.add(movimentacao)
                }
            }
        }
        return movimentacoesList
    }
}


 */