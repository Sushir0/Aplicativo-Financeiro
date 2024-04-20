package com.example.finance.lvl2.Movimentacao

import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl2.validações.validarMovimentacao

fun adicionarMovimentacaoPessoa(assunto : String, valorStr: String, data : Data, categoria: Categoria?, pessoa: Pessoa): List<String>{
    val erros = validarMovimentacao(
        assunto = assunto,
        valorStr = valorStr,
        data = data,
        categoria = categoria)


    if (erros.isEmpty()) {
        var movimentacao = Movimentacao(assunto, data, valorStr.toDouble(), categoria!!)
        pessoa.addMovimentacao(movimentacao)
    }
    return erros
}

fun adicionarMovimentacaoCasa(assunto: String, valorStr: String, data: Data, categoria: Categoria?, casa: Casa): List<String>{
    val erros = validarMovimentacao(
        assunto = assunto,
        valorStr = valorStr,
        data = data,
        categoria = categoria)


    if (erros.isEmpty()) {
        var movimentacao = Movimentacao(assunto, data, valorStr.toDouble(), categoria!!)
        casa.addMovimentacao(movimentacao)
    }
    return erros
}