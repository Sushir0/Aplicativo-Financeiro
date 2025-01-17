package com.example.finance.a_Domain

sealed class Resultado<out T> {
    data class Sucesso<T>(val data: T) : Resultado<T>()
    data class Falha(val errors: List<String>) : Resultado<Nothing>()
}