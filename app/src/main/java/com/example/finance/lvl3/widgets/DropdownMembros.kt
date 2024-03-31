package com.example.finance.lvl3.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl1.Pessoa
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.ui.theme.FinanceTheme


@Composable
fun DropdownMembro(
    expandedInicial : Boolean = false,
    membros : List<Pessoa>,
    modifier : Modifier = Modifier,
    membroSelecionado : MutableState<Pessoa?>,
    isCasaSelected : MutableState<Boolean> = remember{ mutableStateOf(true) }
) {

    var expandedMenu = remember { mutableStateOf<Boolean>(expandedInicial) }
    var textoBotao = if(isCasaSelected.value){
        Login.getCasaLogada().nome
    }else{
        membroSelecionado.value!!.nome
    }
    var imageDoBotao = if (isCasaSelected.value){
        Login.getCasaLogada().perfil.fotoURL
    }else{
        membroSelecionado.value!!.perfil.fotoURL
    }

    Column(modifier = modifier) {
        OutlinedButton(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            onClick = { expandedMenu.value = true },
        ) {
            Image(
                painter = painterResource(id = imageDoBotao)
                , contentDescription = textoBotao,
                modifier = Modifier.size(24.dp))
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = textoBotao,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = {
                expandedMenu.value = false
            }
        ) {
            DropdownItemCasa(
                casa = Login.getCasaLogada(),
                expanded = expandedMenu,
                membroSelecionado = membroSelecionado,
                modifier = modifier,
                isCasaSelected = isCasaSelected
            )
            membros.forEach { membro ->
                DropdownItemPessoa(
                    membro = membro,
                    expanded = expandedMenu,
                    membroSelecionado = membroSelecionado,
                    modifier = modifier,
                    isCasaSelected = isCasaSelected
                )
            }
        }
    }
}


@Composable
private fun DropdownItemPessoa(
    membro : Pessoa,
    expanded: MutableState<Boolean>,
    membroSelecionado: MutableState<Pessoa?>,
    modifier: Modifier,
    isCasaSelected: MutableState<Boolean>
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
            membroSelecionado.value = membro
            isCasaSelected.value = false
            expanded.value = false
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

@Composable
private fun DropdownItemCasa(
    casa : Casa,
    expanded: MutableState<Boolean>,
    membroSelecionado: MutableState<Pessoa?>,
    modifier: Modifier,
    isCasaSelected: MutableState<Boolean>
) {
    DropdownMenuItem(
        text = { Text(
            text = casa.nome,
            style = MaterialTheme.typography.titleSmall,
        ) },
        onClick = {
            membroSelecionado.value = null
            isCasaSelected.value = true
            expanded.value = false
        },
        modifier = modifier.background(casa.perfil.corPerfil),
        leadingIcon = {
            Image(
                painter = painterResource(id = casa.perfil.fotoURL)
                , contentDescription = "Foto de "+casa.nome,
                modifier = Modifier.size(24.dp))
        }
    )
}

@Preview
@Composable
private fun DropdownMembroPrev() {
    FinanceTheme {
        testeCadastro()
        var membroSelecionado = remember { mutableStateOf<Pessoa?>(null) }
        DropdownMembro(
            expandedInicial = false,
            membros = Login.getCasaLogada().moradores,
            membroSelecionado = membroSelecionado,
            modifier = Modifier.width(150.dp)

        )
    }
}
