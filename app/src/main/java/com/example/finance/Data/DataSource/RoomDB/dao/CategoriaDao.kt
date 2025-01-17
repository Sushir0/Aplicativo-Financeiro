package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.CategoriaComMacroCategoriaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.CategoriaEntity

@Dao
interface CategoriaDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(categoria: CategoriaEntity)

    @Query("SELECT * FROM categoria WHERE id = :id")
    suspend fun get(id: String): CategoriaEntity?

    @Query("SELECT * FROM categoria")
    suspend fun getAll(): List<CategoriaEntity>?

    @Query("SELECT * FROM categoria WHERE macro_categoria_id = :macroCategoriaId")
    suspend fun getByMacroCategoria(macroCategoriaId: String): List<CategoriaEntity>?

    @Query("""
        SELECT C.*
        FROM categoria C
        INNER JOIN macro_categoria MC ON C.macro_categoria_id = MC.id
        INNER JOIN casa CA ON MC.casa_id = CA.id
        WHERE 
            (
                CA.id = :idCasa
            )
            AND (mc.afeta_pessoa = :afetaPessoa OR :afetaPessoa IS NULL)
            AND (mc.afeta_casa = :afetaCasa OR :afetaCasa IS NULL)
            AND (mc.is_gasto = :isGasto OR :isGasto IS NULL)
    """)
    suspend fun getFiltrada(idCasa: String, afetaPessoa: Boolean?, afetaCasa: Boolean?, isGasto: Boolean?): List<CategoriaEntity>?

    @Query("DELETE FROM categoria WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM categoria WHERE id = :id")
    suspend fun getCategoriaComMacroCategoria(id: String): CategoriaComMacroCategoriaEntity?




}