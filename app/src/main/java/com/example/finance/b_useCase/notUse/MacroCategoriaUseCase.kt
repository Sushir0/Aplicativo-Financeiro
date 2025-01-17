package com.example.finance.b_useCase.notUse

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria

/*class MacroCategoriaUseCase(val macroCategoria: MacroCategoria) {
    fun getCategorias(chamarSomenteCategoriasAtivas: Boolean = false): MutableList<Categoria>{
        var categoriasList = mutableListOf<Categoria>()
        macroCategoria.categorias.forEach {
            if(it.isActivate || !chamarSomenteCategoriasAtivas){
                categoriasList.add(it)
            }
        }
        return categoriasList
    }
}

 */