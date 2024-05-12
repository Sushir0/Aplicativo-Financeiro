package com.example.finance.lvl1

sealed class SelecaoUsuario {
    data class tipoSelecionado(val tipo: Tipo):SelecaoUsuario()
    data class categoriaSelecionada(val categoria: Categoria, val tipo: Tipo):SelecaoUsuario()
}