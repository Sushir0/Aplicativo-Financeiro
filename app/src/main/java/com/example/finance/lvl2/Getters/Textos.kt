package com.example.finance.lvl2.Getters

import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Periodo
import com.example.finance.lvl1.getPeriodoFromDatasUtilizadas

fun getMembros(): ArrayList<MovimentacaoHolder> {
    val membros =  ArrayList<MovimentacaoHolder>()
    membros.add(Login.getCasaLogada())
    membros.addAll(Login.getCasaLogada().moradores)
    return membros
}

fun getPeriodos(movimentacoes: List<Movimentacao>): ArrayList<Periodo>{
    val periodos = ArrayList<Periodo>()
    periodos.addAll(getPeriodoFromDatasUtilizadas(movimentacoes))
    return periodos
}