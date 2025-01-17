package com.example.finance.a_Domain.model.MovimentacaoHolder

import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Usuario.Perfil
import java.util.Random

data class Pessoa(
    override val id: String,
    override val nome: String

): MovimentacaoHolder() {
    override val perfil = Perfil()
    override val isCasa = false
}
