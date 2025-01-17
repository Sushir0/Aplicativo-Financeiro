package com.example.finance.b_useCase.MacroCategoria

import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Categorizacao.DTO.MacroCategoriaDTO
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoriaMock
import com.example.finance.lvl2.validações.ValidarCategoria
import kotlinx.coroutines.delay

class MacroCategoriaServiceMock(): MacroCategoriaServiceInterface {
    val macroCategorias = mutableListOf(
        MacroCategoriaMock(
            id = "1",
            isGasto = true,
            afetaPessoa = false,
            afetaCasa = true
        ),
        MacroCategoriaMock(
            isGasto = false,
            afetaPessoa = true,
            afetaCasa = false
        )
    )
    override suspend fun criarMacroCategoria(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean,
        idCasa: String
    ): Resultado<String> {
        val erros = ValidarCategoria.validarCategoria(nome)
        if(erros.isNotEmpty()){
            return Resultado.Falha(erros)
        }
        macroCategorias.add(MacroCategoriaMock(nome = nome, isGasto = isGasto, afetaPessoa = afetaPessoa, afetaCasa = afetaCasa))
        return Resultado.Sucesso("true")
    }

    override suspend fun deletarMacroCategoria(id: String): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override fun editarNome(
        macroCategoriaDTO: MacroCategoriaDTO,
        novoNome: String
    ): Resultado<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getMacroCategoria(id: String): Resultado<MacroCategoria?> {
        delay(500)
        return Resultado.Sucesso(macroCategorias.first())
    }

    override suspend fun getMacroCategorias(
        idCasa: String,
        afetaCasa: Boolean?,
        afetaPessoa: Boolean?,
        isGasto: Boolean?
    ): Resultado<List<MacroCategoria>?> {
        delay(500)
        return Resultado.Sucesso(macroCategorias)
    }
}