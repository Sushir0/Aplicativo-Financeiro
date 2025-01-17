package com.example.finance.lvl2.validações

/*
class ValidarLogin(){
    val tamanhoMinimoNome = 2
    val tamanhoMaximoNome = 15

    val tamanhoMinimoEmail = 5
    val tamanhoMaximoEmail = 35

    val tamanhoMinimoSenha = 6
    val tamanhoMaximoSenha = 35


    fun verificarNome(nome: String): Boolean {
        if(nome.isNullOrBlank()){
            return false
        }
        if(validarTamanhoString(nome, tamanhoMinimoNome, tamanhoMaximoNome)){
            return false
        }
        return true
    }

    fun verificarEmail(email: String): Boolean{
        if(email.isNullOrBlank()){ return false }
        if(validarTamanhoString(email, tamanhoMinimoEmail, tamanhoMaximoEmail)){ return false }
        return true
    }

    fun verificarSenha(senha: String): Boolean{
        if(senha.isNullOrBlank()){ return false }
        if(validarTamanhoString(senha, tamanhoMinimoSenha, tamanhoMaximoSenha)){ return false }
        return true
    }

    fun validarFormularioCadastroLogin(nome: String, email: String, senha: String): List<String>{
        val erros = mutableListOf<String>()
        if (!verificarNome(nome)) {
            erros.add("Nome inválido.")
        }
        if (!verificarEmail(email)) {
            erros.add("E-mail inválido.")
        }
        if (!verificarSenha(senha)) {
            erros.add("Senha inválida.")
        }
        return erros;
    }

    fun validarFormularioLogin(email: String, senha: String): List<String>{
        val erros = mutableListOf<String>()
        if (!verificarEmail(email)) {
            erros.add("E-mail inválido.")
        }
        if (!verificarSenha(senha)) {
            erros.add("Senha inválida.")
        }
        if(!verificarCredenciais(email, senha)){
            erros.add("email ou senha incorretos.")
        }

        return erros;
    }
}


 */