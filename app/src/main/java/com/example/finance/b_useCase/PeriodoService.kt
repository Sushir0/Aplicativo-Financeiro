package com.example.finance.b_useCase

import com.example.finance.a_Domain.Repositorio.PeriodoRepository
import com.example.finance.a_Domain.model.Dados.dataFromTimeStamp
import com.example.finance.a_Domain.model.MetaDados.Periodo

class PeriodoService(

) {
    fun gerarPeriodosDoUltimoAno(): List<Periodo> {
        val dataAtual = dataFromTimeStamp(System.currentTimeMillis())
        val periodos = mutableListOf<Periodo>()
        periodos.add(Periodo(mes = 1, ano = dataAtual.ano))
        if(dataAtual.mes == 1){
            periodos.add(Periodo(ano = dataAtual.ano))
        }
        for(i in 1..12){
            val data = dataAtual.criarDataDecrementandoMeses(i)
            if(data.mes == 1){
                periodos.add(Periodo(mes = data.mes, ano = data.ano))
                periodos.add(Periodo(ano = data.ano))
            }else{
                periodos.add(Periodo(mes = data.mes, ano = data.ano))
            }
        }
        return periodos
    }
}

fun main() {
    val periodos = PeriodoService().gerarPeriodosDoUltimoAno()
    for (periodo in periodos) {
        println(periodo.nome)
    }
}