package com.example.finance.lvl1

import android.util.Log
import java.util.Random

class Movimentacao (var assunto: String, var  data: Data, var  valor: Double, var categoria: Categoria){

    val random = Random()
    val id = random.nextInt(100000)

    fun showMovimentacao() {
        Log.d("mostrar", "assunto: $assunto")
        Log.d("mostrar", "ID: $id")
        Log.d("mostrar", "data: $data")
        Log.d("mostrar", "valor: $valor")
    }

    fun isGasto(): Boolean {
        return categoria!!.isGasto
    }

    fun isOnPeriodo(periodo: Periodo): Boolean {
        return periodo.isOnPeriodo(this.data)
    }
    override fun toString(): String {
        return "Movimentacao(id=$id, assunto='$assunto', data=$data, valor=$valor, categoria=$categoria)"
    }
}

fun gerarMovimentacaoTeste(): Movimentacao {
    return Movimentacao(
        assunto = "assunto",
        data = Data(10,5,15),
        valor = 10.0,
        categoria = gerarCategoriaTeste()
    )
}