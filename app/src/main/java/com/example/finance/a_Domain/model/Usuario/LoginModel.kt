package com.example.finance.a_Domain.model.Usuario

import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa

data class LoginModel(
    val email: String,
    val senhaHash: String,
    val pessoa: Pessoa,
    var authToken: String? = null
)
