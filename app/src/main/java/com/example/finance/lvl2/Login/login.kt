package com.example.finance.lvl2.Login

import com.example.finance.lvl1.Login
import com.example.finance.lvl2.validações.validarFormulario

fun logar(email: String, senha: String): List<String> {
    val erros = validarFormulario(email, senha)

    if (erros.isEmpty()) {
        Login.Logar(email, senha)
    }
    return erros
}