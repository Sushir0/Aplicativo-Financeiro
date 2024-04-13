package com.example.finance.lvl2.validações

import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import java.text.SimpleDateFormat
import java.util.Locale

const val tamanhoMinimoAssunto = 2
const val tamanhoMaximoAssunto = 30

const val valorMaximoValor = 99999999

fun validarAssunto(assunto : String): Boolean {
    if (assunto.isNullOrBlank()) {
        return false
    }
    if (validarTamanhoString(assunto, tamanhoMinimoAssunto, tamanhoMaximoAssunto)) {
        return false
    }
    return true
}

fun validarValor(valor: Double): Boolean{
    if(valor.isNaN()){
        return false
    }
    if(validarDisponibilidadeValor(valor, valorMaximoValor)){
        return false
    }
    return true
}

fun validarData(data: Data): Boolean{
    val formato = "dd/MM/yyyy"
    val sdf = SimpleDateFormat(formato, Locale.getDefault())
    sdf.isLenient = false // Define que a validação será rigorosa

    return try {
        sdf.parse(data.toString())
        true // Se conseguir fazer o parse sem exceção, a data é válida
    } catch (e: Exception) {
        false // Se ocorrer alguma exceção, a data é inválida
    }
}

fun validarMovimentacao(assunto: String, valorStr: String, data: Data, categoria: Categoria?): List<String>{
    val erros = mutableListOf<String>()
    if(categoria == null){
        erros.add("Categoria não escolhida")
    }
    if(valorStr.isNullOrBlank()){
        erros.add("Valor nulo")
    }else{
        if(!validarValor(valorStr.toDouble())){
            erros.add("Valor inválido")
        }
    }

    if(!validarAssunto(assunto)){
        erros.add("Assunto inválido")
    }

    if(!validarData(data)){
        erros.add("Data inválida")
    }
    return erros
}