package com.example.finance.a_Domain.model.Categorizacao.DTO

data class MacroCategoriaDTO (
    val id: String,
    val nome: String,
    val isGasto: Boolean,
    val afetaPessoa: Boolean,
    val afetaCasa: Boolean,
)
