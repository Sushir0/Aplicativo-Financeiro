import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.example.finance.VariaveisDeAmbiente
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Data
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.converterDataMillisParaData
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl2.Movimentacao.adicionarMovimentacao
import com.example.finance.lvl2.Movimentacao.testeAdicionarMovimentacao
import com.example.finance.lvl3.componentes.listas.ListaDeMovimentacoes
import com.example.finance.lvl3.componentes.listas.NovaListaDeMembros
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.avisoLongo
import com.example.finance.lvl3.widgets.BuscaDeDatas
import com.example.finance.lvl3.widgets.DropdownCategoria
import com.example.finance.lvl3.widgets.DropdownMembro
import com.example.finance.ui.theme.FinanceTheme
import java.text.DecimalFormat


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioMovimentacao(
    onDismiss : ()-> Unit = {  },
    membroPreSelecionado: MovimentacaoHolder,
    onConfirm: ()-> Unit = {  },
    lockedMembro: Boolean = false
) {
    val paddingValue = 6.dp
    val context = LocalContext.current

    var assunto by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf<Categoria?>(null) }
    var data by remember { mutableStateOf(converterDataMillisParaData(System.currentTimeMillis())) }
    var membroSelecionado by remember{ mutableStateOf(membroPreSelecionado) }
    


    if(VariaveisDeAmbiente.debugMode){
        testeAdicionarMovimentacao(membroPreSelecionado)
        onConfirm()
        onDismiss()
        return
    }



    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(8.dp)){
            DropdownMembro(
                expandedInicial = false,
                membros = getMembros(),
                membroSelecionado = membroSelecionado,
                modifier = Modifier,
                lockedMembro = lockedMembro,
                onChoice = { membroSelecionado = it }
            )


        }

        OutlinedTextField(
            value = assunto,
            onValueChange = {assunto = it},
            label = {
                Text(
                    text = "Assunto",
                    style = MaterialTheme.typography.labelMedium) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValue
                )
        )
        OutlinedTextField(
            value = valor,
            onValueChange = { newValue ->
                if (validarValor(newValue)) {
                    valor = newValue
                }
            },

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
                .padding(paddingValue)
        )


        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            DropdownCategoria(
                categorias = Login.getCasaLogada().getCategorias(
                    afetaCasa = (membroSelecionado.isCasa),
                    afetaPessoa = (!membroSelecionado.isCasa)
                ),
                categoriaSelecionada = categoriaSelecionada,
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(
                        start = 4.dp,
                        top = 4.dp
                    ),
                onChoice = { categoriaSelecionada = it }
            )


            BuscaDeDatas(
                onConfirm = { dataSelecionada ->
                    data = dataSelecionada
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        end = 6.dp
                    )
            )
        }
        Button(
            onClick = { adicionar(
                assunto = assunto,
                valor = valor,
                data = data,
                categoria = categoriaSelecionada,
                membroSelecionado = membroSelecionado,
                onDismiss = onDismiss,
                context = context
            )
                      onConfirm()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Adicionar",
                style = MaterialTheme.typography.titleMedium)
        }
    }


}


private fun validarValor(valor: String): Boolean {
    return try {
        val parsedValue = valor.toDouble()
        val decimalFormat = DecimalFormat("#,##0.00")
        decimalFormat.format(parsedValue)
        true
    } catch (e: NumberFormatException) {
        false
    }
}

private fun adicionar(
    assunto: String,
    valor: String,
    data : Data,
    categoria: Categoria?,
    membroSelecionado: MovimentacaoHolder,
    onDismiss: () -> Unit,
    context: Context
){
    val erros = adicionarMovimentacao(
             assunto = assunto,
             valorStr = valor,
             data = data,
             categoria = categoria,
             movimentacaoHolder = membroSelecionado
         )


    if(erros.isEmpty()){
        onDismiss()
    }else{
        avisoDeErros(context = context, erros = erros)
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun datePrev() {
    FinanceTheme {
        testeCadastro()
        gerarCategoriasBasicas()
        FormularioMovimentacao(
            membroPreSelecionado = Login.getCasaLogada()
        )
    }
}