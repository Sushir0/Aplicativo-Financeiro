package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import java.util.UUID

@Entity(
    tableName = "Categoria",
    foreignKeys = [
        ForeignKey(
            entity = MacroCategoriaEntity::class,
            parentColumns = ["id"],
            childColumns = ["macro_categoria_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["macro_categoria_id"])]
)
data class CategoriaEntity (
    @PrimaryKey() val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "is_activate") val isActivate: Boolean,
    @ColumnInfo(name = "macro_categoria_id") val macroCategoriaId: String,
)

data class CategoriaComMacroCategoriaEntity(
    @Embedded val categoria: CategoriaEntity,
    @Relation(
        parentColumn = "macro_categoria_id",
        entityColumn = "id"
    )
    val macroCategoria: MacroCategoriaEntity
){
    override fun toString(): String {
        return "CategoriaComMacroCategoriaEntity(categoria=$categoria, macroCategoria=$macroCategoria)"
    }
}