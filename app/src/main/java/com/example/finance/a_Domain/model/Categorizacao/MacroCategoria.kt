package com.example.finance.a_Domain.model.Categorizacao

import com.example.finance.a_Domain.model.MetaDados.Tipo
import java.io.Serializable
import java.util.UUID

data class MacroCategoria (
    val id : String,
    var nome : String,
    val idCasa: String,
    val afetaPessoa: Boolean = false,
    val afetaCasa: Boolean = false,
    val isGasto: Boolean = false
) : Serializable {

    override fun toString(): String {
        return "MacroCategoria(nome='$nome', afetaPessoa=$afetaPessoa, afetaCasa=$afetaCasa, isGasto=$isGasto)"
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
    fun verificarMembro(isCasa: Boolean): Boolean {
        return (isCasa && afetaCasa) || (!isCasa && afetaPessoa)
    }
}

fun MacroCategoriaMock(
    id: String = UUID.randomUUID().toString(),
    nome: String = "MacroCategoria Mock",
    afetaPessoa: Boolean = true,
    afetaCasa: Boolean = true,
    isGasto: Boolean = true,
    idCasa: String = "1"
): MacroCategoria {
    return MacroCategoria(
        id = UUID.randomUUID().toString(),
        nome = nome,
        afetaPessoa = afetaPessoa,
        afetaCasa = afetaCasa,
        isGasto = isGasto,
        idCasa = idCasa
    )
}

/*
class MacroCategoriaDebbug(){
    fun gerarMacroCategoriaTeste(): MacroCategoria {
        return MacroCategoria(
            nome = "MacroCategoriaTeste",
            afetaPessoa = true,
            afetaCasa = true,
            isGasto = true)
    }

    fun getMacroCategoriaGastoFixo(): MacroCategoria {
        return Login.getCasaLogada().macroCategorias[0]
    }
}

 */