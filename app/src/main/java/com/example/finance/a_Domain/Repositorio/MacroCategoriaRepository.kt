package com.example.finance.a_Domain.Repositorio

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria

interface MacroCategoriaRepository {
    suspend fun save(nome: String, isGasto: Boolean, afetaPessoa: Boolean, afetaCasa: Boolean, idCasa: String): Resultado<String>
    suspend fun deletar(id: String): Resultado<Boolean>
    fun editarNome(macroCategoriaDTO: MacroCategoriaDTO, novoNome: String): Resultado<Boolean>
    suspend fun get(id: String): Resultado<MacroCategoria?>
    suspend fun getFiltrado(idCasa: String, afetaCasa: Boolean? = null, afetaPessoa: Boolean? = null, isGasto: Boolean? = null): Resultado<List<MacroCategoria>?>

}
