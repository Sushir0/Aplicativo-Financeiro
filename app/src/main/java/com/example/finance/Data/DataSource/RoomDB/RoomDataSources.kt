package com.example.finance.Data.DataSource.RoomDB

import android.content.Context
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.Data.DataSource.RoomDB.dataSource.CasaDataSourceRoom
import com.example.finance.Data.DataSource.RoomDB.dataSource.CategoriaDataSourceRoom
import com.example.finance.Data.DataSource.RoomDB.dataSource.MacroCategoriaDataSourceRoom
import com.example.finance.Data.DataSource.RoomDB.dataSource.MovimentacaoDataSourceRoom
import com.example.finance.Data.DataSource.RoomDB.dataSource.ParcelaDataSourceRoom
import com.example.finance.Data.DataSource.TipoBancoDeDados

class RoomDataSources(
    context: Context
): InterfaceDataSources() {
    override val categoriaDataSource = CategoriaDataSourceRoom(context)
    override val macroCategoriaDataSource = MacroCategoriaDataSourceRoom(context)
    override val movimentacaoDataSource = MovimentacaoDataSourceRoom(context)
    override val parcelaDataSource = ParcelaDataSourceRoom(context)
    override val casaDataSource = CasaDataSourceRoom(context)
    override val type = TipoBancoDeDados.Room

}