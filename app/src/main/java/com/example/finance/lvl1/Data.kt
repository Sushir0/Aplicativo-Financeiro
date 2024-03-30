package com.example.finance.lvl1

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import java.util.Calendar
import java.util.Date

class Data (val dia : Int, val mes : Int, val ano : Int){

    override fun toString() : String{
        val diaString = if (dia < 10){
            "0${dia}"
        }else{
            "${dia}"
        }
        val mesString = if (mes < 10 ){
            "0${mes}"
        }else{
            "${mes}"
        }
        return "${diaString}/${mesString}/${ano}"
    }
}

fun getDatasUtilizadas(movimentacoes: List<Movimentacao>) : MutableList<String>{
    var datas = getDatasDeMovimentacoes(movimentacoes)
    ordenarDatas(datas, false)

    var anosUtilizados = getAnosUtilizados(datas).toList()
    var datasUtilizadas = mutableListOf<String>()

    anosUtilizados.forEach(){ano ->

        /* Exclamação serve como uma flag para saber se é um ano
    ou um mês, para poder aplicar ao tipografia correta*/
        datasUtilizadas.add("!"+ano.toString())

        getMesesUtilizados(datas, ano).forEach(){mes ->
            datasUtilizadas.add(formataMeseUtilizado(mes, ano))
        }
    }
    return datasUtilizadas
}


fun getDatasDeMovimentacoes(movimentacoes: List<Movimentacao>): MutableList<Data>{
    var datas = mutableListOf<Data>()
    movimentacoes.forEach({
        datas.add(it.data)
    })
    return datas
}
fun ordenarDatas(datas: MutableList<Data>, ordemCrescente: Boolean = true){
    ordenarPorDia(datas, ordemCrescente)
    ordenarPorMes(datas, ordemCrescente)
    ordenarPorAno(datas, ordemCrescente)
}
fun ordenarPorAno(datas: MutableList<Data>, ordemCrescente : Boolean = true){
    if(ordemCrescente){
        datas.sortBy { it.ano }
    }else{
        datas.sortByDescending { it.ano }
    }
}
fun ordenarPorMes(datas: MutableList<Data>, ordemCrescente: Boolean = true){
    if(ordemCrescente) {
        datas.sortBy { it.mes }
    }else {
        datas.sortByDescending { it.mes }
    }
}
fun ordenarPorDia(datas: MutableList<Data>, ordemCrescente: Boolean = true){
    if(ordemCrescente) {
        datas.sortBy { it.dia }
    }else{
        datas.sortByDescending { it.dia }
    }
}
fun getMesesUtilizados(datas : List<Data>, ano : Int) : Set<Int>{
    return datas.filter { it.ano.equals(ano) }.map { it.mes }.toSet()
}

fun getAnosUtilizados(datas : List<Data>) : Set<Int>{
    return datas.map { it.ano }.toSet()
}

fun formataMeseUtilizado(mes: Int, ano: Int): String {
    val anoFormatado = (ano % 100).toString().padStart(2, '0')
    return "${getMesAbreviados(mes)}/$anoFormatado"
}
fun getMesAbreviados(mes : Int) : String{
    return when (mes) {
        1 -> "Jan"
        2 -> "Fev"
        3 -> "Mar"
        4 -> "Abr"
        5 -> "Mai"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Ago"
        9 -> "Set"
        10 -> "Out"
        11 -> "Nov"
        12 -> "Dez"
        else -> throw IllegalArgumentException("Mês inválido: $mes")
    }
}


fun converterDataMillisParaData(dataMillis : Long, datePicker : Boolean = false): Data {
    val calendar = Calendar.getInstance().apply { timeInMillis = dataMillis }
    val dia = if(datePicker) {
        calendar.get(Calendar.DAY_OF_MONTH) + 1
    } else{
        calendar.get(Calendar.DAY_OF_MONTH)
    }
    val mes = calendar.get(Calendar.MONTH) + 1 // Adiciona 1 ao mês
    val ano = calendar.get(Calendar.YEAR)
    return Data(dia, mes, ano)
}

