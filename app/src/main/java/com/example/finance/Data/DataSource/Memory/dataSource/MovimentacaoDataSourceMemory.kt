package com.example.finance.Data.DataSource.Memory.dataSource

import com.example.finance.a_Domain.Repositorio.MovimentacaoRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento

class MovimentacaoDataSourceMemory: MovimentacaoRepository {
    override suspend fun criarParcela(
        descricao: String,
        valor: Double,
        data: Data,
        movimentacaoId: String
    ): Resultado<String> {
        TODO("Not yet implemented")
    }

    override suspend fun criarMovimentacao(
        descricao: String,
        valor: Double,
        data: Data,
        formaPagamento: FormaPagamento,
        categoriaId: String,
        movimentacaoHolderType: String,
        movimentacaoHolderId: String
    ): Resultado<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deletarMovimentacao(id: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

}