package com.example.finance.lvl3.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.DetalhesCategoriaViewModel
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.widgets.AlertPersonalizado
import kotlinx.coroutines.launch

@Composable
fun DetalhesCategoria(
    categoriaSelecionada: Categoria,
    viewModel: DetalhesCategoriaViewModel,
    onDismiss : ()-> Unit = {  },
) {
    LaunchedEffect(Unit){
        viewModel.inicializar(categoriaSelecionada.id)
    }

    val textoNome = viewModel.categoria?.nome ?: "Carregando"
    val quantidadeMovimentacoes = viewModel.quantidadeMovimentacoes
    val coroutineScope = rememberCoroutineScope()



    var alertDelete = viewModel.alertDelete
    var alertDesativacao = viewModel.alertDesativacao
    var alertAtivacao = viewModel.alertAtivacao
    var textoButtonAtivacao = viewModel.textoButtonAtivacao

    val errorMessages by viewModel.mensagemAviso.collectAsState()
    if (errorMessages.isNotEmpty()) {
        avisoDeErros(context = LocalContext.current, errorMessages)
        viewModel.limparAviso()
    }

    if(alertDelete){
        AlertPersonalizado(
            title = "Excluir categoria",
            message = "   Ao excluir esta categoria, você tem duas opções:\n"+
                    "   1°Mover movimentações para outra categoria: Você poderá selecionar uma nova categoria para onde todas as movimentações existentes serão transferidas.\n"+
                    "   2°Excluir todas as movimentações: Todas as movimentações associadas a esta categoria serão removidas permanentemente.\n"+
                    "   Essa ação não pode ser desfeita. Se preferir manter a categoria, considere desativá-la.",
            confirmButtonText = "Excluir",
            cancelButtonText = "Cancelar",
            onConfirm = {
                coroutineScope.launch {
                    viewModel.deletarCategoria(onDismiss)
                }
            },
            onCancel = {
                viewModel.fecharAlertDelete()
                onDismiss()
                       },
            tem3Botoes = true,
            terceiroBotaoTexto = "Mover",
            terceiroBotaoFuncao = {  }
        )
    }

    if(alertDesativacao){
        AlertPersonalizado(
            title = "Desativar categoria",
            message = "Você não poderá adicionar novas movimentações, mas as que já existem serão mantidas. A categoria pode ser ativada novamente quando quiser.",
            confirmButtonText = "Desativar",
            cancelButtonText = "Cancelar",
            onConfirm = {
                coroutineScope.launch {
                    viewModel.toggleAtivacaoCategoria(onDismiss)
                }
                        },
            onCancel = { viewModel.fecharAlertDesativacao() })
    }

    if(alertAtivacao){
        AlertPersonalizado(
            title = "Habilitar Categoria",
            message = "Você poderá voltar a adicionar movimentações normalmente.",
            confirmButtonText = "Habilitar",
            cancelButtonText = "Cancelar",
            onConfirm = {
                coroutineScope.launch {
                    viewModel.toggleAtivacaoCategoria(onDismiss)
                }
                        },
            onCancel = { viewModel.fecharAlertAtivacao() })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                text = textoNome,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displaySmall)
        }

        Text(text = "Quantidade de movimentações: "+quantidadeMovimentacoes.toString())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ){
            Button(
                onClick = {
                    viewModel.apertarBotaoAtivacao()
                          },
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(2.dp)
            ){
                Text(
                    text = textoButtonAtivacao,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Button(
                onClick = { viewModel.abrirAlertDelete() },
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Excluir",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }


    }
}

private fun desativarCategoria(categoria: Categoria, macroCategoria: MacroCategoria, onDismiss: ()-> Unit){
    /*
CategoriaController().desativarCategoria(macroCategoria, categoria)
    onDismiss()

     */
}

private fun ativarCategoria(categoria: Categoria, macroCategoria: MacroCategoria, onDismiss: ()-> Unit){
    /*
    CategoriaController().ativarCategoria(macroCategoria, categoria)
    onDismiss()

     */
}

@Preview
@Composable
private fun DetalhesCategoriaPreview() {
    /*
    LoginController().testeCadastro()
    val categoriaDebbug = CategoriaDebbug()
    CategoriaDebbug().gerarCategoriasBasicas()
    DetalhesCategoria(categoriaDebbug.getCategoriaAleatoria())

     */
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DetalhesCategoriaBottomSheetPreview() {
    /*
    LoginController().testeCadastro()
    val categoriaDebbug = CategoriaDebbug()
    CategoriaDebbug().gerarCategoriasBasicas()
    BottomSheet(isSheetOpen = true, onDismiss = { /*TODO*/ }) {
        DetalhesCategoria(categoriaDebbug.getCategoriaAleatoria())
    }

     */
}