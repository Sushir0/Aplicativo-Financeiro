package com.example.finance.lvl1

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.Periodo
import org.junit.Assert
import org.junit.Test

class MovimentacaoUnitTest {
    /*
    private val categoriaTeste = CategoriaDebbug().gerarCategoriaTeste()
    private val categoriaTesteRecebimento = Categoria("recebimento", MacroCategoria("Teste", isGasto = false))

    @Test
    fun testIsGasto(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(10,1,2000), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao("assunto", Data(10,1,2000), 100.0, categoriaTesteRecebimento)

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
        val movimentacao1 = Movimentacao("assunto", Data(10,1,2000), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao("assunto", Data(10,2,2000), 100.0, categoriaTeste)
        val movimentacao3 = Movimentacao("assunto", Data(10,3,2000), 100.0, categoriaTeste)
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

    @Test
    fun testEditarMovimentacao(){
        //Arrange
        val movimentacao = Movimentacao(
            "assunto",
            Data(10,1,2000),
            100.0,
            categoriaTeste
        )

        //Act
        movimentacao.editarMovimentacao(
            assunto = "novo assunto",
            data = Data(11,1,2000),
            valor = 200.0,
            categoria = Categoria("nova categoria", MacroCategoriaDebbug().gerarMacroCategoriaTeste())
        )

        //Assert
        Assert.assertEquals("novo assunto", movimentacao.descricao)
        Assert.assertEquals(Data(11,1,2000).toString(), movimentacao.data.toString())
        Assert.assertEquals(200.0, movimentacao.valor, 0.0)
        Assert.assertEquals("nova categoria", movimentacao.categoria.nome)
    }

     */
}