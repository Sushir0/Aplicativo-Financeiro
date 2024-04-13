package com.example.finance.lvl2.validações

fun validarTamanhoString(frase: String, tamanhoMinimo: Int, tamanhoMaximo: Int): Boolean{
    if(frase.length> tamanhoMaximo && frase.length<tamanhoMinimo){
        return true
    }else{ return false }
}

fun validarDisponibilidadeValor(valor : Double, valorMaximo : Int, canNegativo: Boolean = false): Boolean{
    if (valor < valorMaximo && (!canNegativo && valor < 0)){
        return true
    }else{ return false }
}