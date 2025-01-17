package com.example.finance.lvl3.widgets.Escolher

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.a_Domain.VariaveisDeAmbiente.BetaTeste
import com.example.finance.a_Domain.model.Categorizacao.Categoria
import com.example.finance.a_Domain.model.Categorizacao.CategoriaMock
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoriaMock
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MetaDados.Tipo
import com.example.finance.lvl3.componentes.listas.Categoria.ListaDuplaCategoriaVertical
import com.example.finance.lvl3.componentes.listas.MacroCategoria.ListaDeMacroCategoriasHorizontal
import com.example.finance.lvl3.utils.avisoLongo
import com.example.finance.lvl3.widgets.DivisorHorizontalPersonalizado

@Composable
fun EscolhaSelecaoUsuarioNew(
    isCasa: Boolean,
    macroCategorias: List<MacroCategoria>?,
    categorias: List<Categoria>?,
    categoriaInicial: Categoria? = null,
    needConfirmation: Boolean = BetaTeste.needConfirmation,
    onlyCategoria: Boolean = false,
    onDismiss: () -> Unit = {  },
    onConfirm: (selecaoUsuario: SelecaoUsuario) -> Unit = {  }
) {
    var tipoEscolhido by remember { mutableStateOf(Tipo.TODOS) }
    var macroCategoriaEscolhida by remember { mutableStateOf<MacroCategoria?>(null) }
    var categoriaEscolhida by remember { mutableStateOf<Categoria?>(categoriaInicial) }
    val context = LocalContext.current

    val macroCategoriasFiltradas by remember(macroCategorias, tipoEscolhido, isCasa) {
        derivedStateOf {
            macroCategorias?.filter {
                it.verificarTipo(tipoEscolhido) && it.verificarMembro(isCasa)
            }?.toMutableList()
        }
    }

    val categoriasFiltradas by remember(categorias, macroCategoriaEscolhida, tipoEscolhido, isCasa) {
        derivedStateOf {
            categorias?.filter {
                if (macroCategoriaEscolhida != null) {
                    it.macroCategoriaId == macroCategoriaEscolhida!!.id
                } else {
                    it.macroCategoria.verificarTipo(tipoEscolhido) && it.macroCategoria.verificarMembro(
                        isCasa
                    )
                }
            }
        }
    }

    fun selecionarTipo(tipo: Tipo){
        if(!onlyCategoria){
            if(!needConfirmation){
                onConfirm(SelecaoUsuario.tipoSelecionado(tipo))
                onDismiss()
            }
        }
        if(tipo == tipoEscolhido){
            tipoEscolhido = Tipo.TODOS
        }else{
            tipoEscolhido = tipo
        }
        if(macroCategoriasFiltradas?.contains(macroCategoriaEscolhida) != true){
            macroCategoriaEscolhida = null
        }


    }

    fun selecionarMacroCategoria(macroCategoria: MacroCategoria?){
        if(!onlyCategoria){
            if(!needConfirmation){
                onConfirm(SelecaoUsuario.macroCategoriaSelecionada(macroCategoria!!))
                onDismiss()
            }
        }
        if(macroCategoriaEscolhida == macroCategoria){
            macroCategoriaEscolhida = null
        }else{
            macroCategoriaEscolhida = macroCategoria
        }
        if(categoriasFiltradas?.contains(categoriaEscolhida) != true){
            categoriaEscolhida = null
        }

    }

    fun selecionarCategoria(categoria: Categoria){
        if(!needConfirmation){
            onConfirm(SelecaoUsuario.categoriaSelecionada(categoria))
            onDismiss()
        }else{
            if(categoriaEscolhida == categoria){
                categoriaEscolhida = null
            }else{
                categoriaEscolhida = categoria
            }
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            text = "Tipos",
            style = MaterialTheme.typography.labelMedium
        )
        EscolherTipo(
            tipoEscolhido = tipoEscolhido,
            onEscolha = {
                selecionarTipo(it)
            }
        )

        Divider(
            modifier = Modifier.padding(
                start = 32.dp,
                end = 32.dp,
                top = 8.dp,
            ),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Grupos de categorias",
            style = MaterialTheme.typography.labelMedium
        )
        ListaDeMacroCategoriasHorizontal(
            macroCategoriaEscolhida = macroCategoriaEscolhida,
            macroCategorias = macroCategoriasFiltradas,
            onMacroCategoriaClick = {
                selecionarMacroCategoria(it)
            }
        )
        ElevatedCard() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Categorias",
                    style = MaterialTheme.typography.labelMedium
                )
                ListaDuplaCategoriaVertical(
                    categoriaEscolhida = categoriaEscolhida,
                    categorias = categoriasFiltradas,
                    onCategoriaClick = {
                        selecionarCategoria(it)
                    }
                )
            }
        }
        if(needConfirmation){
            Button(
                modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                onClick = {
                    if(onlyCategoria){
                        if(categoriaEscolhida != null){
                            onConfirm(SelecaoUsuario.categoriaSelecionada(categoriaEscolhida!!))
                            onDismiss()
                        }else{
                            avisoLongo(context = context, "Nenhuma categoria selecionada")
                        }
                    }else{
                        if(categoriaEscolhida != null){
                            onConfirm(SelecaoUsuario.categoriaSelecionada(categoriaEscolhida!!))
                            onDismiss()
                        }else if(macroCategoriaEscolhida != null){
                            onConfirm(SelecaoUsuario.macroCategoriaSelecionada(macroCategoriaEscolhida!!))
                            onDismiss()
                        }else{
                            onConfirm(SelecaoUsuario.tipoSelecionado(tipoEscolhido))
                            onDismiss()
                        }
                    }

                }
            ) {
                Text(text = "Confirmar")
            }
        }
}
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EscolhaDeCategoria2(
    isCasa: Boolean,
    macroCategorias: List<MacroCategoria>?,
    categorias: List<Categoria>?,
    categoriaInicial: Categoria? = null,
    needConfirmation: Boolean = BetaTeste.needConfirmation,
    onDismiss: () -> Unit = {  },
    onConfirm: (categoria: Categoria) -> Unit = {  }){

    var tipoEscolhido by remember { mutableStateOf(Tipo.TODOS) }
    var macroCategoriaEscolhida by remember { mutableStateOf<MacroCategoria?>(null) }
    var categoriaEscolhida by remember { mutableStateOf<Categoria?>(categoriaInicial) }
    val context = LocalContext.current

    var dialogTipo_Grupo by remember{ mutableStateOf(false) }
    var dialogOrderar by remember{ mutableStateOf(false) }


    val macroCategoriasFiltradas by remember(macroCategorias, tipoEscolhido, isCasa) {
        derivedStateOf {
            macroCategorias?.filter {
                it.verificarTipo(tipoEscolhido) && it.verificarMembro(isCasa)
            }?.toMutableList()
        }
    }

    val categoriasFiltradas by remember(categorias, macroCategoriaEscolhida, tipoEscolhido, isCasa) {
        derivedStateOf {
            categorias?.filter {
                if (macroCategoriaEscolhida != null) {
                    it.macroCategoriaId == macroCategoriaEscolhida!!.id
                } else {
                    it.macroCategoria.verificarTipo(tipoEscolhido) && it.macroCategoria.verificarMembro(
                        isCasa
                    )
                }
            }
        }
    }

    fun selecionarTipo(tipo: Tipo){
        if(tipo == tipoEscolhido){
            tipoEscolhido = Tipo.TODOS
        }else{
            tipoEscolhido = tipo
        }
        if(macroCategoriasFiltradas?.contains(macroCategoriaEscolhida) != true){
            macroCategoriaEscolhida = null
        }
        Log.d("EscolhaDeCategoria", "selecionarTipo: $tipoEscolhido")
        Log.d("EscolhaDeCategoria", "macroCategorias Filtradas: ")
        macroCategoriasFiltradas?.forEach {
            Log.d("EscolhaDeCategoria", it.nome)
        }

    }

    fun selecionarMacroCategoria(macroCategoria: MacroCategoria?){
        if(macroCategoriaEscolhida == macroCategoria){
            macroCategoriaEscolhida = null
        }else{
            macroCategoriaEscolhida = macroCategoria
        }
        if(categoriasFiltradas?.contains(categoriaEscolhida) != true){
            categoriaEscolhida = null
        }
    }

    fun selecionarCategoria(categoria: Categoria){
        if(categoriaEscolhida == categoria){
            categoriaEscolhida = null
        }else{
            categoriaEscolhida = categoria
        }
    }

    Column {
        FlowRow (
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ){
            FilterGrupoCategoria_Tipo {
                dialogTipo_Grupo = true
            }
            ElevatedCard(
                modifier = Modifier.padding(6.dp),
                onClick = {

                }
            ) {
                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Ordenar por",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        DivisorHorizontalPersonalizado()
        Text(
            text = "Categorias",
            style = MaterialTheme.typography.labelMedium
        )
        ListaDuplaCategoriaVertical(
            categorias = categoriasFiltradas,
            categoriaEscolhida = categoriaEscolhida,
            onCategoriaClick = {
                if(needConfirmation){
                    selecionarCategoria(it)
                }else{
                    onConfirm(it)
                    onDismiss()
                }

            }
        )
        if(needConfirmation){
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                onClick = {
                    if(categoriaEscolhida != null){
                        onConfirm(categoriaEscolhida!!)
                        onDismiss()
                    }else{
                        avisoLongo(context = context, "Nenhuma categoria selecionada")
                    }
                }
            ) {
                Text(text = "Confirmar")
            }
        }
    }
    if(dialogTipo_Grupo) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { dialogTipo_Grupo = false },
            title = { Text(text = "Escolha um grupo ou tipo") },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Tipos",
                        style = MaterialTheme.typography.labelMedium
                    )
                    EscolherTipo(
                        tipoEscolhido = tipoEscolhido,
                        onEscolha = {
                            selecionarTipo(it)
                        }
                    )

                    Divider(
                        modifier = Modifier.padding(
                            start = 32.dp,
                            end = 32.dp,
                            top = 8.dp,
                        ),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Macro categorias",
                        style = MaterialTheme.typography.labelMedium
                    )
                    ListaDeMacroCategoriasHorizontal(
                        macroCategoriaEscolhida = macroCategoriaEscolhida,
                        macroCategorias = macroCategoriasFiltradas,
                        onMacroCategoriaClick = {
                            selecionarMacroCategoria(it)
                        }
                    )
                    Button(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        onClick = {

                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                }

                },
            confirmButton = {}
        )
    }
}

@Preview
@Composable
private fun EscolhaDeCategoriaPrev() {
    val macroCategorias = listOf(
        MacroCategoriaMock(
            nome = "gasto",
            id = "gasto",
            isGasto = true,
        ),
        MacroCategoriaMock(
            nome = "receita",
            id = "receita",
            isGasto = false,
        ),
        MacroCategoriaMock(
            nome = "gastos fixos",
            id = "gastos fixos",
            isGasto = true,
        ),
        MacroCategoriaMock(
            nome = "Saúde",
            id = "Saúde",
            isGasto = true,
            afetaCasa = true,
        ),
        MacroCategoriaMock(
            nome = "Outros gastos",
            id = "Outros",
            isGasto = true,
            afetaCasa = true,
            afetaPessoa = true,
        ),
        MacroCategoriaMock(
            nome = "Outros Recebimentos",
            id = "Outros Recebimentos",
            isGasto = false,
            afetaCasa = true,
            afetaPessoa = true,
        )
    )

    val categorias = listOf(
        CategoriaMock(
            nome = "Alimentação",
            macroCategoria = macroCategorias[0],
        ),
        CategoriaMock(
            nome = "Transporte",
            macroCategoria = macroCategorias[0],
        ),
        CategoriaMock(
            nome = "receita 1",
            macroCategoria = macroCategorias[1],
        ),
        CategoriaMock(
            nome = "receita 2",
            macroCategoria = macroCategorias[1],
        ),
        CategoriaMock(
            nome = "Conta de luz",
            macroCategoria = macroCategorias[2],
        ),
        CategoriaMock(
            nome = "Conta de água",
            macroCategoria = macroCategorias[2],
        ),
        CategoriaMock(
            nome = "Medicamentos",
            macroCategoria = macroCategorias[3],
        ),
        CategoriaMock(
            nome = "Plano de saúde",
            macroCategoria = macroCategorias[3],
        ),
        CategoriaMock(
            nome = "Gastos Sem classificação",
            macroCategoria = macroCategorias[4],
        ),
        CategoriaMock(
            nome = "Recebimentos sem classificação",
            macroCategoria = macroCategorias[5],
        ),




    )
    val context = LocalContext.current
    EscolhaSelecaoUsuarioNew(
        isCasa = true,
        macroCategorias = macroCategorias,
        categorias = categorias,
        needConfirmation = false,
        onlyCategoria = true,
        onDismiss = {},
        onConfirm = {
            avisoLongo(context, it.toString())
        }
    )


}


@Composable
private fun FilterGrupoCategoria_Tipo(onClick: () -> Unit = {}) {
    var alturaText by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    ElevatedCard (
        onClick = {
            onClick()
        },
        modifier = Modifier.padding(6.dp)
    ){
        Row(
            modifier = Modifier
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.onSizeChanged {
                    with(localDensity) {
                        alturaText = it.height.toDp() // Converte a altura de px para dp
                    }
                },
                text = "Tipo",
                style = MaterialTheme.typography.labelSmall

            )
            VerticalDivider(
                modifier = Modifier
                    .height(alturaText + 8.dp)
                    .padding(horizontal = 4.dp)
            )
            Text(
                text = "Grupo de categorias",
                style = MaterialTheme.typography.labelSmall

            )
        }
    }

}