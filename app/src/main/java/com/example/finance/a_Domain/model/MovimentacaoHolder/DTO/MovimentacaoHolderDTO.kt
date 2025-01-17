package com.example.finance.a_Domain.model.MovimentacaoHolder.DTO

import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa

data class MovimentacaoHolderDTO (
    val id: String,
    val nome: String,
    val isCasa: Boolean
){
    val tipo: String = if (isCasa) "CASA" else "PESSOA"
    fun toMovimentacaoHolder(): MovimentacaoHolder {
        return if (isCasa) {
            Casa(id = id, nome = nome)
        }else{
            Pessoa(id = id, nome = nome)
        }
    }
}
