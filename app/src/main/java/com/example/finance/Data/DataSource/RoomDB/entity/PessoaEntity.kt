package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "pessoa",
    foreignKeys = [
        ForeignKey(
            entity = CasaEntity::class,
            parentColumns = ["id"],
            childColumns = ["casa_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PessoaEntity(
    @PrimaryKey() val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "casa_id") val casaId: String,
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "cor") val cor: String,

    )