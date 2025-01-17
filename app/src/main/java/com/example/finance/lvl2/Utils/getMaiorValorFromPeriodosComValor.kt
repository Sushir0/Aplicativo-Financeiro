package com.example.finance.lvl2.Utils

import com.example.finance.a_Domain.model.MetaDados.PeriodoComValor

class utils(){
    fun getMaiorValorFromPeriodosComValor(periodosComValor: List<PeriodoComValor>): Float {
        var maiorValor = 0f
        for (periodoComValor in periodosComValor) {
            if(maiorValor < periodoComValor.valor){
                maiorValor = periodoComValor.valor
            }
        }
        return maiorValor
    }

    fun getMenorValorFromPeriodosComValor(periodosComValor: List<PeriodoComValor>): Float {
        var menorValor = 0f
        for (periodoComValor in periodosComValor) {
            if(menorValor > periodoComValor.valor){
                menorValor = periodoComValor.valor
            }
        }
        return menorValor
    }

    fun arredondarValor(valor: Int): Int {
        return if (valor >= 0) {
            arredondarValorPositivo(valor)
        } else {
            (arredondarValorPositivo(valor*-1)*-1)
        }
    }

     private fun arredondarValorPositivo(valor: Int): Int {
        return when {
            valor in 1..10 -> 10
            valor in 10..40 -> (valor + 4) / 5 * 5
            valor in 40..100 -> (valor + 9) / 10 * 10
            valor in 100..400 -> (valor + 49) / 50 * 50
            valor in 400..1000 -> (valor + 99) / 100 * 100
            valor in 1000..4000 -> (valor + 499) / 500 * 500
            valor in 4000..10000 -> (valor + 999) / 1000 * 1000
            valor in 10000..40000 -> (valor + 4999) / 5000 * 5000
            valor in 40000..100000 -> (valor + 9999) / 10000 * 10000
            valor in 100000..400000 -> (valor + 49999) / 50000 * 50000
            valor in 400000..1000000 -> (valor + 99999) / 100000 * 100000
            valor in 1000000..4000000 -> (valor + 499999) / 500000 * 500000
            valor in 4000000..10000000 -> (valor + 999999) / 1000000 * 1000000
            valor in 10000000..40000000 -> (valor + 4999999) / 5000000 * 5000000
            valor in 40000000..100000000 -> (valor + 9999999) / 10000000 * 10000000
            valor in 100000000..400000000 -> (valor + 49999999) / 50000000 * 50000000
            valor >= 400000000 -> (valor + 99999999) / 100000000 * 100000000
            else -> valor
        }
    }

    private fun arredondarValorNegativo(valor: Int): Int {
        return when {
            valor in -10..-1 -> -10
            valor in -40..-10 -> (valor + 4) / 5 * 5
            valor in -100..-40 -> (valor + 9) / 10 * 10
            valor in -400..-100 -> (valor + 49) / 50 * 50
            valor in -1000..-400 -> (valor + 99) / 100 * 100
            valor in -4000..-1000 -> (valor + 499) / 500 * 500
            valor in -10000..-4000 -> (valor + 999)
            valor in -40000..-10000 -> (valor + 4999) / 5000 * 5000
            valor in -100000..-40000 -> (valor + 9999) / 10000 * 10000
            valor in -400000..-100000 -> (valor + 49999) / 50000 * 50000
            valor in -1000000..-400000 -> (valor + 99999) / 100000 * 100000
            valor in -4000000..-1000000 -> (valor + 499999) / 500000 * 500000
            valor in -10000000..-4000000 -> (valor + 999999) / 1000000 * 1000000
            valor in -40000000..-10000000 -> (valor + 4999999) / 5000000 * 5000000
            valor in -100000000..-40000000 -> (valor - 9999999) / 10000000 * 10000000
            valor in -400000000..-100000000 -> (valor + 49999999) / 50000000 * 50000000
            valor <= -400000000 -> (valor + 99999999) / 100000000 * 100000000
            else -> valor
        }








    }

}