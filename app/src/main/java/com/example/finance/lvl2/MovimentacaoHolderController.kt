package com.example.finance.lvl2

import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.Resultado
import com.example.finance.lvl2.validações.validarMovimentacao
/*
class MovimentacaoHolderController(movimentacaoHolder: MovimentacaoHolder) {
    private val movimentacaoHolder: MovimentacaoHolder = movimentacaoHolder

    fun adicionarMovimentacao(movimentacao: Movimentacao){
        movimentacaoHolder.addMovimentacao(movimentacao)
    }

    fun criarMovimentacao(assunto : String, valorStr: String, data : Data, categoria: Categoria?,): Resultado<Movimentacao> {
        val erros = validarMovimentacao(
            descricao = assunto,
            valorStr = valorStr,
            data = data,
            categoria = categoria
        )


        if (erros.isEmpty()) {
            return Resultado.Sucesso(Movimentacao(assunto, data, valorStr.toDouble(), categoria!!))
        }else{
            return Resultado.Falha(erros)
        }
    }
}

 */