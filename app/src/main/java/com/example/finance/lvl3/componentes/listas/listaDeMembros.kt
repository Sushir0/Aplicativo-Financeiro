package com.example.finance.lvl3.componentes.listas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl1.Perfil
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.utils.avisoLongo

@Composable
private fun ItemSimples(residente: Pessoa) {
    ElevatedCard(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable(onClick = {

            }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Text(
            text = residente.nome,
            modifier = Modifier
                .padding(6.dp),
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun ItemDetalhado(residente: Pessoa) {
    ElevatedCard(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = 6.dp)) {
        Column (modifier = Modifier.padding(6.dp)){
            Text(text = residente.nome,
                modifier = Modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Valor recebido: "+residente.getRecebimentos(),
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Gasto total: "+residente.getRecebimentos(),
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Valor de sobra: "+residente.getRecebimentos(),
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}
*/

@Composable
fun NovaListaDeMembros(membros: List<MovimentacaoHolder>, membroSelecionado : MutableState<MovimentacaoHolder>, onClick: () -> Unit = {  }) {
    OutlinedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        /*LazyRow(){
            items(pessoas) { pessoa ->
                NovoItemLista(pessoa = pessoa)
            }
        }*/

        Row(modifier = Modifier.horizontalScroll(rememberScrollState())){
            membros.forEach(){ membro ->
                NovoItemLista(
                    nome = membro.nome,
                    perfil = membro.perfil,
                    onClick = {
                        if(membroSelecionado.value.equals(membro)){
                            membroSelecionado.value = membros.get(0)
                        }else{
                            membroSelecionado.value = membro
                        }
                        onClick()
                              },
                    isSelected = membroSelecionado.value == membro
                )
            }
        }

    }
}

@Composable
private fun NovoItemLista(
    nome : String,
    perfil: Perfil,
    onClick : ()->Unit,
    isSelected: Boolean
) {
    val colorBorder = if(isSystemInDarkTheme()) Color.White else Color.Black
    val border = if(isSelected) BorderStroke(2.dp, colorBorder) else BorderStroke(0.dp, colorBorder)
    ElevatedCard (modifier = Modifier
        .width(250.dp)
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clickable {
            onClick()
        }
        .border(
            border
        )


    ){
        Box(
            modifier = Modifier
                .background(perfil.corPerfil)
        ){
            Row (
                modifier = Modifier.
                padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = perfil.fotoURL)
                    , contentDescription = "Foto de $nome",
                    modifier = Modifier.size(96.dp))
                    Text(
                        text = nome,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
            }
        }

    }
}


@Preview
@Composable
fun ListaDeMembrosPreview() {
    testeCadastro()
    var pessoaSelecionada = remember { mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada()) }
    NovaListaDeMembros(membros = Login.getCasaLogada().moradores, pessoaSelecionada)

}