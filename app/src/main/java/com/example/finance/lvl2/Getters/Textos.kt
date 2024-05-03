package com.example.finance.lvl2.Getters

import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.getPeriodosFromMovimentacoes
import com.example.finance.lvl1.getUltimoPeriodoUtilizado
import com.example.finance.lvl3.widgets.Tipo

fun getMembros(): ArrayList<MovimentacaoHolder> {
    val membros =  ArrayList<MovimentacaoHolder>()
    membros.add(Login.getCasaLogada())
    membros.addAll(Login.getCasaLogada().moradores)
    return membros
}

fun getMembroById(id:Int):MovimentacaoHolder{
    var membros =  getMembros()
    membros.forEach{
        if (it.sameID(id)){
            return it
        }
    }
    return Login.getCasaLogada()
}


fun getPeriodos(movimentacoes: List<Movimentacao>): ArrayList<Periodo>{
    val periodos = ArrayList<Periodo>()
    periodos.addAll(getPeriodosFromMovimentacoes(movimentacoes))
    return periodos
}

fun getPeriodoInicial(membroSelecionado: MovimentacaoHolder): Periodo{
    return getUltimoPeriodoUtilizado(
        getPeriodosFromMovimentacoes(
            membroSelecionado.movimentacoes
        )
    )
}

fun getMovimentacoes(movimentacaoHolder: MovimentacaoHolder, isGasto: Boolean, periodo: Periodo):List<Movimentacao>{
    return if (isGasto){
        movimentacaoHolder.getGastos(periodo)
    }else{
        movimentacaoHolder.getRecebimentos(periodo)
    }
}