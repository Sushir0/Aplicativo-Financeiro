package com.example.finance.a_Domain.VariaveisDeAmbiente

import androidx.compose.animation.core.Spring
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Data.DataSource.TipoBancoDeDados

object VariaveisDeAmbiente{
    var  debugMode: Boolean = true
    val dampingRatioBouncy = Spring.DampingRatioLowBouncy
    val stiffness = Spring.StiffnessVeryLow
    var tipoBancoDeDados = TipoBancoDeDados.Room
    var casaId: String = "d3846734-8f4e-4d74-8b78-5ccf6dcb7efe"
    var betaTeste = BetaTeste
}