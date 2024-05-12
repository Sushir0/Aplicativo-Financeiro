package com.example.finance

import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl1.getMovimentacaoHolderById
import com.example.finance.lvl2.Login.testeCadastro
import org.junit.Assert
import org.junit.Test

class MovimentacaoHolderUnitTest {
    @Test
    fun testGetGastos(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = false))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getGastos()

        //Assert
        Assert.assertEquals(1, result.size)
        Assert.assertTrue(result.any{ it.toString() == movimentacao1.toString() })
    }

    @Test
    fun testGetRecebimentos(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = false))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getRecebimentos()

        //Assert
        Assert.assertEquals(1, result.size)
        Assert.assertTrue(result.any{ it.toString() == movimentacao2.toString() })
    }

    @Test
    fun testGetMovimentacoesSemPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getMovimentacoes()

        //Assert
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.any{ it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any{ it.toString() == movimentacao2.toString() })
        Assert.assertTrue(result.any{ it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetMovimentacoesComPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)
        val data1 = Data(1, 1, 2023)
        val data2 = Data(1, 2, 2023)
        val periodo = Periodo(data1, data2)

        //Act
        val result = pessoa.getMovimentacoes(periodo)

        //Assert
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result.any{ it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any{ it.toString() == movimentacao2.toString() })
        Assert.assertFalse(result.any{ it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetMovimentacoesComCategoria(){
        //Arrange
        val categoria = Categoria("categoria", isGasto = true)
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoria)
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, categoria)
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria2", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getMovimentacoes(categoria = categoria)

        //Assert
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result.any{ it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any{ it.toString() == movimentacao2.toString() })
        Assert.assertFalse(result.any{ it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetValorTotal_FromCategoria(){
        //Arrange
        val categoria = Categoria("categoria", isGasto = true)
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoria)
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, categoria)
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria2", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getValorTotalCategoria(categoria)

        //Assert
        Assert.assertEquals(200.0, result, 0.0)
    }


    @Test
    fun testGetGastosTotaisSemPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getGastosTotais()

        //Assert
        Assert.assertEquals(200.0, result, 0.0)
    }

    @Test
    fun testGetRecebimentosTotaisSemPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria", isGasto = false))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getRecebimentosTotais()

        //Assert
        Assert.assertEquals(200.0, result, 0.0)
    }

    @Test
    fun testGetGastosTotaisComPeriodo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 100.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)
        val data1 = Data(1, 1, 2023)
        val data2 = Data(1, 2, 2023)
        val periodo = Periodo(data1, data2)

        //Act
        val result = pessoa.getGastosTotais(periodo)

        //Assert
        Assert.assertEquals(200.0, result, 0.0)
    }

    @Test
    fun testGetSaldo(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 150.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 500.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 250.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getSaldo()

        //Assert
        Assert.assertEquals(100.0, result, 0.0) // 500 - (150 + 250)
    }

    @Test
    fun testAddMovimentacao(){
        //Arrange
        val movimentacao = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")

        //Act
        pessoa.addMovimentacao(movimentacao)

        //Assert
        Assert.assertEquals(1, pessoa.movimentacoes.size)
    }

    @Test
    fun testEditMovimentacao(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 500.0, Categoria("categoria", isGasto = false))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)

        //Act
        pessoa.editMovimentacao(movimentacaoOriginal = movimentacao1, movimentacaoModificada = movimentacao2)

        //Assert
        Assert.assertEquals(1, pessoa.movimentacoes.size)
        Assert.assertEquals(movimentacao2, pessoa.movimentacoes[0])
    }

    @Test
    fun testRemoveMovimentacao(){
        //Arrange
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, Categoria("categoria", isGasto = true))
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 500.0, Categoria("categoria", isGasto = false))
        val movimentacao3 = Movimentacao("assunto", Data(1, 3, 2023), 250.0, Categoria("categoria", isGasto = true))
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        pessoa.removeMovimentacao(movimentacao2)

        //Assert
        Assert.assertEquals(2, pessoa.movimentacoes.size)
        Assert.assertTrue(pessoa.movimentacoes.contains(movimentacao1))
        Assert.assertFalse(pessoa.movimentacoes.contains(movimentacao2))
        Assert.assertTrue(pessoa.movimentacoes.contains(movimentacao3))
    }

    @Test
    fun testgetMovimentacaoHolderFromId(){
        //Arrange
        testeCadastro()
        val pessoa = Pessoa("João")
        Login.getCasaLogada().addMorador(pessoa)

        //Act
        val result = getMovimentacaoHolderById(Login.getCasaLogada().id)

        //Assert
        Assert.assertEquals(result, Login.getCasaLogada())
    }
}