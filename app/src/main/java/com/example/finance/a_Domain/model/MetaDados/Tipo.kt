package com.example.finance.a_Domain.model.MetaDados

enum class Tipo {
    GASTO,
    RECEBIMENTO,
    TODOS;

    override fun toString(): String {
        return when (this) {
            GASTO -> "Gasto"
            RECEBIMENTO -> "Recebimento"
            TODOS -> "Todos"
        }
    }
    fun getNome_Trocando_Todos_Por_Saldo():String{
        return when (this) {
            GASTO -> "Gasto"
            RECEBIMENTO -> "Recebimento"
            TODOS -> "Saldo"
        }
    }

    fun getIsGasto(): Boolean? {
        return when (this) {
            GASTO -> true
            RECEBIMENTO -> false
            TODOS -> null
        }
    }

    companion object {
        fun getTipos(): List<Tipo> {
            return enumValues<Tipo>().toList()
        }
        fun getTipoFromIsGasto(isGasto: Boolean): Tipo {
            return if(isGasto){ GASTO }else{ RECEBIMENTO }
        }
    }
}