package com.example.finance.Data.DataSource

import com.example.finance.a_Domain.Repositorio.CasaRepository
import com.example.finance.a_Domain.Repositorio.CategoriaRepository
import com.example.finance.a_Domain.Repositorio.MacroCategoriaRepository
import com.example.finance.a_Domain.Repositorio.MovimentacaoRepository
import com.example.finance.a_Domain.Repositorio.ParcelaRepository

abstract class InterfaceDataSources (){
    abstract val categoriaDataSource: CategoriaRepository
    abstract val macroCategoriaDataSource: MacroCategoriaRepository
    abstract val movimentacaoDataSource: MovimentacaoRepository
    abstract val parcelaDataSource: ParcelaRepository
    abstract val casaDataSource: CasaRepository
    abstract val type: TipoBancoDeDados
}