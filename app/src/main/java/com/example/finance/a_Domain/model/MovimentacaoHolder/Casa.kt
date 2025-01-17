package com.example.finance.a_Domain.model.MovimentacaoHolder


import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Usuario.Perfil
import java.util.Random
import java.util.UUID

data class Casa(
    override val id: String = UUID.randomUUID().toString(),
    override val nome: String
) : MovimentacaoHolder() {
    override val perfil = Perfil(true)
    override val isCasa = true
}