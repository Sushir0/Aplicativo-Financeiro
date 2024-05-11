package com.example.finance.lvl1

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

    companion object {
        fun getTipos(): List<Tipo> {
            return enumValues<Tipo>().toList()
        }
        fun getTipoFromIsGasto(isGasto: Boolean): Tipo {
            return if(isGasto){ GASTO }else{ RECEBIMENTO }
        }
    }
}