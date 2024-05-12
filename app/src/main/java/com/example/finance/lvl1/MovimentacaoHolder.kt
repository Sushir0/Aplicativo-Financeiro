package com.example.finance.lvl1

import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import java.io.Serializable

abstract class MovimentacaoHolder :Serializable{
    abstract val id: Int
    abstract val nome: String
    abstract val movimentacoes: ArrayList<Movimentacao>
    abstract val perfil: Perfil
    abstract val isCasa: Boolean

    fun getGastos(periodo: Periodo? = null): ArrayList<Movimentacao>{
        val gastos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(periodo)){
                if (movimentacao.isGasto()) {
                    gastos.add(movimentacao)
                }
            }
        }
        return gastos
    }

    fun getRecebimentos(periodo: Periodo? = null): ArrayList<Movimentacao>{
        val recebimentos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(periodo)){
                if (!movimentacao.isGasto()) {
                    recebimentos.add(movimentacao)
                }
            }
        }
        return recebimentos
    }

    fun getMovimentacoes(periodo: Periodo? = null, categoria: Categoria? = null):ArrayList<Movimentacao>{
        val movimentacoes = ArrayList<Movimentacao>()
        for (movimentacao in this.movimentacoes) {
            if(movimentacao.isOnPeriodo(periodo)){
                if (categoria == null || categoria == movimentacao.categoria) {
                    movimentacoes.add(movimentacao)
                }
            }
        }
        return movimentacoes
    }

    fun getGastosTotais(periodo: Periodo? = null):Double{
        var soma = 0.0
        for (movimentacao in movimentacoes) {
            if (movimentacao.isOnPeriodo(periodo)){
                if (movimentacao.isGasto()) {
                    soma += movimentacao.valor
                }
            }
        }
        return soma
    }
    fun getRecebimentosTotais(periodo: Periodo? = null):Double{
        var soma = 0.0
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(periodo)){
                if (!movimentacao.isGasto()) {
                    soma += movimentacao.valor
                }
            }
        }
        return soma
    }
    fun getSaldo(periodo: Periodo? = null):Double{
        return getRecebimentosTotais(periodo) - getGastosTotais(periodo)
    }

    fun addMovimentacao(movimentacao: Movimentacao): Boolean {
        return movimentacoes.add(movimentacao)
    }

    fun editMovimentacao(
        movimentacaoOriginal: Movimentacao,
        movimentacaoModificada: Movimentacao
    ) {
        val index = movimentacoes.indexOf(movimentacaoOriginal)
        movimentacoes[index] = movimentacaoModificada
    }

    fun removeMovimentacao(movimentacaoASerExcluida: Movimentacao): Boolean {
        return movimentacoes.remove(movimentacaoASerExcluida)
    }

    fun sameID(id:Int):Boolean{
        return this.id==id
    }
}

fun getMovimentacaoHolderById(id: Int): MovimentacaoHolder{
    return getMembros().find { it.sameID(id) } ?: Login.getCasaLogada()
}
