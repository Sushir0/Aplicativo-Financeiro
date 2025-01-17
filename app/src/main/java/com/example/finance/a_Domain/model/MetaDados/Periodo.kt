package com.example.finance.a_Domain.model.MetaDados

import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.formataMeseUtilizado
import com.example.finance.a_Domain.model.Dados.getAnosUtilizados
import com.example.finance.a_Domain.model.Dados.getDatasDeMovimentacoes
import com.example.finance.a_Domain.model.Dados.getMesesUtilizados
import com.example.finance.a_Domain.model.Dados.ordenarDatas
import java.io.Serializable
import java.util.Random

class Periodo : Serializable {
    val dataInicial: Data
    val dataFinal: Data
    val nome: String
    val isAno: Boolean
    constructor(mes: Int? = null, ano: Int, nome: String? = null){
        this.dataInicial = Data(1, mes ?: 1 , ano)
        this.dataFinal = Data(31, mes ?: 12, ano)
        this.nome = nome?: if(mes == null){
            ano.toString()
        }else{
            formataMeseUtilizado(mes, ano)
        }
        isAno = mes == null

    }
    constructor(dataInicial: Data, dataFinal: Data){
        this.dataInicial = Data(
            dataInicial.dia,
            dataInicial.mes,
            dataInicial.ano
            )
        this.dataFinal = Data(
            dataFinal.dia,
            dataFinal.mes,
            dataFinal.ano
        )
        this.nome = "entre ${dataInicial} e ${dataFinal}"
        isAno = false
    }

    fun isOnPeriodo(data: Data): Boolean {
        val datas = mutableListOf<Data>()
        datas.add(dataInicial)
        datas.add(data)
        datas.add(dataFinal)
        ordenarDatas(datas)
        return datas.get(1).equals(data)
    }

    override fun toString(): String {
        return "Periodo(dataInicial=$dataInicial, dataFinal=$dataFinal, nome='$nome', isAno=$isAno"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Periodo) return false

        return dataInicial == other.dataInicial &&
                dataFinal == other.dataFinal &&
                nome == other.nome &&
                isAno == other.isAno
    }

}

fun getUltimoPeriodoUtilizado(periodosOrdenados: List<Periodo>): Periodo {
    if(periodosOrdenados.isEmpty()){
        return Periodo(ano = 1)
    }else{
        return periodosOrdenados[0]
    }
}
fun getPeriodosFromMovimentacoes_ComAnos(movimentacoes: List<Movimentacao>) : List<Periodo>{
    var datas = getDatasDeMovimentacoes(movimentacoes)
    ordenarDatas(datas, false)

    var anosUtilizados = getAnosUtilizados(datas).toList()
    var periodosUtilizados = mutableListOf<Periodo>()

    anosUtilizados.forEach(){ano ->
        periodosUtilizados.add(
            Periodo(
            ano = ano,
            nome = ano.toString()
            )
        )

        getMesesUtilizados(datas, ano).forEach(){ mes ->
            periodosUtilizados.add(
                Periodo(
                mes = mes,
                ano = ano,
                nome = formataMeseUtilizado( mes, ano)
                )
            )
        }
    }
    return periodosUtilizados
}

fun getPeriodosFromMovimentacoes_SemAnos(movimentacoes: List<Movimentacao>) : List<Periodo>{
    val datas = getDatasDeMovimentacoes(movimentacoes)
    ordenarDatas(datas, false)

    var anosUtilizados = getAnosUtilizados(datas).toList()
    var periodosUtilizados = mutableListOf<Periodo>()

    anosUtilizados.forEach() { ano ->
        getMesesUtilizados(datas, ano).forEach() { mes ->
            periodosUtilizados.add(
                Periodo(
                    mes = mes,
                    ano = ano
                )
            )
        }
    }
    return periodosUtilizados
}

fun getPeriodoFromNome(nome: String?, periodos:List<Periodo>): Periodo? {
    return periodos.find { it.nome == nome }
}
