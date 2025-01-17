package com.example.finance.validacao

import com.example.finance.lvl2.validações.ValidarCategoria.validarCategoria
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidarCategoriaUnitTest {

    @Test
    fun `validarCategoria deve retornar erro quando o nome for vazio`() {
        val nome = ""
        val erros = validarCategoria(nome)
        assertEquals(1, erros.size)
        assertTrue(erros.contains("Nome não pode ser vazio"))
    }

    @Test
    fun `validarCategoria deve retornar erro quando o nome for menor que 4 caracteres`() {
        val nome = "abc"
        val erros = validarCategoria(nome)
        assertEquals(1, erros.size)
        assertTrue(erros.contains("Nome deve ter entre 4 e 20 caracteres"))
    }

    @Test
    fun `validarCategoria deve retornar erro quando o nome for maior que 20 caracteres`() {
        val nome = "abcdefghijklmnopqrstuvwxyza"
        val erros = validarCategoria(nome)
        assertEquals(1, erros.size)
        assertTrue(erros.contains("Nome deve ter entre 4 e 20 caracteres"))
    }

    @Test
    fun `validarCategoria nao deve retornar erro quando o nome estiver dentro do tamanho valido`() {
        val nome = "Nome Valido"
        val erros = validarCategoria(nome)
        assertTrue(erros.isEmpty())
    }

    @Test
    fun `validarCategoria nao deve retornar erro quando o nome tiver exatamente 4 caracteres`() {
        val nome = "abcd"
        val erros = validarCategoria(nome)
        assertTrue(erros.isEmpty())
    }

    @Test
    fun `validarCategoria nao deve retornar erro quando o nome tiver exatamente 20 caracteres`() {
        val nome = "12345678901234567890"
        val tamanho = nome.length
        val erros = validarCategoria(nome)
        assertTrue(erros.isEmpty())
    }
}