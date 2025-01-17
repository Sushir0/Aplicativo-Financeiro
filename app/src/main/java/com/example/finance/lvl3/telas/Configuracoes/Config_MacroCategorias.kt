package com.example.finance.lvl3.telas.Configuracoes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.Memory.CacheDataSources
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias.AdicionarMacroCategoriaViewModel
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias.ConfiguracaoMacroCategoriasViewModel
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.lvl3.componentes.formularios.FormularioAdicionarMacroCategoria
import com.example.finance.lvl3.componentes.listas.MacroCategoria.ListaDeMacroCategoriasVertical
import com.example.finance.lvl3.layouts.Footer
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.BottomSheet
import com.example.finance.lvl3.widgets.FiltroMacroCategoria
import com.example.finance.ui.theme.backgroundDark
import com.example.finance.ui.theme.backgroundLight
import kotlinx.coroutines.launch

@Composable
fun Config_MacroCategorias(casaId: String, viewModel: ConfiguracaoMacroCategoriasViewModel) {
    LaunchedEffect(casaId) {
        viewModel.inicializar(casaId)
    }
    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparMensagem()
    }

    val titulo by viewModel.titulo
    val macroCategorias by viewModel.macroCategoriasStateFlow.collectAsState()
    val bottomSheetAdicionar by viewModel.bottomSheetAdicionar
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val background: Color = if (isSystemInDarkTheme()) {
        backgroundDark
    } else {
        backgroundLight
    }

    Column(
        modifier = Modifier
            .background(background)
            .fillMaxSize(),

        ){
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        FiltroMacroCategoria(
            filtros = viewModel.filtros,
        ){
            coroutineScope.launch {
                viewModel.setFiltro(it)
            }

        }


        ListaDeMacroCategoriasVertical(
            macroCategorias = macroCategorias,
            onClick = {
                viewModel.selecionarMacroCategoria(context, it)
            }
        )

    }

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
        val adicionarMacroCategoriaViewModel = AdicionarMacroCategoriaViewModel(
            dataSource = viewModel.dataSource
        )
        FormularioAdicionarMacroCategoria(
            onDismiss = {
                coroutineScope.launch {
                    viewModel.fecharBottomSheetAdicionar()
                }
            },
            viewModel = adicionarMacroCategoriaViewModel,
            idCasa = casaId
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Tela_Categorias_Preview() {
    val idCasa = VariaveisDeAmbiente.casaId
    val context = LocalContext.current
    val roomDataSources = RoomDataSources(context)



    val dataSource = CacheDataSources
    val viewModel = ConfiguracaoMacroCategoriasViewModel( roomDataSources )
    Config_MacroCategorias(idCasa, viewModel)
}