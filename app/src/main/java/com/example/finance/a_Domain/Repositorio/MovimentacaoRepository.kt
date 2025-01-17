package com.example.finance.a_Domain.Repositorio

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Id
import java.io.Serializable

interface MovimentacaoRepository{
    suspend fun criarParcela(descricao: String, valor: Double, data: Data, movimentacaoId: String): Resultado<String>
    suspend fun criarMovimentacao(
        descricao: String,
        valor: Double,
        data: Data,
        formaPagamento: FormaPagamento,
        categoriaId: String,
        movimentacaoHolderType: String,
        movimentacaoHolderId: String,
        ): Resultado<String>
    suspend fun deletarMovimentacao(id: String): Resultado<Boolean>
}