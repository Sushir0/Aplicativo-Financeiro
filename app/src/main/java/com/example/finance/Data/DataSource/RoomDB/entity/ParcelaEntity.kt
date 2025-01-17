package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.Dados.dataFromTimeStamp
import java.util.UUID

@Entity(
    tableName = "parcela",
    foreignKeys = [
        ForeignKey(
            entity = MovimentacaoEntity::class,
            parentColumns = ["id"],
            childColumns = ["movimentacao_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["movimentacao_id"]), Index(value = ["data"])]
)
data class ParcelaEntity (
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "data") val data: Long,
    @ColumnInfo(name = "movimentacao_id") val movimentacao_id: String,
)

@DatabaseView(
    """
        SELECT
            parcela.id AS parcela_id,
            parcela.descricao AS parcela_descricao,
            parcela.valor AS parcela_valor,
            parcela.data AS parcela_data,
            parcela.movimentacao_id AS movimentacao_id,
            macroCategoria.is_gasto AS parcela_isGasto
        FROM
            parcela AS parcela
        JOIN movimentacao AS movimentacao ON movimentacao.id = parcela.movimentacao_id
        JOIN categoria AS categoria ON categoria.id = movimentacao.categoria_id
        JOIN macro_categoria AS macroCategoria ON macroCategoria.id = categoria.macro_categoria_id
    """
)
data class ParcelaEntityGet(
    @ColumnInfo(name = "parcela_id") val id: String,
    @ColumnInfo(name = "parcela_descricao") val descricao: String,
    @ColumnInfo(name = "parcela_valor") val valor: Double,
    @ColumnInfo(name = "movimentacao_id") val movimentacao_id: String,
    @ColumnInfo(name = "parcela_data") val data: Long,
    @ColumnInfo(name = "parcela_isGasto") val isGasto: Boolean,
){
    fun toParcela(): Parcela {
        return Parcela(
            id = id,
            descricao = descricao,
            valor = valor,
            movimentacaoid = movimentacao_id,
            data = dataFromTimeStamp(data),
            isGasto = isGasto
        )
    }
}