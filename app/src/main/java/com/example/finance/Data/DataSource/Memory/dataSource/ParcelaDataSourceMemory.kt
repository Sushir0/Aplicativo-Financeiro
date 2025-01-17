package com.example.finance.Data.DataSource.Memory.dataSource

import com.example.finance.a_Domain.Repositorio.ParcelaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO

class ParcelaDataSourceMemory : ParcelaRepository {
    override suspend fun getParcela(id: String): Resultado<Parcela?> {
        TODO("Not yet implemented")
    }

    override suspend fun getParcelasPorMovimentacao(movimentacaoId: String): Resultado<List<Parcela>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getParcelas(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo?,
        selecaoUsuario: SelecaoUsuario,
        formaPagamento: FormaPagamento?
    ): Resultado<List<Parcela>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getValor(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo?,
        selecaoUsuario: SelecaoUsuario,
        formaPagamento: FormaPagamento?
    ): Resultado<Double?> {
        TODO("Not yet implemented")
    }

}
