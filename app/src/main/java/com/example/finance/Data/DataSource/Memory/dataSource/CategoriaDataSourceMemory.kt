package com.example.finance.Data.DataSource.Memory.dataSource

import com.example.finance.Data.DataSource.Memory.entity.CategoriaEntity
import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.a_Domain.Repositorio.CategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO
import java.util.UUID

class CategoriaDataSourceMemory(): CategoriaRepository {
    val categoriaDao = cacheDatabase.categoriaDao

    override suspend fun criarCategoria(
        nome: String,
        macroCategoriaId: String
    ): Resultado<String> {
        val id = UUID.randomUUID().toString()
        val categoriaEntity = CategoriaEntity(
            id = id,
            macroCategoriaId = macroCategoriaId,
            nome = nome
        )
        categoriaDao.save(categoriaEntity)
        return Resultado.Sucesso(id)
    }

    override suspend fun deletarCategoria(id: String): Resultado<Boolean> {
        try{categoriaDao.delete(id)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override suspend fun editarNome(
        categoriaDTO: CategoriaDTO,
        novoNome: String
    ): Resultado<Boolean> {
        try{categoriaDao.editarNome(categoriaDTO.id, novoNome)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override suspend fun toogleAtivo(id: String): Resultado<Boolean> {
        try{categoriaDao.toogleAtivo(id)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override suspend fun getCategoria(id: String): Resultado<Categoria?> {
        val categoriaEntity = categoriaDao.get(id)
        if (categoriaEntity != null){
            return Resultado.Sucesso(categoriaEntity.toCategoria())
        }
        return Resultado.Falha(listOf("Categoria não encontrada"))
    }

    override suspend fun getCategoriasFiltradas(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<Categoria>?> {
        try {
            val categorias = categoriaDao.getFiltradas(idCasa, afetaCasa, afetaPessoa, isGasto)
                .map { it.toCategoria() }
            return Resultado.Sucesso(categorias)
        }
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
    }

    override suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?> {
        try{
            val categorias = categoriaDao.getCategoriasFromMacro(macroCategoriaId).map { it.toCategoria() }
            return Resultado.Sucesso(categorias)
        }
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
    }

}