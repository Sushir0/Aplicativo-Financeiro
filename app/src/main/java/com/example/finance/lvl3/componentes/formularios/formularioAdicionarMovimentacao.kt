import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Presentation.VM.Movimentacao.AdicionarMovimentacaoViewModel
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.avisoLongo
import com.example.finance.lvl3.widgets.BuscaDeDatas
import com.example.finance.lvl3.widgets.Escolher.EscolhaSelecaoUsuarioNew
import com.example.finance.lvl3.widgets.dropdown.DropdownMembro
import com.example.finance.ui.theme.FinanceTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioMovimentacao(
    onDismiss : ()-> Unit = {  },
    membroPreSelecionado: MovimentacaoHolder?,
    viewModel: AdicionarMovimentacaoViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.inicializar(membroPreSelecionado)
    }

    val paddingValue = 8.dp
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val mensagemAviso by viewModel.mensagemAviso.collectAsState()
    if (mensagemAviso.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, mensagemAviso)
        viewModel.limparAviso()
    }

    val descricao by viewModel.descricao
    val valor by viewModel.valor
    val data by viewModel.data
    var membroSelecionado by viewModel.membroSelecionado
    val categoria by viewModel.categoriaEscolhida

    var membros by viewModel.membros

    val textoBotaoAdicionar by viewModel.textoBotaoAdicionar
    val textoBotaoCategoria by viewModel.textoBotaoCategoria

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Adicionar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Box(modifier = Modifier.padding(8.dp)){
            DropdownMembro(
                expandedInicial = false,
                membros = membros,
                membroSelecionado = membroSelecionado,
                modifier = Modifier,
                onChoice = {
                    viewModel.setMembro(it)
                }
            )
        }

        OutlinedTextField(
            value = descricao,
            onValueChange = { viewModel.setDescricao(it) },
            label = {
                Text(
                    text = "Descrição",
                    style = MaterialTheme.typography.labelMedium) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValue
                ),
        )
        OutlinedTextField(
            value = valor,
            onValueChange = { viewModel.setValor(it) },

            label = {
                Text(
                    text = "Valor",
                    style = MaterialTheme.typography.labelMedium) },

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValue),
        )


        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(start = 4.dp, top = 4.dp),
                onClick = {
                    viewModel.abrirDialogoDeCategoria()
                }
            ) {
                Text(
                    text = textoBotaoCategoria
                )
            }
            BuscaDeDatas(
                onConfirm = { dataSelecionada ->
                    viewModel.setData(dataSelecionada)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        end = 6.dp
                    ),
                dataInicial = data,
            )
        }

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.onSave()
                        onDismiss()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = textoBotaoAdicionar,
                    style = MaterialTheme.typography.titleMedium
                )
            }
    }
    if(viewModel.showDialogDeCategoria){
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { viewModel.fecharDialogoDeCategoria() },
            title = { Text(text = "Escolha uma categoria") },
            text = {
                EscolhaSelecaoUsuarioNew(
                    isCasa = membroSelecionado!!.isCasa,
                    macroCategorias = viewModel.macroCategorias.value,
                    categorias = viewModel.categorias.value,
                    categoriaInicial = categoria,
                    needConfirmation = true,
                    onlyCategoria = true,
                    onDismiss = { viewModel.fecharDialogoDeCategoria() },
                    onConfirm = {
                        avisoLongo(context, "Categoria escolhida: ${it.Nome}")
                        if (it is SelecaoUsuario.categoriaSelecionada) {
                            viewModel.setCategoria(categoria = it.categoria)
                        }

                    }
                )
//                EscolhaDeCategoria2(
//                    isCasa = membroSelecionado!!.isCasa,
//                    macroCategorias = viewModel.macroCategorias.value,
//                    categorias = viewModel.categorias.value,
//                    categoriaInicial = categoria,
//                    needConfirmation = true,
//                    onDismiss = { viewModel.fecharDialogoDeCategoria() },
//                    onConfirm = {
//                        avisoLongo(context, "Categoria escolhida: ${it.nome}")
//                        viewModel.setCategoria(it)
//                    }
//                )

            },
            confirmButton = {

            }
        )
        
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FormularioMovimentacaoPreview() {

    FinanceTheme {
        //FormularioMovimentacao()
    }
}
