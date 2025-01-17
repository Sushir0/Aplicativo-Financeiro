package com.example.finance.lvl2.Movimentacao
/*
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaDebbug
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.gerarDataTeste
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl2.validações.validarMovimentacao

class MovimentacaoController {
    fun adicionarMovimentacao(assunto : String, valorStr: String, data : Data, categoria: Categoria?, movimentacaoHolder: MovimentacaoHolder): List<String>{
        val erros = validarMovimentacao(
            descricao = assunto,
            valorStr = valorStr,
            data = data,
            categoria = categoria)


        if (erros.isEmpty()) {
            var movimentacao = Movimentacao(assunto, data, valorStr.toDouble(), categoria!!)
            movimentacaoHolder.addMovimentacao(movimentacao)
        }
        return erros
    }

    fun deleteMovimentacao(movimentacao: Movimentacao, movimentacaoHolder: MovimentacaoHolder):Boolean{
        return movimentacaoHolder.removeMovimentacao(movimentacao)
    }

    fun editarMovimentacao(
        movimentacaoAnterior: Movimentacao,
        assunto: String,
        valorStr: String,
        data: Data,
        categoria: Categoria?,
        movimentacaoHolder: MovimentacaoHolder,
        movimentacaoHolderInicial: MovimentacaoHolder
    ): List<String> {
        val erros = validarMovimentacao(
            descricao = assunto,
            valorStr = valorStr,
            data = data,
            categoria = categoria)

        if (erros.isEmpty()) {
            val novaMovimentacao = movimentacaoAnterior.editarMovimentacao(
                assunto = assunto,
                valor = valorStr.toDouble(),
                data = data,
                categoria = categoria!!,
            )
            if(movimentacaoHolder != movimentacaoHolderInicial){
                val mov = movimentacaoHolderInicial.getMovimentacoes().find{it.id == novaMovimentacao.id}
                if(mov != null){
                    erros.add(movimentacaoHolderInicial.removeMovimentacao(mov).toString())
                    movimentacaoHolder.addMovimentacao(movimentacao = mov)

                }
            }

        }

        return erros
    }

    fun testeAdicionarMovimentacao(movimentacaoHolder: MovimentacaoHolder){
        val categoria = CategoriaDebbug().getCategoriaAleatoria()
        movimentacaoHolder.addMovimentacao(
            Movimentacao(
                "assunto: ${categoria.nome}",
                gerarDataTeste(),
                100.0,
                categoria
            )
        )

    }
}

 */