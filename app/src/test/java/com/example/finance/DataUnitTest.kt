package com.example.finance

import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.converterDataMillisParaData
import com.example.finance.lvl1.formataMeseUtilizado
import com.example.finance.lvl1.gerarCategoriaTeste
import com.example.finance.lvl1.getAnosUtilizados
import com.example.finance.lvl1.getDatasDeMovimentacoes
import com.example.finance.lvl1.getMesAbreviados
import com.example.finance.lvl1.getMesesUtilizados
import com.example.finance.lvl1.ordenarDatas
import com.example.finance.lvl1.ordenarPorAno
import com.example.finance.lvl1.ordenarPorDia
import com.example.finance.lvl1.ordenarPorMes
import org.junit.Assert
import org.junit.Test

class DataUnitTest {

    @Test
    fun testDataToString(){
        //Arrange
        val data1 = Data(1, 2, 2023)
        val data2 = Data(1, 12, 2023)
        val data3 = Data(11, 11, 2023)

        //Act
        val result1 = data1.toString()
        val result2 = data2.toString()
        val result3 = data3.toString()

        //Assert
        Assert.assertEquals("01/02/2023", result1)
        Assert.assertEquals("01/12/2023", result2)
        Assert.assertEquals("11/11/2023", result3)
    }

    @Test
    fun testeDatasFromMovimentacoes(){
        //Arrange
        val movimentacoes = listOf(
            Movimentacao("teste", Data(10, 1,11), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(15, 2,12), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(20, 3,13), 100.0, gerarCategoriaTeste()),
        )


        //Act
        val result = getDatasDeMovimentacoes(movimentacoes)

        //Assert
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.any{ it.toString() == "10/01/11"})
        Assert.assertTrue(result.any{ it.toString() == "15/02/12"})
        Assert.assertTrue(result.any{ it.toString() == "20/03/13"})
    }

    @Test
    fun testOrdenarPorDia(){
        //Arrange
        val datas = mutableListOf(
            Data(15, 1,11),
            Data(10, 2,12),
            Data(2, 3,13)
        )

        //Act
        ordenarPorDia(datas)

        //Assert
        Assert.assertTrue(datas[0].toString() == "02/03/13")
        Assert.assertTrue(datas[1].toString() == "10/02/12")
        Assert.assertTrue(datas[2].toString() == "15/01/11")
    }

    @Test
    fun testOrdenarPorMes(){
        //Arrange
        val datas = mutableListOf(
            Data(15, 9,11),
            Data(10, 3,12),
            Data(2, 6,13)
        )

        //Act
        ordenarPorMes(datas)

        //Assert
        Assert.assertTrue(datas[0].toString() == "10/03/12")
        Assert.assertTrue(datas[1].toString() == "02/06/13")
        Assert.assertTrue(datas[2].toString() == "15/09/11")
    }

    @Test
    fun testOrdenarPorAno(){
        //Arrange
        val datas = mutableListOf(
            Data(15, 9,3),
            Data(10, 3,15),
            Data(2, 6,5)
        )

        //Act
        ordenarPorAno(datas)

        //Assert
        Assert.assertTrue(datas[0].toString() == "15/09/3")
        Assert.assertTrue(datas[1].toString() == "02/06/5")
        Assert.assertTrue(datas[2].toString() == "10/03/15")
    }

    @Test
    fun testOrdenarDatas(){
        //Arrange
        val datas = mutableListOf(
            Data(10, 9,1),
            Data(10, 6,1),
            Data(2, 6,2),
            Data(3, 6,2),
            Data(1, 2,3)
            )

        //Act
        ordenarDatas(datas)

        //Assert
        Assert.assertTrue(datas[0].toString() == "10/06/1")
        Assert.assertTrue(datas[1].toString() == "10/09/1")
        Assert.assertTrue(datas[2].toString() == "02/06/2")
        Assert.assertTrue(datas[3].toString() == "03/06/2")
        Assert.assertTrue(datas[4].toString() == "01/02/3")
    }

    @Test
    fun testGetMesesUtilizados(){
        //Arrange
        val movimentacoes = listOf(
        Movimentacao("teste", Data(10, 1,11), 100.0, gerarCategoriaTeste()),
        Movimentacao("teste", Data(15, 2,11), 100.0, gerarCategoriaTeste()),
        Movimentacao("teste", Data(20, 3,11), 100.0, gerarCategoriaTeste()),
        Movimentacao("teste", Data(21, 3,11), 100.0, gerarCategoriaTeste()),
        )
        val datas = getDatasDeMovimentacoes(movimentacoes)

        //Act
        val result = getMesesUtilizados(datas, 11)

        //Assert
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.any{ it == 1})
        Assert.assertTrue(result.any{ it == 2})
        Assert.assertTrue(result.any{ it == 3})
    }

    @Test
    fun testGetAnosUtilizados(){
        //Arrange
        val movimentacoes = listOf(
            Movimentacao("teste", Data(10, 1,10), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(15, 2,11), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(20, 3,12), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(21, 3,13), 100.0, gerarCategoriaTeste()),
            Movimentacao("teste", Data(21, 3,13), 100.0, gerarCategoriaTeste()),
        )
        val datas = getDatasDeMovimentacoes(movimentacoes)

        //Act
        val result = getAnosUtilizados(datas)

        //Assert
        Assert.assertEquals(4, result.size)
        Assert.assertTrue(result.any{ it == 10})
        Assert.assertTrue(result.any{ it == 11})
        Assert.assertTrue(result.any{ it == 12})
        Assert.assertTrue(result.any{ it == 13})
    }

    @Test
    fun testGetMesesAbreviados(){
        //Arrange
        val meses = listOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        )
        val resultados = mutableListOf<String>()

        //Act
        meses.forEach {
            resultados.add(getMesAbreviados(it))
        }

        //Assert
        Assert.assertEquals(12, resultados.size)
        Assert.assertTrue(resultados.any{ it == "Jan" })
        Assert.assertTrue(resultados.any{ it == "Fev" })
        Assert.assertTrue(resultados.any{ it == "Mar" })
        Assert.assertTrue(resultados.any{ it == "Abr" })
        Assert.assertTrue(resultados.any{ it == "Mai" })
        Assert.assertTrue(resultados.any{ it == "Jun" })
        Assert.assertTrue(resultados.any{ it == "Jul" })
        Assert.assertTrue(resultados.any{ it == "Ago" })
        Assert.assertTrue(resultados.any{ it == "Set" })
        Assert.assertTrue(resultados.any{ it == "Out" })
        Assert.assertTrue(resultados.any{ it == "Nov" })
        Assert.assertTrue(resultados.any{ it == "Dez" })
    }

    @Test
    fun testFormatarMeses_Anos(){
        //Arrange
        val mes_ano = listOf(
            Pair(1, 1),
            Pair(2, 1),
            Pair(1, 2),
            Pair(2, 2),
            Pair(1, 3),
            Pair(6, 2023),
        )
        val resultados = mutableListOf<String>()

        //Act
        mes_ano.forEach {
            resultados.add(formataMeseUtilizado(it.first, it.second))
        }

        //Assert
        Assert.assertEquals(6, resultados.size)
        Assert.assertTrue(resultados.any{ it == "Jan/01" })
        Assert.assertTrue(resultados.any{ it == "Fev/01" })
        Assert.assertTrue(resultados.any{ it == "Jan/02" })
        Assert.assertTrue(resultados.any{ it == "Fev/02" })
        Assert.assertTrue(resultados.any{ it == "Jan/03" })
        Assert.assertTrue(resultados.any{ it == "Jun/23" })
    }

    @Test
    fun testConverterDataMillisParaData(){
        //Arrange
        val dataMillis = 1643673600000L // Correspondendo a 01/02/2022 Utilizando o datePicker
        val expectedData = Data(1, 2, 2022) // Data esperada

        //Act
        val result = converterDataMillisParaData(dataMillis, true)

        //Assert
        Assert.assertEquals(expectedData.toString(), result.toString())
    }
}