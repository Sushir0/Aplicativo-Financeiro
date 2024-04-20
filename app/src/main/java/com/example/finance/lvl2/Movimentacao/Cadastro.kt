package com.example.finance.lvl2.Movimentacao

import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl1.gerarCategoriaTeste
import com.example.finance.lvl2.validações.validarMovimentacao

fun adicionarMovimentacao(assunto : String, valorStr: String, data : Data, categoria: Categoria?, movimentacaoHolder: MovimentacaoHolder): List<String>{
    val erros = validarMovimentacao(
        assunto = assunto,
        valorStr = valorStr,
        data = data,
        categoria = categoria)


    if (erros.isEmpty()) {
        var movimentacao = Movimentacao(assunto, data, valorStr.toDouble(), categoria!!)
        movimentacaoHolder.addMovimentacao(movimentacao)
    }
    return erros
}


fun testeAdicionarMovimentacao(movimentacaoHolder: MovimentacaoHolder){
    movimentacaoHolder.addMovimentacao(
        Movimentacao(
        "assunto",
        Data(20,5,2024),
        100.0,
        gerarCategoriaTeste()
        )
    )

}