import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl1.categorias
import com.example.finance.lvl1.converterDataMillisParaData
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl1.getCategorias
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.widgets.BuscaDeDatas
import com.example.finance.lvl3.widgets.DropdownCategoria
import com.example.finance.lvl3.widgets.DropdownMembro
import com.example.finance.ui.theme.FinanceTheme


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioMovimentacao(

) {
    val paddingValue = 6.dp
    var assunto by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }

    var categoriaSelecionada = remember { mutableStateOf<Categoria?>(null) }
    var data by remember { mutableStateOf(converterDataMillisParaData(System.currentTimeMillis())) }
    var membroSelecionado = remember { mutableStateOf<Pessoa?>(null) }




    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(8.dp)){
            DropdownMembro(
                expandedInicial = false,
                membros = Login.getCasaLogada().moradores,
                membroSelecionado = membroSelecionado,
                modifier = Modifier
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
                .padding(paddingValue
                )
        )
        OutlinedTextField(
            value = valor,
            onValueChange = {valor = it},
            label = {
                Text(
                    text = "Valor",
                    style = MaterialTheme.typography.labelMedium) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
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
                categorias = getCategorias(
                    afetaCasa = (membroSelecionado.value==null),
                    afetaPessoa = (membroSelecionado.value!=null)
                ),
                categoriaSelecionada = categoriaSelecionada,
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(
                        start = 4.dp,
                        top = 4.dp
                        )

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
    }


}




@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun datePrev() {
    FinanceTheme {
        testeCadastro()
        gerarCategoriasBasicas()
        FormularioMovimentacao()
    }



}