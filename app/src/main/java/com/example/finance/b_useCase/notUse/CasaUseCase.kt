package com.example.finance.b_useCase.notUse

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa

/*class CasaUseCase(val casa: Casa) {
    fun getMacroCategorias(
        afetaCasa : Boolean = false,
        afetaPessoa: Boolean = false,
        tipo: Tipo? = null
    ):List<MacroCategoria>{
        return casa.macroCategorias.filter {
            ((it.afetaPessoa && afetaPessoa) || (it.afetaCasa && afetaCasa)) && (it.verificarTipo(tipo))
        }
    }

    fun getCategorias(
        afetaCasa: Boolean = false,
        afetaPessoa: Boolean = false,
        tipo: Tipo? = null,
        macroCategoria: MacroCategoria? = null,
        chamarSomenteCategoriasAtivas: Boolean = false
    ) :List<Categoria>{
        if(macroCategoria != null){
            return MacroCategoriaUseCase(macroCategoria).getCategorias(chamarSomenteCategoriasAtivas)
        }

        var categorias = mutableListOf<Categoria>()
        casa.macroCategorias.forEach {
            if(((it.afetaCasa && afetaCasa) || (it.afetaPessoa && afetaPessoa)) && (it.verificarTipo(tipo))){
                categorias.addAll(MacroCategoriaUseCase(it).getCategorias(chamarSomenteCategoriasAtivas))
            }
        }

        return categorias
    }

}

 */