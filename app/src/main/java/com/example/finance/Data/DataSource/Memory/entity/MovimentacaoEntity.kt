package com.example.finance.Data.DataSource.Memory.entity

import com.example.finance.a_Domain.model.Dados.Data

data class MovimentacaoEntity(
    val id: String,
    val descricao: String,
    val valor: Double,
    val data: Data,
    val formaPagamento: String,
    val categoriaId: String,
    val movimentacaoHolderType: String, // Discriminador
    val casaId: String? = null, // Relacionamento com Casa
    val pessoaId: String? = null // Relacionamento com Pessoa
)
