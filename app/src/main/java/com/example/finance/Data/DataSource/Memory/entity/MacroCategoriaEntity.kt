package com.example.finance.Data.DataSource.Memory.entity

import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria


data class MacroCategoriaEntity(
    val id: String,
    val casaId: String,
    var nome: String,
    val afetaPessoa: Boolean,
    val afetaCasa: Boolean,
    val isGasto: Boolean,
){
    fun toMacroCategoria(): MacroCategoria {
        return MacroCategoria(
            id = id,
            idCasa = casaId,
            nome = nome,
            afetaPessoa = afetaPessoa,
            afetaCasa = afetaCasa,
            isGasto = isGasto
        )
    }
}