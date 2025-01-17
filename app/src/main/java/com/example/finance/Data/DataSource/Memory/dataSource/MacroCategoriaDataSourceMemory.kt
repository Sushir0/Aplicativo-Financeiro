package com.example.finance.Data.DataSource.Memory.dataSource

import com.android.identity.util.UUID
import com.example.finance.Data.DataSource.Memory.dao.MacroCategoriaDao
import com.example.finance.Data.DataSource.Memory.entity.MacroCategoriaEntity
import com.example.finance.a_Domain.Repositorio.MacroCategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria

class MacroCategoriaDataSourceMemory: MacroCategoriaRepository {
    val macroCategoriaDao = MacroCategoriaDao()
    override suspend fun save(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean,
        idCasa: String
    ): Resultado<String> {
        val id = UUID.randomUUID().toString()
        val macroCategoriaEntity = MacroCategoriaEntity(
            nome = nome,
            isGasto = isGasto,
            afetaPessoa = afetaPessoa,
            afetaCasa = afetaCasa,
            casaId = idCasa,
            id = id
        )
        macroCategoriaDao.save(macroCategoriaEntity)
        return Resultado.Sucesso(id)
    }

    override suspend fun deletar(id: String): Resultado<Boolean> {
        try{macroCategoriaDao.delete(id)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override fun editarNome(
        macroCategoriaDTO: MacroCategoriaDTO,
        novoNome: String
    ): Resultado<Boolean> {
        try{macroCategoriaDao.editarNome(macroCategoriaDTO.id, novoNome)}
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}
        return Resultado.Sucesso(true)
    }

    override suspend fun get(id: String): Resultado<MacroCategoria?> {
        val macroCategoriaEntity = macroCategoriaDao.get(id)
        if (macroCategoriaEntity != null){
            return Resultado.Sucesso(macroCategoriaEntity.toMacroCategoria())
        }
        return Resultado.Falha(listOf("MacroCategoria não encontrada"))
    }

    override suspend fun getFiltrado(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<MacroCategoria>?> {
        try{
            val macroCategorias = macroCategoriaDao.getFiltradas(idCasa, afetaCasa, afetaPessoa, isGasto)
                .map { it.toMacroCategoria() }
            return Resultado.Sucesso(macroCategorias) }
        catch (e: Exception){return Resultado.Falha(listOf(e.toString()))}


    }

}