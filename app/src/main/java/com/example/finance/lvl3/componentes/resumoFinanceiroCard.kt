package com.example.finance.lvl3.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.utils.isPortrait
import com.example.finance.lvl3.utils.valorMonetario
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundGasto
import com.example.finance.ui.theme.backgroundLight
import com.example.finance.ui.theme.backgroundRecebimento
import com.example.finance.ui.theme.contentResumo


@Composable
fun NovoResumoFinanceiro(
    recebimentos: Double,
    gastos: Double,
    saldo: Double,
    onClickRecebimentos: ()->Unit = {  },
    onClickGastos: ()->Unit = {  }
    ) {
    Column {
        ItemOutlined(texto = "Recebimentos", valor = recebimentos) {  onClickRecebimentos()  }
        ItemOutlined(texto = "Gastos", valor = gastos) {  onClickGastos()  }
        ItemSaldo(valor = saldo)
    }
}

@Composable
fun ItemSaldo(valor: Double){
    var background = if(valor>=0){
        backgroundRecebimento
    }else{
        backgroundGasto
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        ElevatedCard (
            modifier = Modifier
            .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(background)
                    .fillMaxWidth(.7f),
                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Saldo", style = MaterialTheme.typography.titleLarge)
                    Text(text = valorMonetario(valor), style = MaterialTheme.typography.titleMedium)


                }
            }

        }
    }

}

@Composable
private fun ItemOutlined(texto:String, valor:Double, onClick:()-> Unit) {
    var background: Color
    background = if(isSystemInDarkTheme()){
        backgroundDark
    }else{
        backgroundLight
    }
    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedCard (modifier = Modifier.padding(start = 16.dp, end = 32.dp, top = 24.dp).clickable { onClick() }) {
            Box(modifier = Modifier.background(background)){
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically){
                    Column (Modifier.padding(8.dp)){
                        Text(text = texto, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                        Text(text = valorMonetario(valor), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                    }
                    Text(text = "Ver Mais ->", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }

    
}


@Preview
@Composable
fun NovoResumoPreview() {
    testeCadastro()
    NovoResumoFinanceiro(888.88, 888.88, 80.0)
}




