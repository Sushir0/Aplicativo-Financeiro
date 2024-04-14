package com.example.finance.lvl1

import android.util.Log
import java.util.Random

class Casa(var nome: String) {
    var id: Int
        protected set
    var moradores = ArrayList<Pessoa>()
    var movimentacoes = ArrayList<Movimentacao>()
    val perfil = Perfil(true)

    init {
        val random = Random()
        id = random.nextInt(100000)
        Login.addCasa(this)
    }

    fun addMorador(morador: Pessoa): Boolean {
        return moradores.add(morador)
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

    fun showCasa() {
        Log.d("mostrar", "casa: $nome")
        Log.d("mostrar", "ID casa: $id")
        showMoradores()
        showGastos()
    }

    fun showMoradores() {
        for (morador in moradores) {
            morador.showPessoa()
        }
    }

    fun showGastos() {
        for (gasto in movimentacoes) {
            gasto.showMovimentacao()
        }
    }

    fun getGastos(): ArrayList<Movimentacao>{
        val gastos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if (movimentacao.categoria.isGasto) {
                gastos.add(movimentacao)
            }
        }
        return gastos
    }

    fun getGastosTotais():Double{
        var soma = 0.0
        for (movimentacao in movimentacoes) {
            if (movimentacao.isGasto()) {
                soma += movimentacao.valor
            }
        }
        return soma
    }

    fun getRecebimentosTotais():Double{
        var soma = 0.0
        for (morador in moradores) {
            soma += morador.getRecebimentosTotais()
        }
        for (movimentacao in movimentacoes) {
            if (!movimentacao.isGasto()) {
                soma += movimentacao.valor
            }
        }
        return soma
    }

    fun getSaldo():Double{
        return getRecebimentosTotais() - getGastosTotais()
    }
}