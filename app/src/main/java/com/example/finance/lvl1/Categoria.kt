package com.example.finance.lvl1

import java.io.Serializable


class Categoria (
    val nome : String,
    val afetaPessoa: Boolean = false,
    val afetaCasa: Boolean = false,
    val isGasto: Boolean = false
) : Serializable{


}
var categorias = mutableListOf<Categoria>()


fun getCategorias(
    afetaCasa : Boolean = false,
    afetaPessoa: Boolean = false,
):List<Categoria>{
    return categorias.filter {
        (it.afetaPessoa && afetaPessoa) || (it.afetaCasa && afetaCasa)
    }
}

fun gerarCategoriasBasicas(){
    categorias.add( Categoria(
        nome = "Contas da casa",
        afetaPessoa = false,
        afetaCasa = true,
        isGasto = true)
    )

    categorias.add( Categoria(
        nome = "Recebimentos",
        afetaPessoa = true,
        afetaCasa = false,
        isGasto = false)
    )

    categorias.add( Categoria(
        nome = "Gastos pessoais",
        afetaPessoa = true,
        afetaCasa = false,
        isGasto = true)
    )

    categorias.add( Categoria(nome = "Mercado",
        afetaPessoa = true,
        afetaCasa = true,
        isGasto = true)
    )

    categorias.add( Categoria(nome = "Serviços de streaming",
        afetaPessoa = true,
        afetaCasa = true,
        isGasto = true)
    )
}


