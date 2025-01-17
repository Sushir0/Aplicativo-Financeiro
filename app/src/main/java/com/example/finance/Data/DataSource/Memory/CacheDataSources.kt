package com.example.finance.Data.DataSource.Memory

import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.Data.DataSource.Memory.dataSource.CasaDataSourceMemory
import com.example.finance.Data.DataSource.Memory.dataSource.CategoriaDataSourceMemory
import com.example.finance.Data.DataSource.Memory.dataSource.MacroCategoriaDataSourceMemory
import com.example.finance.Data.DataSource.Memory.dataSource.MovimentacaoDataSourceMemory
import com.example.finance.Data.DataSource.Memory.dataSource.ParcelaDataSourceMemory
import com.example.finance.Data.DataSource.RoomDB.dataSource.CasaDataSourceRoom
import com.example.finance.Data.DataSource.TipoBancoDeDados
import com.example.finance.a_Domain.Repositorio.CasaRepository
import com.example.finance.a_Domain.Repositorio.CategoriaRepository
import com.example.finance.a_Domain.Repositorio.MacroCategoriaRepository
import com.example.finance.a_Domain.Repositorio.MovimentacaoRepository
import com.example.finance.a_Domain.Repositorio.ParcelaRepository

object CacheDataSources: InterfaceDataSources(){
    override val categoriaDataSource: CategoriaRepository = CategoriaDataSourceMemory()
    override val macroCategoriaDataSource: MacroCategoriaRepository = MacroCategoriaDataSourceMemory()
    override val movimentacaoDataSource: MovimentacaoRepository = MovimentacaoDataSourceMemory()
    override val parcelaDataSource: ParcelaRepository = ParcelaDataSourceMemory()
    override val casaDataSource: CasaRepository = CasaDataSourceMemory()
    override val type: TipoBancoDeDados = TipoBancoDeDados.Cache


}