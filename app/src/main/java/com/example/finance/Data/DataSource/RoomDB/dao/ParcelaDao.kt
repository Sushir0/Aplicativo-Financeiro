package com.example.finance.Data.DataSource.RoomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finance.Data.DataSource.RoomDB.entity.ParcelaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.ParcelaEntityGet

@Dao
interface ParcelaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(parcela: ParcelaEntity)

    @Query("SELECT * FROM ParcelaEntityGet WHERE parcela_id = :id")
    suspend fun get(id: String): ParcelaEntityGet?

    @Query("SELECT * FROM ParcelaEntityGet WHERE movimentacao_id = :movimentacaoId")
    suspend fun getParcelasPorMovimentacao(movimentacaoId: String): List<ParcelaEntityGet>?

    @Query(
        """
    SELECT p.*
    FROM parcelaentityget p
    INNER JOIN movimentacao m ON p.movimentacao_id = m.id
    INNER JOIN categoria c ON m.categoria_id = c.id
    INNER JOIN macro_categoria mc ON c.macro_categoria_id = mc.id
    WHERE  
        (
            -- Filtra movimentacaoHolder
            (m.casa_id = :holderId AND m.movimentacao_holder_type = 'CASA') OR
            (m.pessoa_id = :holderId AND m.movimentacao_holder_type = 'PESSOA')
        )
            -- Filtra Periodo
        AND (:dataInicial IS NULL OR p.parcela_data >= :dataInicial)
        AND (:dataFinal IS NULL OR p.parcela_data <= :dataFinal)
            -- Filtra SelecaoUsuario    
        AND ((:tipo = "TODOS" OR :tipo IS NULL) OR (mc.is_gasto = 1 AND :tipo = "GASTO") OR (mc.is_gasto = 0 AND :tipo = "RECEBIMENTO"))
        AND (:categoriaId IS NULL OR c.id = :categoriaId)
        AND (:macroCategoriaId IS NULL OR mc.id = :macroCategoriaId)
            -- Filtra FormaPagamento
        AND (:formaPagamento IS NULL OR (:formaPagamento = 0 AND m.forma_pagamento = "DEBITO") OR (:formaPagamento = 1 AND m.forma_pagamento = "CREDITO") OR (:formaPagamento = 2 AND m.forma_pagamento = "PARCELADO"))
"""
    )
    suspend fun getParcelas(
        holderId: String,
        dataInicial: Long?,
        dataFinal: Long?,
        tipo: String?,
        categoriaId: String?,
        macroCategoriaId: String?,
        formaPagamento: Int?
    ): List<ParcelaEntityGet?>

    @Query(
        """
    SELECT p.valor
    FROM parcela p
    INNER JOIN movimentacao m ON p.movimentacao_id = m.id
    INNER JOIN categoria c ON m.categoria_id = c.id
    INNER JOIN macro_categoria mc ON c.macro_categoria_id = mc.id
    WHERE  
        (
            -- Filtra movimentacaoHolder
            (m.casa_id = :holderId AND m.movimentacao_holder_type = 'CASA') OR
            (m.pessoa_id = :holderId AND m.movimentacao_holder_type = 'PESSOA')
        )
            -- Filtra Periodo
        AND (:dataInicial IS NULL OR p.data >= :dataInicial)
        AND (:dataFinal IS NULL OR p.data <= :dataFinal)
            -- Filtra SelecaoUsuario    
        AND ((:tipo = "TODOS" OR :tipo IS NULL) OR (mc.is_gasto = 1 AND :tipo = "GASTO") OR (mc.is_gasto = 0 AND :tipo = "RECEBIMENTO"))
        AND (:categoriaId IS NULL OR c.id = :categoriaId)
        AND (:macroCategoriaId IS NULL OR mc.id = :macroCategoriaId)
            -- Filtra FormaPagamento
        AND (:formaPagamento IS NULL OR (:formaPagamento = 0 AND m.forma_pagamento = "DEBITO") OR (:formaPagamento = 1 AND m.forma_pagamento = "CREDITO") OR (:formaPagamento = 2 AND m.forma_pagamento = "PARCELADO"))
"""
    )
    suspend fun getValores(
        holderId: String,
        dataInicial: Long?,
        dataFinal: Long?,
        tipo: String?,
        categoriaId: String?,
        macroCategoriaId: String?,
        formaPagamento: Int?
    ): List<Double?>

}