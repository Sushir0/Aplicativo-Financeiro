package com.example.finance.lvl1

import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import java.io.Serializable
import java.util.Random

class Periodo : Serializable {
    val dataInicial: Data
    val dataFinal: Data
    val nome: String
    val isAno: Boolean
    val id: Int
    constructor(mes: Int? = null, ano: Int, nome: String? = null){
        this.dataInicial = Data(1, mes ?: 1 , ano)
        this.dataFinal = Data(31, mes ?: 12, ano)
        this.nome = nome?: "Período personalizado"
        isAno = mes == null
        id = Random().nextInt(100000)

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
        this.nome = "Período personalizado"
        isAno = false
        id = Random().nextInt(100000)
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
        return "Periodo(dataInicial=$dataInicial, dataFinal=$dataFinal, nome='$nome', isAno=$isAno, id=$id)"
    }

}

fun getUltimoPeriodoUtilizado(periodosOrdenados: List<Periodo>):Periodo{
    if(periodosOrdenados.isEmpty()){
        return Periodo(ano = 1)
    }else{
        return periodosOrdenados[0]
    }
}
fun getPeriodosFromMovimentacoes(movimentacoes: List<Movimentacao>) : List<Periodo>{
    var datas = getDatasDeMovimentacoes(movimentacoes)
    ordenarDatas(datas, false)

    var anosUtilizados = getAnosUtilizados(datas).toList()
    var periodosUtilizados = mutableListOf<Periodo>()

    anosUtilizados.forEach(){ano ->
        periodosUtilizados.add(Periodo(
            ano = ano,
            nome = ano.toString()
        ))

        getMesesUtilizados(datas, ano).forEach(){mes ->
            periodosUtilizados.add(Periodo(
                mes = mes,
                ano = ano,
                nome = formataMeseUtilizado( mes, ano)
            ))
        }
    }
    return periodosUtilizados
}

fun getPeriodoFromNome(nome: String, periodos:List<Periodo>): Periodo? {
    return periodos.find { it.nome == nome }
}
