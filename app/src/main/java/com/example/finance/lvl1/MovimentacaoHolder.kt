package com.example.finance.lvl1

abstract class MovimentacaoHolder {
    abstract val nome: String
    abstract val movimentacoes: ArrayList<Movimentacao>
    abstract val perfil: Perfil
    abstract val isCasa: Boolean

    fun getGastos(mes: Int? = null, ano: Int): ArrayList<Movimentacao>{
        val gastos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(mes, ano)){
                if (movimentacao.isGasto()) {
                    gastos.add(movimentacao)
                }
            }
        }
        return gastos
    }

    fun getRecebimentos(mes: Int? = null, ano: Int): ArrayList<Movimentacao>{
        val recebimentos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(mes, ano)){
                if (!movimentacao.isGasto()) {
                    recebimentos.add(movimentacao)
                }
            }
        }
        return recebimentos
    }

    fun getGastosTotais(mes: Int? = null, ano: Int):Double{
        var soma = 0.0
        for (movimentacao in movimentacoes) {
            if (movimentacao.isOnPeriodo(mes, ano)){
                if (movimentacao.isGasto()) {
                    soma += movimentacao.valor
                }
            }
        }
        return soma
    }
    fun getRecebimentosTotais(mes: Int? = null, ano: Int):Double{
        var soma = 0.0
        for (movimentacao in movimentacoes) {
            if(movimentacao.isOnPeriodo(mes, ano)){
                if (!movimentacao.isGasto()) {
                    soma += movimentacao.valor
                }
            }
        }
        return soma
    }
    fun getSaldo(mes: Int? = null, ano: Int):Double{
        return getRecebimentosTotais(mes, ano) - getGastosTotais(mes, ano)
    }

    fun addMovimentacao(movimentacao: Movimentacao): Boolean {
        return movimentacoes.add(movimentacao)
    }

    private fun editMovimentacao(
        movimentacaoOriginal: Movimentacao,
        movimentacaoModificada: Movimentacao
    ) {
        val index = movimentacoes.indexOf(movimentacaoOriginal)
        movimentacoes[index] = movimentacaoModificada
    }

    private fun excluirMovimentacao(movimentacaoASerExcluida: Movimentacao): Boolean {
        return movimentacoes.remove(movimentacaoASerExcluida)
    }

}