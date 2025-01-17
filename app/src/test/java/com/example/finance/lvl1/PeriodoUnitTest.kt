package com.example.finance.lvl1

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.getPeriodosFromMovimentacoes_ComAnos
import com.example.finance.a_Domain.model.MetaDados.getUltimoPeriodoUtilizado
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa
import org.junit.Assert
import org.junit.Test

class PeriodoUnitTest {
    @Test
    fun testGerarPeriodo_DataInicial_DataFinal() {
        //Arrange
        val dataInicial = Data(1, 1, 2020)
        val dataFinal = Data(31, 12, 2020)

        //Act
        val periodo = Periodo(dataInicial, dataFinal)

        //Assert
        Assert.assertEquals(dataInicial.toString(), periodo.dataInicial.toString())
        Assert.assertEquals(dataFinal.toString(), periodo.dataFinal.toString())
    }

    @Test
    fun testGerarPereiodo_ano_mes(){
        //Arrange
        val ano = 2020
        val mes = 5

        //Act
        val periodo = Periodo(mes, ano)

        //Assert
        val dataInicial = Data(1, mes, ano)
        val dataFinal = Data(31, mes, ano)
        Assert.assertEquals(dataInicial.toString(), periodo.dataInicial.toString())
        Assert.assertEquals(dataFinal.toString(), periodo.dataFinal.toString())
    }

    @Test
    fun testGerarPeriodo_ano(){
        //Arrange
        val ano = 2020

        //Act
        val periodo = Periodo(ano = ano)

        //Assert
        val dataInicial = Data(1, 1, ano)
        val dataFinal = Data(31, 12, ano)
        Assert.assertEquals(dataInicial.toString(), periodo.dataInicial.toString())
        Assert.assertEquals(dataFinal.toString(), periodo.dataFinal.toString())
    }

    @Test
    fun testIsOnPeriodo(){
        //Arrange
        val dataTrue1 = Data(1, 1, 2020)
        val dataTrue2 = Data(31, 1, 2020)
        val dataTrue3 = Data(15, 1, 2020)
        val dataFalse1 = Data(1, 2, 2020)
        val dataFalse2 = Data(31, 12, 2019)
        val dataFalse3 = Data(1, 6, 2020)
        val periodo = Periodo(1, 2020)

        //Act
        periodo.isOnPeriodo(dataTrue1)
        periodo.isOnPeriodo(dataTrue2)
        periodo.isOnPeriodo(dataTrue3)
        periodo.isOnPeriodo(dataFalse1)
        periodo.isOnPeriodo(dataFalse2)
        periodo.isOnPeriodo(dataFalse3)

        //Assert
        Assert.assertTrue(periodo.isOnPeriodo(dataTrue1))
        Assert.assertTrue(periodo.isOnPeriodo(dataTrue2))
        Assert.assertTrue(periodo.isOnPeriodo(dataTrue3))
        Assert.assertFalse(periodo.isOnPeriodo(dataFalse1))
        Assert.assertFalse(periodo.isOnPeriodo(dataFalse2))
        Assert.assertFalse(periodo.isOnPeriodo(dataFalse3))
    }

    @Test
    fun testGeracao_IsAno(){
        //Arrange
        val ano = 2020
        val mes = 1
        val dataInicial = Data(10, 5, 2020)
        val dataFinal = Data(20, 6, 2020)

        //Act
        val periodoFalse1 = Periodo(mes, ano)
        val periodoFalse2 = Periodo(dataInicial, dataFinal)
        val periodoTrue = Periodo(ano = ano)

        //Assert
        Assert.assertFalse(periodoFalse1.isAno)
        Assert.assertFalse(periodoFalse2.isAno)
        Assert.assertTrue(periodoTrue.isAno)
    }
    /*

    @Test
    fun testGetUltimoPeriodoUtilizado(){
        //Arrange
        val pessoa = Pessoa("Fulano")
        pessoa.addMovimentacao(
            Movimentacao(
            "assunto",
            Data(1,1,2020),
            20.00,
            Categoria("categoria", MacroCategoriaDebbug().gerarMacroCategoriaTeste())
        )
        )
        pessoa.addMovimentacao(
            Movimentacao(
            "assunto",
            Data(1,2,2020),
            20.00,
            Categoria("categoria", MacroCategoriaDebbug().gerarMacroCategoriaTeste()
            )
            )
        )
        val periodos = getPeriodosFromMovimentacoes_ComAnos(pessoa.movimentacoes)

        //Act
        val ultimoPeriodo = getUltimoPeriodoUtilizado(periodos)

        //Assert
        Assert.assertEquals("2020", ultimoPeriodo.nome)
        Assert.assertEquals(Data(1, 1, 2020).toString(), ultimoPeriodo.dataInicial.toString()) // data inicial
        Assert.assertEquals(Data(31, 12, 2020).toString(), ultimoPeriodo.dataFinal.toString()) // data final

    }

     */
}