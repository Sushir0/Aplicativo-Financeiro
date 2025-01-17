package com.example.finance.b_useCase.Categoria

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO

interface CategoriaServiceInterface {
    suspend fun criarCategoria(nome: String, macroCategoriaId: String): Resultado<String>
    suspend fun deletarCategoria(id: String): Resultado<Boolean>
    suspend fun editarNome(categoriaDTO: CategoriaDTO, novoNome: String): Resultado<Boolean>
    suspend fun togleAtivo(categoriaId: String): Resultado<Boolean>
    suspend fun getCategoria(id: String): Resultado<Categoria?>
    suspend fun getCategoriasFiltradas(
        idCasa: String,
        afetaCasa: Boolean? = null,
        afetaPessoa: Boolean? = null,
        isGasto: Boolean? = null
    ): Resultado<List<Categoria>?>
    suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?>
}