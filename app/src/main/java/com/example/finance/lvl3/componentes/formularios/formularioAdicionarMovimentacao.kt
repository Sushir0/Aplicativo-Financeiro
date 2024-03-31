import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Categoria
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl1.categorias
import com.example.finance.lvl1.converterDataMillisParaData
import com.example.finance.lvl1.gerarCategoriasBasicas
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.widgets.BuscaDeDatas
import com.example.finance.lvl3.widgets.BuscaDeDatasDialog
import com.example.finance.lvl3.widgets.DropdownCategoria
import com.example.finance.lvl3.widgets.DropdownMembro
import com.example.finance.ui.theme.FinanceTheme
import com.example.finance.ui.theme.backgroundDark


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioMovimentacao(

) {

    var assunto by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }

    var categoriaSelecionada = remember { mutableStateOf<Categoria?>(null) }
    var data by remember { mutableStateOf(converterDataMillisParaData(System.currentTimeMillis())) }
    var membroSelecionado = remember { mutableStateOf<Pessoa?>(null) }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropdownMembro(
            expandedInicial = false,
            membros = Login.getCasaLogada().moradores,
            membroSelecionado = membroSelecionado,
            modifier = Modifier.width(250.dp)
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            BuscaDeDatas(
                onConfirm = { dataSelecionada ->
                    data = dataSelecionada
                },
                modifier = Modifier.width(150.dp)
            )


            DropdownCategoria(
                categorias = categorias,
                categoriaSelecionada = categoriaSelecionada,
                modifier = Modifier.width(150.dp)
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