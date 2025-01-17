package com.example.finance.lvl3.widgets.Escolher

import ListaDuplaMacroCategoria
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.EscolherSelecaoUsuarioViewModel
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl3.componentes.listas.Categoria.ListaDuplaCategoriaHorizontal
import com.example.finance.lvl3.utils.avisoDeErros
import kotlinx.coroutines.launch

@Composable
fun EscolhaSelecaoUsuario(
    movimentacaoHolder: MovimentacaoHolder,
    viewModel: EscolherSelecaoUsuarioViewModel,
    selecaoUsuarioInicial: SelecaoUsuario = SelecaoUsuario.tipoSelecionado(Tipo.TODOS),
    onSelecaoChange: (SelecaoUsuario) -> Unit = {  },
    onDismiss: () -> Unit = {  },
) {
    LaunchedEffect(Unit) {
        viewModel.inicializar(
            movimentacaoHolder,
            selecaoUsuarioInicial,
        )
    }


    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }


    val categorias by viewModel.categorias
    val macroCategorias by viewModel.macroCategorias

    val tipoEscolhido by viewModel.tipoEscolhido
    val macroCategoriaEscolhida by viewModel.macroCategoriaEscolhida
    val categoriaEscolhida by viewModel.categoriaEscolhida

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),

        ) {
        OutlinedCard(
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier//.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Tipos",
                    style = MaterialTheme.typography.titleMedium
                )
                EscolherTipo(
                    tipoEscolhido = tipoEscolhido,
                    onEscolha = {
                        coroutineScope.launch {
                            viewModel.setTipoEscolhido(it)
                            onSelecaoChange(viewModel.selecaoUsuarioEscolhido.value)
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
                            viewModel.setMacroCategoriaEscolhida(it)
                            onSelecaoChange(viewModel.selecaoUsuarioEscolhido.value)
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
                            categoriaEscolhida = categoriaEscolhida,
                            categorias = categorias,
                            onCategoriaClick = {
                                coroutineScope.launch {
                                    viewModel.setCategoriaEscolhida(it)
                                    onSelecaoChange(viewModel.selecaoUsuarioEscolhido.value)
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




}
