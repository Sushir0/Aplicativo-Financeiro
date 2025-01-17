package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.MovimentacaoComCategoriaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.MovimentacaoEntity

@Dao
interface MovimentacaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movimentacao: MovimentacaoEntity)

    @Query("SELECT * FROM movimentacao WHERE id = :id")
    suspend fun getMovimentacao(id: String): MovimentacaoEntity?

//    @Query("SELECT * FROM movimentacao WHERE id = :id")
//    suspend fun getMovimentacaoDetalhada(id: String): MovimentacaoComCategoriaEntity?

}