package com.example.finance.lvl2.validações

object ValidarCategoria{

    fun validarCategoria(nome: String): ArrayList<String> {
        var erros = ArrayList<String>()
        if(nome.isEmpty()){
            erros.add("Nome não pode ser vazio")
            return erros
        }

        if(validarTamanhoString(nome, 4, 20)){
            erros.add("Nome deve ter entre 4 e 20 caracteres")
        }
        return erros
    }
}