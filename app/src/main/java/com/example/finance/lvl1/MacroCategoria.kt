package com.example.finance.lvl1

import com.example.finance.lvl2.Login.testeCadastro
import java.io.Serializable

class MacroCategoria (
    val nome : String,
    val afetaPessoa: Boolean = false,
    val afetaCasa: Boolean = false,
    val isGasto: Boolean = false
) : Serializable {
    private var categorias = mutableListOf<Categoria>()

    override fun toString(): String {
        return "MacroCategoria(nome='$nome', afetaPessoa=$afetaPessoa, afetaCasa=$afetaCasa, isGasto=$isGasto)"
    }

    fun createCategoria(nome: String):Boolean{
        return categorias.add(Categoria(nome, afetaPessoa, afetaCasa, isGasto))
    }

    fun getCategorias(): MutableList<Categoria> {
        return categorias
    }

    fun verificarTipo(tipo: Tipo?): Boolean{
        if (tipo == Tipo.TODOS || tipo == null){
            return true
        }
        if (tipo == Tipo.GASTO && isGasto){
            return true
        }
        if (tipo == Tipo.RECEBIMENTO && !isGasto){
            return true
        }
        return false
    }




}

fun main() {
    testeCadastro()
    gerarCategoriasBasicas()
    val macroCategoria = MacroCategoria(
        nome = "Contas",
        afetaPessoa = true,
        afetaCasa = true,
        isGasto = true)
    macroCategoria.createCategoria("mercado")
    macroCategoria.createCategoria("streaming")

    println(macroCategoria.toString())
    println(macroCategoria.getCategorias().toString())
    val casa = Login.getCasaLogada()
    println("casinha")
    println(Login.getCasaLogada().getCategorias(true, false).toString())

}