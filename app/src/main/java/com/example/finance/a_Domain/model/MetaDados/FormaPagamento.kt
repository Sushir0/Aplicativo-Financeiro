package com.example.finance.a_Domain.model.MetaDados

import com.example.finance.a_Domain.model.Dados.Data

sealed class FormaPagamento{
    class debito(): FormaPagamento()
    data class credito(val dataPagamento: Data): FormaPagamento()
    data class parcelado(val quantidadeDeParcelas: Int, val provedorDeDatasDeParcelas: ProvedorDeDatasDeParcelas = provedorDeDatasDeParcelasPorDiaDoMes()): FormaPagamento()

    override fun toString(): String {
        return when(this){
            is debito -> "DEBITO"
            is credito -> "CREDITO"
            is parcelado -> "PARCELADO"
        }
    }
}