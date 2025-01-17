package com.example.finance.Data.DataSource.Memory.dao

import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.Data.DataSource.Memory.cacheDatabase.cacheCategorias
import com.example.finance.Data.DataSource.Memory.entity.CategoriaEntity

class CategoriaDao() {
    fun save(categoria: CategoriaEntity) {
        cacheCategorias.add(categoria)
    }

    fun get(id: String): CategoriaEntity? {
        return cacheCategorias.find { it.id == id }
    }

    fun delete(id: String) {
        cacheCategorias.removeIf { it.id == id }
    }

    fun editarNome(id: String, novoNome: String) {
        val categoria = cacheCategorias.find { it.id == id }
        categoria?.nome = novoNome
    }

    fun toogleAtivo(id: String) {
        val categoria = cacheCategorias.find { it.id == id }
        categoria?.isActivate = !categoria?.isActivate!!
    }

    fun getCategoriasFromMacro(macroCategoriaId: String): List<CategoriaEntity> {
        return cacheCategorias.filter { it.macroCategoriaId == macroCategoriaId }
    }

    fun getFiltradas(idCasa: String, afetaCasa: Boolean?, afetaPessoa: Boolean?, isGasto: Boolean?): List<CategoriaEntity> {
        val macroCategoriasFiltradas = cacheDatabase.cacheMacroCategorias
            .filter { it.casaId == idCasa }
            .filter { afetaCasa == null || it.afetaCasa == afetaCasa }
            .filter { afetaPessoa == null || it.afetaPessoa == afetaPessoa }
            .filter { isGasto == null || it.isGasto == isGasto }
            .map { it.id }
            .toSet()

        return cacheCategorias.filter { it.macroCategoriaId in macroCategoriasFiltradas }
    }

}