package com.example.finance.lvl3.widgets.Escolher

import ListaDuplaMacroCategoria
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.EscolhaCategoriaViewModel
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.MovimentacaoHolder.Pessoa
import com.example.finance.lvl3.componentes.listas.Categoria.ListaDuplaCategoriaHorizontal
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.avisoLongo
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EscolherCategoria(
    movimentacaoHolder: MovimentacaoHolder,
    escolhaCategoriaviewModel: EscolhaCategoriaViewModel,
    onDismiss: () -> Unit,
    onConfirm: (categoria: Categoria)-> Unit,
) {
    LaunchedEffect(Unit){
        escolhaCategoriaviewModel.inicializar(movimentacaoHolder)
    }

    val mensagemAviso by escolhaCategoriaviewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        escolhaCategoriaviewModel.limparAviso()
    }


    val categorias by escolhaCategoriaviewModel.categorias
    val macroCategorias by escolhaCategoriaviewModel.macroCategorias

    val tipoEscolhido by escolhaCategoriaviewModel.tipoEscolhido
    val macroCategoriaEscolhida by escolhaCategoriaviewModel.macroCategoriaEscolhida
    val categoriaEscolhida by escolhaCategoriaviewModel.categoriaEscolhida

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(10.dp).fillMaxSize(),

    ) {
        OutlinedCard(
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Tipos",
                    style = MaterialTheme.typography.titleMedium
                )
                EscolherTipo(
                    tipoEscolhido = tipoEscolhido,
                    onEscolha = {
                        coroutineScope.launch {
                            escolhaCategoriaviewModel.setTipoEscolhido(it)
                        }
                    }
                )

                Divider(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    ),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Macro categorias",
                    style = MaterialTheme.typography.titleMedium
                )
                ListaDuplaMacroCategoria(
                    macroCategoriaEscolhida = macroCategoriaEscolhida,
                    macroCategorias = macroCategorias,
                    onMacroCategoriaClick = {
                        coroutineScope.launch {
                            escolhaCategoriaviewModel.setMacroCategoriaEscolhida(it)
                        }
                    }
                )
                OutlinedCard(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Categorias",
                            style = MaterialTheme.typography.titleMedium
                        )
                        ListaDuplaCategoriaHorizontal(
                            categorias = categorias,
                            onCategoriaClick = {
                                coroutineScope.launch {
                                    onConfirm(it)
                                    onDismiss()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun EscolhaCategoriaPreview() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        //RoomMock(context)
    }
    //val context = LocalContext.current
    val dataSources = RoomDataSources(context)
    val viewModel = EscolhaCategoriaViewModel(dataSources)
    Surface(
        contentColor = Color.Black,

    ) {
        EscolherCategoria(
            Pessoa("IDCasa", "Casa"),
            escolhaCategoriaviewModel = viewModel,
            onDismiss = {},
            onConfirm = { avisoLongo(context, "Categoria escolhida: ${it.nome}") }
        )
    }
}