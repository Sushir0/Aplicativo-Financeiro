package com.example.finance.lvl3.telas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.layouts.Header
import com.example.finance.lvl3.componentes.NovoResumoFinanceiro
import com.example.finance.lvl3.componentes.ResumoFinanceiroCardCasa
import com.example.finance.lvl3.componentes.listas.ListaDeMembros
import com.example.finance.lvl3.componentes.listas.ListaDeMovimentacoes
import com.example.finance.lvl3.componentes.listas.NovaListaDeMembros
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
                    NewDashboard()
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

    ){
        Text(
            text = casa.nome,
            color = textColor,
            style = MaterialTheme.typography.displaySmall
            )


        ResumoFinanceiroCardCasa(casa = casa)
        Box(modifier = Modifier
            .padding(vertical = 16.dp)){
            ListaDeMembros(casa = casa)
        }
        Box (modifier = Modifier
            .padding(vertical = 16.dp)){
            ListaDeMovimentacoes(movimentacoes = casa.gastos)
        }

    }


}

@Composable
fun NewDashboard() {
    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    val casa = Login.getCasaLogada()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(casa = casa)
        Column (modifier = Modifier
            .verticalScroll(rememberScrollState()),
        ){
            NovoResumoFinanceiro(casa.gastosTotais, casa.gastosTotais,casa.gastosTotais)
            Box(modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp)
            ) {
                Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
            }
            NovaListaDeMembros(pessoas = casa.moradores)
            Spacer(modifier = Modifier.height(64.dp))

        }
    }
    Footer()

}







@Preview
@Composable
fun DashboardPreview() {
    testeCadastro()
    val gasto = Movimentacao("assunto", Data(15,10,2024), 3596.5)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    Login.getCasaLogada().addGasto(gasto)
    NewDashboard()

    
}

fun abrirDashboard(mContext: Context){
    val intent = Intent(mContext, dashboardActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    ActivityCompat.startActivity(mContext, intent, null)
}