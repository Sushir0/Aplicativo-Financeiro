package com.example.finance.lvl1

import android.util.Log
import java.util.Random

class Pessoa(val nome: String, casa: Casa) {
    private val id: Int
    val movimentacoes = ArrayList<Movimentacao>()
    val perfil = Perfil()

    fun getGastos(): ArrayList<Movimentacao>{
        val gastos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if (movimentacao.isGasto()) {
                gastos.add(movimentacao)
            }
        }
        return gastos
    }

    fun getRecebimentos(): ArrayList<Movimentacao>{
        val recebimentos = ArrayList<Movimentacao>()
        for (movimentacao in movimentacoes) {
            if (!movimentacao.isGasto()) {
                recebimentos.add(movimentacao)
            }
        }
        return recebimentos
    }

    init {
        val random = Random()
        id = random.nextInt(100000)
        casa.addMorador(this)
    }

    fun addMovimentacao(movimentacao: Movimentacao): Boolean {
        return movimentacoes.add(movimentacao)
    }

    fun showPessoa() {
        Log.d("mostrar", "nome: $nome")
        Log.d("mostrar", "ID: $id")
        showGastos()
        showRecebimentos()
    }

    fun showGastos() {
        val gastos = getGastos()
        for (gasto in gastos) {
            gasto.showMovimentacao()
        }
    }

    fun showRecebimentos() {
        val recebimentos = getRecebimentos()
        for (recebimento in recebimentos) {
            recebimento.showMovimentacao()
        }
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
