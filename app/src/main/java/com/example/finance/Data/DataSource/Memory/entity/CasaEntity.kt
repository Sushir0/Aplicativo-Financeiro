package com.example.finance.Data.DataSource.Memory.entity

import com.example.finance.a_Domain.model.MovimentacaoHolder.Casa
import java.util.UUID

data class CasaEntity(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val cor: String,
    val foto: String,
    )