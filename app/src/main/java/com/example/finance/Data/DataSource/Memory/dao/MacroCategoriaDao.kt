package com.example.finance.Data.DataSource.Memory.dao

import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.Data.DataSource.Memory.entity.MacroCategoriaEntity

class MacroCategoriaDao {
    fun save(macroCategoria: MacroCategoriaEntity) {
        cacheDatabase.cacheMacroCategorias.add(macroCategoria)
    }

    fun get(id: String): MacroCategoriaEntity? {
        return cacheDatabase.cacheMacroCategorias.find { it.id == id }
    }

    fun delete(id: String) {
        cacheDatabase.cacheMacroCategorias.removeIf { it.id == id }
    }

    fun editarNome(id: String, novoNome: String) {
        val macroCategoria = cacheDatabase.cacheMacroCategorias.find { it.id == id }
        macroCategoria?.nome = novoNome
    }

    fun getFiltradas(idCasa: String, afetaCasa: Boolean?, afetaPessoa: Boolean?, isGasto: Boolean?): List<MacroCategoriaEntity> {
        val macroCategoriasFiltradas = cacheDatabase.cacheMacroCategorias
            .filter { it.casaId == idCasa }
            .filter { afetaCasa == null || it.afetaCasa == afetaCasa }
            .filter { afetaPessoa == null || it.afetaPessoa == afetaPessoa }
            .filter { isGasto == null || it.isGasto == isGasto }
            .map { it.id }
            .toSet()
        return cacheDatabase.cacheMacroCategorias.filter { it.id in macroCategoriasFiltradas }
    }


}