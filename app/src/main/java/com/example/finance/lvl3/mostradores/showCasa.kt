package com.example.finance.lvl3.mostradores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Movimentacao
import com.example.finance.lvl1.Pessoa

@Composable
fun showCasa(casa : Casa) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        casa.showCasa()
        Text(
            text = casa.nome,
            fontSize = 32.sp,
            modifier = Modifier.padding(12.dp)
        )

        Text(text = "Gastos:")
        listageMovimentacaoHorizontal(movimentacoes = casa.gastos)
        listagePessoasHorizontal(pessoas = casa.moradores)



    }

}

@Preview
@Composable
fun showCasaPreview() {
    val casinha = Casa("nome da casa")
    casinha.showCasa()


    casinha.addGasto(Movimentacao("Aluguel", Movimentacao.Tipo.gastoCasa, "01/01/2022", 1000.0))
    casinha.addGasto(Movimentacao("Supermercado", Movimentacao.Tipo.gastoCasa, "02/01/2022", 150.0))
    casinha.addGasto(Movimentacao("Energia", Movimentacao.Tipo.gastoCasa, "05/01/2022", 80.0))
    casinha.addGasto(Movimentacao("Internet", Movimentacao.Tipo.gastoCasa, "10/01/2022", 50.0))
    casinha.addGasto(Movimentacao("Salário", Movimentacao.Tipo.gastoCasa, "15/01/2022", 2500.0))
    casinha.addGasto(Movimentacao("Água", Movimentacao.Tipo.gastoCasa, "20/01/2022", 40.0))
    casinha.addGasto(Movimentacao("Gasolina", Movimentacao.Tipo.gastoCasa, "25/01/2022", 70.0))
    casinha.addGasto(Movimentacao("Lazer", Movimentacao.Tipo.gastoCasa, "02/02/2022", 200.0))
    casinha.addGasto(Movimentacao("Reembolso", Movimentacao.Tipo.gastoCasa, "10/02/2022", 300.0))
    casinha.addGasto(Movimentacao("Telefone", Movimentacao.Tipo.gastoCasa, "15/02/2022", 60.0))
    val pessoa1 = Pessoa("nome pessoa teste", casinha)
    showCasa(casinha)
}

