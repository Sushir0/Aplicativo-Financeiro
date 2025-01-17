package com.example.finance.a_Domain.model.Categorizacao

data class CategoriaDTO (
    val id: String,
    val nome: String,
    val macroCategoriaId: String,
    val isActivate: Boolean
)
