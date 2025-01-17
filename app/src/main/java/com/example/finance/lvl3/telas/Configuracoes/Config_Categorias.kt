package com.example.finance.lvl3.telas.Configuracoes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.AdicionarCategoriaViewModel
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.ConfiguracaoCategoriasViewModel
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.DetalhesCategoriaViewModel
import com.example.finance.lvl3.componentes.DetalhesCategoria
import com.example.finance.lvl3.componentes.formularios.FormularioAdicionarCategoria
import com.example.finance.lvl3.componentes.listas.Categoria.ListaDeCategorias
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.ShowAtributosMacroCategoria
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Config_Categorias(macroCategoriaId: String, viewModel: ConfiguracaoCategoriasViewModel) {

    LaunchedEffect(macroCategoriaId) {
        viewModel.setMacroCategoria(macroCategoriaId)

    }
    if (viewModel.macroCategoria.value != null) {
        println("idCasa = "+viewModel.macroCategoria.value!!.idCasa)
    }

    val errorMessages by viewModel.errorMessages.collectAsState()
    if (errorMessages.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, errorMessages)
        viewModel.limparErros()
    }

    val categorias by viewModel.categoriasStateFlow.collectAsState()
    val macroCategoria by viewModel.macroCategoria
    val bottomSheetAdicionar by viewModel.bottomSheetAdicionar
    val bottomSheetDetalhes by viewModel.bottomSheetDetalhes
    val categoriaSelecionada by viewModel.categoriaSelecionada




    var background: Color = if (isSystemInDarkTheme()) {
        backgroundDark
    } else {
        backgroundLight
    }
    Column(
        modifier = Modifier
            .background(background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

        ){
        Text(
            text = macroCategoria?.nome ?: "Carregando",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        )


        ShowAtributosMacroCategoria(macroCategoria = macroCategoria)
        ListaDeCategorias(
            categorias = categorias,
            onClick = {
                viewModel.selecionarCategoria(it)
            }
        )

    }
    if (macroCategoria != null) {
        val coroutineScope = rememberCoroutineScope()
        Footer(
            openBottomSheetClick = { viewModel.abrirBottomSheetAdicionar() }
        )
        BottomSheet(
            isSheetOpen = bottomSheetAdicionar,
            onDismiss = {
                coroutineScope.launch {
                    viewModel.fecharBottomSheetAdicionar()
                }
            }
        ) {
            val adicionarCategoriaViewModel = remember {
                AdicionarCategoriaViewModel(viewModel.dataSource)
            }
            FormularioAdicionarCategoria(
                onDismiss = {
                    coroutineScope.launch {
                        viewModel.fecharBottomSheetAdicionar()
                    }
                            },
                macroCategoriaId = viewModel.macroCategoria.value!!.id,
                viewModel = adicionarCategoriaViewModel
            )
        }
        BottomSheet(
            isSheetOpen = bottomSheetDetalhes,
            onDismiss = {
                coroutineScope.launch {
                    viewModel.fecharBottomSheetDetalhes()
                }
            }
        ) {
            val detalhesCategoriaViewModel = remember {
                DetalhesCategoriaViewModel(viewModel.dataSource)
            }
            DetalhesCategoria(categoriaSelecionada!!, onDismiss = {
                coroutineScope.launch {
                    viewModel.fecharBottomSheetDetalhes()
                }
            },
                viewModel = detalhesCategoriaViewModel)
        }
    }
}

@Preview
@Composable
private fun configuracaoCategoriasPrev() {
    val context = LocalContext.current
    val roomDataSources = RoomDataSources(context)

    val idCasa = "8d06ab49-9e66-48d3-8edb-2702b1b31c62"

    var macroCategoriaId by remember {
        mutableStateOf("a5e3a7be-e866-46cc-b587-4fe277444411")
    }
    val viewModel = ConfiguracaoCategoriasViewModel(
        dataSource = roomDataSources
    )
    Config_Categorias(macroCategoriaId, viewModel)

}