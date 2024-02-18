package com.example.finance.lvl3.componentes

import android.content.Context
import android.widget.Toast

fun avisoLongo(context: Context, texto: String){
    Toast.makeText(context, texto, Toast.LENGTH_LONG).show()
}

fun avisoDeErros(context: Context, erros: List<String>){
    var frase = ""
    erros.forEach({ erro ->
        frase = frase+erro+"; "
    })
    Toast.makeText(context, frase, Toast.LENGTH_LONG).show()
}