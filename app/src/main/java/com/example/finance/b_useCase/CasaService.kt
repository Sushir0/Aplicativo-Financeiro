package com.example.finance.b_useCase

import com.example.finance.a_Domain.Repositorio.CasaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder

class CasaService(
    val casaRepository: CasaRepository
) {

    suspend fun getMembros(idCasa: String): Resultado<List<MovimentacaoHolder>?>{
        try {
            val resultado = casaRepository.getCasa(idCasa)
            var membros = mutableListOf<MovimentacaoHolder>()
            if(resultado is Resultado.Sucesso){
                membros.add(resultado.data!!)
                val resultadoMovimentacoes = casaRepository.getMembros(idCasa)
                if(resultadoMovimentacoes is Resultado.Sucesso){
                    membros.addAll(resultadoMovimentacoes.data!!)
                }
                return Resultado.Sucesso(membros)
            }else{
                return Resultado.Falha(listOf("Erro ao buscar membros"))
            }
        }
        catch (e: Exception){
            return Resultado.Falha(listOf("Erro ao buscar membros"))
        }
    }

    suspend fun criarCasa(nome: String): Resultado<String>{
        return casaRepository.criarCasa(nome)
    }

    suspend fun criarMembro(casaId: String, nome: String): Resultado<String>{
        return casaRepository.criarMembro(casaId, nome)
    }
}
