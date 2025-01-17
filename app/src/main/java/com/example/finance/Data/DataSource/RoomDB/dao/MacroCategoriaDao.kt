package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.MacroCategoriaEntity

@Dao
interface MacroCategoriaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(macroCategoria: MacroCategoriaEntity)

    @Query("SELECT * FROM macro_categoria WHERE id = :id")
    suspend fun get(id: String): MacroCategoriaEntity?

    @Query("""
        SELECT MC.*
        FROM macro_categoria MC
        INNER JOIN casa CA ON MC.casa_id = CA.id
        WHERE( CA.id = :idCasa )
        AND (mc.afeta_pessoa = :afetaPessoa OR :afetaPessoa IS NULL)
        AND (mc.afeta_casa = :afetaCasa OR :afetaCasa IS NULL)
        AND (mc.is_gasto = :isGasto OR :isGasto IS NULL)
    """)
    suspend fun getFiltrada(idCasa: String, afetaPessoa: Boolean?, afetaCasa: Boolean?, isGasto: Boolean?): List<MacroCategoriaEntity>?

    @Query("DELETE FROM macro_categoria WHERE id = :id")
    suspend fun delete(id: String)





}