package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.CasaEntity

@Dao
interface CasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(casa: CasaEntity)

    @Query("SELECT * FROM casa WHERE id = :id")
    suspend fun getCasa(id: String): CasaEntity





}