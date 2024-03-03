package com.example.finance.lvl2.Login

import com.example.finance.lvl1.Login
import com.example.finance.lvl2.validações.validarFormularioLogin

fun logar(email: String, senha: String): List<String> {
    val erros = validarFormularioLogin(email, senha)

    if (erros.isEmpty()) {
        Login.Logar(email, senha)
    }
    return erros
}