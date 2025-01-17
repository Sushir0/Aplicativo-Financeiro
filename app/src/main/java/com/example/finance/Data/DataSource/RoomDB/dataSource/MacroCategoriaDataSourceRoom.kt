package com.example.finance.Data.DataSource.RoomDB.dataSource

import android.content.Context
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.Data.DataSource.RoomDB.dao.MacroCategoriaDao
import com.example.finance.Data.DataSource.RoomDB.entity.MacroCategoriaEntity
import com.example.finance.a_Domain.Repositorio.MacroCategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import java.util.UUID

class MacroCategoriaDataSourceRoom(
    val context: Context
) : MacroCategoriaRepository {
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val macroCategoriaDao: MacroCategoriaDao = database.macroCategoriaDao()
    override suspend fun save(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean,
        idCasa: String
    ): Resultado<String> {
        try {
            val id = UUID.randomUUID().toString()
            val macroCategoriaEntity = MacroCategoriaEntity(
                id = id,
                nome = nome,
                isGasto = isGasto,
                afetaPessoa = afetaPessoa,
                afetaCasa = afetaCasa,
                casaId = idCasa
            )
            macroCategoriaDao.save(macroCategoriaEntity)
            return Resultado.Sucesso(id)
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }

    }

    override suspend fun deletar(id: String): Resultado<Boolean> {
        try {
            macroCategoriaDao.delete(id)
            return Resultado.Sucesso(true)
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }

    override fun editarNome(
        macroCategoriaDTO: MacroCategoriaDTO,
        novoNome: String
    ): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: String): Resultado<MacroCategoria?> {
        try {
            val macroCategoriaEntity = macroCategoriaDao.get(id) ?: return Resultado.Falha(listOf("MacroCategoria não encontrada"))
            return Resultado.Sucesso(macroCategoriaEntity.toModel())
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }

    override suspend fun getFiltrado(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<MacroCategoria>?> {
        try {
            val macroCategoriaEntities = macroCategoriaDao.getFiltrada(
                idCasa = idCasa,
                afetaCasa = afetaCasa,
                afetaPessoa = afetaPessoa,
                isGasto = isGasto
            ) ?: return Resultado.Falha(listOf("MacroCategorias não encontradas"))
            return Resultado.Sucesso(macroCategoriaEntities.map { it.toModel() })
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }


}