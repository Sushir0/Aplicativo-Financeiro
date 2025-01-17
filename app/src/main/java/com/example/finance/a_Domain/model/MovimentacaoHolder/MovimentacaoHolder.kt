package com.example.finance.a_Domain.model.MovimentacaoHolder

import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO
import com.example.finance.a_Domain.model.Usuario.Perfil
import java.io.Serializable

abstract class MovimentacaoHolder :Serializable{
    abstract val id: String
    abstract val nome: String
    abstract val perfil: Perfil
    abstract val isCasa: Boolean


    fun sameID(id:String):Boolean{
        return this.id==id
    }

    fun toDTO(): MovimentacaoHolderDTO {
        return MovimentacaoHolderDTO(
            id = id,
            nome = nome,
            isCasa = isCasa
        )
    }

}
