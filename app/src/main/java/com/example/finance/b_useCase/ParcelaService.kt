package com.example.finance.b_useCase

import com.example.finance.a_Domain.Repositorio.ParcelaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO

class ParcelaService (private val parcelaRepository: ParcelaRepository){

    suspend fun getParcelas(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
        periodo: Periodo? = null,
        formaPagamento: FormaPagamento? = null
        ): Resultado<List<Parcela>?> {
        return parcelaRepository.getParcelas(movimentacaoHolderDTO, periodo, selecaoUsuario, formaPagamento)
    }

    suspend fun getValor(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        selecaoUsuario: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
        periodo: Periodo? = null,
        formaPagamento: FormaPagamento? = null
    ): Resultado<Double?> {
        return parcelaRepository.getValor(movimentacaoHolderDTO, periodo, selecaoUsuario, formaPagamento)
    }

}