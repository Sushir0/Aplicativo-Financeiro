package com.example.finance.lvl3

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import com.example.finance.lvl3.componentes.cadastroForm
import com.example.finance.lvl3.componentes.loginForm
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import com.example.finance.ui.theme.contentBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme() {
                telaInicialDeLogin()
            }
        }
    }
}


@Composable
fun telaInicialDeLogin() {
    var background: Color
    if(isSystemInDarkTheme()){
        background = backgroundDark
    }else{
        background = backgroundLight
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
                        loginForm({ exibirLoginForm = !exibirLoginForm }, context = LocalContext.current)
                    }else{
                        cadastroForm({ exibirLoginForm = !exibirLoginForm }, context = LocalContext.current)
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun mainPreview(){
    FinanceTheme() {
        telaInicialDeLogin()
    }

}




