package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.PessoaEntity

@Dao
interface PessoaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pessoa: PessoaEntity)

    @Query("SELECT * FROM pessoa WHERE casa_id = :casaId")
    suspend fun getMembros(casaId: String): List<PessoaEntity>

    @Query("SELECT * FROM pessoa WHERE id = :id")
    suspend fun getMembro(id: String): PessoaEntity

}