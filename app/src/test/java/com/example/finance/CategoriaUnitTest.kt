package com.example.finance

import com.example.finance.lvl1.Login
import com.example.finance.lvl1.categorias_Contas
import com.example.finance.lvl1.categorias_Recebimentos
import com.example.finance.lvl1.gerarCategoriaTeste
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl1.getSizeCategoriasBase
import com.example.finance.lvl2.Login.testeCadastro
import org.junit.Assert
import org.junit.Test

class CategoriaUnitTest {

    @Test
    fun testGerarCategoria(){
        //Arrenge
        testeCadastro()


        //Act
        gerarCategoriasBasicas()

        //Assert
        val categorias = Login.getCasaLogada().getCategorias(afetaCasa = true, afetaPessoa = true)
        Assert.assertEquals(getSizeCategoriasBase(), categorias.size)
        categorias_Contas.forEach {nomeCategoria ->
            Assert.assertTrue(categorias.any { it.nome == nomeCategoria })
        }
        categorias_Recebimentos.forEach {nomeCategoria ->
            Assert.assertTrue(categorias.any { it.nome == nomeCategoria })
        }

    }


}