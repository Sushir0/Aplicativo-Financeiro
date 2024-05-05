package com.example.finance

import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.MacroCategoria
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.Periodo
import org.junit.Assert
import org.junit.Test

class MovimentacaoUnitTest {

    @Test
    fun testIsGasto(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(10,1,2000), 100.0, Categoria("Categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(10,1,2000), 100.0, Categoria("Categoria", isGasto = false))

        //Act
        val isGasto1 = movimentacao1.isGasto()
        val isGasto2 = movimentacao2.isGasto()

        //Assert
        Assert.assertTrue(isGasto1)
        Assert.assertFalse(isGasto2)
    }

    @Test
    fun testIsOnPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(10,1,2000), 100.0, Categoria("Categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(10,2,2000), 100.0, Categoria("Categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(10,3,2000), 100.0, Categoria("Categoria", isGasto = true))
        val periodo = Periodo(Data(10,2,2000), Data(10,3,2000))

        //Act
        val isOnPeriodo1 = movimentacao1.isOnPeriodo(periodo)
        val isOnPeriodo2 = movimentacao2.isOnPeriodo(periodo)
        val isOnPeriodo3 = movimentacao3.isOnPeriodo(periodo)

        //Assert
        Assert.assertFalse(isOnPeriodo1)
        Assert.assertTrue(isOnPeriodo2)
        Assert.assertTrue(isOnPeriodo3)
    }

}