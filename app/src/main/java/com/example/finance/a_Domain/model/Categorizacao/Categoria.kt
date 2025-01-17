package com.example.finance.a_Domain.model.Categorizacao

import java.io.Serializable
import java.util.UUID


data class Categoria (

    val id: String,
    var nome: String,
    val macroCategoria: MacroCategoria,
    var isActivate: Boolean = true
) : Serializable{

    val isGasto: Boolean
        get() = macroCategoria.isGasto
    val afetaPessoa: Boolean
        get() = macroCategoria.afetaPessoa
    val afetaCasa: Boolean
        get() = macroCategoria.afetaCasa
    val macroCategoriaId: String
        get() = macroCategoria.id

    override fun toString(): String {
        return "Categoria(nome='$nome', afetaPessoa=${macroCategoria.afetaPessoa}, afetaCasa=${macroCategoria.afetaCasa}, isGasto=${macroCategoria.isGasto})"
    }
}

fun CategoriaMock(
    id: String = UUID.randomUUID().toString(),
    nome: String = "Categoria Mock",
    afetaPessoa: Boolean = true,
    afetaCasa: Boolean = true,
    isGasto: Boolean = true,
    macroCategoriaId: String = UUID.randomUUID().toString()

): Categoria {
    return Categoria(
        id = id,
        nome = nome,
        macroCategoria = MacroCategoriaMock(id = macroCategoriaId, afetaPessoa = afetaPessoa, afetaCasa = afetaCasa, isGasto =  isGasto)
    )
}

fun CategoriaMock(
    id: String = UUID.randomUUID().toString(),
    nome: String = "Categoria Mock",
    macroCategoria: MacroCategoria = MacroCategoriaMock()
): Categoria {
    return Categoria(
        id = id,
        nome = nome,
        macroCategoria = macroCategoria
    )
}

/*

class CategoriaDebbug(){
    val categorias_Contas = arrayOf(
        "mercado",
        "streaming"
    )

    val categorias_Recebimentos = arrayOf(
        "recebimento fixo"
    )

    fun getSizeCategoriasBase():Int{
        return categorias_Contas.size + categorias_Recebimentos.size
    }

    fun gerarCategoriasBasicas(){
        val contas = MacroCategoria(
            nome = "Contas",
            afetaPessoa = true,
            afetaCasa = true,
            isGasto = true)

        val recebimentos = MacroCategoria(
            nome = "Recebimentos",
            afetaPessoa = true,
            afetaCasa = false,
            isGasto = false)

        categorias_Contas.forEach {
            contas.createCategoria(it)
        }
        categorias_Recebimentos.forEach {
            recebimentos.createCategoria(it)
        }

        Login.getCasaLogada().macroCategorias.add( contas )
        Login.getCasaLogada().macroCategorias.add( recebimentos )
    }

    fun getCategoriaAleatoria(): Categoria {
        return Login.getCasaLogada().macroCategorias.random().getCategorias().random()
    }

    fun getCategoriaGastoFixo(): Categoria {
        return Login.getCasaLogada().macroCategorias[0].getCategorias()[0]
    }

    fun getCategoriaRecebimentoFixo(): Categoria {
        return Login.getCasaLogada().macroCategorias[1].getCategorias()[0]
    }

    fun gerarCategoriaTeste(): Categoria {
        return Categoria("teste", MacroCategoriaDebbug().gerarMacroCategoriaTeste())
    }
}
 */