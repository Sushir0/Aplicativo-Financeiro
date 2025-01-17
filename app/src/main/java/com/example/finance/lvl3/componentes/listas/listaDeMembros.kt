package com.example.finance.lvl3.componentes.listas
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente.dampingRatioBouncy
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente.stiffness
import com.example.finance.a_Domain.model.MovimentacaoHolder.MovimentacaoHolder
import com.example.finance.a_Domain.model.Usuario.Perfil


@Composable
fun NovaListaDeMembros(
    membros: List<MovimentacaoHolder>,
    membroSelecionado : MovimentacaoHolder?,
    onClick: (MovimentacaoHolder) -> Unit = {  }
) {
    val context = LocalContext.current
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
                    onClick = { onClick(membro) },
                    isSelected = membroSelecionado == membro
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
    val tamanho : Double = if(isSelected) 1.2 else 1.0
    val tamanhoFotoAnimada by animateDpAsState(
        targetValue = (96 * tamanho).dp,
        animationSpec = spring(
            dampingRatio = dampingRatioBouncy,
            stiffness = stiffness
        )
    )

    Card (modifier = Modifier
        .width((250*tamanho).dp)
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .clickable {
            onClick()
        }
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = dampingRatioBouncy,
                stiffness = stiffness
            )
        )


    ){
        Box(
            modifier = Modifier
                .background(perfil.corPerfil)
                .fillMaxWidth()
        ){
            Row (
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = perfil.fotoURL)
                    , contentDescription = "Foto de $nome",
                    modifier = Modifier
                        .size(tamanhoFotoAnimada)

                )
                    Text(
                        text = nome,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(12.dp),
                        textAlign = TextAlign.Center
                    )
            }
        }

    }
}


@Preview
@Composable
fun ListaDeMembrosPreview() {
    /*
    LoginController().testeCadastro()
    var pessoaSelecionada by remember { mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada()) }
    NovaListaDeMembros(membros = Login.getCasaLogada().moradores, pessoaSelecionada)


     */
}