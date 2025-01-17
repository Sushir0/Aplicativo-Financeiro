package com.example.finance.lvl3.componentes.formularios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Data.DataSource.Memory.CacheDataSources
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias.AdicionarMacroCategoriaViewModel
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaServiceMock
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.SetaMovimentacao
import com.example.finance.lvl3.widgets.buttons.ButtonAlternar_C_Icone
import kotlinx.coroutines.launch

@Composable
fun FormularioAdicionarMacroCategoria(
    idCasa: String,
    onDismiss: () -> Unit = { },
    viewModel: AdicionarMacroCategoriaViewModel
) {
    val nome by viewModel.nome
    val isGasto by viewModel.isGasto
    val afetaPessoa by viewModel.afetaPessoa
    val afetaCasa by viewModel.afetaCasa
    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Adicionar macro categoria",
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
                    viewModel.onSave(
                        idCasa = idCasa,
                        onDismiss = onDismiss
                    )
                }
            }),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        )
        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            ButtonAlternar_C_Icone(
                texto = "Afeta pessoa",
                isTrue = afetaPessoa,
                onClick = { viewModel.toggleAfetaPessoa() },
                modifier = Modifier.weight(1f)
            ){
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = if (afetaPessoa) "Afeta pessoa" else "Não afeta pessoa",
                )
            }
            ButtonAlternar_C_Icone(
                texto = if (isGasto) "Gasto" else "Recebimento",
                isTrue = !isGasto,
                onClick = { viewModel.toggleIsGasto() },
                modifier = Modifier.weight(1f)
            ){
                SetaMovimentacao(isGasto = isGasto)
            }
            ButtonAlternar_C_Icone(
                texto = "Afeta casa",
                isTrue = afetaCasa,
                onClick = { viewModel.toggleAfetaCasa() },
                modifier = Modifier.weight(1f)
            ){
                Icon(
                    Icons.Outlined.Home,
                    contentDescription = if (afetaCasa) "Afeta casa" else "Não afeta casa"
                )
            }


        }
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.onSave(
                        idCasa = idCasa,
                        onDismiss = onDismiss
                    )
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
private fun FormularioMacroCategoriaPreview() {
    FormularioAdicionarMacroCategoria(
        idCasa = "1",
        viewModel = AdicionarMacroCategoriaViewModel(
            CacheDataSources
        )
    )
}