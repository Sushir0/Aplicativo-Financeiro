package com.example.finance.a_Domain.model.MetaDados

enum class Ordem{
    MaisRecente,
    MaisAntigo,
    MenorValor,
    MaiorValor;

    override fun toString(): String {
        return when (this) {
            MaisRecente -> "Mais Recente"
            MaisAntigo -> "Mais Antigo"
            MenorValor -> "Menor Valor"
            MaiorValor -> "Maior Valor"
        }
    }

}
