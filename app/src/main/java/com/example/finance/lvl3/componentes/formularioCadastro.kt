package com.example.finance.lvl3.componentes

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl2.Login.cadastrarComCasaExistente
import com.example.finance.lvl2.Login.cadastrarComNovaCasa
import com.example.finance.lvl2.Login.testeCadastro
import com.example.finance.lvl3.abrirDashboard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cadastroForm(onCadastroClick: () -> Unit, context: Context) {
    var textColor = MaterialTheme.colorScheme.onBackground
    val paddings = 6.dp

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var idCasa by remember { mutableStateOf("") }
    var nomeCasa by remember { mutableStateOf("") }
    var criarNovaCasa by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = {nome = it},
            label = {
                Text(
                    text = "Nome",
                    style = MaterialTheme.typography.labelMedium) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddings))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.labelMedium) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddings),
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = {
                Text(
                    text = "Senha",
                    style = MaterialTheme.typography.labelMedium
            ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password

            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddings),
        )
        if (criarNovaCasa){
            OutlinedTextField(
                value = nomeCasa,
                onValueChange = { nomeCasa = it },
                label = { Text(
                    text = "Nome da casa",
                    style = MaterialTheme.typography.labelMedium)},
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        cadastrarNovaCasa(nome, email, senha, nomeCasa, context)
                    }
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddings),)
        }else{
            OutlinedTextField(
                value = idCasa,
                onValueChange = { idCasa = it },
                label = { Text(
                    text = "ID da casa",
                    style = MaterialTheme.typography.labelMedium)},
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        cadastrarEmCasaExistente(nome, email, senha, idCasa, context)
                    }
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddings),)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text("Criar nova casa",
                style = MaterialTheme.typography.bodyLarge)
            Checkbox(
                checked = criarNovaCasa,
                onCheckedChange = {
                    criarNovaCasa = it },
            )
        }


        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = onCadastroClick) {
                Text(text = "Já tem uma conta? Entre agora",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }



        Button(
            onClick = {
                if(criarNovaCasa){
                    cadastrarNovaCasa(nome, email, senha, nomeCasa, context)
                }else{
                    try {
                        cadastrarEmCasaExistente(nome, email, senha, idCasa, context)
                    }catch(e: Error){
                        avisoLongo(context, e.message.toString())
                    }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Cadastrar",
                style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun cadastroFormPreview() {
    cadastroForm({ }, context = LocalContext.current)
}


fun cadastrarNovaCasa(nome: String, email: String, senha: String, nomeCasa: String, context: Context){
    val erros = cadastrarComNovaCasa(nome, email, senha, nomeCasa)
    if(erros.isEmpty()){
        Login.getCasaLogada().showCasa()
        abrirDashboard(context)
    }else{
        avisoDeErros(context, erros)
    }
}
fun cadastrarEmCasaExistente(nome: String, email: String, senha: String, idCasa: String, context: Context){
    val idCasaTeste = testeCadastro()
    val erros = cadastrarComCasaExistente(nome, email, senha, idCasaTeste)
    if(erros.isEmpty()){
        Login.getCasaLogada().showCasa()
        abrirDashboard(context)
    }else{
        avisoDeErros(context, erros)
    }
}

fun testeCasaExistente(){
    var flagFirstCasa = mutableStateOf(true)
    if(flagFirstCasa.value){
        cadastrarComNovaCasa("nome teste", "email teste", "senha teste", "nome Casa")
        flagFirstCasa.value = false
    }
}