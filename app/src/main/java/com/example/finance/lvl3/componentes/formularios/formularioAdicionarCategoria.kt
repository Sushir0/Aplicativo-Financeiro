package com.example.finance.lvl3.componentes.formularios

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.Memory.CacheDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.AdicionarCategoriaViewModel
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoriaMock
import com.example.finance.b_useCase.Categoria.CategoriaServiceMock
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.avisoLongo
import kotlinx.coroutines.launch

@Composable
fun FormularioAdicionarCategoria(
    onDismiss: () -> Unit = { },
    modifier: Modifier = Modifier,
    macroCategoriaId: String,
    viewModel: AdicionarCategoriaViewModel
) {
    val nome by viewModel.nome

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Categoria",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(8.dp)
        )
        OutlinedTextField(
            value = nome,
            onValueChange = { viewModel.onNomeChange(it) },
            label = {
                Text(
                    text = "Nome",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go,
            ),
            keyboardActions = KeyboardActions(onGo = {
                coroutineScope.launch {
                    viewModel.onSave(macroCategoriaId, onDismiss)
                }
            }),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable{ avisoLongo(context, "Indisponível no momento") },
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(text = "Personalizar previsões")
            Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = "Abrir opções")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.onSave(macroCategoriaId, onDismiss)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Salvar",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
private fun FormularioAdicionarCategoriaPreview() {
    FormularioAdicionarCategoria(
        macroCategoriaId = MacroCategoriaMock().id,
        viewModel = AdicionarCategoriaViewModel(
            dataSources = CacheDataSources
        )

    )
}