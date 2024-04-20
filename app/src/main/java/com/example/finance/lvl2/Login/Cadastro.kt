package com.example.finance.lvl2.Login

import com.example.finance.lvl1.Casa
import com.example.finance.lvl1.Login
import com.example.finance.lvl2.validações.validarFormularioCadastroLogin
import com.example.finance.lvl2.validações.verificarNome

fun cadastrarComCasaExistente(nome : String, email : String, senha : String, idCasa: Int): List<String>{
    val erros = validarFormularioCadastroLogin(nome, email, senha)

    if (erros.isEmpty()) {
        Login.Cadastro(nome, email, senha, idCasa)
    }

    return erros;
}

fun cadastrarComNovaCasa(nome: String, email: String, senha: String, nomeCasa: String): List<String>{
    val erros = ArrayList<String>()
        erros.addAll(validarFormularioCadastroLogin(nome, email, senha))
    if(!verificarNome(nomeCasa)){erros.add("Nome da casa inválido.")}

    if (erros.isEmpty()) {
        val casa = Casa(nomeCasa)
        Login.Cadastro(nome, email, senha, casa.id)
    }
    return erros
}

fun testeCadastro() : Int{
    val nomeTeste = "nome teste"
    val emailTeste = "email teste"
    val senhaTeste = "senha teste"
    val nomeCasaTeste = "nome casa teste"
    val erros = cadastrarComNovaCasa("pessoa 1", "email 1", "senha 1", nomeCasaTeste)
    cadastrarComCasaExistente("pessoa 2", "email 2", "senha 2", Login.getCasaLogada().id)
    cadastrarComCasaExistente("pessoa 3", "email 3", "senha 3", Login.getCasaLogada().id)
    cadastrarComCasaExistente(nomeTeste, emailTeste, senhaTeste, Login.getCasaLogada().id)
    return Login.getCasaLogada().id
}