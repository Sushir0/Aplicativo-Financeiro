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

fun gerarCategoriasBasicas(){
    val contas = MacroCategoria(
    nome = "Contas",
    afetaPessoa = true,
    afetaCasa = true,
    isGasto = true)
    contas.createCategoria("mercado")
    contas.createCategoria("streaming")

    val recebimentos = MacroCategoria(
        nome = "Recebimentos",
        afetaPessoa = true,
        afetaCasa = false,
        isGasto = false)
    recebimentos.createCategoria("recebimento fixo")

    Login.getCasaLogada().macroCategorias.add( contas )
    Login.getCasaLogada().macroCategorias.add( recebimentos )




}


fun gerarCategoriaTeste():Categoria{
    return Categoria(
        nome = "categoria",
        afetaPessoa = true,
        afetaCasa = true,
        isGasto = true
    )
}
