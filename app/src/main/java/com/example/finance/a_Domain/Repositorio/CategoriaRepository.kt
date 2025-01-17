package com.example.finance.a_Domain.Repositorio

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO

interface CategoriaRepository {
    suspend fun criarCategoria(nome: String, macroCategoriaId: String): Resultado<String>
    suspend fun deletarCategoria(id: String): Resultado<Boolean>
    suspend fun editarNome(categoriaDTO: CategoriaDTO, novoNome: String): Resultado<Boolean>
    suspend fun toogleAtivo(id: String): Resultado<Boolean>
    suspend fun getCategoria(id: String): Resultado<Categoria?>
    suspend fun getCategoriasFiltradas(idCasa: String, afetaCasa: Boolean?, afetaPessoa: Boolean?, isGasto: Boolean?): Resultado<List<Categoria>?>
    suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?>




}
