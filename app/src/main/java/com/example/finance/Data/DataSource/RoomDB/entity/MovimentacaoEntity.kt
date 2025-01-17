package com.example.finance.Data.DataSource.RoomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Dados.Data
import com.example.finance.a_Domain.model.Dados.Movimentacao
import com.example.finance.a_Domain.model.Dados.dataFromTimeStamp
import java.util.UUID

@Entity(
    tableName = "movimentacao",
    foreignKeys = [
        ForeignKey(
            entity = CasaEntity::class,
            parentColumns = ["id"],
            childColumns = ["casa_id"],
            onDelete = ForeignKey.CASCADE
            ),
        ForeignKey(
            entity = PessoaEntity::class,
            parentColumns = ["id"],
            childColumns = ["pessoa_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoria_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["movimentacao_holder_type"]), Index(value = ["casa_id"]), Index(value = ["pessoa_id"])]
)
data class MovimentacaoEntity (
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "data") val data: Long,
    @ColumnInfo(name = "forma_pagamento") val formaPagamento: String,
    @ColumnInfo(name = "categoria_id") val categoriaId: String,
    @ColumnInfo(name = "movimentacao_holder_type") val movimentacaoHolderType: String, // Discriminador
    @ColumnInfo(name = "casa_id") val casaId: String? = null, // Relacionamento com Casa
    @ColumnInfo(name = "pessoa_id") val pessoaId: String? = null // Relacionamento com Pessoa
)

data class MovimentacaoComCategoriaEntity(
    @Embedded val movimentacao: MovimentacaoEntity,
    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "id"
    )
    val categoria: CategoriaComMacroCategoriaEntity
){
    override fun toString(): String {
        return "MovimentacaoComCategoriaEntity(movimentacao=$movimentacao, categoria=$categoria)"
    }

}