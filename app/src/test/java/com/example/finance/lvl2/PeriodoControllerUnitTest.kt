package com.example.finance.lvl2

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa
import com.example.finance.lvl2.Periodo.PeriodoController
import org.junit.Assert
import org.junit.Test

class PeriodoControllerUnitTest {
    /*
    val categoriaGasto = Categoria("Gasto", MacroCategoria(nome = "Macro Gasto", afetaPessoa = true, isGasto = true))
    val categoriaRecebimento = Categoria("Recebimento", MacroCategoria(nome = "Macro Recebimento", afetaPessoa = true, isGasto = false))
    val movimentacoes = listOf(
        Movimentacao(
            descricao = "Movimentacao 1",
            valor = 10.0,
            data = Data(21, 11, 2023),
            categoria = categoriaGasto
        ),
        Movimentacao(
            descricao = "Movimentacao 2",
            valor = 20.0,
            data = Data(22, 12, 2023),
            categoria = categoriaRecebimento)
    )


    @Test
    fun testGetPeriodosComAnosInclusos() {
        // Arrange
        val periodoController = PeriodoController()

        // Act
        val periodos = periodoController.getPeriodos_ComAnosInclusos(movimentacoes)

        // Assert
        Assert.assertTrue(periodos.size == 3)
        Assert.assertEquals(periodos[0].nome, "2023")
        Assert.assertEquals(periodos[1].nome, "Dez/23")
        Assert.assertEquals(periodos[2].nome, "Nov/23")
    }

    @Test
    fun testGetPeriodosSemAnos() {
        // Arrange
        val periodoController = PeriodoController()

        // Act
        val periodos = periodoController.getPeriodos_SemAnosInclusos(movimentacoes)

        // Assert
        Assert.assertTrue(periodos.size == 2)
        Assert.assertEquals(periodos[0].nome, "Dez/23")
        Assert.assertEquals(periodos[1].nome, "Nov/23")
    }

    @Test
    fun testGetPeriodoInicial() {
        // Arrange
        val pessoa = Pessoa("João")
        pessoa.addMovimentacao(movimentacoes[0])
        pessoa.addMovimentacao(movimentacoes[1])

        val periodoController = PeriodoController()

        // Act
        val periodoInicial = periodoController.getPeriodoInicial(pessoa)

        // Assert
        Assert.assertEquals(periodoInicial.nome, "2023")
    }

    @Test
    fun testGetPeriodosComValor_Todos_ComAnosInclusos() {
        // Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 10.0,
                data = Data(21, 11, 2023),
                categoria = CategoriaDebbug().getCategoriaGastoFixo()
            )
        )
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 20.0,
                data = Data(22, 12, 2023),
                categoria = CategoriaDebbug().getCategoriaRecebimentoFixo())
        )
        val selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS)

        // Act
        val periodoController = PeriodoController()
        val periodosComValor = periodoController.getPeriodosComValor_ComAnosInclusos(getCasa(), selecaoUsuario)

        // Assert
        Assert.assertTrue(periodosComValor.size == 3)
        Assert.assertEquals(periodosComValor[0].periodo.nome, "2023")
        Assert.assertEquals(periodosComValor[0].valor, 10f)
        Assert.assertEquals(periodosComValor[1].periodo.nome, "Dez/23")
        Assert.assertEquals(periodosComValor[1].valor, 20f)
        Assert.assertEquals(periodosComValor[2].periodo.nome, "Nov/23")
        Assert.assertEquals(periodosComValor[2].valor, -10f)
    }

    @Test
    fun testGetPeriodosComValor_Recebimentos_ComAnosInclusos() {
        // Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 10.0,
                data = Data(21, 11, 2023),
                categoria = CategoriaDebbug().getCategoriaGastoFixo()
            )
        )
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 20.0,
                data = Data(22, 12, 2023),
                categoria = CategoriaDebbug().getCategoriaRecebimentoFixo())
        )
        val selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.RECEBIMENTO)

        // Act
        val periodoController = PeriodoController()
        val periodosComValor = periodoController.getPeriodosComValor_ComAnosInclusos(getCasa(), selecaoUsuario)

        // Assert
        Assert.assertEquals(periodosComValor.size, 2)
        Assert.assertEquals(periodosComValor[0].periodo.nome, "2023")
        Assert.assertEquals(periodosComValor[0].valor, 20f)
        Assert.assertEquals(periodosComValor[1].periodo.nome, "Dez/23")
        Assert.assertEquals(periodosComValor[1].valor, 20f)
    }

    @Test
    fun testGetPeriodosComValor_Gastos_ComAnosInclusos() {
        // Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 10.0,
                data = Data(21, 11, 2023),
                categoria = CategoriaDebbug().getCategoriaGastoFixo()
            )
        )
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 20.0,
                data = Data(22, 12, 2023),
                categoria = CategoriaDebbug().getCategoriaRecebimentoFixo())
        )
        val selecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.GASTO)
        val periodoController = PeriodoController()

        // Act
        val periodosComValor = periodoController.getPeriodosComValor_ComAnosInclusos(getCasa(), selecaoUsuario)

        // Assert
        Assert.assertEquals(periodosComValor.size, 2)
        Assert.assertEquals(periodosComValor[0].periodo.nome, "2023")
        Assert.assertEquals(periodosComValor[0].valor, 10f)
        Assert.assertEquals(periodosComValor[1].periodo.nome, "Nov/23")
        Assert.assertEquals(periodosComValor[1].valor, 10f)
    }

    @Test
    fun testGetPeriodosComValor_Categoria_ComAnosInclusos() {
        // Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 10.0,
                data = Data(21, 11, 2023),
                categoria = CategoriaDebbug().getCategoriaGastoFixo()
            )
        )
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 20.0,
                data = Data(22, 12, 2023),
                categoria = CategoriaDebbug().getCategoriaRecebimentoFixo()
            )
        )
        val selecaoUsuario = SelecaoUsuario.categoriaSelecionada(CategoriaDebbug().getCategoriaGastoFixo())
        val periodoController = PeriodoController()

        // Act
        val periodosComValor = periodoController.getPeriodosComValor_ComAnosInclusos(getCasa(), selecaoUsuario)

        // Assert
        Assert.assertEquals(periodosComValor.size, 2)
        Assert.assertEquals(periodosComValor[0].periodo.nome, "2023")
        Assert.assertEquals(periodosComValor[0].valor, 10f)
        Assert.assertEquals(periodosComValor[1].periodo.nome, "Nov/23")
        Assert.assertEquals(periodosComValor[1].valor, 10f)
    }

    @Test
    fun testGetPeriodosComValor_MacroCategoria_ComAnosInclusos() {
        // Arrange
        LoginController().testeCadastro()
        CategoriaDebbug().gerarCategoriasBasicas()
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 10.0,
                data = Data(21, 11, 2023),
                categoria = CategoriaDebbug().getCategoriaGastoFixo()
            )
        )
        getCasa().addMovimentacao(
            Movimentacao(
                descricao = "assunto",
                valor = 20.0,
                data = Data(22, 12, 2),
                categoria = CategoriaDebbug().getCategoriaRecebimentoFixo()
            )
        )
        val selecaoUsuario = SelecaoUsuario.macroCategoriaSelecionada(MacroCategoriaDebbug().getMacroCategoriaGastoFixo())
        val periodoController = PeriodoController()

        // Act
        val periodosComValor = periodoController.getPeriodosComValor_ComAnosInclusos(getCasa(), selecaoUsuario)

        // Assert
        Assert.assertEquals(periodosComValor.size, 2)
        Assert.assertEquals(periodosComValor[0].periodo.nome, "2023")
        Assert.assertEquals(periodosComValor[0].valor, 10f)
        Assert.assertEquals(periodosComValor[1].periodo.nome, "Nov/23")
        Assert.assertEquals(periodosComValor[1].valor, 10f)
    }


     */
}