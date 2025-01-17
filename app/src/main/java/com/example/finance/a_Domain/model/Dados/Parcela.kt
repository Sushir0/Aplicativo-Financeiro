package com.example.finance.a_Domain.model.Dados

import com.example.finance.a_Domain.model.MetaDados.Periodo
import java.util.UUID
import kotlin.random.Random

data class Parcela(
    val id: String,
    val descricao: String,
    val valor: Double,
    val data: Data,
    val movimentacaoid: String,
    val isGasto: Boolean
){
    constructor(
        descricao: String,
        valor: Double,
        data: Data,
        movimentacaoid: String,
        isGasto: Boolean
    ) : this(
        id = UUID.randomUUID().toString(),
        descricao = descricao,
        valor = valor,
        data = data,
        movimentacaoid = movimentacaoid,
        isGasto = isGasto
    )

    fun isOnPeriodo(periodo: Periodo?): Boolean {
        return periodo?.isOnPeriodo(data) ?: true
    }

    override fun toString(): String {
        return "Parcela(id=$id, data=$data, valor=$valor)"
    }
}

fun ParcelaMock(
    descricao: String = "Parcela: ",
    valor: Double = Random.nextDouble(100.0),
    data: Data = DataMock(),
    movimentacaoid: String = UUID.randomUUID().toString(),
    isGasto: Boolean = Random.nextBoolean()
): Parcela {
    val descricaoCompleta = if (descricao == "Parcela: "){
        descricao+data.toString()
    }else{
        descricao
    }
    return Parcela(
        id = UUID.randomUUID().toString(),
        descricao = descricaoCompleta,
        valor = valor,
        data = data,
        movimentacaoid = movimentacaoid,
        isGasto = isGasto
    )

}