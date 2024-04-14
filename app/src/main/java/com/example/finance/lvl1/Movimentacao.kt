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

    fun isOnPeriodo(mes: Int?, ano: Int): Boolean {
        return if (mes == null) {
            ano == data!!.ano
        } else {
            ano == data!!.ano && mes == data!!.mes
        }
    }
}