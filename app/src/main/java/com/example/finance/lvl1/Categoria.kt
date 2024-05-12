package com.example.finance.lvl1

import java.io.Serializable


class Categoria (
    val nome : String,
    val afetaPessoa: Boolean = false,
    val afetaCasa: Boolean = false,
    val isGasto: Boolean = false
) : Serializable{

    override fun toString(): String {
        return "Categoria(nome='$nome', afetaPessoa=$afetaPessoa, afetaCasa=$afetaCasa, isGasto=$isGasto)"
    }


}

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


fun gerarCategoriaTeste():Categoria{
    return Login.getCasaLogada().macroCategorias.random().getCategorias().random()
}
