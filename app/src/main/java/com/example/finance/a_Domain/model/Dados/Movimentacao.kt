package com.example.finance.a_Domain.model.Dados

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MetaDados.Periodo
import java.io.Serializable
import java.util.Random

data class Movimentacao (
    val id: String,
    var descricao: String,
    var data: Data,
    var valor: Double,
    var categoria: Categoria
): Serializable{
    val isGasto: Boolean
        get() = categoria.isGasto

    fun isOnPeriodo(periodo: Periodo?): Boolean {
        return periodo?.isOnPeriodo(data) ?: true
    }

    fun isOnSelecaoUsuario(selecaoUsuario: SelecaoUsuario): Boolean {
        return when (selecaoUsuario) {
            is SelecaoUsuario.tipoSelecionado -> {
                when (selecaoUsuario.tipo) {
                    Tipo.RECEBIMENTO -> !isGasto
                    Tipo.GASTO -> isGasto
                    else -> true
                }
            }
            is SelecaoUsuario.categoriaSelecionada -> {
                categoria == selecaoUsuario.categoria
                }
            is SelecaoUsuario.macroCategoriaSelecionada -> {
                categoria.macroCategoria == selecaoUsuario.macroCategoria
            }
        }
    }

    override fun toString(): String {
        return "Movimentacao(id=$id, assunto='$descricao', data=$data, valor=$valor, categoria=$categoria)"
    }
}


/*
class MovimentacaoDebbug(){
    fun gerarMovimentacaoGasto(): Movimentacao {
        return Movimentacao(
            assunto = "assunto",
            data = Data(10,5,15),
            valor = 10.0,
            categoria = CategoriaDebbug().getCategoriaGastoFixo()
        )
    }
    fun gerarMovimentacaoRecebimento(): Movimentacao {
        return Movimentacao(
            assunto = "assunto",
            data = Data(10,5,15),
            valor = 10.0,
            categoria = CategoriaDebbug().getCategoriaRecebimentoFixo()
        )
    }
}

 */