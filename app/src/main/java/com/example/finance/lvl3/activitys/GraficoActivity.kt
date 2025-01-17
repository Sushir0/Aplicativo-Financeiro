package com.example.finance.lvl3.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl3.telas.Graficos
import com.example.finance.ui.theme.FinanceTheme


class GraficoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movimentacaoHolder = intent.getSerializableExtra("movimentacaoHolder") as MovimentacaoHolder
        val selecaoUsuarioInicial = intent.getSerializableExtra("selecaoUsuarioInicial") as SelecaoUsuario
        val grafico = Graficos(movimentacaoHolder, selecaoUsuarioInicial)
        setContent {

            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    grafico.content()
                }
            }
        }
    }
}

fun abrirGrafico(context: Context, movimentacaoHolder: MovimentacaoHolder, selecaoUsuarioInicial: SelecaoUsuario){
    val intent = Intent(context, GraficoActivity::class.java)
    intent.putExtra("movimentacaoHolder", movimentacaoHolder)
    intent.putExtra("selecaoUsuarioInicial", selecaoUsuarioInicial)
    context.startActivity(intent)
}