package com.example.finance.b_useCase.Categoria

import com.android.identity.util.UUID
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.CategoriaMock
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoriaMock
import com.example.finance.lvl2.validações.ValidarCategoria
import kotlinx.coroutines.delay

class CategoriaServiceMock(): CategoriaServiceInterface {
    val categorias = mutableListOf(
        Categoria("cat1", "categoria 1", MacroCategoriaMock()),
        Categoria("cat2", "categoria 2", MacroCategoriaMock()),
        Categoria("cat3", "categoria 3", MacroCategoriaMock()),
        Categoria("cat4", "categoria 4", MacroCategoriaMock())
    )


    override suspend fun criarCategoria(
        nome: String,
        macroCategoriaId: String
    ): Resultado<String> {
        val erros = ValidarCategoria.validarCategoria(nome)
        if(erros.isNotEmpty()){
            return Resultado.Falha(erros)
        }
        categorias.add(CategoriaMock(nome = nome, macroCategoriaId = macroCategoriaId))
        return Resultado.Sucesso("true")
    }

    override suspend fun deletarCategoria(id: String): Resultado<Boolean> {
        categorias.removeIf { it.id == id }
        return Resultado.Sucesso(true)
    }

    override suspend fun editarNome(
        categoriaDTO: CategoriaDTO,
        novoNome: String
    ): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun togleAtivo(categoriaId: String): Resultado<Boolean> {
        val categoria = getCategoria(id = categoriaId)
        if(categoria is Resultado.Sucesso){
            categoria.data!!.isActivate = !categoria.data.isActivate
            return Resultado.Sucesso(true)
        }else{
            return Resultado.Falha(listOf("Erro ao ativar/desativar categoria"))
        }
    }

    override suspend fun getCategoria(id: String): Resultado<Categoria?> {
        return Resultado.Sucesso(categorias.find{
            it.id == id
        } )
    }

    override suspend fun getCategoriasFiltradas(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<Categoria>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoriasFromMacro(macroCategoriaId: String): Resultado<List<Categoria>?> {
        delay(500)
        val categoriasFiltradas = categorias.filter {
            it.macroCategoria.id == macroCategoriaId
        }
        return Resultado.Sucesso(categoriasFiltradas)
    }
}