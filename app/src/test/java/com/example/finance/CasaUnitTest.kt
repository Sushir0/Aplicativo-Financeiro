package com.example.finance

import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.MacroCategoria
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl1.Tipo
import org.junit.Assert
import org.junit.Test

class CasaUnitTest {

    @Test
    fun testAddMoradorToCasa() {
        //Arrange
        val casa = Casa("Casa")
        val morador = Pessoa("Morador")

        //Act
        casa.addMorador(morador)

        //Assert
        Assert.assertTrue(casa.moradores.contains(morador))
    }

    @Test
    fun testAddMacroCategorialToCasa(){
        //Arrange
        val casa = Casa("Casa")
        val macroCategoria = MacroCategoria("macroCategoria")
        macroCategoria.createCategoria("categoria")

        //Act
        casa.addMacroCategoria(macroCategoria)

        //Assert
        Assert.assertTrue(casa.macroCategorias.contains(macroCategoria))
    }

    @Test
    fun testGetCategorias_AfetaCasaTrue(){
        //Arrange
        val casa = Casa("Casa")
        val macroCategoria = MacroCategoria("macroCategoria", afetaCasa = true)
        macroCategoria.createCategoria("categoria 1")
        macroCategoria.createCategoria("categoria 2")
        casa.addMacroCategoria(macroCategoria)

        //Act
        val categorias = casa.getCategorias(afetaCasa = true)

        //Assert
        Assert.assertEquals(2, categorias.size)
        Assert.assertTrue(categorias.any { it.nome == "categoria 1" })
        Assert.assertTrue(categorias.any { it.nome == "categoria 2" })
    }

    @Test
    fun testGetCategorias_AfetaPessoaTrue(){
        //Arrange
        val casa = Casa("Casa")
        val macroCategoria = MacroCategoria("macroCategoria", afetaPessoa = true)
        macroCategoria.createCategoria("categoria 1")
        macroCategoria.createCategoria("categoria 2")
        casa.addMacroCategoria(macroCategoria)

        //Act
        val categorias = casa.getCategorias(afetaPessoa = true)

        //Assert
        Assert.assertEquals(2, categorias.size)
        Assert.assertTrue(categorias.any { it.nome == "categoria 1" })
        Assert.assertTrue(categorias.any { it.nome == "categoria 2" })
    }

    @Test
    fun testGetCategorias_Tipo(){
        //Arrange
        val casa = Casa("Casa")
        val macroCategoria = MacroCategoria("macroCategoria", afetaPessoa = true)
        macroCategoria.createCategoria("categoria 1")
        macroCategoria.createCategoria("categoria 2")
        casa.addMacroCategoria(macroCategoria)

        val macroCategoria2 = MacroCategoria("macroCategoria2", afetaPessoa = true, isGasto = true)
        macroCategoria2.createCategoria("categoria 3")
        macroCategoria2.createCategoria("categoria 4")
        casa.addMacroCategoria(macroCategoria2)

        //Act
        val categoriasTipoGasto = casa.getCategorias(afetaPessoa = true, tipo = Tipo.GASTO)
        val categoriasTipoRecebimento = casa.getCategorias(afetaPessoa = true, tipo = Tipo.RECEBIMENTO)
        val categoriasTipoTodos = casa.getCategorias(afetaPessoa = true, tipo = Tipo.TODOS)

        //Assert
        Assert.assertEquals(2, categoriasTipoGasto.size)
        Assert.assertTrue(categoriasTipoGasto.any { it.nome == "categoria 3" })
        Assert.assertTrue(categoriasTipoGasto.any { it.nome == "categoria 4" })

        Assert.assertEquals(2, categoriasTipoRecebimento.size)
        Assert.assertTrue(categoriasTipoRecebimento.any { it.nome == "categoria 1" })
        Assert.assertTrue(categoriasTipoRecebimento.any { it.nome == "categoria 2" })

        Assert.assertEquals(4, categoriasTipoTodos.size)
    }

}