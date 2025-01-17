package com.example.finance.a_Domain.model.MetaDados

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import java.io.Serializable

sealed class SelecaoUsuario : Serializable {
    data class tipoSelecionado(val tipo: Tipo): SelecaoUsuario()
    data class categoriaSelecionada(val categoria: Categoria): SelecaoUsuario()
    data class macroCategoriaSelecionada(val macroCategoria: MacroCategoria): SelecaoUsuario()

    val Nome: String
        get() {
            return when (this) {
                is tipoSelecionado -> tipo.toString()
                is categoriaSelecionada -> categoria.nome
                is macroCategoriaSelecionada -> macroCategoria.nome
            }
        }
}