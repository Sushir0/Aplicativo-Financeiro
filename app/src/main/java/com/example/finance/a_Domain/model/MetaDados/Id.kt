package com.example.finance.a_Domain.model.MetaDados

import kotlin.random.Random


data class Id(
    val id: Int
)

interface iId{
    fun gerarId(): Id
}

object idService: iId {
    override fun gerarId(): Id {
        return Id(Random.nextInt(10000))
    }
}