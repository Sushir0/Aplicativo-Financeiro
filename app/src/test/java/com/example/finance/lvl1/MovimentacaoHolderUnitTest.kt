package com.example.finance.lvl1

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa
import org.junit.Assert
import org.junit.Test

class MovimentacaoHolderUnitTest {
    /*
    private val categoriaTeste = CategoriaDebbug().gerarCategoriaTeste()
    private val categoriaTesteRecebimento = Categoria("recebimento", MacroCategoria("Recebimento", isGasto = false))

    @Test
    fun testGetGastos() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getGastos()

        //Assert
        Assert.assertEquals(1, result.size)
        Assert.assertTrue(result.any { it.toString() == movimentacao1.toString() })
    }

    @Test
    fun testGetRecebimentos() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getRecebimentos()

        //Assert
        Assert.assertEquals(1, result.size)
        Assert.assertTrue(result.any { it.toString() == movimentacao2.toString() })
    }

    @Test
    fun testGetMovimentacoesSemPeriodo() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoriaTeste
        )
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getMovimentacoes()

        //Assert
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.any { it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any { it.toString() == movimentacao2.toString() })
        Assert.assertTrue(result.any { it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetMovimentacoesComPeriodo() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            100.0,
            categoriaTeste
        )
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 3, 2023), 100.0, categoriaTeste)
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
        Assert.assertTrue(result.any { it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any { it.toString() == movimentacao2.toString() })
        Assert.assertFalse(result.any { it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetMovimentacoesComCategoria() {
        //Arrange
        val categoria = categoriaTeste
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoria)
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, categoria)
        val movimentacao3 = Movimentacao(
            "assunto",
            Data(1, 3, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getMovimentacoes(categoria = categoria)

        //Assert
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result.any { it.toString() == movimentacao1.toString() })
        Assert.assertTrue(result.any { it.toString() == movimentacao2.toString() })
        Assert.assertFalse(result.any { it.toString() == movimentacao3.toString() })
    }

    @Test
    fun testGetValorTotal_FromCategoria() {
        //Arrange
        val categoria = categoriaTeste
        val movimentacao1 = Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoria)
        val movimentacao2 = Movimentacao("assunto", Data(1, 2, 2023), 100.0, categoria)
        val movimentacao3 = Movimentacao(
            "assunto",
            Data(1, 3, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)
        pessoa.addMovimentacao(movimentacao3)

        //Act
        val result = pessoa.getValorTotalFromCategoria(categoria)

        //Assert
        Assert.assertEquals(200.0, result, 0.0)
    }


    @Test
    fun testGetGastosTotaisSemPeriodo() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            100.0,
            categoriaTeste
        )
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 3, 2023), 100.0, categoriaTesteRecebimento)
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
    fun testGetRecebimentosTotaisSemPeriodo() {
        //Arrange
        val movimentacao1 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val movimentacao3 = Movimentacao(
            "assunto",
            Data(1, 3, 2023),
            100.0,
            categoriaTeste
        )
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
    fun testGetGastosTotaisComPeriodo() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 =
            Movimentacao("assunto", Data(1, 2, 2023), 100.0, categoriaTeste)
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 3, 2023), 100.0, categoriaTeste)
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
    fun testGetSaldo() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 150.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            500.0,
            categoriaTesteRecebimento
        )
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 3, 2023), 250.0, categoriaTeste)
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
    fun testAddMovimentacao() {
        //Arrange
        val movimentacao =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val pessoa = Pessoa("João")

        //Act
        pessoa.addMovimentacao(movimentacao)

        //Assert
        Assert.assertEquals(1, pessoa.movimentacoes.size)
    }

    @Test
    fun testEditMovimentacao() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            500.0,
            categoriaTeste
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)

        //Act
        pessoa.editMovimentacao(
            movimentacaoOriginal = movimentacao1,
            movimentacaoModificada = movimentacao2
        )

        //Assert
        Assert.assertEquals(1, pessoa.movimentacoes.size)
        Assert.assertEquals(movimentacao2, pessoa.movimentacoes[0])
    }

    @Test
    fun testRemoveMovimentacao() {
        //Arrange
        val movimentacao1 =
            Movimentacao("assunto", Data(1, 1, 2023), 100.0, categoriaTeste)
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 2, 2023),
            500.0,
            categoriaTeste
        )
        val movimentacao3 =
            Movimentacao("assunto", Data(1, 3, 2023), 250.0, categoriaTeste)
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
    fun testgetMovimentacaoHolderFromId() {
        //Arrange
        LoginController().testeCadastro()
        val pessoa = Pessoa("João")
        Login.getCasaLogada().addMorador(pessoa)

        //Act
        val result = getMovimentacaoHolderById(Login.getCasaLogada().id)

        //Assert
        Assert.assertEquals(result, Login.getCasaLogada())
    }

    @Test
    fun testTransferirMovimentacao() {
        //Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        val movimentacao = gerarMovimentacaoTeste()
        val pessoa = Pessoa("João")
        val pessoa2 = Pessoa("Maria")
        pessoa.addMovimentacao(movimentacao)

        //Act
        pessoa.transferirMovimentacaoParaOutroMovimentacaoHolder(movimentacao, pessoa2)

        //Assert
        Assert.assertEquals(0, pessoa.movimentacoes.size)
        Assert.assertEquals(1, pessoa2.movimentacoes.size)
    }

    @Test
    fun testeTransferirEntreACasa() {
        //Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        MovimentacaoController().adicionarMovimentacao(
            assunto = "assunto",
            data = Data(1, 1, 2023),
            valorStr = "100.0",
            categoria = categoriaTeste,
            movimentacaoHolder = Login.getCasaLogada()
        )
        val movimentacao = Login.getCasaLogada().movimentacoes[0]

        //Act
        Login.getCasaLogada().transferirMovimentacaoParaOutroMovimentacaoHolder(
            movimentacao,
            Login.getCasaLogada().moradores[1]
        )

        //Assert
        Assert.assertEquals(0, Login.getCasaLogada().movimentacoes.size)
        Assert.assertEquals(1, Login.getCasaLogada().moradores[1].movimentacoes.size)
    }

    @Test
    fun testeRemoverMovimentacaoFromCasa() {
        //Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        val movimentacao = gerarMovimentacaoTeste()
        Login.getCasaLogada().addMovimentacao(movimentacao)

        //Act
        Login.getCasaLogada().removeMovimentacao(movimentacao)

        //Assert
        Assert.assertEquals(0, Login.getCasaLogada().movimentacoes.size)

    }

    @Test
    fun testeGetMovimentacoesFromMacroCategoria() {
        //Arrange
        val macroCategoria = MacroCategoria("macro", isGasto = true)
        macroCategoria.createCategoria("categoria1")
        macroCategoria.createCategoria("categoria2")
        val movimentacao1 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            macroCategoria.getCategorias()[0]
        )
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            macroCategoria.getCategorias()[1]
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getMovimentacoesFromMacroCategoria(macroCategoria = macroCategoria)

        //Assert
        Assert.assertEquals(2, result.size)
    }

    @Test
    fun testeGetMovimentacoesFromCategoria() {
        //Arrange
        val categoria = categoriaTeste
        val movimentacao1 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoria
        )
        val movimentacao2 = Movimentacao(
            "assunto",
            Data(1, 1, 2023),
            100.0,
            categoriaTesteRecebimento
        )
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacao1)
        pessoa.addMovimentacao(movimentacao2)

        //Act
        val result = pessoa.getMovimentacoesFromCategoria(categoria = categoria)

        //Assert
        Assert.assertEquals(1, result.size)
    }


     */
}