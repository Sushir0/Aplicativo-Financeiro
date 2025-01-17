package com.example.finance.a_Domain.model.MetaDados

import com.example.finance.a_Domain.model.Dados.Data

interface ProvedorDeDatasDeParcelas {
    fun gerarDatasDasParcelas(numeroDeParcelas: Int, dataInicial: Data): List<Data>
}

class provedorDeDatasDeParcelasPorDiaDoMes(val diaDoMes: Int = 5): ProvedorDeDatasDeParcelas{
    override fun gerarDatasDasParcelas(numeroDeParcelas: Int, dataInicial: Data): List<Data> {
        val datas = mutableListOf<Data>()
        for (i in 1..numeroDeParcelas){
            if (dataInicial.dia < diaDoMes){
                val data = dataInicial.criarDataIncrementandoMeses(i-1)
                datas.add(data)
            }else{
                val dataIncrementadaSemDiaAlterado = dataInicial.criarDataIncrementandoMeses(i)
                val dataFinal = Data(diaDoMes, dataIncrementadaSemDiaAlterado.mes, dataIncrementadaSemDiaAlterado.ano)
                datas.add(dataFinal)
            }
        }
        return datas
    }
}

class provedorDeDatasDeParcelasPorDiasCorridos(val diasCorridos: Int = 15): ProvedorDeDatasDeParcelas{
    override fun gerarDatasDasParcelas(numeroDeParcelas: Int, dataInicial: Data): List<Data> {
        val datas = mutableListOf<Data>()
        for (i in 1..numeroDeParcelas){
            val data = dataInicial.criarDataIncrementandoDias(diasCorridos*i)
            datas.add(data)
        }
        return datas
    }
}