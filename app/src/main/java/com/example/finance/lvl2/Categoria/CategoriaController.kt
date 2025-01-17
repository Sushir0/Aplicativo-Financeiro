package com.example.finance.lvl2.Categoria
/*
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl2.Getters.Login.getCasa
import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.validações.ValidarCategoria

class CategoriaController {

    fun desativarCategoria(
        macroCategoria: MacroCategoria,
        categoria: Categoria
    ){
        macroCategoria.desativarCategoria(categoria)
    }

    fun ativarCategoria(
        macroCategoria: MacroCategoria,
        categoria: Categoria
    ){
        macroCategoria.ativarCategoria(categoria)
    }

    fun adicionarCategoria(
        nome: String,
        macroCategoria: MacroCategoria
    ): ArrayList<String> {
        val erros = ValidarCategoria().validarCategoria(nome = nome)

        if(erros.isEmpty()){
            macroCategoria.createCategoria(nome)
        }
        return erros
    }

    fun deleteMovimentacoesFromCategoria(categoria: Categoria){
        val membros = getMembros()
        for (membro in membros){
            membro.removeMovimentacoesPorCategoria(categoria)
        }

    }

    fun getCategoriaFromId(categoria: Categoria): Categoria? {
        return getCasa().getCategorias(afetaCasa = true, afetaPessoa = true).find { it == categoria }
    }

    fun getVezesUtilizadas(categoria: Categoria): Int {
        return getMembros().sumOf { it.getMovimentacoes(selecaoUsuario = SelecaoUsuario.categoriaSelecionada(categoria)).size }
    }

    fun verificarSeCategoriaAfetaMovimentacaoHolder(categoria: Categoria?, movimentacaoHolder: MovimentacaoHolder): Boolean {
        if(categoria == null){ return true }
        return (categoria.afetaCasa() && movimentacaoHolder.isCasa) || (categoria.afetaPessoa() && !movimentacaoHolder.isCasa)
    }
}

 */