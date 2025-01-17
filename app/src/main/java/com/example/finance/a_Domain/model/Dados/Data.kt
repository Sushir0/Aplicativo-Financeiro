package com.example.finance.a_Domain.model.Dados

import java.io.Serializable
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

data class Data (val dia : Int, val mes : Int, val ano : Int): Serializable{

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
    fun isPosterior(data: Data): Boolean {
        return this.ano > data.ano ||
                (this.ano == data.ano && this.mes > data.mes) ||
                (this.ano == data.ano && this.mes == data.mes && this.dia > data.dia)
    }

    fun toTimeStamp(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, ano)
        calendar.set(Calendar.MONTH, mes - 1) // Meses começam do índice 0
        calendar.set(Calendar.DAY_OF_MONTH, dia)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun criarDataIncrementandoMeses(quantidadeDeMeses: Int): Data {
        var novoMes = this.mes + quantidadeDeMeses
        var novoAno = this.ano

        while (novoMes > 12){
            novoMes -= 12
            novoAno += 1
        }

        val diasValidosNoMes = diasNoMes(novoMes, novoAno)
        val novoDia = if (dia > diasValidosNoMes) diasValidosNoMes else dia

        return Data(novoDia, novoMes, novoAno)
    }
    fun criarDataIncrementandoDias(quantidadeDeDias: Int): Data {
        var novoDia = this.dia + quantidadeDeDias
        var novoMes = this.mes
        var novoAno = this.ano

        val diasValidosNoMes = diasNoMes(novoMes, novoAno)

        while (novoDia > diasValidosNoMes){
            novoDia -= diasValidosNoMes
            novoMes += 1
            if (novoMes > 12){
                novoMes -= 12
                novoAno += 1
            }

        }
        return Data(novoDia, novoMes, novoAno)
    }

    fun criarDataDecrementandoMeses(quantidadeDeMeses: Int): Data {
        var novoMes = this.mes - quantidadeDeMeses
        var novoAno = this.ano

        while (novoMes <= 0) {
            novoMes += 12
            novoAno -= 1
        }

        val diasValidosNoMes = diasNoMes(novoMes, novoAno)
        val novoDia = if (dia > diasValidosNoMes) diasValidosNoMes else dia

        return Data(novoDia, novoMes, novoAno)
    }


    private fun diasNoMes(mes: Int, ano: Int): Int {
        return when (mes) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) 29 else 28
            else -> throw IllegalArgumentException("Mês inválido: $mes")
        }
    }
}

fun getDatasDeMovimentacoes(movimentacoes: List<Movimentacao>): MutableList<Data>{
    var datas = mutableListOf<Data>()
    movimentacoes.forEach {
        datas.add(it.data)
    }
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

fun dataFromTimeStamp(timeStamp: Long): Data {
    val localDate = Instant.ofEpochMilli(timeStamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return Data(
        dia = localDate.dayOfMonth,
        mes = localDate.monthValue,
        ano = localDate.year
    )
}


fun converterDataMillisParaData(dataMillis : Long, datePicker : Boolean = false): Data {
    val calendar = Calendar.getInstance().apply { timeInMillis = dataMillis }
    var dia : Int

    if(datePicker) {
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        dia = calendar.get(Calendar.DAY_OF_MONTH)
    } else{
        dia = calendar.get(Calendar.DAY_OF_MONTH)
    }
    val mes = calendar.get(Calendar.MONTH) + 1
    val ano = calendar.get(Calendar.YEAR)
    return Data(dia, mes, ano)
}


fun DataMock(): Data {
    val random = java.util.Random()
    return Data(
        dia = random.nextInt(28) + 1,
        mes = random.nextInt(12) + 1,
        ano = random.nextInt(5) + 2023
    )
}

