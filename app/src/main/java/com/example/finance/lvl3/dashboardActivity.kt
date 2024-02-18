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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.componentes.isPortrait
import com.example.finance.lvl3.componentes.listaDeMembros
import com.example.finance.lvl3.componentes.resumoFinanceiroCardLado
import com.example.finance.lvl3.componentes.resumoFinanceiroCardPe
import com.example.finance.lvl3.mostradores.showCasa
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import com.example.finance.ui.theme.contentBackground

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
                    dashboard()
                }
            }
        }
    }
}


@Composable
fun dashboard() {
    var background: Color
    if(isSystemInDarkTheme()){
        background = backgroundDark
    }else{
        background = backgroundLight
    }
    var textColor = MaterialTheme.colorScheme.onBackground
    val casa = Login.getCasaLogada()
    Column (modifier = Modifier
        .fillMaxSize()
        .background(background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ){
        Box(modifier = Modifier,
            contentAlignment = Alignment.Center){
            Text(
                text = casa.nome,
                color = textColor,
                style = MaterialTheme.typography.displaySmall
                )
        }

        if(isPortrait()){
            resumoFinanceiroCardPe(casa = Login.getCasaLogada())
        }else{
            resumoFinanceiroCardLado(casa = Login.getCasaLogada())
        }
        Box(modifier = Modifier
            .fillMaxWidth()){
            listaDeMembros(casa = casa)
        }
        Box (modifier = Modifier
            .weight(6f)
            .padding(8.dp)){

        }





    }


}

@Preview
@Composable
fun dashboardPreview() {
    testeCadastro()
    dashboard()
    
}

fun abrirDashboard(mContext: Context){
    val intent = Intent(mContext, dashboardActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    ActivityCompat.startActivity(mContext, intent, null)
}