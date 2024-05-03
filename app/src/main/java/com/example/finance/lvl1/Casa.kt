package com.example.finance.lvl1

import java.util.Random

class Casa(override var nome: String) : MovimentacaoHolder() {
    override val id: Int
    override val movimentacoes = ArrayList<Movimentacao>()
    override val perfil = Perfil(true)
    override val isCasa = true
    var moradores = ArrayList<Pessoa>()
    var macroCategorias = ArrayList<MacroCategoria>()


    init {
        val random = Random()
        id = random.nextInt(100000)
        Login.addCasa(this)
    }

    fun addMorador(morador: Pessoa): Boolean {
        return moradores.add(morador)
    }

    fun addCategoria(categoria: MacroCategoria):Boolean{
        return macroCategorias.add(categoria)
    }

    fun getMacroCategorias(
        afetaCasa : Boolean = false,
        afetaPessoa: Boolean = false,
    ):List<MacroCategoria>{
        return macroCategorias.filter {
            (it.afetaPessoa && afetaPessoa) || (it.afetaCasa && afetaCasa)
        }
    }

    fun getCategorias(
        afetaCasa: Boolean = false,
        afetaPessoa: Boolean = false
    ) :List<Categoria>{
        var categorias = mutableListOf<Categoria>()
        macroCategorias.forEach {
            if((it.afetaCasa && afetaCasa) || (it.afetaPessoa && afetaPessoa)){
                categorias.addAll(it.getCategorias())
            }
        }

        return categorias
    }
}