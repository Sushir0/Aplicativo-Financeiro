package com.example.finance.lvl3.mostradores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.lvl1.Movimentacao
import androidx.compose.material3.Card as Card

@Composable
fun listageMovimentacaoHorizontal(movimentacoes : ArrayList<Movimentacao>) {
    LazyRow(content = {

        items(movimentacoes) { movimentacao ->
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(8.dp),

            ) {
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = movimentacao.assunto,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "R$ ${movimentacao.valor}",
                        fontSize = 24.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = movimentacao.data,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

        }
    }, modifier = Modifier.height(180.dp))
}

@Preview
@Composable
fun listagemMovimentacaoHorizontalPreview() {
    val lista = ArrayList<Movimentacao>()
    lista.add(Movimentacao("Assunto urgente", Movimentacao.Tipo.recebimentoPessoal, "11:03:2024", 500.0))
    lista.add(Movimentacao("Assunto urgente2", Movimentacao.Tipo.gastoCasa, "13:03:2024", 600.0))
    listageMovimentacaoHorizontal(lista)
}