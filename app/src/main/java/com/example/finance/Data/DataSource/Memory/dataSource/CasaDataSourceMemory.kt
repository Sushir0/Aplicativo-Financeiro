package com.example.finance.Data.DataSource.Memory.dataSource

import com.example.finance.a_Domain.Repositorio.CasaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.CasaDTO
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa

class CasaDataSourceMemory : CasaRepository {
    override suspend fun getCasa(id: String): Resultado<Casa?> {
        TODO("Not yet implemented")
    }

    override suspend fun criarCasa(nome: String): Resultado<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deletarCasa(id: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun editarNome(casaDTO: CasaDTO, novoNome: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun criarMembro(casaId: String, nome: String): Resultado<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getMembros(casaId: String): Resultado<List<Pessoa>?> {
        TODO("Not yet implemented")
    }

}
