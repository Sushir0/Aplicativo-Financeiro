package com.example.finance.b_useCase.MacroCategoria

import com.example.finance.a_Domain.Repositorio.MacroCategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import kotlinx.coroutines.delay

class MacroCategoriaService (val macroCategoriaRepository: MacroCategoriaRepository): MacroCategoriaServiceInterface {
    override suspend fun criarMacroCategoria(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean,
        idCasa: String
    ): Resultado<String> {
        return macroCategoriaRepository.save(nome, isGasto, afetaPessoa, afetaCasa, idCasa)
    }
    override suspend fun deletarMacroCategoria(id: String): Resultado<Boolean> {
        return macroCategoriaRepository.deletar(id)
    }
    override fun editarNome(macroCategoriaDTO: MacroCategoriaDTO, novoNome: String): Resultado<Boolean> {
        return macroCategoriaRepository.editarNome(macroCategoriaDTO, novoNome)
    }
    override suspend fun getMacroCategoria(id: String): Resultado<MacroCategoria?> {
        return macroCategoriaRepository.get(id)
    }
    override suspend fun getMacroCategorias(idCasa: String, afetaCasa: Boolean?, afetaPessoa: Boolean?, isGasto: Boolean?): Resultado<List<MacroCategoria>?> {
        return macroCategoriaRepository.getFiltrado(idCasa, afetaCasa, afetaPessoa, isGasto)
    }

}