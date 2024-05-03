package com.example.finance.lvl1

import java.util.Random

class Casa(override var nome: String) : MovimentacaoHolder() {
    override val id: Int
    override val movimentacoes = ArrayList<Movimentacao>()
    override val perfil = Perfil(true)
    override val isCasa = true
    var moradores = ArrayList<Pessoa>()


    init {
        val random = Random()
        id = random.nextInt(100000)
        Login.addCasa(this)
    }

    fun addMorador(morador: Pessoa): Boolean {
        return moradores.add(morador)
    }
}