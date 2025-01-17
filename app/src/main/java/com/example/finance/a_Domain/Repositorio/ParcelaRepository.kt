package com.example.finance.a_Domain.Repositorio

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Id
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO

interface ParcelaRepository {
    // a responsabilidade de criar parcela ficará no Movimentacao repository
    suspend fun getParcela(id: String): Resultado<Parcela?>
    suspend fun getParcelasPorMovimentacao(movimentacaoId: String): Resultado<List<Parcela>?>
    suspend fun getParcelas(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo? = null,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
        formaPagamento: FormaPagamento? = null
    ): Resultado<List<Parcela>?>
    suspend fun getValor(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo? = null,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
        formaPagamento: FormaPagamento? = null
    ): Resultado<Double?>
}