package com.example.finance.lvl3.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.MovimentacaoHolder
import com.example.finance.lvl2.Getters.getMembros
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.ui.theme.FinanceTheme


@Composable
fun DropdownMembro(
    expandedInicial : Boolean = false,
    membros : List<MovimentacaoHolder>,
    modifier : Modifier = Modifier,
    membroSelecionado : MovimentacaoHolder,
    lockedMembro : Boolean = false,
    onChoice: (MovimentacaoHolder)->Unit = {  }
) {
    var expandedMenu = remember { mutableStateOf<Boolean>(expandedInicial) }

    Column(
        modifier = modifier,
    ) {
        OutlinedButton(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            onClick = { expandedMenu.value = true },
            enabled = !lockedMembro
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = membroSelecionado.perfil.fotoURL)
                    , contentDescription = membroSelecionado.nome,
                    modifier = Modifier.size(24.dp))
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = membroSelecionado.nome,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = {
                expandedMenu.value = false
            },
            modifier = modifier.fillMaxWidth(.95f)
        ) {
            membros.forEach { membro ->
                DropdownItem(
                    membro = membro,
                    onChoice = {
                        onChoice(it)
                        expandedMenu.value = false },
                    modifier = modifier
                )
            }
        }
    }
}


@Composable
private fun DropdownItem(
    membro : MovimentacaoHolder,
    onChoice: (MovimentacaoHolder) -> Unit = {},
    modifier: Modifier
) {
    Divider(
        color = MaterialTheme.colorScheme.onBackground,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
    DropdownMenuItem(
        text = { Text(
            text = membro.nome,
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            onChoice(membro)
        },
        modifier = modifier.background(membro.perfil.corPerfil),
        leadingIcon = {
            Image(
            painter = painterResource(id = membro.perfil.fotoURL)
            , contentDescription = "Foto de "+membro.nome,
            modifier = Modifier.size(24.dp))
        }
    )
}



@Preview
@Composable
private fun DropdownMembroPrev() {
    FinanceTheme {
        var flag by remember {
            mutableStateOf(true)
        }
        if(flag){
            testeCadastro()
            flag = false
        }

        val membros = getMembros()
        var membroSelecionado = remember { mutableStateOf<MovimentacaoHolder>(Login.getCasaLogada()) }
        DropdownMembro(
            expandedInicial = false,
            membros = membros,
            onChoice = { membroSelecionado.value = it },
            membroSelecionado = membroSelecionado.value
        )
    }
}
