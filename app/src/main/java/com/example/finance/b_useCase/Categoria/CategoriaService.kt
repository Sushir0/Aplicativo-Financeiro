package com.example.finance.b_useCase.Categoria

import android.util.Log
import com.example.finance.a_Domain.Repositorio.CategoriaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO
import com.example.finance.lvl2.validações.ValidarCategoria

class CategoriaService (
    private val categoriaRepository: CategoriaRepository
): CategoriaServiceInterface {
    override suspend fun criarCategoria(nome: String, macroCategoriaId: String): Resultado<String>{
        val erros = ValidarCategoria.validarCategoria(nome)
        if(erros.isNotEmpty()){
            return Resultado.Falha(erros)
        }
        return categoriaRepository.criarCategoria(nome, macroCategoriaId)
    }
    override suspend fun deletarCategoria(id: String): Resultado<Boolean>{
        return categoriaRepository.deletarCategoria(id)
    }
    override suspend fun editarNome(categoriaDTO: CategoriaDTO, novoNome: String): Resultado<Boolean>{
        val erros = ValidarCategoria.validarCategoria(novoNome)
        if(erros.isNotEmpty()){
            return Resultado.Falha(erros)
        }
        return categoriaRepository.editarNome(categoriaDTO, novoNome)
    }
    override suspend fun togleAtivo(categoriaId: String): Resultado<Boolean>{
        return categoriaRepository.toogleAtivo(categoriaId)
    }
    override suspend fun getCategoria(id: String): Resultado<Categoria?>{
        return categoriaRepository.getCategoria(id)
    }
    override suspend fun getCategoriasFiltradas(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<Categoria>?>{
        return categoriaRepository.getCategoriasFiltradas(idCasa, afetaCasa, afetaPessoa, isGasto)
    }
    override suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?>{
        return categoriaRepository.getCategoriasFromMacro(macroCategoriaId)
    }


}