package com.example.finance.Data.DataSource.Memory.entity

import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.a_Domain.model.Categorizacao.Categoria

data class CategoriaEntity(
    val id: String,
    val macroCategoriaId: String,
    var nome: String,
    var isActivate: Boolean = true,
){
    fun toCategoria(): Categoria {
        return Categoria(
            id = id,
            macroCategoria = cacheDatabase.cacheMacroCategorias.find { it.id == macroCategoriaId }!!.toMacroCategoria(),
            nome = nome,
            isActivate = isActivate
        )
    }
}
