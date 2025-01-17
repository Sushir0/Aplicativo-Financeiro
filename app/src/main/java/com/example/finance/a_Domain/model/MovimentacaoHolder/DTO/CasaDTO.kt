package com.example.finance.a_Domain.model.MovimentacaoHolder.DTO

import com.example.finance.a_Domain.model.Usuario.Perfil

data class CasaDTO (
    val id: String,
    val nome: String,
    val perfil: Perfil,
    val isCasa: Boolean
)