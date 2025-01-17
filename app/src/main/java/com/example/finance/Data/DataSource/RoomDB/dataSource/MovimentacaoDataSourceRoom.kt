package com.example.finance.Data.DataSource.RoomDB.dataSource

import android.content.Context
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.Data.DataSource.RoomDB.entity.MovimentacaoEntity
import com.example.finance.Data.DataSource.RoomDB.entity.ParcelaEntity
import com.example.finance.a_Domain.Repositorio.MovimentacaoRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import java.util.UUID


class MovimentacaoDataSourceRoom(context: Context): MovimentacaoRepository {
    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private val movimentacaoDao = database.movimentacaoDao()
    private val parcelaDao = database.parcelaDao()

    override suspend fun criarParcela(
        descricao: String,
        valor: Double,
        data: Data,
        movimentacaoId: String
    ): Resultado<String> {
        val id = UUID.randomUUID().toString()
        val parcelaEntity = ParcelaEntity(
            id = id,
            descricao = descricao,
            valor = valor,
            data = data.toTimeStamp(),
            movimentacao_id = movimentacaoId
        )
        parcelaDao.save(parcelaEntity)
        return Resultado.Sucesso(id)
    }

    override suspend fun criarMovimentacao(
        descricao: String,
        valor: Double,
        data: Data,
        formaPagamento: FormaPagamento,
        categoriaId: String,
        movimentacaoHolderType: String,
        movimentacaoHolderId: String,
    ): Resultado<String> {
        val id = UUID.randomUUID().toString()
        // posteriormente, adicionar validação do id
        val movimentacaoEntity = MovimentacaoEntity(
            id = id,
            descricao = descricao,
            valor = valor,
            data = data.toTimeStamp(),
            formaPagamento = formaPagamento.toString(),
            categoriaId = categoriaId,
            movimentacaoHolderType = movimentacaoHolderType,
            casaId = if (movimentacaoHolderType == "CASA") movimentacaoHolderId else null,
            pessoaId = if (movimentacaoHolderType == "PESSOA") movimentacaoHolderId else null
        )
        movimentacaoDao.save(movimentacaoEntity)
        return Resultado.Sucesso(id)
    }

    override suspend fun deletarMovimentacao(id: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

}