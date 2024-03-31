package com.example.finance.lvl1

import androidx.compose.runtime.MutableState


class Categoria (
    var nome : String,
    var afetaPessoa: Boolean,
    var afetaCasa: Boolean,
    var isGasto: Boolean) {



}
var categorias = mutableListOf<Categoria>()


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


