package com.example.finance

import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.MacroCategoria
import com.example.finance.lvl1.Tipo
import org.junit.Assert
import org.junit.Test

class MacroCategoriaUnitTest {
    @Test
    fun testCreateCategoria(){
        //Arrange
        val macroCategoria = MacroCategoria("macroCategoria", afetaPessoa = true)

        //Act
        macroCategoria.createCategoria("Categoria")

        //Assert
        Assert.assertEquals(1, macroCategoria.getCategorias().size)
        Assert.assertTrue(macroCategoria.getCategorias()[0].toString() == Categoria("Categoria", afetaPessoa = true).toString())
    }

    @Test
    fun testGetCategorias(){
        //Arrange
        val macroCategoria = MacroCategoria("macroCategoria")
        macroCategoria.createCategoria("Categoria1")
        macroCategoria.createCategoria("Categoria2")
        macroCategoria.createCategoria("Categoria3")

        //Act
        val categorias = macroCategoria.getCategorias()

        //Assert
        Assert.assertEquals(3, categorias.size)
        Assert.assertTrue(categorias.any { it.toString() == Categoria("Categoria1").toString()})
        Assert.assertTrue(categorias.any { it.toString() == Categoria("Categoria2").toString()})
        Assert.assertTrue(categorias.any { it.toString() == Categoria("Categoria3").toString()})
    }

    @Test
    fun testVerificarTipo(){
        //Arrange
        val macroCategoria = MacroCategoria("macroCategoria", isGasto = false)

        //Act
        val verificacaoRecebimento = macroCategoria.verificarTipo(Tipo.RECEBIMENTO)
        val verificacaoGasto = macroCategoria.verificarTipo(Tipo.GASTO)
        val verificacaoTodos = macroCategoria.verificarTipo(Tipo.TODOS)
        val verificacaoNull = macroCategoria.verificarTipo(null)

        //Assert
        Assert.assertTrue(verificacaoRecebimento)
        Assert.assertFalse(verificacaoGasto)
        Assert.assertTrue(verificacaoTodos)
        Assert.assertTrue(verificacaoNull)

    }
}