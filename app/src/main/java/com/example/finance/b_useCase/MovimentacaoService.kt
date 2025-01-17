package com.example.finance.b_useCase

import com.example.finance.a_Domain.Repositorio.MovimentacaoRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Id
import com.example.finance.a_Domain.model.MetaDados.ProvedorDeDatasDeParcelas
import com.example.finance.a_Domain.model.MetaDados.provedorDeDatasDeParcelasPorDiaDoMes
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO
import com.example.finance.lvl2.validações.validarMovimentacao

class MovimentacaoService(
    private val movimentacaoRepository: MovimentacaoRepository
) {

    suspend fun criarMovimentacao(
        descricao: String?,
        valor: Double,
        data: Data,
        categoriaId: String,
        formaPagamento: FormaPagamento,
        movimentacaoHolder: MovimentacaoHolderDTO,
    ): Resultado<String> {
        val erros = validarMovimentacao(descricao, valor, data)
        if(erros.isNotEmpty()){
            return Resultado.Falha(erros)
        }
        val resultadoMovimentacao = movimentacaoRepository.criarMovimentacao(
            descricao = descricao!!,
            valor = valor,
            data = data,
            formaPagamento = formaPagamento,
            categoriaId = categoriaId,
            movimentacaoHolderType = movimentacaoHolder.tipo,
            movimentacaoHolderId = movimentacaoHolder.id
        )
        if(resultadoMovimentacao is Resultado.Sucesso){
            val movimentacaoId = resultadoMovimentacao.data
            val parcelas: List<ParcelaInput> = criarParcelas(descricao, valor, data, movimentacaoId, formaPagamento)
            for(parcela in parcelas){
                val resultadoParcela = movimentacaoRepository.criarParcela(parcela.descricao, parcela.valor, parcela.data, parcela.movimentacaoId)
                if(resultadoParcela is Resultado.Falha){
                    return Resultado.Falha(resultadoParcela.errors)
                }
            }
            return Resultado.Sucesso(movimentacaoId)
        }else{
            return Resultado.Falha(listOf("Erro interno ao criar movimentação"))
        }
    }

    suspend fun deleteMovimentacao(id: String): Resultado<Boolean> {
        return movimentacaoRepository.deletarMovimentacao(id)
    }




    private fun criarParcelas(
        descricao: String,
        valor: Double,
        data: Data,
        movimentacaoId: String,
        formaPagamento: FormaPagamento
    ): List<ParcelaInput>{
        val parcelas = mutableListOf<ParcelaInput>()
        when(formaPagamento){
            is FormaPagamento.debito -> parcelas.add(ParcelaInput(
                descricao = descricao,
                valor = valor,
                data = data,
                movimentacaoId = movimentacaoId
            ))
            is FormaPagamento.credito -> parcelas.add(ParcelaInput(
                descricao = descricao,
                valor = valor,
                data = formaPagamento.dataPagamento,
                movimentacaoId = movimentacaoId,
            ))
            is FormaPagamento.parcelado -> {
                val tamanho = formaPagamento.quantidadeDeParcelas
                val valorParcela = valor / tamanho
                val datas = formaPagamento.provedorDeDatasDeParcelas.gerarDatasDasParcelas(tamanho, data)
                for (indice in 1..tamanho){
                    val parcela = ParcelaInput(
                        descricao = "Parcela $indice de $tamanho de $descricao",
                        valor = valorParcela,
                        data = datas[indice-1],
                        movimentacaoId = movimentacaoId
                    )
                    parcelas.add(parcela)
                }
            }
        }
        return parcelas
    }
}

private data class ParcelaInput(
    val descricao: String,
    val valor: Double,
    val data: Data,
    val movimentacaoId: String,
)