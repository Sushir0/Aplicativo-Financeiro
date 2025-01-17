package com.example.finance.Data.DataSource.Memory.entity

import com.example.finance.a_Domain.model.Dados.Data

data class ParcelaEntity(
    val id: String,
    val descricao: String,
    val valor: Double,
    val data: Data,
    val movimentacao_id: String
)
