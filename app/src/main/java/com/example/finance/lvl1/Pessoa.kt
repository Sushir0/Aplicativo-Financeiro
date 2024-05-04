package com.example.finance.lvl1

import android.util.Log
import java.util.Random

class Pessoa(override val nome: String): MovimentacaoHolder() {
    override val id: Int
    override val movimentacoes = ArrayList<Movimentacao>()
    override val perfil = Perfil()
    override val isCasa = false

    init {
        val random = Random()
        id = random.nextInt(100000)
    }

}
