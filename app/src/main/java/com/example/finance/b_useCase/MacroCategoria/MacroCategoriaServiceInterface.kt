package com.example.finance.b_useCase.MacroCategoria

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria

interface MacroCategoriaServiceInterface {
    suspend fun criarMacroCategoria(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean,
        idCasa: String
    ): Resultado<String>
    suspend fun deletarMacroCategoria(id: String): Resultado<Boolean>
    fun editarNome(macroCategoriaDTO: MacroCategoriaDTO, novoNome: String): Resultado<Boolean>
    suspend fun getMacroCategoria(id: String): Resultado<MacroCategoria?>
    suspend fun getMacroCategorias(idCasa: String, afetaCasa: Boolean? = null, afetaPessoa: Boolean? = null, isGasto: Boolean? = null): Resultado<List<MacroCategoria>?>
}