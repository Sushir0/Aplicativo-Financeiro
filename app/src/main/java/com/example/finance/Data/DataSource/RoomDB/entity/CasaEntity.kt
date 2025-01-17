package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "casa")
data class CasaEntity (
    @PrimaryKey() val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "cor") val cor: String,
    @ColumnInfo(name = "foto") val foto: String,
    )