package com.example.finance.lvl3

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.componentes.CadastroForm
import com.example.finance.lvl3.componentes.LoginForm
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import com.example.finance.ui.theme.contentBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme() {
                TelaInicialDeLogin()
            }
        }
    }
}


@Composable
fun TelaInicialDeLogin() {
    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    var textColor = MaterialTheme.colorScheme.onBackground


    var exibirLoginForm by remember { mutableStateOf(true) }
    var pesoCard = 3f
    if(exibirLoginForm){
        pesoCard = 2f
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Planeja Dinheiro",
                color = textColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall

            )
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .weight(pesoCard),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),

            ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(contentBackground)
            ){
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center
                    
                ){
                    if(exibirLoginForm){
                        LoginForm({ exibirLoginForm = !exibirLoginForm }, context = LocalContext.current)
                    }else{
                        CadastroForm({ exibirLoginForm = !exibirLoginForm }, context = LocalContext.current)
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    FinanceTheme() {
        testeCadastro()
        val gasto = Movimentacao("assunto", Movimentacao.Tipo.gastoPessoal, "15/10/2024", 3596.5)
        Login.getCasaLogada().addGasto(gasto)
        Login.getCasaLogada().addGasto(gasto)
        TelaInicialDeLogin()
    }

}




