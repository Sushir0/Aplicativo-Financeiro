package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import java.util.UUID

@Entity(
    tableName = "macro_categoria",
    foreignKeys = [
        ForeignKey(
            entity = CasaEntity::class,
            parentColumns = ["id"],
            childColumns = ["casa_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["casa_id"]), Index(value = ["is_gasto"])]
)
data class MacroCategoriaEntity(
    @PrimaryKey() val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "afeta_casa") val afetaCasa: Boolean,
    @ColumnInfo(name = "afeta_pessoa") val afetaPessoa: Boolean,
    @ColumnInfo(name = "is_gasto") val isGasto: Boolean,
    @ColumnInfo(name = "casa_id") val casaId: String
){
    fun toModel(): MacroCategoria {
        return MacroCategoria(
            id = id,
            nome = nome,
            afetaCasa = afetaCasa,
            afetaPessoa = afetaPessoa,
            isGasto = isGasto,
            idCasa = casaId
        )
    }
}