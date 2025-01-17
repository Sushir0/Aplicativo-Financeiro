package com.example.finance.Data.DataSource.RoomDB.dataSource

import android.content.Context
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.Data.DataSource.RoomDB.dao.CategoriaDao
import com.example.finance.Data.DataSource.RoomDB.dao.MacroCategoriaDao
import com.example.finance.Data.DataSource.RoomDB.entity.CategoriaComMacroCategoriaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.CategoriaEntity
import com.example.finance.a_Domain.Repositorio.CategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO
import java.util.UUID

class CategoriaDataSourceRoom(
    val context: Context
): CategoriaRepository {
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val categoriaDao: CategoriaDao = database.categoriaDao()
    val macroCategoriaDao: MacroCategoriaDao = database.macroCategoriaDao()

    override suspend fun criarCategoria(
        nome: String,
        macroCategoriaId: String
    ): Resultado<String> {
        val id = UUID.randomUUID().toString()
        val categoriaEntity = CategoriaEntity(
            id = id,
            nome = nome,
            macroCategoriaId = macroCategoriaId,
            isActivate = true
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
        val categoriaEntity = CategoriaEntity(
            id = categoriaDTO.id,
            nome = novoNome,
            macroCategoriaId = categoriaDTO.macroCategoriaId,
            isActivate = categoriaDTO.isActivate
        )
        try{categoriaDao.save(categoriaEntity)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override suspend fun toogleAtivo(id: String): Resultado<Boolean> {
        val categoria = categoriaDao.get(id) ?: return Resultado.Falha(listOf("Categoria não encontrada"))
        val categoriaEntity = CategoriaEntity(
            id = categoria.id,
            nome = categoria.nome,
            macroCategoriaId = categoria.macroCategoriaId,
            isActivate = !categoria.isActivate
        )
        try{categoriaDao.save(categoriaEntity)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)


    }

    override suspend fun getCategoria(id: String): Resultado<Categoria?> {
        try{
            val categoriaEntity =
                categoriaDao.get(id) ?: return Resultado.Falha(listOf("Categoria não encontrada"))
            val macroCategoria =
                macroCategoriaDao.get(categoriaEntity.macroCategoriaId) ?: return Resultado.Falha(
                    listOf("Macro Categoria não encontrada")
                )
            return Resultado.Sucesso(
                Categoria(
                    id = categoriaEntity.id,
                    nome = categoriaEntity.nome,
                    macroCategoria = macroCategoria.toModel(),
                    isActivate = categoriaEntity.isActivate
                )
            )
        }
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
    }

    override suspend fun getCategoriasFiltradas(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<Categoria>?> {
        try {
            val categorias = categoriaDao.getFiltrada(
                idCasa = idCasa,
                afetaCasa = afetaCasa,
                afetaPessoa = afetaPessoa,
                isGasto = isGasto
            )
            return Resultado.Sucesso(categorias?.map {
                Categoria(
                    id = it.id,
                    nome = it.nome,
                    macroCategoria = macroCategoriaDao.get(it.macroCategoriaId)!!.toModel(),
                )})
        }
        catch (
            e: Exception
        ){return Resultado.Falha(listOf(e.toString()))}
    }

    override suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?> {
        try {
            val categorias = categoriaDao.getByMacroCategoria(macroCategoriaId)
            return Resultado.Sucesso(categorias?.map {
                Categoria(
                    id = it.id,
                    nome = it.nome,
                    macroCategoria = macroCategoriaDao.get(it.macroCategoriaId)!!.toModel(),
                    isActivate = it.isActivate
                )
            })
        }
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
    }

    suspend fun getCategoriaComMacroCategoria(id: String): CategoriaComMacroCategoriaEntity? {
        return categoriaDao.getCategoriaComMacroCategoria(id)
    }
}