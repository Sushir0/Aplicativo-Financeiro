package com.example.finance.lvl3.componentes

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.ui.theme.contentResumo

@Composable
fun ResumoFinanceiroCardVertical(casa: Casa) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Card(shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(72.dp)
                    .weight(1f)
                    .padding(horizontal = 8.dp)

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(contentResumo),
                    contentAlignment = Alignment.Center

                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Gastos",
                            style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = casa.gastosTotais.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Card(shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(72.dp)
                        .weight(1f)
                        .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(contentResumo),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(3.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Recebimentos",
                            style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = casa.gastosTotais.toString(),
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Card(shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(72.dp)
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(contentResumo),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Valor de sobra",
                            style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = casa.gastosTotais.toString(),
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

        }
    }
}
@Composable
fun ResumoFinanceiroCardHorizontal(casa: Casa) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(72.dp)
                .weight(1f)
                .padding(horizontal = 8.dp)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contentResumo),
                contentAlignment = Alignment.Center

            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Gastos",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = casa.gastosTotais.toString(),
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(72.dp)
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contentResumo),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Recebimentos",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = casa.gastosTotais.toString(),
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(72.dp)
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contentResumo),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sobra",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = casa.gastosTotais.toString(),
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

    }
}

@Preview
@Composable
fun ResumoFinanceiroCardHorizontalPreview() {
    testeCadastro()
    ResumoFinanceiroCardHorizontal(Login.getCasaLogada())

}

@Preview
@Composable
fun ResumoFinanceiroCardVerticalPreview() {
    testeCadastro()
    ResumoFinanceiroCardVertical(casa = Login.getCasaLogada())

}

@Composable
fun isPortrait(): Boolean {
    val orientacao = LocalConfiguration.current.orientation
    return orientacao == Configuration.ORIENTATION_PORTRAIT
}