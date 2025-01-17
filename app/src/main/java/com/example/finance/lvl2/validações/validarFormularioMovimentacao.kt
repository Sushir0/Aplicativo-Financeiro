package com.example.finance.lvl2.validações

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Dados.Data
import java.text.SimpleDateFormat
import java.util.Locale

const val tamanhoMinimoAssunto = 2
const val tamanhoMaximoAssunto = 30

const val valorMaximoValor = 99999999

fun validarAssunto(assunto : String?): Boolean {
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

fun validarMovimentacao(descricao: String?, valor: Double, data: Data): ArrayList<String>{
    val erros = ArrayList<String>()
    if(!validarValor(valor)){
        erros.add("Valor inválido")
    }

    if(!validarAssunto(descricao)){
        erros.add("Descrição inválida")
    }

    if(!validarData(data)){
        erros.add("Data inválida")
    }
    return erros
}