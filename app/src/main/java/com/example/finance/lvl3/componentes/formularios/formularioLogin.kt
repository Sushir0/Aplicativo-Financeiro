package com.example.finance.lvl3.componentes.formularios

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance.lvl3.activitys.abrirDashboard
import com.example.finance.lvl3.utils.avisoDeErros
import com.example.finance.lvl3.utils.avisoLongo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(onCadastroClick: () -> Unit, context: Context) {

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
                .padding(8.dp),
        )


        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(
                text = "Senha",
                style = MaterialTheme.typography.labelMedium
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
                Text(
                    text = "Esqueceu a senha?",
                    style = MaterialTheme.typography.bodyMedium)
            }
            TextButton(onClick = onCadastroClick) {
                Text(
                    text = "Não tem uma conta? Cadastre-se",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }



        Button(
            onClick = { logar(email, senha, context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Entrar",
                style = MaterialTheme.typography.titleMedium)

        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    LoginForm({ }, context = LocalContext.current)
}

@ExperimentalMaterial3Api
fun logar(email: String, senha: String, context: Context){
    /*
    val erros = LoginController().logar(email, senha)
    if(erros.isEmpty()){
        abrirDashboard(context)
    }else{
        avisoDeErros(context, erros)
    }

     */
}