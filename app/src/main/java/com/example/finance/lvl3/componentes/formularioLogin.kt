package com.example.finance.lvl3.componentes

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl1.Login
import com.example.finance.lvl2.Login.cadastrarComNovaCasa
import com.example.finance.lvl2.Login.logar
import com.example.finance.lvl3.abrirDashboard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginForm(onCadastroClick: () -> Unit, context: Context) {
    testeCasaExistente()

    var textColor = MaterialTheme.colorScheme.onBackground
    var email by remember { mutableStateOf("email teste") }
    var senha by remember { mutableStateOf("senha teste") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = textColor) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )


        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(
                "Senha",
                color = textColor
            ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password

            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    logar(email, senha, context)
                }
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            TextButton(onClick = {
                avisoLongo(context, "trocar a senha está indisponível no momento.")
            }) {
                Text(text = "Esqueceu a senha?")
            }
            TextButton(onClick = onCadastroClick) {
                Text(text = "Não tem uma conta? Cadastre-se")
            }
        }



        Button(
            onClick = {logar(email, senha, context)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text("Entrar")

        }
    }
}

@Preview
@Composable
fun loginFormPreview() {
    loginForm({ }, context = LocalContext.current)
}

fun logar(email: String, senha: String, context: Context){
    val erros = logar(email, senha)
    if(erros.isEmpty()){
        Login.getCasaLogada().showCasa()
        abrirDashboard(context)
    }else{
        avisoDeErros(context, erros)
    }
}