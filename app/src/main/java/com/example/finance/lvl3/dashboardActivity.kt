package com.example.finance.lvl3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.componentes.isPortrait
import com.example.finance.lvl3.listas.ListaDeMembros
import com.example.finance.lvl3.componentes.ResumoFinanceiroCardHorizontal
import com.example.finance.lvl3.componentes.ResumoFinanceiroCardVertical
import com.example.finance.lvl3.listas.ListaDeMovimentacoes
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight

class dashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Dashboard()
                }
            }
        }
    }
}


@Composable
fun Dashboard() {
    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    var textColor = MaterialTheme.colorScheme.onBackground
    val casa = Login.getCasaLogada()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ){
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ){
            Text(
                text = casa.nome,
                color = textColor,
                style = MaterialTheme.typography.displaySmall
                )
        }

        if(isPortrait()){
            ResumoFinanceiroCardVertical(casa = Login.getCasaLogada())
        }else{
            ResumoFinanceiroCardHorizontal(casa = Login.getCasaLogada())
        }
        Box(modifier = Modifier
            .fillMaxWidth()){
            ListaDeMembros(casa = casa)
        }
        Box (modifier = Modifier
            .weight(6f)
            .padding(8.dp)){
            ListaDeMovimentacoes(movimentacoes = casa.gastos)

        }





    }


}

@Preview
@Composable
fun DashboardPreview() {
    testeCadastro()
    val gasto = Movimentacao("assunto", Movimentacao.Tipo.gastoPessoal, "15/10/2024", 3596.5)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Dashboard()
    
}

fun abrirDashboard(mContext: Context){
    val intent = Intent(mContext, dashboardActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    ActivityCompat.startActivity(mContext, intent, null)
}