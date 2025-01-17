package com.example.finance.lvl2.validações

fun validarTamanhoString(texto: String, min: Int, max: Int): Boolean {
    return texto.length < min || texto.length > max
}

fun validarDisponibilidadeValor(valor : Double, valorMaximo : Int, canNegativo: Boolean = false): Boolean{
    if (valor < valorMaximo && (!canNegativo && valor < 0)){
        return true
    }else{ return false }
}