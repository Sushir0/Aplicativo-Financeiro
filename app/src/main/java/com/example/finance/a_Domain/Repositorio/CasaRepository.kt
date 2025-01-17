package com.example.finance.a_Domain.Repositorio

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.CasaDTO
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa

interface CasaRepository {
    suspend fun getCasa(id: String): Resultado<Casa?>
    suspend fun criarCasa(nome: String): Resultado<String>
    suspend fun deletarCasa(id: String): Resultado<Boolean>
    suspend fun editarNome(casaDTO: CasaDTO, novoNome: String): Resultado<Boolean>
    suspend fun criarMembro(casaId: String, nome: String): Resultado<String>
    suspend fun getMembros(casaId: String): Resultado<List<Pessoa>?>

}