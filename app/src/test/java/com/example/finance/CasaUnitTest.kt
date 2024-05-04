package com.example.finance

import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.MacroCategoria
import com.example.finance.lvl1.Pessoa
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

}