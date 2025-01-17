package com.example.finance.lvl2.MacroCategoria

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.lvl2.validações.ValidarCategoria
/*
class MacroCategoriaController {

    fun adicionarMacroCategoria(
        nome: String,
        isGasto: Boolean,
        afetaPessoa: Boolean,
        afetaCasa: Boolean
    ): ArrayList<String> {
        val erros = ValidarCategoria().validarCategoria(nome = nome)

        if(erros.isEmpty()){
            val macroCategoria = MacroCategoria(
                nome = nome,
                isGasto = isGasto,
                afetaPessoa = afetaPessoa,
                afetaCasa = afetaCasa
            )
            getCasa().addMacroCategoria(macroCategoria = macroCategoria)
        }
        return erros

    }

    fun getMacroCategorias(
        afetaCasa: Boolean = false,
        afetaPessoa: Boolean = false,
        tipo: Tipo? = null
    ):List<MacroCategoria>{
        return getCasa().macroCategorias

    }

    fun getMacroCategoriaCasa_FromMacroCategoria(nome: String): MacroCategoria {
        return getCasa().getMacroCategorias(afetaCasa = true, afetaPessoa = true).find { it.nome == nome }!!
    }

    fun getMacroCategoriaFromCategoria(categoria: Categoria): MacroCategoria? {
        val macroCategorias = getMacroCategorias()
        for (macroCategoria in macroCategorias){
            if (macroCategoria.getCategorias().contains(categoria)){
                return macroCategoria
            }
        }
        return null
    }

}

 */