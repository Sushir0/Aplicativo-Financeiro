package com.example.finance.Data.DataSource.RoomDB.dataSource

import android.content.Context
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.Data.DataSource.RoomDB.dao.CasaDao
import com.example.finance.Data.DataSource.RoomDB.dao.PessoaDao
import com.example.finance.Data.DataSource.RoomDB.entity.CasaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.PessoaEntity
import com.example.finance.a_Domain.Repositorio.CasaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.CasaDTO
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa
import java.util.UUID

class CasaDataSourceRoom(
    val context: Context
): CasaRepository {
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val casaDao: CasaDao = database.casaDao()
    val pessoaDao: PessoaDao = database.pessoaDao()
    override suspend fun getCasa(id: String): Resultado<Casa?> {
        try {
            val casaEntity = casaDao.getCasa(id)
            val casa = Casa(
                id = casaEntity.id,
                nome = casaEntity.nome,
            )
            return Resultado.Sucesso(casa)
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }

    override suspend fun criarCasa(nome: String): Resultado<String> {
        try {
            val id = UUID.randomUUID().toString()
            val casaEntity = CasaEntity(
                id = id,
                nome = nome,
                cor = "",
                foto = ""
            )
            casaDao.save(casaEntity)
            return Resultado.Sucesso(id)
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))

        }
    }

    override suspend fun deletarCasa(id: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun editarNome(casaDTO: CasaDTO, novoNome: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun criarMembro(casaId: String, nome: String): Resultado<String> {
        try {
            val id = UUID.randomUUID().toString()
            val pessoaEntity = PessoaEntity(
                id = id,
                nome = nome,
                casaId = casaId,
                foto = "",
                cor = ""
            )
            pessoaDao.save(pessoaEntity)
            return Resultado.Sucesso(id)
        }
        catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }

    override suspend fun getMembros(casaId: String): Resultado<List<Pessoa>?> {
        try {
            val pessoasEntity = pessoaDao.getMembros(casaId)
            val pessoas = pessoasEntity.map {
                Pessoa(
                    id = it.id,
                    nome = it.nome,
                )
                }
            return Resultado.Sucesso(pessoas)
        }catch (e: Exception){
            return Resultado.Falha(listOf(e.toString()))
        }
    }


}